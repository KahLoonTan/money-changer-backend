package com.rwsentosa.moneychanger.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString
public class TransactionsDTO {


    private String currency_code;
    private String transaction_type;
    private Double transaction_rate;
    private Double receive_amt;
    private Double release_amt;
    protected String created_by;
    protected OffsetDateTime created_time;




    public void setCurrency_code(String currency_code){
        this.currency_code = currency_code;
    }

    public void setTransaction_type(String transaction_type){
        this.transaction_type = transaction_type;
    }

    public void setTransaction_rate(Double transaction_rate){
        this.transaction_rate = transaction_rate;
    }
    public void setRelease_amt(Double release_amt){
        this.release_amt = release_amt;
    }
    public void setReceive_amt(Double receive_amt){
        this.receive_amt = receive_amt;
    }

}
