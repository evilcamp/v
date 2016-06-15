package org.evilcamp.v.framework.common;

import java.util.HashMap;

/**
 * The Class ThreadCache.
 *
 * @date 2016-1-14 21:58:43
 * @author suxiaofei
 * @version 1.0
 * @since jdk 1.7,shield 1.0
 */
public class ThreadCache {
	
	/** cache. */
	private static ThreadLocal<HashMap<String,Object>> cache = new ThreadLocal<HashMap<String,Object>>();
	
	
	/**
	 * Gets cache.
	 *
	 * @return cache
	 */
	public static HashMap<String,Object> getCache(){
		if (cache.get()==null) {
			cache.set(new HashMap<String,Object>());
		}
		
		return cache.get();
	}

	/**
	 * get.
	 *
	 * @param key comments
	 * @return Object
	 */
	public static Object get(String key){
		if (cache.get()==null) {
			cache.set(new HashMap<String,Object>());
		}
		return cache.get().get(key);
	}
	
	
	public static void put(String key,Object value){
		if (cache.get()==null) {
			cache.set(new HashMap<String,Object>());
		}
		cache.get().put(key, value);
	}
}
