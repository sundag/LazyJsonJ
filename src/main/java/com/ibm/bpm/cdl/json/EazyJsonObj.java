package com.ibm.bpm.cdl.json;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class EazyJsonObj {

    private static char[] controls = new char[] { ' ', '\t', '\n', '\n' };

    private Map<String, Object> values;

    private int state;
    
    private boolean updated = true;
    
    private String jsonStr;
    
    public EazyJsonObj(){
        values = new HashMap<>();
    }
    
    public EazyJsonObj(String content) throws IOException{
        StringReader reader = new StringReader(content);
        while(reader.read() != '{'){}
        parse(reader);
    }

    public Object optGet(String key){
        if( key == null ) return null;
        return values.get(key);
    }
    
    public EazyJsonObj optGetEazyJsonObj(String key){
        if( key == null ) return null;
        return (EazyJsonObj) values.get(key);
    }
    
    public EazyJsonArr optGetEazyJsonArr(String key){
        if( key == null ) return null;
        return (EazyJsonArr) values.get(key);
    }
    
    public void put(String key, Object value){
        updated = true;
        values.put(key, value);
    }
    
    protected EazyJsonObj(StringReader reader) throws IOException {
        parse(reader);
    }

    private void parse(StringReader reader) throws IOException {
        values = new HashMap<>();
        state = 1;
        StringBuffer key = null;
        StringBuffer val1 = null;
        Object val2 = null;
        while (state != 8) {
            int cc = reader.read();
            if(cc == -1){
                throw new IOException("Unexpected end of file.");
            }
            char value = (char) cc;
            switch (state) {
            case 1:
                if (Character.isISOControl(value) || value == ' ')
                    continue;
                if (value == '"') {
                    key = new StringBuffer();
                    state = 2;
                    break;
                }
                throw new IOException(String.format("Unexcepted character %c", value));
            case 2:
                if (value == '"') {
                    state = 3;
                } else {
                    key.append(value);
                }
                break;
            case 3:
                if (Character.isISOControl(value) || value == ' ')
                    continue;
                if (value == ':') {
                    val1 = new StringBuffer();
                    val2 = null;
                    state = 4;
                    break;
                }
                break;
            case 4:
                if (Character.isISOControl(value) || value == ' ')
                    continue;
                if (value == '"') {
                    val1 = new StringBuffer();
                    state = 5;
                    break;
                } else if(value <= '9' && value >='0' || value == '+' || value == '-'){
                    val1 = new StringBuffer();
                    val1.append(value);
                    state = 6;
                    break;
                } else if(value == '{'){
                    val2 = new EazyJsonObj(reader);
                    values.put(key.toString(), val2);
                    state = 7;
                    break;
                } else if(value == '['){
                    val2 = new EazyJsonArr(reader);
                    values.put(key.toString(), val2);
                    state = 7;
                    break;
                } else {
                    val1 = new StringBuffer();
                    val1.append(value);
                    state = 9;
                    break;
                }
            case 5:
                if (value == '"') {
                    values.put(key.toString(), val1.toString());
                    state = 7;
                } else {
                    val1.append(value);
                }
                break;
            case 6:
                if (value == ',') {
                    values.put(key.toString(), JsonParserUtility.parseDecimalValues(val1.toString()));
                    state = 1;
                    break;
                } else if(value == '}'){
                    values.put(key.toString(), JsonParserUtility.parseDecimalValues(val1.toString()));
                    state = 8;
                    break;
                } else if( value <= '9' && value >='0' || value == '.'){
                    val1.append(value);
                    break;
                } else if( value == ' '){
                    values.put(key.toString(), JsonParserUtility.parseDecimalValues(val1.toString()));
                    state = 7;
                    break;
                }
                throw new IOException(String.format("Unexcepted character %c", value));
            case 7:
                if (Character.isISOControl(value) || value == ' ')
                    continue;
                if (value == ',') {
                    state = 1;
                } else if(value == '}'){
                    state = 8;
                }
                break;
            case 9:
                if (value == ',') {
                    String val3 = val1.toString();
                    values.put(key.toString(), JsonParserUtility.determineSpecialValues(val3));
                    state = 1;
                    break;
                } else if(value == '}'){
                    String val3 = val1.toString();
                    values.put(key.toString(), JsonParserUtility.determineSpecialValues(val3));
                    state = 8;
                    break;
                } else if( value == ' '){
                    state = 7;
                    String val3 = val1.toString();
                    values.put(key.toString(), JsonParserUtility.determineSpecialValues(val3));
                    break;
                } else {
                    val1.append(value);
                    break;
                }
            }
        }
    }
    
    @Override
    public String toString(){
        if(!updated) return jsonStr;
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("{");
        Iterator<String> keys = values.keySet().iterator();
        while(keys.hasNext()){
            String key = keys.next();
            strBuf.append("\"");
            strBuf.append(key);
            strBuf.append("\":");
            strBuf.append(JsonParserUtility.getJsonValueText(values.get(key)));
            if(keys.hasNext())
                strBuf.append(",");
        }
        strBuf.append("}");
        jsonStr = strBuf.toString();
        updated = false;
        return jsonStr;
    }

}
