package com.rwsentosa.moneychanger.entity.dto;

public class CurrencyCount {

    private String currencyCode;
    private Integer total;

    public CurrencyCount(String currencyCode, Integer total) {
        this.currencyCode = currencyCode;
        this.total = total;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
