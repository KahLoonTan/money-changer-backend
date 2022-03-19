package com.rwsentosa.moneychanger.entity;

import com.rwsentosa.moneychanger.constant.Constant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "currency")
@Where(clause = "record_status = " + Constant.RECORD_STATUS_ACTIVE)
public class Currency extends BaseEntity {

    @Nationalized
    @Column(name = "code", nullable = false, length = 10)
    private String code;

    @Nationalized
    @Column(name = "description", nullable = false, length = 50)
    private String description;

    @Nationalized
    @Column(name = "buy_rate", nullable = false, length = 50)
    private Double buy_rate;

    @Nationalized
    @Column(name = "sell_rate", nullable = false, length = 50)
    private Double sell_rate;

    @Nationalized
    @Column(name = "smallest_note", nullable = false, length = 50)
    private Integer smallest_note;


    public String getCode(){
        return code;
    }

    public String getDescription(){
        return description;
    }

    public Double getBuy_rate(){
        return buy_rate;
    }

    public Double getSell_rate(){
        return sell_rate;
    }

}
