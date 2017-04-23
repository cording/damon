package com.cord.damon.bean;

import com.cord.damon.config.DamonConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * Created by lgm12 on 2017/1/18.
 * 工具类
 */
public class ToolUtil {
    private static Logger logger = Logger.getLogger(ToolUtil.class);
    
    /**程序工作路径*/
    public static final String rootPath = System.getProperty("user.dir");
    
    /**shell脚本路径*/
    public static final String shellPath = rootPath + "/shell/";

    
	private static final Gson gson = new GsonBuilder().serializeNulls().create();
	
    /**
     *调用shell脚本获取返回值
     * @param cmd shell脚本带路径执行命令
     */
    public static int invokeShell(String cmd) throws Exception{
        Process process = Runtime.getRuntime().exec(cmd);
        int exitValue = process.waitFor();
		logger.info(String.format("%s执行结果:%s", cmd, exitValue));
        return exitValue;
    }


    /**
	 *调用shell脚本获取输出结果
	 */
	public static String invokeCommand(String cmd) throws Exception{
		Process process = Runtime.getRuntime().exec(cmd);
		process.waitFor();
		BufferedReader input = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String retContent = input.readLine();
		logger.info(String.format("执行%s返回值:",cmd)+retContent);
		return retContent;
//		try {
//			return new ProcessExecutor().command(cmd)
//					.timeout(10, TimeUnit.SECONDS)
//                    .readOutput(true).execute()
//                    .outputUTF8();
//		}catch (Exception e) {
//			logger.error(e);
//		}
//		return "";
	}

	
	/**获取监控的所有程序名*/
	public static List<String> getAllName(){
		String files = DamonConfig.config.Name();
		if(files==null||files.isEmpty()){
			return null;
		}else{
			return Arrays.asList(files.split(","));
			
		}
	}
	
	/**获取运行指定程序的所有用户*/
//	public static List<String> getUserByType(String type){
//		String users = MonitorConfig.getInstance(type).fileUser();
//		if(users==null||users.isEmpty()){
//			return null;
//		}else{
//			return Arrays.asList(users.split(","));
//		}
//	}

	
	/**获取系统时间戳字符串*/
	public static String getTimeStamp(){
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
	}
	
	/**
	 * 将对象转换为json字符串
	 */
	public static <T> String object2Json(T t){
		return gson.toJson(t);
	}
	
	/**
	 * json字符串转换为对象
	 */
	public static <T> T json2Object(String jsonStr, Class<T> clazz){
		return gson.fromJson(jsonStr, clazz);
	}
	
	/**
	 *json字符串转换为map 
	 */
	public static Map<String, String> json2Map(String jsonStr){
		return gson.fromJson(jsonStr, new TypeToken<Map<String, String>>(){}.getType());
	}
	
//	public static void main(String[] args){
//		String str = "{\"versions\":\"1u003d2\",\"retCode\":\"0\"}";
//		String str = "{\"versions\":\"1\\u003d2\",\"retCode\":\"0\"}";
//		String str = "{\"versions\":\"1\\u003d2\",\"retCode\":\"0\"}";
//		Map<String, String> map = json2Map(str);
//		System.out.println(map.get("versions"));
//		try {
//			System.out.println(StringEscapeUtils.unescapeJava(str));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		Map<String,String> map = json2Object(str, Map.class);
//		Map<String,String> map = gson.fromJson(str, new TypeToken<Map<String, String>>(){}.getType());
//		System.out.println(map.get("versions"));
//		Map<String, String> map = new HashMap<>();
//		map.put("version", "1=2&3=4");
//		System.out.println(object2Json(map));
//		List<String> list = new ArrayList<>();
//		System.out.println(String.join("&", list));
//	}
}
