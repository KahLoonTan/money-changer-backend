package com.rwsentosa.moneychanger.service;

import com.rwsentosa.moneychanger.entity.Currency;
import com.rwsentosa.moneychanger.intf.service.CurrencyService;
import com.rwsentosa.moneychanger.repository.CurrencyRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> findAll() {
        List<Currency> emptyList = currencyRepository.findAll();
        if(emptyList.isEmpty())
            throw new IllegalStateException("List is empty");
        return emptyList;
    }

    public Currency findByCode(String code) {

        if(code.isBlank())
            throw new ResourceNotFoundException("Input Code is missing");
        code = code.toUpperCase();

        Optional<Currency> result = currencyRepository.findByCode(code);
        if (result.isPresent()){
            return result.get();
        }else{
            throw new ResourceNotFoundException("Currency", "Code not found: " + code);
        }
    }


}
