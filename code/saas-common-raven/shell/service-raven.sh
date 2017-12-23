#!/bin/sh -l

#storm env
export STORM_DIR=/usr/local/apache-storm-0.10.2
export TOPOLOGY_NAME=project_logs
export SERVICE_DIR=/zhou/raven

#kill old topology
$STORM_DIR/bin/storm kill $TOPOLOGY_NAME
echo "=== stop $TOPOLOGY_NAME"
sleep 5

#start new topology
echo "=== start $TOPOLOGY_NAME"
$STORM_DIR/bin/storm jar $SERVICE_DIR/saas-common-raven-0.0.1-SNAPSHOT-jar-with-dependencies.jar com.bc.common.raven.topology.CfpWebErpTopology $TOPOLOGY_NAME
echo "=== finish"

exit 0