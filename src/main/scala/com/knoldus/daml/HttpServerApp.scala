package com.knoldus.daml

import akka.Done
import akka.actor.CoordinatedShutdown.Reason
import akka.actor.{ActorSystem, CoordinatedShutdown}
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.knoldus.daml.bootstrap.{DaoInstantiator, RouteInstantiator, ServiceInstantiator}
import com.typesafe.config.ConfigFactory
import slick.jdbc.H2Profile.api._
import scala.concurrent.duration.DurationInt
import scala.concurrent.ExecutionContext.global
import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object HttpServerApp extends App {

  implicit val actorSystem: ActorSystem = ActorSystem("daml-dcredit-actor-system")
  implicit val materializer: ActorMaterializer =
    ActorMaterializer()(actorSystem)
  implicit val logger: LoggingAdapter = Logging(actorSystem, "Daml-decredit-middleware")
  implicit val executionContext: ExecutionContextExecutor = global
 val database =  Database.forConfig("database")
  val daoInstantiator = new DaoInstantiator(database)

  val akkaShutdown = CoordinatedShutdown(actorSystem)
  val conf = ConfigFactory.load()

  try {
    val httpServerConfig = conf.getConfig("http")
    val httpServer = new HttpServer(httpServerConfig)(
      system = actorSystem,
      executionContext = actorSystem.dispatcher,
      materializer = materializer,
      logger = logger
    )
    val services = new ServiceInstantiator(conf,daoInstantiator)
    val routes = new RouteInstantiator(conf,services).routes
    val serverBinding: Future[Http.ServerBinding] = httpServer.start(routes).andThen {
      case Failure(ex) => shutdown(ex)
    }

    akkaShutdown.addTask(CoordinatedShutdown.PhaseServiceUnbind, "Unbinding http server") { () =>
      serverBinding.transformWith {
        case Success(binding) =>
          binding.unbind().andThen {
            case Success(_) => logger.info("Has unbounded http server.")
            case Failure(ex) => logger.error(ex, "Has failed to unbind http server.")
          }
        case Failure(_) => Future.successful(Done)
      }
    }
  } catch {
    case e: Throwable =>
      Await.result(shutdown(e), 30.seconds)
  }

  private def shutdown(e: Throwable): Future[Done] = {
    logger.error(e, "Error starting application:")
    akkaShutdown.run(new Reason {
      override def toString: String = "Error starting application: " ++ e.getMessage
    })
  }
}
