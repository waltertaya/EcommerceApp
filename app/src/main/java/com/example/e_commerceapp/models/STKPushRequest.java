package com.example.e_commerceapp.models;

import com.google.gson.annotations.SerializedName;

public class STKPushRequest {
    @SerializedName("BusinessShortCode")
    private String businessShortCode;

    @SerializedName("Password")
    private String password;

    @SerializedName("Timestamp")
    private String timestamp;

    @SerializedName("TransactionType")
    private String transactionType;

    @SerializedName("Amount")
    private String amount;

    @SerializedName("PartyA")
    private String partyA;

    @SerializedName("PartyB")
    private String partyB;

    @SerializedName("PhoneNumber")
    private String phoneNumber;

    @SerializedName("CallBackURL")
    private String callbackUrl;

    @SerializedName("AccountReference")
    private String accountReference;

    @SerializedName("TransactionDesc")
    private String transactionDesc;

    public STKPushRequest(String businessShortCode, String password, String timestamp, String transactionType,
                          String amount, String partyA, String partyB, String phoneNumber, String callbackUrl,
                          String accountReference, String transactionDesc) {
        this.businessShortCode = businessShortCode;
        this.password = password;
        this.timestamp = timestamp;
        this.transactionType = transactionType;
        this.amount = amount;
        this.partyA = partyA;
        this.partyB = partyB;
        this.phoneNumber = phoneNumber;
        this.callbackUrl = callbackUrl;
        this.accountReference = accountReference;
        this.transactionDesc = transactionDesc;
    }
}
