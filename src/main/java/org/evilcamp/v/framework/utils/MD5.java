package org.evilcamp.v.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;



public class MD5 {

    private static final Logger logger = LoggerFactory.getLogger(MD5.class);
	
	private static char[] hexdigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	
	public static String result(String src)
	{
		try {
		    if (StringUtils.isEmpty(src)) {
		        return null;
		    }
		    
			return result(src.getBytes("UTF-8"));
		} catch (Throwable t) {
			logger.info("Error while cal md5.", t);
			return null;
		}
	}
	
	public static String result(byte[] byteArray)
	{
		if(byteArray == null || byteArray.length <= 0) return null;
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("md5");
			md5.update(byteArray);
			byte[] _result = md5.digest();
			
			int l = _result.length;
			char str[] = new char[l * 2];
			
			int k = 0;
			byte b;
			for(int i=0; i<l; i++)
			{
				b = _result[i];
				str[k++] = hexdigits[b >>> 4 & 0x0f];
				str[k++] = hexdigits[b & 0x0f];
			}
			return new String(str);
		} catch (Throwable t) {
			logger.info("Error while cal md5.", t);
			return null;
		}
	}
	
	public static String result(Object... params) {
		StringBuffer buf = new StringBuffer();
		for(Object p : params) buf.append(p);
		return result(buf.toString());
	}
	
	
	public static long bytes2Long(byte[] b)	{
		if(b == null)return 0;
		int mask = 0xff;
		int temp = 0;
		long res = 0;
		for (int i = 0; i < 8; i++) {
			res <<= 8;
			temp = b[i] & mask;
			res |= temp;
		}
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(result("12345"));
	}
}
