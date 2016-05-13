import java.util

import backtype.storm.task.{TopologyContext, OutputCollector}
import backtype.storm.topology.OutputFieldsDeclarer
import backtype.storm.topology.base.BaseRichBolt
import backtype.storm.tuple.{Fields, Values, Tuple}

import scala.collection.mutable

/**
  * Created by davidsuarez on 8/05/16.
  */
class ContadorParcialBolt extends BaseRichBolt {

  var collector : OutputCollector = _
  var totalWords : Int = 0
  var uniqueWords : Int = 0
  var dictionary : Map[String, Int] = Map()

  override def execute(input: Tuple): Unit = {
    val words = input.getString(0).split(" ")
    for(word <- words) {
      if (dictionary.contains(word)){
        val counter = dictionary(word) + 1
        dictionary = dictionary + (word -> counter)
      } else {
        dictionary += (word -> 1)
      }
    }

    print (dictionary + "\n")
    var counter = 0
    for(key <- dictionary.keys) {
      counter += dictionary(key)
      collector.emit(new Values(key))
    }

    println("Contador Parcial Bolt got the following results:")
    println("Total number of words: " + counter)
    println("Total number of unique words:" + dictionary.keys.size)
  }

  override def prepare(stormConf: util.Map[_, _], context: TopologyContext, collector: OutputCollector): Unit = {
    this.collector = collector
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {
    declarer.declare(new Fields("word"))
  }
}
