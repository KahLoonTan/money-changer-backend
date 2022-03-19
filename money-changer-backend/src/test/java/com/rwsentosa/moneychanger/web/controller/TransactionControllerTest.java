package com.rwsentosa.moneychanger.web.controller;

import com.rwsentosa.moneychanger.entity.dto.TransactionsDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;

@Log4j2
@SpringBootTest
@WebAppConfiguration
public class TransactionControllerTest {
    @Autowired
    private TransactionsController transactionController;

    @Test
    @Sql("classpath:test-data.sql")
    void  testFindAll() {
        log.info("Transaction findAll: {}", transactionController.findAll());
    }

    @Test
    void  testFindTransactionByCurrency() {
        log.info("Transaction findTransactionByCurrency: {}", transactionController.findTransactionByCurrency());

    }

    @Test
    void testPostTransaction() {
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setCurrency_code("USD");
        transactionsDTO.setRelease_amt(271.50);
        transactionsDTO.setReceive_amt(200.5);
        transactionsDTO.setTransaction_rate(1.3574);
        transactionsDTO.setTransaction_type("Sell");

        log.info("Transaction testPostTransaction: {}", transactionController.createTransactions(transactionsDTO));
    }
}
