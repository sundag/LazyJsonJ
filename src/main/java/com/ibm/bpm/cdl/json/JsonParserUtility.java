package com.ibm.bpm.cdl.json;

import java.io.IOException;
import java.math.BigDecimal;

class JsonParserUtility {
    public static final Object[] specialValues;
    
    static{
        specialValues = new Object[]{"NULL", "true", "false", null, true, false};
    }
    
    public static Object determineSpecialValues(String value) throws IOException{
        if(value == null) return null;
        for(int i=0;i<specialValues.length/2;i++){
            if(value.equalsIgnoreCase((String) specialValues[i])){
                return specialValues[i+specialValues.length/2];
            }
        }
        throw new IOException("Invalid special value: " + value);
    }
    
    public static Object parseDecimalValues(String value) throws IOException{
        if(value == null) return null;
        try{
            return Integer.valueOf(value);
        }catch(NumberFormatException e1){
            try{
                return Long.valueOf(value);
            }catch(NumberFormatException e2){
                try{
                    return Float.valueOf(value);
                }catch(NumberFormatException e3){
                    try{
                        return Double.valueOf(value);
                    }catch(NumberFormatException e4){
                        try{
                            return new BigDecimal(value);
                        }catch(NumberFormatException e5){
                            throw new IOException(value + " is not a valid decimal value.");
                        }
                    }
                }
            }
        }
    }
    
    public static String getJsonValueText(Object value){
        if(value == null) return "NULL";
        if(value instanceof String) return "\"" + (String)value + "\"";
        return value.toString();
    }
}
