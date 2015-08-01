enablePlugins(ScalaJSPlugin)

name := "LifeGame"

scalaVersion := "2.11.7"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "0.8.0"
