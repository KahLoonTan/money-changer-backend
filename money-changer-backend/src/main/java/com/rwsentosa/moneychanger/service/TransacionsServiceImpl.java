package com.rwsentosa.moneychanger.service;

import com.rwsentosa.moneychanger.entity.Transactions;
import com.rwsentosa.moneychanger.entity.dto.CurrencyCount;
import com.rwsentosa.moneychanger.entity.dto.TransactionsDTO;
import com.rwsentosa.moneychanger.intf.service.TransactionsService;
import com.rwsentosa.moneychanger.repository.TransactionsRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransacionsServiceImpl implements TransactionsService {

    @Autowired
    private TransactionsRepository transactionsRepository;

    public List<Transactions> findAll(){
        List<Transactions> emptyList = transactionsRepository.findAll();
        if(emptyList.isEmpty())
            throw new IllegalStateException("List is empty");

        return transactionsRepository.findAll();
    }

    public Transactions createTransactions(TransactionsDTO transactionsDTO){
        try {
            Transactions transactions = new Transactions();
            transactions.setCurrency_code(transactionsDTO.getCurrency_code());
            transactions.setTransaction_type(transactionsDTO.getTransaction_type());
            transactions.setCreated_by(transactionsDTO.getCreated_by());
            //transactions.setCreated_time(transactionsDTO.getCreated_time());
            transactions.setTransaction_rate(transactionsDTO.getTransaction_rate());
            transactions.setReceive_amt(transactionsDTO.getReceive_amt());
            transactions.setRelease_amt(transactionsDTO.getRelease_amt());
            return transactionsRepository.save(transactions);
        }catch (Exception err){
            throw new RuntimeException("Service error: " + err.getMessage());
        }
    }

    public List<CurrencyCount> countTotalTransactionsByCurrency(){
        List<CurrencyCount> currencyCounts = new ArrayList<CurrencyCount>();
        List<Object[]> result = transactionsRepository.countTotalTransactionsByCurrency();
        for (Object[] ob : result)
        {
            currencyCounts.add(new CurrencyCount((String)ob[0], ((BigInteger) ob[1]).intValue()));
        }
        if (!currencyCounts.isEmpty()){
            return currencyCounts;
        }else{
            throw new ResourceNotFoundException("Transaction", "No records found" );
        }
    }


}
