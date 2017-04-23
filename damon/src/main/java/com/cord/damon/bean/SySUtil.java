package com.cord.damon.bean;

import java.io.File;
import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.sun.management.OperatingSystemMXBean;

/**
 *@author lgm12
 *linux系统资源负载获取 
 */
@SuppressWarnings("restriction")
public class SySUtil {
	private static final OperatingSystemMXBean os = (OperatingSystemMXBean)ManagementFactory.getOperatingSystemMXBean();
	private static final File rootfile = new File("/");
	private static final File datafile = new File("/data");
//	private static final DecimalFormat pattern = new DecimalFormat("#.##%");
	private static final DecimalFormat pattern = new DecimalFormat("#.##");
	
	/**cpu负载*/
	public static String cpuload(){
		return pattern.format(os.getSystemCpuLoad());
	}
	
	/**内存负载*/
	public static String ramload(){
		double free = os.getFreePhysicalMemorySize();
		double total = os.getTotalPhysicalMemorySize();
		return pattern.format((total - free)/total);
	}
	
	/**系统盘使用率*/
	public static String rootDiskload(){
		double free = rootfile.getUsableSpace();
		double total = rootfile.getTotalSpace();
		return pattern.format((total-free)/total);
	}
	
	/**数据盘使用率*/
	public static String dataDiskload(){
		double free = datafile.getUsableSpace();
		double total = datafile.getTotalSpace();
		return pattern.format((total-free)/total);
	}
	
	/**获取系统综合信息*/
	public static String getSysInfo(){
//		List<String> list = new ArrayList<>();
//		list.add(String.format("%s=%s", "cpu", cpuload()));
//		list.add(String.format("%s=%s", "ram", ramload()));
//		list.add(String.format("%s=%s", "rootDisk", rootDiskload()));
//		list.add(String.format("%s=%s", "dataDisk", dataDiskload()));
//		return String.join("&", list);
		Map<String, String> map = new HashMap<>();
		map.put("cpu", cpuload());
		map.put("ram", ramload());
		map.put("rootDisk", rootDiskload());
		map.put("dataDisk", dataDiskload());
		return ToolUtil.object2Json(map);
	}

}
