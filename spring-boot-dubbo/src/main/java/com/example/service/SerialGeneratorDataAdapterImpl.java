package com.example.service;

import com.example.mapper.SerialNoMapper;
import com.example.utils.keygen.SerialInfo;
import com.example.utils.keygen.adapter.SerialGeneratorDataAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;


@Service("serialGeneratorDataAdapter")
public class SerialGeneratorDataAdapterImpl implements SerialGeneratorDataAdapter {
    @Autowired
    private PlatformTransactionManager txManager;
    @Autowired
    private TransactionDefinition txDefinition;
    @Autowired
    private SerialNoMapper serialNoMapper;

    public long getSerialValue(SerialInfo serialInfo) {
        Long serialValue = 0L;
        TransactionStatus txStatus=txManager.getTransaction(txDefinition);
        try {
            serialNoMapper.updateKeyDataValue(serialInfo);
            serialValue = (Long) serialNoMapper.queryForObject(serialInfo);
            txManager.commit(txStatus);
        } catch (DataAccessException e) {
             txManager.commit(txStatus);
        }
        if (serialValue == null) {
            throw new DataRetrievalFailureException("B2C_SERAIL_NO.updateKeyDataValue must retrieva at least one row,but null.");
        }
        return serialValue.longValue();
    }
}
