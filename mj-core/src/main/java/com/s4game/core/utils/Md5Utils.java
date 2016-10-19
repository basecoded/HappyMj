package com.s4game.core.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Md5Utils {
	
	private static final Logger LOG = LoggerFactory.getLogger(Md5Utils.class);
	
	private static final String[] hexDigits = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String md5To32(String value) {
		StringBuffer buffer = new StringBuffer("");
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(value.getBytes());
			
			byte[] bytes = messageDigest.digest();
			for (int i = 0; i < bytes.length; i++) {
				int j = bytes[i];
				if (j < 0) {
					j += 256;
				}
				if (j < 16) {
					buffer.append("0");
				}
				buffer.append(Integer.toHexString(j));
			}
			
			return buffer.toString().toUpperCase();
			
		} catch (NoSuchAlgorithmException exception) {
			LOG.error("", exception);
		}
		return null;
	}

	public static String md5To16(String paramString) {
		StringBuffer buffer = new StringBuffer("");
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(paramString.getBytes());
			
			byte[] bytes = messageDigest.digest();
			for (int i = 0; i < bytes.length; i++) {
				int j = bytes[i];
				if (j < 0) {
					j += 256;
				}
				if (j < 16) {
					buffer.append("0");
				}
				buffer.append(Integer.toHexString(j));
			}
			
			return buffer.toString().substring(8, 24).toUpperCase();

		} catch (NoSuchAlgorithmException exception) {
			LOG.error("", exception);
		}
		return null;
	}

	public static String md5Bytes(byte[] bytes) {
		String str = null;
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			messageDigest.update(bytes);
			
			str = byteArrayToHexString(messageDigest.digest());
		} catch (Exception localException) {
			LOG.error("", localException);
		}
		return str;
	}

	public static String byteArrayToHexString(byte[] bytes) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < bytes.length; i++) {
			buffer.append(byteToHexString(bytes[i]));
		}
		
		return buffer.toString();
	}

	private static String byteToHexString(byte paramByte) {
		int i = paramByte;
		if (i < 0) {
			i = 256 + i;
		}
		int j = i / 16;
		int k = i % 16;
		return hexDigits[j] + hexDigits[k];
	}

	public static void main(String[] paramArrayOfString) {
		String str1 = md5To16("test");
		System.out.println(str1);

		String str2 = md5To32("test");
		System.out.println(str2);
	}
}