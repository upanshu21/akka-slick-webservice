name := "updated-akka-http"

version := "0.1"

scalaVersion := "2.13.2"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % "10.1.11"

libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.26" // or whatever the latest version is

libraryDependencies += "com.typesafe.akka" %% "akka-http-spray-json" % "10.1.11"

// https://mvnrepository.com/artifact/de.heikoseeberger/akka-http-play-json
libraryDependencies += "de.heikoseeberger" %% "akka-http-play-json" % "1.27.0"

// https://mvnrepository.com/artifact/com.typesafe.play/play-json
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.7.4"

// https://mvnrepository.com/artifact/com.h2database/h2
libraryDependencies += "com.h2database" % "h2" % "1.4.196" % Test

// https://mvnrepository.com/artifact/com.typesafe.slick/slick-codegen
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.3.2"
