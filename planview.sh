#!/bin/sh

DEV_DIR=.
PROJECT_DIR=$DEV_DIR/planview
LIB_DIR=$PROJECT_DIR/distribution/lib
JAR=$LIB_DIR/planview-DEV.jar
MAIN=amiller.planview.Main

#echo jar file: $JAR
#echo class:    $MAIN

java -Xmx256m -cp $JAR:$LIB_DIR/log4j-1.2.6.jar $MAIN $@
