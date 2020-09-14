package com.example.tayqaprojectv1;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.text.DecimalFormat;

public class rates {

    @SerializedName("code")
    @Expose
    private String code = "USD";
    @SerializedName("alphaCode")
    @Expose
    private String alphaCode;
    @SerializedName("numericCode")
    @Expose
    private String numericCode;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("rate")
    @Expose
    private Double rate;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("inverseRate")
    @Expose
    private Double inverseRate;

    public rates(String code, String alphaCode, String numericCode, String name, Double rate, String date, Double inverseRate) {
        this.code = code;
        this.alphaCode = alphaCode;
        this.numericCode = numericCode;
        this.name = name;
        this.rate = rate;
        this.date = date;
        this.inverseRate = inverseRate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAlphaCode() {
        return alphaCode;
    }

    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getRate() {
        return Math.floor(rate * 100) / 100;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getInverseRate() {
        return inverseRate;
    }

    public void setInverseRate(Double inverseRate) {
        this.inverseRate = inverseRate;
    }

    public void changeText(String text) {
        code = text;
    }
}
