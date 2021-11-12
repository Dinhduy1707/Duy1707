package com.example.model;
import com.example.valida.NotBlank;
import lombok.*;
import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name = "user")
public class User  implements  Serializable {
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank
    private String tokenKey;
    @NotBlank
    private String apiID;
    @NotBlank
    private String mobile;
    @NotBlank
    private String bankCode;
    @NotBlank
    private String accountNo;
    @NotBlank
    private String payDate;
    @NotBlank
    private String addtionalData;

    private int debitAmount;
    @NotBlank
    private String respCode;
    @NotBlank
    private String respDesc;
    @NotBlank
    private String traceTransfer;
    @NotBlank
    private String messageType;
    @NotBlank
    private String checkSum;
    @NotBlank
    private String oderCode;
    @NotBlank
    private String userName;
    @NotBlank
    private String realAmount;
    @NotBlank
    private String promotionCode;
    @NotBlank
    private String addValue;

    public User() {
    }
}
