name := """hello-scala"""

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  // Select Play modules
  //jdbc,      // The JDBC connection pool and the play.api.db API
  //anorm,     // Scala RDBMS Library
  //javaJdbc,  // Java database API
  //javaEbean, // Java Ebean plugin
  //javaJpa,   // Java JPA plugin
  //filters,   // A set of built-in filters
  //javaCore,  // The core Java API
  // WebJars pull in client-side web libraries
  //"org.webjars" %% "webjars-play" % "2.2.0",
  //"org.webjars" % "bootstrap" % "2.3.1"
  // Add your own project dependencies in the form:
  // "group" % "artifact" % "version"
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.1"
)