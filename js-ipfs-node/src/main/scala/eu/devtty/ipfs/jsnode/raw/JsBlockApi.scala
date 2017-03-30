package eu.devtty.ipfs.jsnode.raw

import eu.devtty.cid.CID
import eu.devtty.ipfs.{Block, BlockStat}
import io.scalajs.nodejs.buffer.Buffer

import scala.scalajs.js

@js.native
trait JsBlockApi extends js.Object {
  def get(cid: CID, callback: js.Function2[String, Block, _]): Unit = js.native
  def get(cid: String, callback: js.Function2[String, Block, _]): Unit = js.native
  def get(cid: Buffer, callback: js.Function2[String, Block, _]): Unit = js.native

  def put(block: Block, cid: CID, callback: js.Function2[String, Block, _]): Unit = js.native
  def put(block: Buffer, cid: CID, callback: js.Function2[String, Block, _]): Unit = js.native
  def put(block: Block, cid: String, callback: js.Function2[String, Block, _]): Unit = js.native
  def put(block: Buffer, cid: String, callback: js.Function2[String, Block, _]): Unit = js.native
  def put(block: Block, cid: Buffer, callback: js.Function2[String, Block, _]): Unit = js.native
  def put(block: Buffer, cid: Buffer, callback: js.Function2[String, Block, _]): Unit = js.native

  def stat(cid: CID, callback: js.Function2[String, BlockStat, _]): Unit = js.native
  def stat(cid: String, callback: js.Function2[String, BlockStat, _]): Unit = js.native
  def stat(cid: Buffer, callback: js.Function2[String, BlockStat, _]): Unit = js.native
}
