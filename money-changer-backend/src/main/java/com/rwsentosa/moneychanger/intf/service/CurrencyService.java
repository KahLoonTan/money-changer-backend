package com.rwsentosa.moneychanger.intf.service;

import com.rwsentosa.moneychanger.entity.Currency;

import java.util.List;

public interface CurrencyService {

    List<Currency> findAll();
    Currency findByCode(String code);



}
