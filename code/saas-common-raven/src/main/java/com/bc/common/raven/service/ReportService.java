package com.bc.common.raven.service;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;

/**
 * 
 * 报表统计service
 * @author whl
 *
 */
public interface ReportService {
	
	public void execute(Tuple tuple ,OutputCollector collector);
}
