package com.example.utils.keygen;

import com.example.utils.keygen.adapter.SerialGeneratorDataAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class SerailGenerator {

    private static final String FORMATTER_REGEX_DATE = "\\[\\w*\\]";
    private static final Pattern FORMATTER_REGEX_DATE_PATTERN = Pattern.compile(FORMATTER_REGEX_DATE);
    private static final String FORMATTER_REGEX_NUMBER = "\\{#*\\}";
    private static final Pattern FORMATTER_REGEX_NUMBER_PATTERN   = Pattern.compile(FORMATTER_REGEX_NUMBER);
    private static final String ZERO = "0";
    private final int poolSize;
    private final String keyName;
    private final String formatter;

    //=以下字段表示序列产生的缓存值 
    
    private long keyMax = 0;
    private long keyMin = 0;
    private long nextKey = 0;

    public SerailGenerator(int poolSize, String formatter, String keyName) {
        this.poolSize = poolSize;
        this.keyName = keyName;
        this.formatter =formatter;
    }

    /**
     * 
     * <b>Method Function Description:</b><br>
     * 获取下一序列
     */
    public  synchronized String getNextKey(SerialGeneratorDataAdapter dataAdapter) {
        SerialInfo keyinfo = new SerialInfo(this.poolSize, this.keyName);
        long keyValue= getNextKey(dataAdapter,keyinfo); 
        //[yyyyMMdd]{#####}==>[20131010]{#####}
        String originalFormatter=this.formatter;
        String formatterRegEx = FORMATTER_REGEX_DATE;
        Matcher matcher = FORMATTER_REGEX_DATE_PATTERN.matcher(this.formatter);
        while (matcher.find()) {
            String datePattern=matcher.group();
            datePattern = datePattern.substring(1, datePattern.length()-1);
            SimpleDateFormat dateFormat=new SimpleDateFormat(datePattern);
            String dateString=dateFormat.format(new Date());
            originalFormatter=originalFormatter.replaceFirst(FORMATTER_REGEX_DATE, dateString);
        }
        //[yyyyMMdd]{#####}==>20131010{#####}
        formatterRegEx = FORMATTER_REGEX_NUMBER;
        matcher = FORMATTER_REGEX_NUMBER_PATTERN.matcher(this.formatter);
        String keyValueStr=(new Long(keyValue)).toString();
        if (matcher.find()) {
            String keyPattern=matcher.group();
            int patternLength=keyPattern.length()-2;
            //如果有5个#，但是序列值为4位数字(4567)时
            if (patternLength > keyValueStr.length()) {
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < patternLength - keyValueStr.length(); i++) {
                    stringBuffer.append(ZERO);
                }
                stringBuffer.append(keyValueStr);
                keyValueStr=stringBuffer.toString();
               //如果有5个#，但是序列值为4位数字(4567)时,变为04567  
            }else if(patternLength<keyValueStr.length()){
            	//如果有5个#(patternLength)，但是序列值为6位数字(100001,keyValueStr.length())时,变为00001
            	keyValueStr = keyValueStr.substring(keyValueStr.length()-patternLength);
            }
        }
        return originalFormatter.replaceFirst(formatterRegEx, keyValueStr);
    }

    //1.如果nextKey是 0 的时候，表示刚被初始化，应该从数据库中取序列的最大值，刷新缓存
    //2.判断nextKey是 大于缓存的最大值的时候，表示缓存用完了，需要从数据库中再次取一次序列的最大值，刷新缓存
    //3.直接从缓存中取序列值
    private  long getNextKey(SerialGeneratorDataAdapter dataAdapter, SerialInfo keyinfo) {
        if(nextKey==0){
            retrieveFromDB(dataAdapter,keyinfo);
        }
        if (nextKey > keyMax) {
            retrieveFromDB(dataAdapter,keyinfo);
        }
        return nextKey++;
    }

    private void retrieveFromDB(SerialGeneratorDataAdapter dataAdapter, SerialInfo keyinfo) {
        long keyStart = dataAdapter.getSerialValue(keyinfo);
        keyMax = keyStart;
        keyMin = keyStart - keyinfo.getPoolSize() + 1;
        nextKey = keyMin;
    }

}