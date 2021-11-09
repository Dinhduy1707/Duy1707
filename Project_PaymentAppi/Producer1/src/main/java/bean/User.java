package bean;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private int id;
    private String apiID;
    private String tokenKey;
    private String bankCode;
    private String moblie;
    private String accountNo;
    private String payDate;
    private String addtionalData;
    private int debitAmount;
    private String respCode;
    private String respDesc;
    private String traceTransfer;
    private String messageType;
    private String checkSum;
    private String oderCode;
    private String userName;
    private String realAmount;
    private String promotionCode;
    private String addvalue;


    public User() {
    }


    public User(int id, String tokenKey, String apiID, String bankCode, String moblie, String accountNo, String payDate, String addtionalData, int debitAmount, String respCode, String respDesc, String traceTransfer, String messageType,
                String checkSum, String oderCode, String userName, String realAmount, String promotionCode, String addvalue) {
        this.tokenKey = tokenKey;
        this.id = id;
        this.apiID = apiID;
        this.bankCode = bankCode;
        this.moblie = moblie;
        this.accountNo = accountNo;
        this.payDate = payDate;
        this.addtionalData = addtionalData;
        this.debitAmount = debitAmount;
        this.respCode = respCode;
        this.respDesc = respDesc;
        this.traceTransfer = traceTransfer;
        this.messageType = messageType;
        this.checkSum = checkSum;
        this.oderCode = oderCode;
        this.userName = userName;
        this.realAmount = realAmount;
        this.promotionCode = promotionCode;
        this.addvalue = addvalue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTokenKey() {
        return tokenKey;
    }

    public void setTokenKey(String tokenKey) {
        this.tokenKey = tokenKey;
    }

    public String getApiID() {
        return apiID;
    }

    public void setApiID(String apiID) {
        this.apiID = apiID;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getMoblie() {
        return moblie;
    }

    public void setMoblie(String moblie) {
        this.moblie = moblie;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getPayDate() {
        return payDate;
    }

    public void setPayDate(String payDate) {
        this.payDate = payDate;
    }

    public String getAddtionalData() {
        return addtionalData;
    }

    public void setAddtionalData(String addtionalData) {
        this.addtionalData = addtionalData;
    }

    public int getDebitAmount() {
        return debitAmount;
    }

    public void setDebitAmount(int debitAmount) {
        this.debitAmount = debitAmount;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getTraceTransfer() {
        return traceTransfer;
    }

    public void setTraceTransfer(String traceTransfer) {
        this.traceTransfer = traceTransfer;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(String checkSum) {
        this.checkSum = checkSum;
    }

    public String getOderCode() {
        return oderCode;
    }

    public void setOderCode(String oderCode) {
        this.oderCode = oderCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealAmount() {
        return realAmount;
    }

    public void setRealAmount(String realAmount) {
        this.realAmount = realAmount;
    }

    public String getPromotionCode() {
        return promotionCode;
    }

    public void setPromotionCode(String promotionCode) {
        this.promotionCode = promotionCode;
    }

    public String getAddvalue() {
        return addvalue;
    }

    public void setAddvalue(String addvalue) {
        this.addvalue = addvalue;
    }

}
