package com.rwsentosa.moneychanger.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "transactions")
public class Transactions {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Id
    protected Long id;

    @Nationalized
    @Column(name = "created_by", nullable = false)
    protected String created_by;

    @CreationTimestamp
    @Column(name = "created_time", nullable = false)
    protected OffsetDateTime created_time;

    @Nationalized
    @Column(name = "currency_code", nullable = false, length = 10)
    private String currency_code;

    @Nationalized
    @Column(name = "transaction_type", nullable = false, length = 50)
    private String transaction_type;

    @Nationalized
    @Column(name = "transaction_rate", nullable = false, length = 50)
    private Double transaction_rate;

    @Nationalized
    @Column(name = "receive_amt", nullable = false, length = 50)
    private Double receive_amt;

    @Nationalized
    @Column(name = "release_amt", nullable = false, length = 50)
    private Double release_amt;

    public String getCreated_by() { return created_by;}

    public OffsetDateTime getCreated_time() {return created_time;}

    public String getCurrency_code(){
        return currency_code;
    }

    public String getTransaction_type(){
        return transaction_type;
    }

    public Double getTransaction_rate(){
        return transaction_rate;
    }

    public Double getReceive_amt(){
        return receive_amt;
    }

    public Double getRelease_amt(){
        return release_amt;
    }

    public void setCreated_by(String created_by) { this.created_by = created_by;}

    public void setCreated_time(OffsetDateTime created_time) { this.created_time=created_time;}

    public void setCurrency_code(String currency_code){this.currency_code=currency_code;}

    public void setTransaction_type(String transaction_type){this.transaction_type=transaction_type;}

    public void setTransaction_rate(Double transaction_rate){this.transaction_rate=transaction_rate;}

    public void setReceive_amt(Double receive_amt){this.receive_amt=receive_amt;}

    public void setRelease_amt(Double release_amt){this.release_amt=release_amt;}

}
