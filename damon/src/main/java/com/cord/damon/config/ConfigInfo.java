package com.cord.damon.config;

import org.aeonbits.owner.Accessible;
import org.aeonbits.owner.Config.HotReload;
import org.aeonbits.owner.Config.HotReloadType;
import org.aeonbits.owner.Config.Sources;
import org.aeonbits.owner.Mutable;
import org.aeonbits.owner.Reloadable;

/**
 * @author lgm12
 * 与damon.properties对应的对象
 */

@Sources("file:${rootpath}/damon.properties")
@HotReload(type=HotReloadType.SYNC)
public interface ConfigInfo extends Mutable, Accessible, Reloadable{
	//所有的程序名
	@DefaultValue("")
	@Key("name")
	String Name();
	
	//指定程序在哪些用户有运行
	@DefaultValue("")
	@Key("${name}.command")
	String Command();
	
	//进程过滤值
	@DefaultValue("")
	@Key("${name}.filter")
	String Filter();

	//程序运行用户
	@DefaultValue("")
	@Key("${name}.user")
	String User();
}
