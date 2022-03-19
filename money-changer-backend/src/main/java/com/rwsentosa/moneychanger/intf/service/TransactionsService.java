package com.rwsentosa.moneychanger.intf.service;

import com.rwsentosa.moneychanger.entity.Transactions;
import com.rwsentosa.moneychanger.entity.dto.CurrencyCount;
import com.rwsentosa.moneychanger.entity.dto.TransactionsDTO;

import java.util.List;

public interface TransactionsService {

    List<Transactions> findAll();
    Transactions createTransactions(TransactionsDTO transactionsDTO);
    List<CurrencyCount> countTotalTransactionsByCurrency();
}
