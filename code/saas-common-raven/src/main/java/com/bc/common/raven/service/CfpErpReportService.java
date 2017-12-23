package com.bc.common.raven.service;

import backtype.storm.task.OutputCollector;
import backtype.storm.tuple.Tuple;

public interface CfpErpReportService {

	public void execute(Tuple tuple ,OutputCollector collector);
}
