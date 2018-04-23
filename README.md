+java实现的守护进程服务，该服务是由spring-boot实现，通过调用shell的方式，实现对指定用户指定进程进行监控，
 +如果服务进程消失，会自动重新启动。
 +
 +使用说明如下:
 +
 +在damon.properties中增加需要监控程序的配置(下面以tomcat为例):
 +name=tomcat,                                              #需要监控的程序名,以逗号分隔
 +tomcat.command=/root/apache-tomcat-8.0.28/bin/startup.sh  #tomcat的启动命令
 +tomcat.filter=tomcat                                      #tomcat进程的过滤匹配特征，ps -ef|grep能锁定这个进程
 +tomcat.user=root                                          #tomcat运行用户
 +
 +使用步骤，
 +编译项目，生成打包文件damon-1.0-SNAPSHOT.zip，
 +上传damon-1.0-SNAPSHOT.zip至root用户路径下,
 +解压damon-1.0-SNAPSHOT.zip 进入解压后文件夹，执行chmod +x damon.sh
 +然后执行./damon.sh chmod给shell文件夹中的脚本授权,
 +接下来配置好damon.properties，然后执行./damon.sh start启动服务即可。
 +
 +
