#!/bin/sh
# edit by zsj
# ./
echo "good day"
echo `date`

####################### PROC NAME
export GDCP_PROC_NAME=GDCP-SCollector01

####################### CVLR SERVER
export GDCP_CVLR_Master=mina#192.168.1.14:8701:-1
export GDCP_CVLR_Slave=mina#192.168.1.15:8701:-1

#######################  SERVER ID
export GDCP_SERVER_ID=SCollector01

#######################  APP ENV
export APPHOME=/data/server/$GDCP_PROC_NAME
export GDCP_DYNC_LIB=/data/server/$GDCP_PROC_NAME/dynclib
export GDCP_LOGDB_PATH=/data/server/$GDCP_PROC_NAME/file/
export GDCP_BACKUP_PATH=/data/server/GDCPData/scdata
export GDCP_INDEXS_PATH=/data/server/$GDCP_PROC_NAME/indexs/
#######################  MAIN CLASS
export MAINCLASS=com.cst.gdcp.scollector.core.SCollectorStart

####################### CREATE DIR IF NOT EXIST
mkdir -p $GDCP_BACKUP_PATH
mkdir -p $GDCP_LOGDB_PATH
mkdir -p $GDCP_INDEXS_PATH
####################### DO NOT MODIFY , IF YOU DON'T KNOW HOW 
##JAVA_OPTS="-server -Xms3072m -Xmx3072m -Xss1024K -XX:PermSize=256M -XX:MaxPermSize=256m "
##	-Dcom.sun.management.jmxremote  -Duser.timezone=GMT+08 -Djava.awt.headless=true
#
# -Xms：JVM初始分配的堆内存
# -Xmx：JVM最大允许分配的堆内存，按需分配
# -Xss：设置每个线程的堆栈大小。JDK5.0以后每个线程堆栈大小为1M，以前每个线程堆栈大小为256K。更具应用的线程所需内存大小进行调整。
#		在相同物理内存下，减小这个值能生成更多的线程。但是操作系统对一个进程内的线程数还是有限制的，不能无限生成，经验值在3000~5000左右。
# -XX:PermSize：JVM初始分配的非堆内存
# -XX:MaxPermSize：JVM最大允许分配的非堆内存，按需分配
# 
 
 

export JVMPARA='-Xms2048m -Xmx3072m -Xss1024K -XX:PermSize=256m -XX:MaxPermSize=256m -verbose:gc -Xloggc:gc.log -XX:+PrintGCDateStamps -Dfile.encoding=GBK'

cd $APPHOME
JARS="./conf:"
LIB=$APPHOME/lib
if [ -d $LIB ]; then
  for i in $LIB/*/*.jar; do
    JARS="$JARS":$i
  done
fi
if [ -d $LIB ]; then
  for i in $LIB/*.jar; do
    JARS="$JARS":$i
  done
fi
export JARS

java -Dprogram.name=$GDCP_SERVER_ID $JVMPARA -cp $JARS $MAINCLASS

####################### DO NOT MODIFY , IF YOU DON'T KNOW HOW 
