package com.example.utils.keygen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.example.mapper.AuthMapper;
import com.example.mapper.SerialNoMapper;
import com.example.model.SerialNo;
import com.example.utils.keygen.adapter.SerialGeneratorDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
@Component
public class SerialGeneratorMgr {
	
    public static final Map<String, SerailGenerator> sgs = new ConcurrentHashMap<String, SerailGenerator>();
	private static SerialGeneratorMgr serialGeneratorMgr;
	@Autowired
	private SerialNoMapper serialNoMapper;
	@Autowired
	private SerialGeneratorDataAdapter serialGeneratorDataAdapter;

	@PostConstruct
	private void init(){
		serialGeneratorMgr = this;
		serialGeneratorMgr.serialNoMapper = this.serialNoMapper;
		serialGeneratorMgr.serialGeneratorDataAdapter = this.serialGeneratorDataAdapter;
	}
    /**
     * 
     * <b>Method Function Description:</b><br>
     * 根据Serial Name获取序列号
     * @param serialName
     * @return
     * <br><b>Method Logic Description:</b>
     * 1.先根据Serial Name获取对应的KeyGenerator。
     * 2.再调用SerialGenerator的getNextKey获得下一序列号。
     */
    public String getSerialKey(String serialName) {
        if (sgs.containsKey(serialName)) {
        	SerailGenerator sg = sgs.get(serialName);
            return sg.getNextKey(serialGeneratorMgr.serialGeneratorDataAdapter);
        } else {
        	SerailGenerator sg = getSerailGenerator(serialName);
        	if(sg==null){
        		throw new IllegalArgumentException("The keygen cannot be null with the key name:"+serialName);
        	}
        	sgs.put(serialName, sg);
        	return sg.getNextKey(serialGeneratorMgr.serialGeneratorDataAdapter);
        }
    }

    private SerailGenerator getSerailGenerator(String serialName){
		SerialNo serialNo = serialGeneratorMgr.serialNoMapper.selectByPrimaryKey(serialName);
    	if(serialNo==null){
    		throw new IllegalArgumentException("The serial name "+serialName+" not defined in the DB");
    	}
    	return  new SerailGenerator(serialNo.getSerialPoolSize(),serialNo.getSerialFormat(),serialName);
    }

}
