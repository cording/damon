package com.cord.damon.config;

import com.cord.damon.bean.ToolUtil;
import org.aeonbits.owner.ConfigFactory;

import java.util.HashMap;
import java.util.Map;


/**
 * @author lgm12
 * monitor配置文件
 */
public class DamonConfig {
	static{
		ConfigFactory.setProperty("rootpath", ToolUtil.rootPath);
	}
	public static final ConfigInfo config = ConfigFactory.create(ConfigInfo.class);
	
	/**
	 *@param name 程序名
	 *根据类型读取配置
	 */
	public static ConfigInfo getInstance(String name){
		Map<String,String> map = new HashMap<>();
		map.put("name", name);
		return ConfigFactory.create(ConfigInfo.class, map);
	}
}
