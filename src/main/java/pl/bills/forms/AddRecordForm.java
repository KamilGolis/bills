package pl.bills.forms;

import java.math.BigDecimal;

/**
 * Created by trot on 22.01.17.
 */
public class AddRecordForm {

    private String title;
    private String comment;
    private BigDecimal price;
    private String status;

    public AddRecordForm(String title, BigDecimal price, String status, String comment) {
        this.title = title;
        this.comment = comment;
        this.price = price;
        this.status = status;
    }

    public AddRecordForm() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getComment() {
        return comment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }
}
