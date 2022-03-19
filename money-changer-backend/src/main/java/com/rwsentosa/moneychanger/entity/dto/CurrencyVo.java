package com.rwsentosa.moneychanger.entity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CurrencyVo {

    private String code;
    private String description;
    private Double buyrate;
    private Double sellrate;
    private Integer smallestnotes;


/*    public CurrencyVo(String code, String description, Double buyrate, Double sellrate){
        this.code = code;
        this.description = description;
        this.buyrate = buyrate;
        this.sellrate = sellrate;
    }*/

    public void setCode(String code){
        this.code = code;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public void setBuyrate(Double buyrate){
        this.buyrate = buyrate;
    }
    public void setSellrate(Double sellrate){
        this.sellrate = sellrate;
    }
    public void setSmallestnotes(Integer smallestnotes){
        this.smallestnotes= smallestnotes;
    }


}
