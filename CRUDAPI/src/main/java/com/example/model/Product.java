package com.example.model;

import com.example.valida.DuyValidate;
import lombok.*;
import org.springframework.stereotype.Component;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;



@Getter
@Setter
@Data
@Entity
@Table(name = "user")
@NotNull


public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  Long id;

    @DuyValidate
    private String tokenKey;
    @DuyValidate
    private String apiID;
    @DuyValidate
    private String mobie;
    @DuyValidate
    private String bankCode;
    @DuyValidate
    private String accountNo;
    @DuyValidate
    private String payDate;
    @DuyValidate
    private  String addtionalData;

    private int debitAmount;
    @DuyValidate
    private String respCode;
    @DuyValidate
    private String respDesc;
    @DuyValidate
    private String traceTransfer;
    @DuyValidate
    private String messageType;
    @DuyValidate
    private String checkSum;
    @DuyValidate
    private String oderCode;
    @DuyValidate
    private String userName;
    @DuyValidate
    private String realAmount;
    @DuyValidate
    private String promotionCode;
    @DuyValidate
    private String addValue;

    public Product() {
    }
}
