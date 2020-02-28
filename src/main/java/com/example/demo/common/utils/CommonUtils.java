package com.example.demo.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.util.StringUtils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 公共工具类
 * 
 * @date 2016 4 6
 *
 */
@SuppressWarnings("all")
public class CommonUtils {

	/**
	 * 判断空值
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String value) {
		return value != null && !"".equals(value) && !"null".equals(value.toLowerCase());
	}

	/**
	 * 验证字符数组不为空
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNotNull(String[] value) {
		boolean b = false;
		for (int i = 0; i < value.length; i++) {
			if (value[i] != null && !"".equals(value[i]) && !"null".equals(value[i].toLowerCase())) {
				b = true;
			}
		}
		return b;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 返回listmap中的小写字段数据
	 * 
	 * @param listmap
	 * @return
	 */
	public static List<Map<String, Object>> getLowerCaseMaplist(List<Map<String, Object>> listmap) {
		List<Map<String, Object>> reusltlist = new LinkedList<Map<String, Object>>();
		for (int i = 0; i < listmap.size(); i++) {
			Map<String, Object> m = listmap.get(i);
			Map<String, Object> newmap = new HashMap<String, Object>();
			Set<String> keys = m.keySet();
			for (String key : keys) {
				for (int j = 0; j < key.length(); j++) {
					char c = key.charAt(j);
					if (Character.isLowerCase(c)) {
						newmap.put(key, m.get(key));
						break;
					}
				}
			}
			reusltlist.add(newmap);
		}
		return reusltlist;
	}

	/**
	 * 返回map中的小写字段数据
	 * 
	 * @param listmap
	 * @return
	 */
	public static Map<String, Object> getLowerCaseMap(Map<String, Object> map) {
		Map<String, Object> newmap = new HashMap<String, Object>();
		Set<String> keys = map.keySet();
		for (String key : keys) {
			for (int j = 0; j < key.length(); j++) {
				char c = key.charAt(j);
				if (Character.isLowerCase(c)) {
					newmap.put(key, map.get(key));
					break;
				}
			}
		}
		return newmap;
	}

	/**
	 * 将4字节转换为长度
	 * 
	 * @param res
	 * @return
	 */
	public static int byte2int(byte[] res) {
		// 一个byte数据左移24位变成0x??000000，再右移8位变成0x00??0000
		int targets = (res[5] & 0xff) | ((res[6] << 8) & 0xff00) // | 表示安位或
				| ((res[7] << 24) >>> 8) | (res[8] << 24);
		return targets;
	}

	/**
	 * 将值转换为长度为iArrayLen的byte[]
	 * 
	 * @param iSource   值
	 * @param iArrayLen 长度
	 * @return
	 */
	public static byte[] toByteArray(int iSource, int iArrayLen) {
		byte[] bLocalArr = new byte[iArrayLen];
		for (int i = 0; (i < 4) && (i < iArrayLen); i++) {
			bLocalArr[i] = (byte) (iSource >> 8 * i & 0xFF);
		}
		return bLocalArr;
	}

	/**
	 * 将ip转换为数字字符串
	 * 
	 * @param ipAddress
	 * @return
	 */
	public static String ipToIntString(String ipAddress) {
		long result = 0;
		String[] ipAddressInArray = ipAddress.split("\\.");
		for (int i = 3; i >= 0; i--) {
			long ip = Long.parseLong(ipAddressInArray[3 - i]);
			// left shifting 24,16,8,0 and bitwise OR

			// 1. 192 << 24
			// 1. 168 << 16
			// 1. 1 << 8
			// 1. 2 << 0
			result |= ip << (i * 8);
		}
		return Long.toString(result);
//        数字转ip
//        long ip = Long.parseLong(CommonUtil.ipToIntString("21.0.0.43"));
//		StringBuilder sb = new StringBuilder(15);  
//        for (int i = 0; i < 4; i++) {  
//            // 1. 2  
//            // 2. 1  
//            // 3. 168  
//            // 4. 192  
//            sb.insert(0, Long.toString(ip & 0xff));  
//            if (i < 3) {  
//                sb.insert(0, '.');  
//            }  
//            // 1. 192.168.1.2  
//            // 2. 192.168.1  
//            // 3. 192.168  
//            // 4. 192  
//            ip = ip >> 8;  
//        }  
//        System.out.println(sb.toString());
	}

