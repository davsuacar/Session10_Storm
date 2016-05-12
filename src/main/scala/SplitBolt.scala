import java.util

import backtype.storm.task.{OutputCollector, TopologyContext}
import backtype.storm.topology.{OutputFieldsDeclarer}
import backtype.storm.topology.base.{BaseRichBolt}
import backtype.storm.tuple.{Values, Fields, Tuple}

/**
  * Created by davidsuarez on 8/05/16.
  */
class SplitBolt extends BaseRichBolt {

  var collector : OutputCollector = _

  override def execute(input: Tuple): Unit = {
    val sentence = input.getString(0).replace(".", " ")
    print("Sentence: " + sentence + "\n")
    collector.emit(new Values(sentence))
  }


  override def prepare(stormConf: util.Map[_, _], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("SplitBolt"))
  }
}
