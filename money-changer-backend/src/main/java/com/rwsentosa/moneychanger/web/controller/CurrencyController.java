package com.rwsentosa.moneychanger.web.controller;

import com.rwsentosa.moneychanger.entity.Currency;
import com.rwsentosa.moneychanger.entity.dto.CurrencyVo;
import com.rwsentosa.moneychanger.entity.dto.TransactionsDTO;
import com.rwsentosa.moneychanger.intf.service.CurrencyService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("currency")
class CurrencyController {

    @Autowired
    private CurrencyService currencyService;


    @GetMapping("findAll")
    ResponseEntity<?> findAll() {
        try {
            List<CurrencyVo> currencyList = currencyService.findAll().stream().map(c -> {
                CurrencyVo currencyVo = new CurrencyVo();
                currencyVo.setCode(c.getCode());
                currencyVo.setDescription(c.getDescription());
                currencyVo.setBuyrate(c.getBuy_rate());
                currencyVo.setSellrate(c.getSell_rate());
                currencyVo.setSmallestnotes(c.getSmallest_note());
                return currencyVo;
            })
                    .collect(Collectors.toList());;
            return ResponseEntity.ok(currencyList);

        } catch (IllegalStateException err) {
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NO_CONTENT);
        } catch (Exception err){
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



   @GetMapping(path = "/findByCode/{code}")
   ResponseEntity<?> findByCode(@PathVariable("code") String code) {
       try {
           Currency currency = currencyService.findByCode(code);
           return new ResponseEntity<Currency>(currency, HttpStatus.OK);
       }
       catch (ResourceNotFoundException err) {
           return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_FOUND);

       }
       catch (Exception err) {
           return new ResponseEntity<String>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
       }
   }





    @GetMapping(path = "/findBuyAmtByCode",consumes = "application/json", produces = "application/json" )
    ResponseEntity<?> findBuyAmtByCode(@RequestBody Map<String, String> inputFile) {
        try {
            String code = inputFile.get("code");
            String value = inputFile.get("amt");
            if (value.isBlank()) {
                throw new ResourceNotFoundException("Missing Input", "Value not found: ");
            }
            Double receiveAmt = Double.parseDouble(value);
            List<TransactionsDTO> resultMessages = new ArrayList();
            Currency currency = currencyService.findByCode(code);

            double releaseAmt, buyRate = currency.getBuy_rate();
            releaseAmt = roundTo1Decimal(buyRate * receiveAmt);
            resultMessages.add(processBuyMsg(receiveAmt, releaseAmt, buyRate, code));

            return new ResponseEntity<List<TransactionsDTO>>(resultMessages, HttpStatus.OK);
        }
        catch (ResourceNotFoundException err)
        {
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_FOUND);

        }
        catch (Exception err) {
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/findSellAmtByCode",consumes = "application/json", produces = "application/json")
    ResponseEntity<?>  findSellAmtByCode(@RequestBody Map<String, String> inputFile) {
        try {
            String code = inputFile.get("code");
            String value = inputFile.get("amt");
            if (value.isBlank()) {
                throw new ResourceNotFoundException("Missing Input", "Value not found: ");
            }


            Double receiveAmt = Double.parseDouble(value);
            List<TransactionsDTO> resultMessages = new ArrayList();
            Currency currency = currencyService.findByCode(code);

            double sellRate = currency.getSell_rate();
            double releaseAmt = roundTo1Decimal(receiveAmt / sellRate);
            resultMessages.add(processSellMsg(receiveAmt, releaseAmt, sellRate, code));

            int smallestNotes = currency.getSmallest_note();
            double releaseRoundedAmt = roundUpBySmallestNotes(releaseAmt, smallestNotes);
            double receiveRoundedAmt = Math.round(releaseRoundedAmt * sellRate);
            if (releaseAmt != releaseRoundedAmt) {
                resultMessages.add(processSellMsg(receiveRoundedAmt, releaseRoundedAmt, sellRate, code));
            }
            return new ResponseEntity<List<TransactionsDTO>>(resultMessages, HttpStatus.OK);

        }
        catch (ResourceNotFoundException err)
        {
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.NOT_FOUND);

        }
        catch (Exception err) {
            return new ResponseEntity<String>(err.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    private TransactionsDTO processSellMsg(double receiveAmt, double releaseAmt, double sellRate, String code){
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setTransaction_type("Sell");
        transactionsDTO.setCurrency_code(code);
        transactionsDTO.setTransaction_rate(sellRate);
        transactionsDTO.setReceive_amt(receiveAmt);
        transactionsDTO.setRelease_amt(releaseAmt);

        return transactionsDTO;
    }

    private TransactionsDTO processBuyMsg(double receiveAmt, double releaseAmt, double buyRate, String code){
        TransactionsDTO transactionsDTO = new TransactionsDTO();
        transactionsDTO.setTransaction_type("Sell");
        transactionsDTO.setCurrency_code(code);
        transactionsDTO.setTransaction_rate(buyRate);
        transactionsDTO.setReceive_amt(receiveAmt);
        transactionsDTO.setRelease_amt(releaseAmt);

        return transactionsDTO;
    }

    private double roundUpBySmallestNotes(double inputAmt, int smallestNote)
    {
        double result = Math.round(inputAmt/smallestNote)*smallestNote;

        return result;
    }

    private double roundTo1Decimal(double amt)
    {
        return Math.round(amt*10.0)/10.0;
    }



}

