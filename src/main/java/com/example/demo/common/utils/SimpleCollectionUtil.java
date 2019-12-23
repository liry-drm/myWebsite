package com.example.demo.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * fun：简化操作
 */
public class SimpleCollectionUtil {
	/**
	 * fun：把可变参数封装到map中
	 * 
	 * @param keys        数组键
	 * @param stringArray 数组值
	 * @return
	 */
	public static Map<String, String> stringsToMap(String[] keys, String... stringArray) {
		Map<String, String> map = new HashMap<String, String>();
		int i = 0;
		if (keys.length == stringArray.length) {
			for (String string : stringArray) {
				map.put(keys[i++], string);
			}
		}
		return map;
	}

	public static Map<String, Object> objectsToMap(String[] keys, Object... stringArray) {
		Map<String, Object> map = new HashMap<String, Object>();
		int i = 0;
		if (keys.length == stringArray.length) {
			for (Object object : stringArray) {
				map.put(keys[i++], object);
			}
		}
		return map;
	}

	/**
	 * 工具类：设置对应parmsMapKey没有的键,添加并设置该键值对为空
	 * 创建原因：由于mybatis返回的map，如果数据库中的单元格为空，则映射出来的map中这个列对应的键值对没有
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
	 * 把字节看着无符号的转成有符号的整数 byte(128) = -128(1000 0000) ----> 128
	 * 
	 * @param byten
	 * @return
	 */
	public static int[] unsignedBytesToInt(byte[] byten) {
		int[] ints = new int[byten.length];
		int i = 0, count = 0, length = 0;
		for (byte b : byten) {
			length = Integer.toBinaryString(b).length();
			// Integer.toBinaryString(b)得到的二进制字符串，高位是零的被省略了
			if (length - 8 > 0) {
				// 截取后八位二进制数据
				String[] binaryStrings = Integer.toBinaryString(b).substring(length - 8).split("");
				count = 0;
				// 把符号为包含在内求后八位二进制表示的大小
				for (int j = 1; j <= 8; j++) {
					count = (int) (count + Integer.parseInt(binaryStrings[j]) * Math.pow(2, 8 - j));
				}
			} else {
				count = b;
			}
			ints[i++] = count;
		}
		return ints;
	}

	/**
	 * 通过Map<String, Object>中的某个key value，从List<Map<String, Object>>获取map
	 * 
	 * @param list
	 * @param key
	 * @param value
	 * @return
	 */
	public static Map<String, Object> getMapFromListMap(List<Map<String, Object>> list, String key, String value) {
		for (Map<String, Object> map : list) {
			String mapValue = (String) map.get(key);
			if (mapValue != null) {
				if (mapValue.equals(value)) {
					return map;
				}
			}
		}
		return null;
	}

	/**
	 * 获取map中指定K-V的map
	 * 
	 * @param map  待过滤的map
	 * @param keys 需要的key
	 * @return 只有需要的K-V的map
	 */
	public static Map<String, Object> getMapOfKeys(Map<String, Object> map, String... keys) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		try {
			for (String key : keys) {
				resMap.put(key, map.get(key));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resMap;
	}

	/**
	 * 获取list中特定map的key-value
	 * 
	 * @param list 待过滤的list
	 * @param keys 需要保存的key
	 * @return 结果集list<Map<k,V>>
	 */
	public static List<Map<String, Object>> getListOfKeys(List<Map<String, Object>> list, String... keys) {
		List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
		for (Map<String, Object> map : list) {
			Map<String, Object> newMap = getMapOfKeys(map, keys);
			returnList.add(newMap);
		}
		return returnList;
	}
}
