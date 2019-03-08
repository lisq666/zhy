package com.example.service;

import com.example.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service("asyncService")
public class AsyncServiceImpl implements AsyncService{

    @Override
    @Async("asyncServiceExecutor")
    public void executeAsync() {
        try{
            Thread.sleep(5000);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception{
        String time = "2019-01-07 23:46:00";
        String time1 = "2019-01-08 00:16:00";
        long a = DateUtils.dateMinusDateForDays(time,time1,DateUtils.LONG_WEB_FORMAT);
        if(a == 30){
            System.out.println(true);
        }
        System.out.println(a);
    }
}
