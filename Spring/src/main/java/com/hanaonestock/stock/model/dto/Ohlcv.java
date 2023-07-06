package com.hanaonestock.stock.model.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Ohlcv {

    String isin;
    Date s_date;
    int oepn;
    int high;
    int low;
    int close;
    long volume;
    long amount;
    double updown;

    public String getIsin() {
        return isin;
    }

    public void setIsin(String isin) {
        this.isin = isin;
    }

    public Date getS_date() {
        return s_date;
    }

    public void setS_date(Date s_date) {
        this.s_date = s_date;
    }

    public int getOepn() {
        return oepn;
    }

    public void setOepn(int oepn) {
        this.oepn = oepn;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getClose() {
        return close;
    }

    public void setClose(int close) {
        this.close = close;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public double getUpdown() {
        return updown;
    }

    public void setUpdown(double updown) {
        this.updown = updown;
    }

}
