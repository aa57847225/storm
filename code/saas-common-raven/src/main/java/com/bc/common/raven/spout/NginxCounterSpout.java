package com.bc.common.raven.spout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Map;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseRichSpout;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;

public class NginxCounterSpout extends BaseRichSpout
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SpoutOutputCollector collector;  
	
	private FileReader fileReader;
	
	//是否处理完成
	
	public void nextTuple() 
	{
		String str; 
		// Open the reader  
        BufferedReader reader = new BufferedReader(fileReader);  
        try 
        {  
            // Read all lines  
            while ((str = reader.readLine()) != null) 
            {  
                /** 
                 * 发射每一行，Values是一个ArrayList的实现 
                 */  
                this.collector.emit(new Values(str), str);  
            }  
        } 
        catch (Exception e) 
        {  
            throw new RuntimeException("Error reading tuple", e);  
        } 
	}

	/**
	 * 这是第一个方法
	 * @param conf 创建Topology时的配置
	 * @param context 所有的Topology数据
	 * @param collector 用来把Spout的数据发射给bolt 
	 */
	@SuppressWarnings("rawtypes")
	public void open(Map conf, TopologyContext context, SpoutOutputCollector collector)
	{
		try
		{
			fileReader = new FileReader(conf.get("nginxAccessLog").toString());
		}
		catch (Exception e)
		{
			throw new RuntimeException("Error reading file ["  
                    + conf.get("nginxAccessLog") + "]");
		}
		
		//初始化发射器
		this.collector = collector;
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) 
	{
		declarer.declare(new Fields("nginxCounterSpout"));
	}
	
}
