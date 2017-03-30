lazy val publishDir = settingKey[String]("Publishing directory")
lazy val publishIpfs = taskKey[Unit]("Publish to IPFS")

def commonSettings(pname: String) = Seq(
  version := "0.1-SNAPSHOT",
  organization := "io.ipfs",
  scalaVersion := "2.12.1",
  name := pname,
  emitSourceMaps in fullOptJS := false,

  libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.4.5" % "test"
  ),
  testFrameworks += new TestFramework("utest.runner.Framework")
)

lazy val multibase = (project in file("multibase")).settings(commonSettings("multibase"): _*).settings(
  resolvers += "multihash" at "https://ipfs.io/ipfs/QmZBAJGLhy36WNgatg6AbSvrRJtod76bvyoyY8SUH5cMPU",
  libraryDependencies ++= Seq(
    "eu.devtty" %%% "multihash" % "0.4.5"
  ),
  npmDependencies in Compile += "multibase" -> "0.3.4"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)

lazy val cid = (project in file("cid")).settings(commonSettings("cid"): _*).settings(
  resolvers += "multihash" at "https://ipfs.io/ipfs/QmZBAJGLhy36WNgatg6AbSvrRJtod76bvyoyY8SUH5cMPU",
  libraryDependencies ++= Seq(
    "eu.devtty" %%% "multihash" % "0.4.5"
  ),
  npmDependencies in Compile += "cids" -> "0.4.2"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)

lazy val coreApi = (project in file("core-api")).settings(commonSettings("core-api"): _*).settings(
  resolvers += "multihash" at "https://ipfs.io/ipfs/QmZBAJGLhy36WNgatg6AbSvrRJtod76bvyoyY8SUH5cMPU",
  libraryDependencies ++= Seq(
    "eu.devtty" %%% "multihash" % "0.4.5"
  ),
  npmDependencies in Compile += "ipfs-block" -> "0.6.0"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(cid)

lazy val jsNode = (project in file("js-ipfs-node")).settings(commonSettings("js-ipfs-node"): _*).settings(
  npmDependencies in Compile += "ipfs" -> "0.23.1"
).enablePlugins(ScalaJSPlugin, ScalaJSBundlerPlugin, IpfsPublish)
  .dependsOn(coreApi)

lazy val httpNode = (project in file("http-node")).settings(commonSettings("http-node"): _*).settings(

).enablePlugins(ScalaJSPlugin, IpfsPublish)
  .dependsOn(coreApi)

onLoad in Global := (Command.process("project coreApi", _: State)) compose (onLoad in Global).value