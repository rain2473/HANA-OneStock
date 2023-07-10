package com.hanaonestock.transaction.model.dao;

import com.hanaonestock.transaction.model.dto.Transaction;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TransactionMapper {
    // 매수
    int insertBuyTransaction(Transaction transaction);

    // 매도
    int updateSellTransaction(Transaction transaction);



}
