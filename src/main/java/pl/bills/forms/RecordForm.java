package pl.bills.forms;

import java.math.BigDecimal;

/**
 * Created by trot on 22.01.17.
 */
public class RecordForm {

    private Integer id;
    private String title;
    private String comment;
    private String price;
    private String status;
    private String statusColour;
    private String categoryIcon;
    private String category;

    public RecordForm(String title, String price, String status, String comment) {
        this.title = title;
        this.comment = comment;
        this.price = price;
        this.status = status;
    }

    public RecordForm() {
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPrice(String price) {
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

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatusColour() {
        return statusColour;
    }

    public void setStatusColour(String statusColour) {
        this.statusColour = statusColour;
    }

    public String getCategoryIcon() {
        return categoryIcon;
    }

    public void setCategoryIcon(String categoryIcon) {
        this.categoryIcon = categoryIcon;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
