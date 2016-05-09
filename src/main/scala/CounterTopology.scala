import backtype.storm.{ Config, LocalCluster, StormSubmitter }
import backtype.storm.testing.TestWordSpout
import backtype.storm.topology.TopologyBuilder
import backtype.storm.utils.Utils

/**
  * Created by davidsuarez on 8/05/16.
  */
object CounterTopology {
  def main(args: Array[String]) {

    val builder: TopologyBuilder = new TopologyBuilder()

    builder.setSpout("LectorSpout", new LectorSpout(), 10)
    builder.setBolt("SplitBolt", new SplitBolt(), 3).shuffleGrouping("LectorSpout")

    val config = new Config()
    config.setDebug(true)

    if (args != null && args.length > 0) {
      config.setNumWorkers(3)
      StormSubmitter.submitTopology(args(0), config, builder.createTopology())
    } else {
      val cluster: LocalCluster = new LocalCluster()
      cluster.submitTopology("CounterTopology", config, builder.createTopology())
      Utils.sleep(5000)
      cluster.killTopology("CounterTopology")
      cluster.shutdown()
    }
  }
}
