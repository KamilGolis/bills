package pl.bills.dto;

import java.math.BigDecimal;

/**
 * Created by trot on 09.04.17.
 */
public class CountingDataDTO {

    private BigDecimal biggestBillPrice;
    private BigDecimal totalBillsPrice;
    private String mostFrequentBillTitle;

    public CountingDataDTO() {
        super();
    }

    public CountingDataDTO(BigDecimal biggestBillPrice, BigDecimal totalBillsPrice, String mostFrequentBillTitle) {
        this.biggestBillPrice = biggestBillPrice;
        this.totalBillsPrice = totalBillsPrice;
        this.mostFrequentBillTitle = mostFrequentBillTitle;
    }

    public BigDecimal getBiggestBillPrice() {
        return biggestBillPrice;
    }

    public void setBiggestBillPrice(BigDecimal biggestBillPrice) {
        this.biggestBillPrice = biggestBillPrice;
    }

    public BigDecimal getTotalBillsPrice() {
        return totalBillsPrice;
    }

    public void setTotalBillsPrice(BigDecimal totalBillsPrice) {
        this.totalBillsPrice = totalBillsPrice;
    }

    public String getMostFrequentBillTitle() {
        return mostFrequentBillTitle;
    }

    public void setMostFrequentBillTitle(String mostFrequentBillTitle) {
        this.mostFrequentBillTitle = mostFrequentBillTitle;
    }
}
