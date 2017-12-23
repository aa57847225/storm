package com.bc.common.raven.topology;

import java.util.ArrayList;
import java.util.List;

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
public class NginxTopology 
{
	
	public static void main(String[] args) throws Exception
	{
	    //kafka
		System.out.println("======start========");
	    String topic = "test";
	    String zkRoot = "/brokers";
	    String spoutId = "KafkaSpout";
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
		builder.setBolt("nginxCounterBolt", new NginxCounterBolt()).shuffleGrouping(spoutId);
		
		//配置
		Config config = new Config();
		config.setDebug(true); 
		
		if (args != null && args.length > 0) 
		{
			config.setNumWorkers(10);  
            StormSubmitter.submitTopology(args[0], config, builder.createTopology());  
        }
		else 
		{
            LocalCluster cluster = new LocalCluster();  
            cluster.submitTopology("test", config, builder.createTopology());  
//            Utils.sleep(1000000);
//            cluster.killTopology("test");  
//            cluster.shutdown();  
        }  
	}
}