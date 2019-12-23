package com.example.demo.common.utils.en_decode;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class Md5Util {
	/**
	 * Used building output as Hex
	 */
	private static final char[] DIGITS = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

    
	/**
	 * 对字符串进行MD5加密
	 * @param text 明文
	 * @return 密文
	 */
	public static String md5(String text) {
		return md5(text,Charset.forName("utf8"));
	}
	
	/**
	 * 对字符串进行MD5加密
	 * @param text 明文
	 * @return 密文
	 */
	public static String md5(String text,Charset charset) {
		try {
			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
			msgDigest.update(text.getBytes(charset));    //按照utf-8编码形式加密
			return  new String(encodeHex(msgDigest.digest()));
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("当前系统不支持Md5加密！");
        }
	}

	public static char[] encodeHex(byte[] data) {

		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = DIGITS[(0xF0 & data[i]) >>> 4];
			out[j++] = DIGITS[0x0F & data[i]];
		}
		return out;
	}
	
	public static void main(String[] args) {
		System.out.println(Md5Util.md5("ty189@2013admin"));
	}
	
	public static byte[] md5Byte(byte[] input) {
	    try {
	    	MessageDigest msgDigest = MessageDigest.getInstance("MD5");
	    	msgDigest.update(input);
	 	    return msgDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	      throw new IllegalStateException("System doesn't support MD5 algorithm.");
	    }
	  }
//	public static String md5Byte(byte[] array) {
//		try {
//			MessageDigest msgDigest = MessageDigest.getInstance("MD5");
//			msgDigest.update(array);
//			return  new String(encodeHex(msgDigest.digest()));
//		} catch (NoSuchAlgorithmException e) {
//			throw new IllegalStateException("当前系统不支持Md5加密！");
//        }
//	}
	/**
	 * shiro用户名密码 加盐加密
	 * @param password 密码
	 * @param salt 盐值
	 * @return
	 */
    public static String md5_salt(String password, String salt){
        //加密方式
        String hashAlgorithmName = "MD5";
        //盐：为了即使相同的密码不同的盐加密后的结果也不同
        ByteSource byteSalt = ByteSource.Util.bytes(salt);
        //密码
        Object source = password;
        //加密次数
        int hashIterations = 1024;
        SimpleHash result = new SimpleHash(hashAlgorithmName, source, byteSalt, hashIterations);
        return result.toString();
    }
}