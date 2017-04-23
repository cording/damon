#!/bin/sh
user=$1
filter=$2

if [ -z $user ]; then
  exit 0
fi

if [ -z $filter ]; then
 exit 0
fi


count=`echo $(ps -ef -u $user|grep $filter|grep -v grep|grep -v check.sh|wc -l)`

exit $count