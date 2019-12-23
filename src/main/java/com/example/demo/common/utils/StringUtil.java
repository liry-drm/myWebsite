package com.example.demo.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串处理类
 * 
 */
public class StringUtil {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D',
			'E', 'F' };
	/**
	 * <pre>
	 * 省、直辖市代码表： 
	 *     11 : 北京  12 : 天津  13 : 河北       14 : 山西  15 : 内蒙古   
	 *     21 : 辽宁  22 : 吉林  23 : 黑龙江  31 : 上海  32 : 江苏   
	 *     33 : 浙江  34 : 安徽  35 : 福建       36 : 江西  37 : 山东   
	 *     41 : 河南  42 : 湖北  43 : 湖南       44 : 广东  45 : 广西      46 : 海南   
	 *     50 : 重庆  51 : 四川  52 : 贵州       53 : 云南  54 : 西藏   
	 *     61 : 陕西  62 : 甘肃  63 : 青海       64 : 宁夏  65 : 新疆   
	 *     71 : 台湾   
	 *     81 : 香港  82 : 澳门   
	 *     91 : 国外
	 * </pre>
	 */
	private static String cityCode[] = { "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35",
			"36", "37", "41", "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65",
			"71", "81", "82", "91" };

	/**
	 * 身份证号每位加权因子
	 */
	private static int power[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };

	/**
	 * 判断给定字符串是否为空
	 * 
	 * @param sourceString 待判断的字符串
	 * @return true 空字符串，false 非空字符串
	 */
	public static boolean isEmpty(String sourceString) {
		if (null == sourceString)
			return true;
		if (sourceString.trim().length() == 0) {
			return true;
		}
		return false;
	}

	/**
	 * 去掉首尾空字符
	 * 
	 * @param sourceString 待处理字符串
	 * @return 返回去掉首尾空字符后的字符串
	 */
	public static String trim(String sourceString) {
		if (null == sourceString) {
			return "";
		}
		return sourceString.trim();
	}

	/**
	 * 去掉左边空字符
	 * 
	 * @param sourceString 待处理字符串
	 * @return 返回去掉左边空字符后的字符串
	 */
	public static String leftTrim(String sourceString) {
		if (null == sourceString) {
			return "";
		}
		String regEx = "^\\s{1,}";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(sourceString);
		String result = mat.replaceAll("");
		return result;
	}

	/**
	 * 去掉右边空字符
	 * 
	 * @param sourceString 待处理字符串
	 * @return 返回去掉右边空字符后的字符串
	 */
	public static String rightTrim(String sourceString) {
		if (null == sourceString) {
			return "";
		}
		String regEx = "\\s{1,}$";
		Pattern pat = Pattern.compile(regEx);
		Matcher mat = pat.matcher(sourceString);
		String result = mat.replaceAll("");
		return result;
	}

	/**
	 * 将给定的字符串进行加密
	 * 
	 * @param sourceString 待加密字符串
	 * @return MD5加密字符串
	 */
	public static String toMD5(String sourceString) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
		if (isEmpty(sourceString)) {
			return null;
		}
		byte[] md = md5.digest(sourceString.getBytes());
		char[] c = new char[md.length * 2];
		int k = 0;
		// 将每位都进行双字节加密
		for (int i = 0; i < md.length; i++) {
			byte b = md[i];
			c[k++] = HEX_DIGITS[b >> 4 & 0xf];
			c[k++] = HEX_DIGITS[b & 0xf];
		}
		return new String(c);
	}

	/**
	 * 比较两个字符串值是否相同
	 * 
	 * @param sourceString 源字符
	 * @param targetString 目标字符串
	 * @return true 相同,false 不同
	 */
	public static boolean matche(String sourceString, String targetString) {
		if (null == sourceString || null == targetString) {
			return false;
		}
		if (sourceString.length() != targetString.length()) {
			return false;
		}
		if (sourceString.equals(targetString)) {
			return true;
		}
		return false;
	}

	/**
	 * 判断邮箱是否有效
	 * 
	 * @param emailString 待验证的email地址
	 * @return true 有效 ,false 无效
	 */
	public static boolean isEmail(String emailString) {
		if (isEmpty(emailString)) {
			return false;
		}
		String regEx = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		// String regex =
		// "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		return match(regEx, emailString);

	}

	/**
	 * 判断是否是有效的手机号<br>
	 * &nbsp;&nbsp;移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188 <br>
	 * &nbsp;&nbsp;联通：130、131、132、152、155、156、185、186<br>
	 * &nbsp;&nbsp;电信：133、153、180、189、（1349卫通）
	 * 
	 * @param number 待验证的手机号
	 * @return true 有效，false 无效
	 */
	public static boolean isMobileNumber(String number) {
		if (isEmpty(number)) {
			return false;
		}
		String regEx = "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
		return match(regEx, number);
	}

	/**
	 * 判断是否是有效的ip
	 * 
	 * @param ip 待验证的ip
	 * @return true 有效，false 无效
	 */
	public static boolean isIp(String ip) {
		if (isEmpty(ip)) {
			return false;
		}
		String regEx = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";
		return match(regEx, ip);
	}

	public static final String apiKey = "YzyCi5Gxc1G8BpywdCGCemxN";
	public static final String secretKey = "k0SIGDDfKNAYRtyPkPoKGgPrtgfCxPj4";

	/**
	 * 获取uuid
	 * 
	 * @return
	 */
	public static String getUUid() {
		String result = "";
		UUID uuid = UUID.randomUUID();
		String uuid2 = uuid.toString();
		result = uuid2.replace("-", "");
		return result;
	}

	/**
	 * 是否存在中文字符
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isContainChinese(String str) {

		Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
		Matcher m = p.matcher(str);
		if (m.find()) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串转码
	 * 
	 * @param sourceString  源字符串
	 * @param targerCharset 目标字符集(UTF-8,GBK,GB2312,ISO-8859-1)
	 * @return 返回转码后的字符
	 * 
	 * @exception UnsupportedEncodingException 你的编码不支持、可能是你给你的编码书写错误或者是其他编码异常！
	 */
	public static String transformEncode(String sourceString, String targerCharset)
			throws UnsupportedEncodingException {
		if (isEmpty(sourceString) || isEmpty(sourceString)) {
			return null;
		}
		String result;
		result = new String(sourceString.getBytes(targerCharset), targerCharset);
		return result;
	}

	/**
	 * 字符串反转
	 * 
	 * @param str
	 * @return
	 */
	public static String reverseStr(String str) {

		StringBuilder b = new StringBuilder(str);
		return b.reverse().toString();
	}

	/**
	 * 获取文件的MD5
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public static String getMd5ByFile(File file) throws FileNotFoundException {
		String value = null;
		FileInputStream in = new FileInputStream(file);
		try {
			MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(byteBuffer);
			BigInteger bi = new BigInteger(1, md5.digest());
			value = bi.toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return value;
	}

	/**
	 * 验证所有的身份证的合法性
	 * 
	 * @param idcard 身份证
	 * @return 合法返回true，否则返回false
	 */
	public static boolean isValidatedAllIdcard(String idcard) {
		if (idcard == null || "".equals(idcard)) {
			return false;
		}
		if (idcard.length() == 15) {
			return validate15IDCard(idcard);
		}
		return validate18Idcard(idcard);
	}

	/**
	 * <p>
	 * 判断18位身份证的合法性
	 * </p>
	 * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
	 * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
	 * <p>
	 * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
	 * </p>
	 * <p>
	 * 1.前1、2位数字表示：所在省份的代码； 2.第3、4位数字表示：所在城市的代码； 3.第5、6位数字表示：所在区县的代码；
	 * 4.第7~14位数字表示：出生年、月、日； 5.第15、16位数字表示：所在地的派出所的代码； 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
	 * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。校检码可以是0~9的数字，有时也用x表示。
	 * </p>
	 * <p>
	 * 第十八位数字(校验码)的计算方法为： 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1
	 * 6 3 7 9 10 5 8 4 2
	 * </p>
	 * <p>
	 * 2.将这17位数字和系数相乘的结果相加。
	 * </p>
	 * <p>
	 * 3.用加出来和除以11，看余数是多少
	 * </p>
	 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
	 * <p>
	 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
	 * </p>
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean validate18Idcard(String idcard) {
		if (idcard == null) {
			return false;
		}

		// 非18位为假
		if (idcard.length() != 18) {
			return false;
		}
		// 获取前17位
		String idcard17 = idcard.substring(0, 17);

		// 前17位全部为数字
		if (!isDigital(idcard17)) {
			return false;
		}

		String provinceid = idcard.substring(0, 2);
		// 校验省份
		if (!checkProvinceid(provinceid)) {
			return false;
		}

		// 校验出生日期
		String birthday = idcard.substring(6, 14);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

		try {
			Date birthDate = sdf.parse(birthday);
			String tmpDate = sdf.format(birthDate);
			if (!tmpDate.equals(birthday)) {// 出生年月日不正确
				return false;
			}

		} catch (ParseException e1) {

			return false;
		}

		// 获取第18位
		String idcard18Code = idcard.substring(17, 18);

		char c[] = idcard17.toCharArray();

		int bit[] = converCharToInt(c);

		int sum17 = 0;

		sum17 = getPowerSum(bit);

		// 将和值与11取模得到余数进行校验码判断
		String checkCode = getCheckCodeBySum(sum17);
		if (null == checkCode) {
			return false;
		}
		// 将身份证的第18位与算出来的校码进行匹配，不相等就为假
		if (!idcard18Code.equalsIgnoreCase(checkCode)) {
			return false;
		}

		return true;
	}

	/**
	 * 校验15位身份证
	 * 
	 * <pre>
	 * 只校验省份和出生年月日
	 * </pre>
	 * 
	 * @param idcard
	 * @return
	 */
	public static boolean validate15IDCard(String idcard) {
		if (idcard == null) {
			return false;
		}
		// 非15位为假
		if (idcard.length() != 15) {
			return false;
		}

		// 15全部为数字
		if (!isDigital(idcard)) {
			return false;
		}

		String provinceid = idcard.substring(0, 2);
		// 校验省份
		if (!checkProvinceid(provinceid)) {
			return false;
		}

		String birthday = idcard.substring(6, 12);

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

		try {
			Date birthDate = sdf.parse(birthday);
			String tmpDate = sdf.format(birthDate);
			if (!tmpDate.equals(birthday)) {// 身份证日期错误
				return false;
			}

		} catch (ParseException e1) {

			return false;
		}

		return true;
	}

	/**
	 * 将15位的身份证转成18位身份证
	 * 
	 * @param idcard
	 * @return
	 */
	public static String convertIdcarBy15bit(String idcard) {
		if (idcard == null) {
			return null;
		}

		// 非15位身份证
		if (idcard.length() != 15) {
			return null;
		}

		// 15全部为数字
		if (!isDigital(idcard)) {
			return null;
		}

		String provinceid = idcard.substring(0, 2);
		// 校验省份
		if (!checkProvinceid(provinceid)) {
			return null;
		}

		String birthday = idcard.substring(6, 12);

		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

		Date birthdate = null;
		try {
			birthdate = sdf.parse(birthday);
			String tmpDate = sdf.format(birthdate);
			if (!tmpDate.equals(birthday)) {// 身份证日期错误
				return null;
			}

		} catch (ParseException e1) {
			return null;
		}

		Calendar cday = Calendar.getInstance();
		cday.setTime(birthdate);
		String year = String.valueOf(cday.get(Calendar.YEAR));

		String idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

		char c[] = idcard17.toCharArray();
		String checkCode = "";

		// 将字符数组转为整型数组
		int bit[] = converCharToInt(c);

		int sum17 = 0;
		sum17 = getPowerSum(bit);

		// 获取和值与11取模得到余数进行校验码
		checkCode = getCheckCodeBySum(sum17);

		// 获取不到校验位
		if (null == checkCode) {
			return null;
		}
		// 将前17位与第18位校验码拼接
		idcard17 += checkCode;
		return idcard17;
	}

	/**
	 * 校验省份
	 * 
	 * @param provinceid
	 * @return 合法返回TRUE，否则返回FALSE
	 */
	private static boolean checkProvinceid(String provinceid) {
		for (String id : cityCode) {
			if (id.equals(provinceid)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 数字验证
	 * 
	 * @param str
	 * @return
	 */
	private static boolean isDigital(String str) {
		return str.matches("^[0-9]*$");
	}

	/**
	 * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
	 * 
	 * @param bit
	 * @return
	 */
	private static int getPowerSum(int[] bit) {

		int sum = 0;

		if (power.length != bit.length) {
			return sum;
		}

		for (int i = 0; i < bit.length; i++) {
			for (int j = 0; j < power.length; j++) {
				if (i == j) {
					sum = sum + bit[i] * power[j];
				}
			}
		}
		return sum;
	}

	/**
	 * 将和值与11取模得到余数进行校验码判断
	 * 
	 * @param checkCode
	 * @param sum17
	 * @return 校验位
	 */
	private static String getCheckCodeBySum(int sum17) {
		String checkCode = null;
		switch (sum17 % 11) {
		case 10:
			checkCode = "2";
			break;
		case 9:
			checkCode = "3";
			break;
		case 8:
			checkCode = "4";
			break;
		case 7:
			checkCode = "5";
			break;
		case 6:
			checkCode = "6";
			break;
		case 5:
			checkCode = "7";
			break;
		case 4:
			checkCode = "8";
			break;
		case 3:
			checkCode = "9";
			break;
		case 2:
			checkCode = "x";
			break;
		case 1:
			checkCode = "0";
			break;
		case 0:
			checkCode = "1";
			break;
		}
		return checkCode;
	}

	/**
	 * 将字符数组转为整型数组
	 * 
	 * @param c
	 * @return
	 * @throws NumberFormatException
	 */
	private static int[] converCharToInt(char[] c) throws NumberFormatException {
		int[] a = new int[c.length];
		int k = 0;
		for (char temp : c) {
			a[k++] = Integer.parseInt(String.valueOf(temp));
		}
		return a;
	}

	/**
	 * @param regex 正则表达式字符串
	 * @param str   要匹配的字符串
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */
	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 向文件中写入内容
	 * 
	 * @param content
	 * @param file_name
	 */
	public static void file_writer(String content, String file_name) {
		FileWriter fw = null;
		try {
			// 如果文件存在，则追加内容；如果文件不存在，则创建文件
			File f = new File("E:\\" + file_name);
			fw = new FileWriter(f, true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		PrintWriter pw = new PrintWriter(fw);
		pw.println(content);
		pw.flush();
		try {
			fw.flush();
			pw.close();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
