package com.mycompany.stock_managment.model;

import java.util.Date;

public class SalesByDay {
    private Date saleDate;
    private int totalSold;
    private double totalDebt; // Dette totale
    private double totalPayment; // Paiement total

    public SalesByDay(Date saleDate, int totalSold, double totalDebt, double totalPayment) {
        this.saleDate = saleDate;
        this.totalSold = totalSold;
        this.totalDebt = totalDebt;
        this.totalPayment = totalPayment;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public int getTotalSold() {
        return totalSold;
    }

    public void setTotalSold(int totalSold) {
        this.totalSold = totalSold;
    }

    public double getTotalDebt() {
        return totalDebt;
    }

    public void setTotalDebt(double totalDebt) {
        this.totalDebt = totalDebt;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }
}
