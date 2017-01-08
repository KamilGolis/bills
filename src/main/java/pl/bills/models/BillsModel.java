package pl.bills.models;

/**
 * Created by trot on 08.01.17.
 */

public class BillsModel {

    private Integer billId;
    private Double price;
    private String comment;
    private StatusModel status;
    private String category;
    private String title;

    public Integer getBillId() {
        return billId;
    }

    public void setBillId(Integer billId) {
        this.billId = billId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public StatusModel getStatus() {
        return status;
    }

    public void setStatus(StatusModel status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "BillsModel{" +
                "billId=" + billId +
                ", price=" + price +
                ", comment='" + comment + '\'' +
                ", status=" + status +
                ", category='" + category + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}