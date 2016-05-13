import backtype.storm.tuple.Fields
import backtype.storm.{ Config, LocalCluster, StormSubmitter }
import backtype.storm.topology.TopologyBuilder
import backtype.storm.utils.Utils

/**
  * Created by davidsuarez on 8/05/16.
  */
object CounterTopology {
  def main(args: Array[String]) {

    val builder: TopologyBuilder = new TopologyBuilder()

    builder.setSpout("LectorSpout", new LectorSpout(), 1)
    builder.setBolt("SplitBolt", new SplitBolt(), 1).shuffleGrouping("LectorSpout")
    builder.setBolt("ContadorParcialBolt", new ContadorParcialBolt(), 5).fieldsGrouping("SplitBolt", new Fields("SplitBolt"))
    builder.setBolt("ContadorTotalBolt", new ContadorTotalesBolt(), 5).shuffleGrouping("ContadorParcialBolt")

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
