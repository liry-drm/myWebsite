package com.example.demo.common.utils.pinyin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.github.stuxuhai.jpinyin.ChineseHelper;
import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;

/**
 * 汉字转拼音工具类 注意：大小写字母、数字、特殊字符等忽略，保持不变
 * 
 * @author liruyi
 *
 */
public class pinyinUtils {

	final static String separator = "	";// 默认分割符 tab符

	public static void main(String[] args) throws Exception {

// 		multi_convert2py("A2是Y3了I4长！@重#……*!（（！**（&……%……&").forEach((o)->System.out.println(o));
//		long millis = System.currentTimeMillis();
//		List<String> list = multi_convert2py("和珅");
//		System.out.println(System.currentTimeMillis() - millis);
//		list.forEach((o) -> System.out.println(o));
//
//		long millis2 = System.currentTimeMillis();
//		List<String> li = multi_convert2py("阿了");
//		System.out.println(System.currentTimeMillis() - millis2);
//		li.forEach((o) -> System.out.println(o));
//
//		long millis3 = System.currentTimeMillis();
//		List<String> listy = multi_convert2py("更参");
//		System.out.println(System.currentTimeMillis() - millis3);
//		listy.forEach((o) -> System.out.println(o));
//		multi_convert2py("李如意454").forEach((o) -> System.out.println(o));
	}

	/**
	 * 将字符串转换成相应格式的拼音(单读音)
	 *
	 * @param str          待转字符串
	 * @param separator    拼音分隔符
	 * @param pinyinFormat 指定格式 WITH_TONE_MARK 带音调（默认）, WITHOUT_TONE 不带音调,
	 *                     WITH_TONE_NUMBER 数字代表声调置于拼音后;
	 * @return 字符串的拼音
	 */
	public static String multi_convert2singel_py(String str, String separator, PinyinFormat pinyinFormat) {
		String result = null;
		try {
			result = PinyinHelper.convertToPinyinString(str, separator, pinyinFormat);
		} catch (PinyinException e) {
			e.printStackTrace();
		}
		return result;

	}

	/**
	 * 将字符串转换成相应格式的拼音(多音字)
	 *
	 * @param str 待转字符串
	 * @return 字符串的所有拼音组合(多音字情况)
	 */
	public static List<String> multi_convert2py(String str) {
		List<String> list = new ArrayList<>();
		if (!containsChinese(str)) {// 不存在汉字，直接返回原字符串
			list.add(str);
		} else {
			// 使用set并初始化一个空StringBuilder，由于拼接操作过多，所以使用StringBuilder，不考虑线程安全
			Set<StringBuilder> set = new LinkedHashSet<>();
			StringBuilder sb = new StringBuilder();
			set.add(sb);
			// 每次使用该set中拼接字符串
			Set<StringBuilder> tempSet = null;
			char[] chars = str.toCharArray();
			for (char c : chars) {
				if (hasMultiPinyin(c)) {// 多音汉字符
					String[] strs = single_convert2py(c);
					tempSet = new LinkedHashSet<>();
					Iterator<StringBuilder> it = set.iterator();
					/*
					 * 在每一个元素的基础上拼接。 set的新长度=set的原长度n*多音个数m
					 */
					while (it.hasNext()) {
						StringBuilder strB = (StringBuilder) it.next();
						StringBuilder newSb = null;
						for (String st : strs) {
							newSb = new StringBuilder(strB);
							tempSet.add(newSb.append(st));
						}
					}
					set = tempSet;
				} else {// 非汉字符/无多音的汉字符
					// 直接在每个元素中拼接该字符或其单拼音
					set.forEach((tempSb) -> {
						tempSb.append(single_convert2pyString(c));
					});
				}
			}
			StringBuilder[] array = set.toArray(new StringBuilder[set.size()]);
			for (StringBuilder stringBui : array) {
				list.add(stringBui.toString());
			}
		}
		return list;
	}

	/**
	 * 字符串转首字母简拼
	 * 
	 * @param str 待转字符串
	 * @return
	 * @throws PinyinException
	 */
	public static String getShortPinyin(String str) throws PinyinException {
		return PinyinHelper.getShortPinyin(str);
	}

	/**
	 * 单个汉字转换为拼音,不带音调
	 * 
	 * @param c 待转字符
	 * @return 非汉字则返回原字符，多音只返回第一个音
	 */
	public static String single_convert2pyString(char c) {
		String[] str = PinyinHelper.convertToPinyinArray(c, PinyinFormat.WITHOUT_TONE);
		switch (str.length) {
		case 0:
			return String.valueOf(c);
		default:
			return str[0];
		}
	}

	/**
	 * 单个汉字转换为拼音,不带音调
	 * 
	 * @param c 待转字符
	 * @return 汉字的所有拼音,即多音字有多个拼音
	 */
	public static String[] single_convert2py(char c) {
		return single_convert2py(c, PinyinFormat.WITHOUT_TONE);
	}

	/**
	 * 单个汉字转换为拼音
	 * 
	 * @param c            待转字符
	 * @param pinyinFormat 指定格式 WITH_TONE_MARK 带音调（默认）, WITHOUT_TONE
	 *                     不带音调,WITH_TONE_NUMBER 音调转数字置于拼音后;
	 * @return 汉字的所有拼音,即多音字有多个拼音
	 */
	public static String[] single_convert2py(char c, PinyinFormat pinyinFormat) {
		return PinyinHelper.convertToPinyinArray(c, pinyinFormat);
	}

	/**
	 * 是否存在中文
	 * 
	 * @param str 待检测字符串
	 * @return 存在true，不存在false
	 */
	public static boolean containsChinese(String str) {
		return ChineseHelper.containsChinese(str);
	}

	/**
	 * 是否是多音字
	 * 
	 * @param c 待检测字符
	 * @return
	 */
	public static boolean hasMultiPinyin(char c) {
		return PinyinHelper.hasMultiPinyin(c);
	}

	/**
	 * 简体转换为繁体
	 * 
	 * @param pinYinStr
	 * @return
	 */
	public static String changeToTraditional(String pinYinStr) {
		String tempStr = null;
		try {
			tempStr = ChineseHelper.convertToTraditionalChinese(pinYinStr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempStr;
	}

	/**
	 * 繁体转换为简体
	 * 
	 * @param pinYinSt
	 * @return
	 */
	public static String changeToSimplified(String pinYinSt) {
		String tempStr = null;
		try {
			tempStr = ChineseHelper.convertToSimplifiedChinese(pinYinSt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempStr;
	}
}
