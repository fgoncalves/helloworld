name := """helloworld"""
organization := "com.babbel"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava)

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "org.hibernate" % "hibernate-core" % "5.1.0.Final",
  "org.hibernate" % "hibernate-entitymanager" % "5.1.0.Final",
  "mysql" % "mysql-connector-java" % "5.1.35",
  guice,
  javaJpa,

  "org.mockito" % "mockito-core" % "2.13.0" % "test",
  "org.hamcrest" % "hamcrest-junit" % "2.0.0.0" % "test"
)