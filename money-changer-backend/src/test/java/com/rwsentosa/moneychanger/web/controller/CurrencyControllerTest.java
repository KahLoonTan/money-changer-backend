package com.rwsentosa.moneychanger.web.controller;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Map;

@Log4j2
@SpringBootTest
@WebAppConfiguration
class CurrencyControllerTest {

  @Autowired
  private CurrencyController currencyController;

  @Test
  @Sql("classpath:test-data.sql")
  void  testFindAll() {
    log.info("Currency findAll: {}", currencyController.findAll());
  }

    @Test
    void  testFindByCode() {
        log.info("Currency findAll: {}", currencyController.findByCode("USD"));
        log.info("Currency findAll: {}", currencyController.findByCode(""));

    }

    @Test
    void testFindBuyAmtByCode() {
        Map<String, String> testInputFile = Map.of(
                "Code", "USD",
                "Amt", "200"

        );
        log.info("Currency findBuyAmtByCode: {}", currencyController.findBuyAmtByCode(testInputFile));

    }

    @Test
    void testFindSellAmtByCode() {
        Map<String, String> testInputFile = Map.of(
                "Code", "HKD",
                "Amt", "200"

        );
        log.info("Currency findSellAmtByCode: {}", currencyController.findSellAmtByCode(testInputFile));

    }



}