	/**
	 * 判断数据类型，返回相应的数据类型
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> T getDataType(Object obj) {
		if (obj instanceof Float) {
			return (T) obj;
		} else if (obj instanceof Integer) {
			return (T) obj;
		} else {
			return (T) obj;
		}
	}

	/**
	 * 返回周末字符串
	 * 
	 * @param str
	 * @return
	 */
	public static String getWeekString(String[] str) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < str.length; i++) {

				if (str[i].equals("周一")) {
					sb.append("MON");
				}
				if (str[i].equals("周二")) {
					sb.append("TUE");
				}
				if (str[i].equals("周三")) {
					sb.append("WED");
				}
				if (str[i].equals("周四")) {
					sb.append("THU");
				}
				if (str[i].equals("周五")) {
					sb.append("FRI");
				}
				if (str[i].equals("周六")) {
					sb.append("SAT");
				}
				if (str[i].equals("周日")) {
					sb.append("SUN");
				}
				if (str.length - 1 > i) {
					sb.append(",");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getWeekint(String[] str) {
		StringBuffer sb = new StringBuffer();
		try {
			for (int i = 0; i < str.length; i++) {
				if (str[i].equals("MON")) {
					sb.append("周一");
				}
				if (str[i].equals("TUE")) {
					sb.append("周二");
				}
				if (str[i].equals("WED")) {
					sb.append("周三");
				}
				if (str[i].equals("THU")) {
					sb.append("周四");
				}
				if (str[i].equals("FRI")) {
					sb.append("周五");
				}
				if (str[i].equals("SAT")) {
					sb.append("周六");
				}
				if (str[i].equals("SUN")) {
					sb.append("周日");
				}
				if (str.length - 1 > i) {
					sb.append(",");
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

	/**
	 * 工具类：设置对应parmsMapKey没有的键,添加并设置该键值对为空
	 * 创建原因：由于mybatis返回的map，如果数据库中的单元格为空，则映射出来的map中这个列对应的键值对没有 张世杰 2016-8-12
	 * 
	 * @param presetMap
	 * @param parmsMap
	 * @return
	 */
	public static Map<String, Object> changeMapNull(Map<String, Object> presetMap, String[] parmsMapKey) {
		for (int i = 0; i < parmsMapKey.length; i++) {
			String parm = parmsMapKey[i];
			// 这个为空因为mybatis中查询出来的键值对没有
			if (presetMap.get(parm) == null) {
				presetMap.put(parm, "");
			}
		}
		return presetMap;
	}

	/**
	 * 获取路径下的所有文件/文件夹名称和路径
	 * 
	 * @param directoryPath  需要遍历的文件夹路径
	 * @param isAddDirectory 是否将子文件夹的路径也添加到list集合中
	 * @return
	 */
	public static List<Map<String, Object>> getAllFileName(String directoryPath) {
		List<Map<String, Object>> list = new LinkedList<Map<String, Object>>();
		File baseFile = new File(directoryPath);
		if (baseFile.isFile() || !baseFile.exists()) {
			return list;
		}
		File[] files = baseFile.listFiles();
		for (File file : files) {
			Map<String, Object> filemap = new HashMap<String, Object>();
			if (file.isDirectory()) {
				filemap.put("status", 1);// 状态： 目录
			} else {
				filemap.put("status", 0);// 状态： 文件
			}
			filemap.put("filename", file.getName());// 名称
			filemap.put("filepath", file.getPath());// 路径
			list.add(filemap);
		}
		return list;
	}

	/**
	 * 图片转base64编码字符串
	 * 
	 * @param filePath
	 * @return
	 */
	public static String img2Base64(String filePath) {
		InputStream in;
		byte[] data = null;
		try {
			in = new FileInputStream(filePath);
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "";
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		BASE64Encoder encoder = new BASE64Encoder();
		return encoder.encode(data);
	}

	/**
	 * base64编码字符串转图片二进制码
	 * 
	 * @param imgStr 字符串
	 * @return
	 */
	public static byte[] base64_2img(String imgStr) {
		if (StringUtils.isEmpty(imgStr)) { // 图像数据为空
			return null;
		}
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
			/*
			 * OutputStream out = new FileOutputStream(imgFilePath); out.write(b);
			 * out.flush(); out.close();
			 */
			return b;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 获取指定范围随机数
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static int getRandom(int start, int end) {
		return new Random().nextInt(end - start + 1) + start;
	}

	public static boolean isBlank(String _baseSt) {

		boolean b = false;

		if (_baseSt == null) {
			b = true;
		} else {

			String s = _baseSt.trim();

			if (s.length() > 0) {
				b = false;
			} else {
				b = true;
			}
		}
		return b;

	}

	public static boolean isBlank(Object _baseSt) {

		boolean b = false;

		if (_baseSt == null) {
			b = true;
		} else {

			String s = _baseSt.toString().trim();

			if (s.length() > 0) {
				b = false;
			} else {
				b = true;
			}
		}
		return b;

	}

	/**
	 * 将list<map>中的key全部转换为小写
	 * 
	 * @param listmap
	 * @return
	 */
	public static List<Map<String, Object>> convertToLowerCaseMap(List<Map<String, Object>> listmap) {
		List<Map<String, Object>> reusltlist = new LinkedList<Map<String, Object>>();
		StringBuffer sb;
		for (int i = 0; i < listmap.size(); i++) {
			Map<String, Object> m = listmap.get(i);
			Map<String, Object> newmap = new HashMap<String, Object>();
			Set<String> keys = m.keySet();
			for (String key : keys) {
				sb = new StringBuffer();
				for (int j = 0; j < key.length(); j++) {
					char c = key.charAt(j);
					sb.append(Character.toLowerCase(c));
				}
				newmap.put(sb.toString(), m.get(key));
			}
			reusltlist.add(newmap);
		}
		return reusltlist;
	}

	/**
	 * 获取主键 (生成至少12位的随机id)
	 * 
	 * @param length 超过12位的指定长度
	 * @return
	 */
	public static String getID(int length) {
		/** 当前日期 **/
		String key = DateUtils.getNowTime(DateUtils.DATE_KEY_STR);
		/** 添加3为随机码 **/
		String random = DateUtils.genRandomNum(length);
		return key + random;
	}
	
	
	
	
	
}