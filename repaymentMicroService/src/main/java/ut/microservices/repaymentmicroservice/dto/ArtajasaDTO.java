package ut.microservices.repaymentmicroservice.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ArtajasaDTO {
    private String type;
    private String bookingid;
    private String customer_name;
    private String issuer_bank;
    private String issuer_name;
    private Double amount;
    private String productid;
    private String trxid;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date trx_date;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date notification_datetime;
    private String vaid;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date booking_datetime;
    private Integer reference_number;
    private String username;
    private String signature;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVaid() {
        return vaid;
    }

    public void setVaid(String vaid) {
        this.vaid = vaid;
    }

    public Date getBooking_datetime() {
        return booking_datetime;
    }

    public void setBooking_datetime(Date booking_datetime) {
        this.booking_datetime = booking_datetime;
    }

    public Integer getReference_number() {
        return reference_number;
    }

    public void setReference_number(Integer reference_number) {
        this.reference_number = reference_number;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getBookingid() {
        return bookingid;
    }

    public void setBookingid(String bookingid) {
        this.bookingid = bookingid;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getIssuer_bank() {
        return issuer_bank;
    }

    public void setIssuer_bank(String issuer_bank) {
        this.issuer_bank = issuer_bank;
    }

    public String getIssuer_name() {
        return issuer_name;
    }

    public void setIssuer_name(String issuer_name) {
        this.issuer_name = issuer_name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getTrxid() {
        return trxid;
    }

    public void setTrxid(String trxid) {
        this.trxid = trxid;
    }

    public Date getTrx_date() {
        return trx_date;
    }

    public void setTrx_date(Date trx_date) {
        this.trx_date = trx_date;
    }

    public Date getNotification_datetime() {
        return notification_datetime;
    }

    public void setNotification_datetime(Date notification_datetime) {
        this.notification_datetime = notification_datetime;
    }
    
}
