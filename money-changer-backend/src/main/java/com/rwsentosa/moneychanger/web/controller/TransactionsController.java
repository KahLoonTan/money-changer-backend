package com.rwsentosa.moneychanger.web.controller;

import com.rwsentosa.moneychanger.entity.dto.CurrencyCount;
import com.rwsentosa.moneychanger.entity.dto.TransactionsDTO;
import com.rwsentosa.moneychanger.intf.service.TransactionsService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions")
public class TransactionsController {
    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/findAll")
    ResponseEntity<?> findAll() {
        try {
            List<TransactionsDTO> transactionsDTOList = transactionsService.findAll().stream()
                    .map(t -> {
                        TransactionsDTO transactionsDTO = new TransactionsDTO();
                        transactionsDTO.setCreated_by(t.getCreated_by());
                        transactionsDTO.setCreated_time(t.getCreated_time());
                        transactionsDTO.setCurrency_code(t.getCurrency_code());
                        transactionsDTO.setTransaction_type(t.getTransaction_type());
                        transactionsDTO.setTransaction_rate(t.getTransaction_rate());
                        transactionsDTO.setRelease_amt(t.getRelease_amt());
                        transactionsDTO.setReceive_amt(t.getReceive_amt());
                        return transactionsDTO;
                    })
                    .collect(Collectors.toList());
            return ResponseEntity.ok(transactionsDTOList);

        } catch (IllegalStateException err) {
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/post", consumes = "application/json")
    public ResponseEntity<?> createTransactions(@RequestBody TransactionsDTO transactionsDTO) {
        try {
            transactionsService.createTransactions(transactionsDTO);
            return new ResponseEntity<TransactionsDTO>(transactionsDTO, HttpStatus.OK);
        } catch(Exception err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/findTransactionByCurrency")
    ResponseEntity<?> findTransactionByCurrency() {
        try {
            List<CurrencyCount> currencyCounts = transactionsService.countTotalTransactionsByCurrency();
            return new ResponseEntity<List<CurrencyCount>>(currencyCounts, HttpStatus.OK);
        } catch (ResourceNotFoundException err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception err) {
            return new ResponseEntity<String >(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
