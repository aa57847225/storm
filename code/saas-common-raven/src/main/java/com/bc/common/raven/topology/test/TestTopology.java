package com.bc.common.raven.topology.test;

import java.util.ArrayList;
import java.util.List;

import com.bc.common.raven.bolt.CfpWebErpBolt;
import com.bc.common.raven.bolt.NginxCounterBolt;
import com.bc.common.raven.spout.MessageScheme;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.spout.SchemeAsMultiScheme;
import backtype.storm.topology.TopologyBuilder;
//import backtype.storm.utils.Utils;
import storm.kafka.BrokerHosts;
import storm.kafka.KafkaSpout;
import storm.kafka.SpoutConfig;
import storm.kafka.ZkHosts;

/**
 * 读取Nginx日志的拓扑
 * @author zhou
 * @since 2017-07-29
 *
 */
public class TestTopology 
{
	
	public static void main(String[] args) throws Exception
	{
	    //kafka
		System.out.println("======start========");
	    String topic = "test_flume_1_7";
	    String zkRoot = "/test_flume_1_7";
	    String spoutId = "test_flume_1_7";
	    BrokerHosts brokerHosts = new ZkHosts("192.168.0.48:2181"); 
	    SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, zkRoot, spoutId);
	    //true:每次都从头读取,即读取这个topic下的全量消息
        //false:storm读取kafka中的新消息
//	    spoutConfig.forceFromStart = true;
//	    spoutConfig.forceFromStart = false;
	    
	    //这个配置local无效
	    spoutConfig.ignoreZkOffsets = false;
	    spoutConfig.zkPort = 2181;
        List<String> servers = new ArrayList<>();
        servers.add("192.168.0.48");
        spoutConfig.zkServers = servers;
        
	    spoutConfig.scheme = new SchemeAsMultiScheme(new MessageScheme());
	    
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout(spoutId, new KafkaSpout(spoutConfig));
		builder.setBolt("test_flume_1_7_bolt", new TestBolt()).shuffleGrouping(spoutId);
		
		//配置
		Config config = new Config();
		config.setDebug(false); 
		
		//这个设置一个spout task上面最多有多少个没有处理(ack/fail)的tuple，防止tuple队列过大, 只对可靠任务起作用
		config.setMaxSpoutPending(10000);
		
		if (args != null && args.length > 0) 
		{
			config.setNumWorkers(10);  
            StormSubmitter.submitTopology(args[0], config, builder.createTopology());  
        }
		else 
		{
            LocalCluster cluster = new LocalCluster();  
            cluster.submitTopology("test_flume_1_7", config, builder.createTopology());  
//            Utils.sleep(1000000);
//            cluster.killTopology("test");  
//            cluster.shutdown();  
        }  
	}
}
