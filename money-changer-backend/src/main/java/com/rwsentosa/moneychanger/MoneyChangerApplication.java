package com.rwsentosa.moneychanger;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.rwsentosa.moneychanger"})
@EnableJpaRepositories(basePackages = {"com.rwsentosa.moneychanger.repository"})
@EntityScan(basePackages = {"com.rwsentosa.moneychanger.entity"})
public class MoneyChangerApplication {

  public static void main(String[] args) {
    new SpringApplicationBuilder(MoneyChangerApplication.class).run();
  }

}

