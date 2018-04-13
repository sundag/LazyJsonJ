package com.ibm.bpm.cdl.json.LazyJson;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 
 * @author sundag
 *
 */
public class LazyJsonObject {
	
	private Map<String,Object> values;
	private LazyJsonTokens tokens;
	
	public LazyJsonObject(){
		
	}
	
	public LazyJsonObject(String content) throws LazyJsonException {
		
	}
	
	public Object get(String key) throws LazyJsonException{
		if(key == null) throw new LazyJsonException("Key cannot be null.");
		Object value = this.optGet(key);
		if(value != null)
			return value;
		throw new LazyJsonException("[" + key + "] doesn't existed!");
	}
	
	public Object optGet(String key){
		if( key == null) return null;
		if(values == null){
			values = new ConcurrentHashMap<>();
		}
		if(values.containsKey(key)){
			return values.get(key);
		}
		while(tokens.hasMore()){
			LazyJsonToken token = tokens.next();
			String localKey = token.getKey();
			values.put(localKey, token.getValue());
			if(localKey.equals(key))
				return token.getValue();
		}
		return null;
	}
	
	@Override
	public String toString(){
		return null;
	}
}
