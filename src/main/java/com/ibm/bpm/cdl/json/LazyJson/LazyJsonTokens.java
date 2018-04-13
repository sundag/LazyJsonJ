package com.ibm.bpm.cdl.json.LazyJson;

public class LazyJsonTokens {
	
	private boolean eof;
	
	public LazyJsonToken next(){
		if(!eof){
			return null;
		}else{
			return null;
		}
	}
	
	public boolean hasMore(){
		return !eof;
	}
}
