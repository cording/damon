package com.cord.damon.config;

import com.cord.damon.bean.ToolUtil;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConfigInfoCache {
	
	private static final Logger logger = Logger.getLogger(ConfigInfoCache.class);
	
	private static final Map<String, CacheValue> cache = new ConcurrentHashMap<>();
	
	private ConfigInfoCache(){};
	
	public static Map<String, CacheValue> getInstance(){
		return cache;
	}
	
	static{
		//初始化缓存
		ConfigInfo info = null;
		List<String> names = ToolUtil.getAllName();
		List<String> users = new ArrayList<>();
		if(names!=null&&names.size()!=0){
		}
		for(String name : names){
			info = DamonConfig.getInstance(name);
			cache.put(name, new CacheValue().setCommand(info.Command()).setFilter(info.Filter()).setUser(info.User()));
		}
		
		
//		MonitorConfig.config.addPropertyChangeListener(new PropertyChangeListener() {
//
//			@Override
//			public void propertyChange(PropertyChangeEvent event) {
//				System.out.println("配置文件发生变化.");
//				String propertyName = event.getPropertyName();
//				if(propertyName!=null&&propertyName.endsWith("Ver")){
//					String version = (String)event.getNewValue();
//					String id = MonitorConfig.config.getProperty(propertyName.replace(".Ver", ".Id"), "");
//					logger.info(String.format("配置文件版本有改动id[%s]version[%s]", id, version));
//					CacheValue value = cache.get(id);
//					if(value!=null){
//						value.setVersion(version);
//					}
//				}
//			}
//		});
		
	}
	
	/**
	 *缓存值对象 
	 */
	public static class CacheValue {
		
		private String command;

		private String filter;

		private String user;

		public String getCommand() {
			return command;
		}

		public CacheValue setCommand(String command) {
			this.command = command;
			return this;
		}

		public String getFilter() {
			return filter;
		}

		public CacheValue setFilter(String filter) {
			this.filter = filter;
			return this;
		}

		public String getUser() {
			return user;
		}

		public CacheValue setUser(String user) {
			this.user = user;
			return this;
		}
	}
	
}
