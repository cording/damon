#!/bin/sh
damon=damon-1.0-SNAPSHOT
start(){
   echo $"Starting damon: "
   total=$(pgrep -u $USER -f damon.*jar|wc -l)
   if [ $total -eq 0 ]; then
        cd $(pwd | awk -F /damon '{print $1}')/$damon
	nohup java -jar  damon-1.0-SNAPSHOT.jar > output.log 2>&1 &
   else
      echo "damon service is still runing"
   fi
}

stop(){
   echo $"Stop damon: "
   kill -9 `pgrep -u $USER -f damon.*jar`
}

restart(){
  stop
  sleep 3
  start
}

status(){
   total=$(pgrep -u $USER -f monitor-service.*jar|wc -l)
   if [ $total -eq 0 ]; then
      echo "damon service is stopped..."
      exit 1
   else
      echo "damon service is running..."
      exit 0
   fi
}

chmodall(){
   echo "grant to shell script."
   chmod 755 shell/*.sh
   chmod 755 publish/*.sh
}

boot(){
   echo "set damon self boot."
   user=$(pwd|awk -F / '{print $3}')
   path=$(pwd | awk -F /damon '{print $1}')/$damon/damon.sh
   content=${path}" start"
   if [ $(cat /etc/rc.local|grep "${content}"|wc -l) -eq 0 ]; then
      echo ${content} >> /etc/rc.local
   else
      echo "damon is already setted self boot."
   fi
}

case "$1" in
  start)
    start
    ;;
  stop)
    stop
    ;;
  restart)
    restart
    ;;
  status)
    status
    ;;
  chmod)
    chmodall
    ;;
  boot)
    boot
    ;;
  *)
   echo $"Usage: $0 {start|stop|restart|status|chmod|boot}"
   exit 1
esac

exit 0 
