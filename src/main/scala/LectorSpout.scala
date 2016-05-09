import backtype.storm.spout.SpoutOutputCollector
import backtype.storm.topology.base.BaseRichSpout
import backtype.storm.task.{ OutputCollector, TopologyContext }
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.tuple.{ Fields, Tuple, Values }
import java.util.{ Map => JMap }

import backtype.storm.utils.Utils

import scala.util.Random

/**
  * Created by davidsuarez on 8/05/16.
  */
class LectorSpout  extends BaseRichSpout {

  var collector: OutputCollector = _

  override def declareOutputFields(declarer: OutputFieldsDeclarer) {
    declarer.declare(new Fields("LectorSpout"))
  }

  override def nextTuple(): Unit = {
    Utils.sleep(100)
    val words = List("palabra1", "palabra2", "palabra3")
    val rand = Random.nextInt(words.length)
    val word = words(rand)
    collector.emit(new Values(word))
  }

  override def open(conf: JMap[_, _], context: TopologyContext, collector: SpoutOutputCollector): Unit = {

  }
}