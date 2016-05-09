import backtype.storm.topology.{BasicOutputCollector, OutputFieldsDeclarer}
import backtype.storm.topology.base.BaseBasicBolt
import backtype.storm.tuple.Tuple

/**
  * Created by davidsuarez on 8/05/16.
  */
class SplitBolt extends BaseBasicBolt {
  override def execute(input: Tuple, collector: BasicOutputCollector): Unit = {
    print("Tuple" + input)
  }

  override def declareOutputFields(declarer: OutputFieldsDeclarer): Unit = {

  }
}
