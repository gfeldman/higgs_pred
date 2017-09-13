
name := "Higgs"

scalaVersion := "2.11.8"


libraryDependencies ++= Seq("org.apache.spark" %% "spark-core" % "2.1.0",
  "org.apache.spark" %% "spark-sql" % "2.1.0")

spDependencies += "ai.h2o/sparkling-water-core_2.11:2.1.0"
spDependencies += "ai.h2o/sparkling-water-examples_2.11:2.1.0"