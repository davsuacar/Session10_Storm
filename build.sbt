name := "storm-ejercicio"

version := "1.0"

scalaVersion := "2.11.8"


libraryDependencies += "org.apache.storm" % "storm-core" % "0.10.0-beta1"

resolvers ++= Seq(
  "twitter4j" at "http://twitter4j.org/maven2",
  "clojars.org" at "http://clojars.org/repo"
)