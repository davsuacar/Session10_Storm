import java.util

import backtype.storm.task.{TopologyContext, OutputCollector}
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseRichBolt
import backtype.storm.tuple.Tuple

/**
  * Created by davidsuarez on 8/05/16.
  */
class ContadorTotalesBolt extends BaseRichBolt {

  var collector : OutputCollector = _
  var totalWords : Int = 0
  var uniqueWords : Int = 0
  var dictionaryTotal : Map[String, Int] = Map()

  override def execute(input: Tuple): Unit = {
    val word = input.getString(0)

    if (dictionaryTotal.contains(word)){
      val counter = dictionaryTotal(word) + 1
      dictionaryTotal = dictionaryTotal + (word -> counter)
    } else {
      dictionaryTotal += (word -> 1)
    }

    print (dictionaryTotal.keys + "\n")
    print ("LLEGAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAaa\n")

  }

  override def prepare(stormConf: util.Map[_, _], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {

  }
}
