package com.ibm.bpm.cdl.json;

import java.io.IOException;
import java.io.StringReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class EazyJsonArr {

    private List<Object> values;
    private boolean updated = true;
    private String jsonStr;

    public EazyJsonArr() {
        values = new LinkedList<Object>();
    }

    protected EazyJsonArr(StringReader reader) throws IOException {
        values = new LinkedList<Object>();
        parse(reader);
    }
    
    public Object get(int index){
        return values.get(index);
    }
    
    public Object remove(int index){
        updated = true;
        return values.remove(index);
    }
    
    public void add(Object value){
        updated = true;
        values.add(value);
    }
    
    public void insert(int index, Object value){
        updated = true;
        values.add(index, value);
    }

    public int size(){
        return values.size();
    }
    
    private void parse(StringReader reader) throws IOException {
        int state = 1;
        StringBuffer val1 = null;
        while (state != 5) {
            int cc = reader.read();
            if (cc == -1) {
                throw new IOException("Unexpected end of file.");
            }
            char value = (char) cc;

            switch (state) {
            case 1:
                if (Character.isISOControl(value) || value == ' ')
                    continue;
                if (value == '"') {
                    val1 = new StringBuffer();
                    state = 2;
                    break;
                } else if(value <= '9' && value >='0' || value == '+' || value == '-'){
                    val1 = new StringBuffer();
                    val1.append(value);
                    state = 3;
                    break;
                } else if(value == '{'){
                    Object val2 = new EazyJsonObj(reader);
                    values.add(val2);
                    state = 4;
                    break;
                } else if(value == ']'){
                    state = 5;
                    break;
                } else if(value == '['){
                    Object val2 = new EazyJsonArr(reader);
                    values.add(val2);
                    state = 1;
                    break;
                } else if(value == ','){
                    values.add("");
                    break;
                } else {
                    val1 = new StringBuffer();
                    val1.append(value);
                    state = 6;
                    break;
                }
                //throw new IOException(String.format("Unexcepted character %c", value));
            case 2:
                if (value == '"') {
                    values.add(val1.toString());
                    state = 4;
                } else {
                    val1.append(value);
                }
                break;
            case 3:
                if (value == ',') {
                    values.add(JsonParserUtility.parseDecimalValues(val1.toString()));
                    state = 1;
                    break;
                } else if(value == ']'){
                    values.add(JsonParserUtility.parseDecimalValues(val1.toString()));
                    state = 5;
                    break;
                } else if( value <= '9' && value >='0' || value == '.'){
                    val1.append(value);
                    break;
                } else if( value == ' '){
                    state = 4;
                    values.add(JsonParserUtility.parseDecimalValues(val1.toString()));
                    break;
                }
                throw new IOException(String.format("Unexcepted character %c", value));
            case 4:
                if (Character.isISOControl(value) || value == ' ')
                    continue;
                if (value == ',') {
                    state = 1;
                    break;
                }else if(value == ']'){
                    state = 5;
                    break;
                }
                throw new IOException(String.format("Unexcepted character %c", value));
            case 6:
                if (value == ',') {
                    String val2 = val1.toString();
                    values.add(JsonParserUtility.determineSpecialValues(val2));
                    state = 1;
                    break;
                } else if(value == ']'){
                    String val2 = val1.toString();
                    values.add(JsonParserUtility.determineSpecialValues(val2));
                    state = 5;
                    break;
                } else if( value == ' '){
                    state = 4;
                    String val2 = val1.toString();
                    values.add(JsonParserUtility.determineSpecialValues(val2));
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
        if(!updated)
            return jsonStr;
        StringBuffer strBuf = new StringBuffer();
        strBuf.append("[");
        Iterator<Object> value = values.iterator();
        while(value.hasNext()){
            strBuf.append(JsonParserUtility.getJsonValueText(value.next()));
            if(value.hasNext()){
                strBuf.append(",");
            }
        }
        strBuf.append("]");
        jsonStr = strBuf.toString();
        updated = false;
        return jsonStr;
    }
}
