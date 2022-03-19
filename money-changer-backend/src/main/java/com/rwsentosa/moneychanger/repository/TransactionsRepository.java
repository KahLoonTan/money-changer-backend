package com.rwsentosa.moneychanger.repository;

import com.rwsentosa.moneychanger.entity.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionsRepository extends JpaRepository<Transactions, Long> {
    @Query(value = "SELECT t.currency_code, COUNT(t.*) FROM Transactions AS t GROUP BY t.currency_code ORDER BY COUNT(t.*) DESC", nativeQuery = true)
    List<Object[]> countTotalTransactionsByCurrency();

}
