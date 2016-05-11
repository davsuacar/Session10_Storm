import java.util

import backtype.storm.task.{TopologyContext, OutputCollector}
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseRichBolt
import backtype.storm.tuple.{Fields, Values, Tuple}

/**
  * Created by davidsuarez on 8/05/16.
  */
class ContadorParcialBolt extends BaseRichBolt {

  var collector : OutputCollector = _

  override def execute(input: Tuple): Unit = {
    val word = input.getString(0)
    print("Contador Parcial Bolt: " + word)
  }

  override def prepare(stormConf: util.Map[_, _], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {

  }

}
