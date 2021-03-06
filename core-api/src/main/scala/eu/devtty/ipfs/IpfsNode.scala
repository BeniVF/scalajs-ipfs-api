package eu.devtty.ipfs

import scala.concurrent.Future

trait IpfsNode {
  def id(): Future[PeerID]
  def version: Future[Version]

  def block: BlockApi
  def config: ConfigApi
  def dag: DagApi
  def files: FilesApi
  def pubsub: PubsubApi
  def swarm: SwarmApi
}
