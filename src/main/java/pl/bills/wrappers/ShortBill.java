package pl.bills.wrappers;

/**
 * Created by trot on 10.01.17.
 */

import java.math.BigDecimal;

/** Represents a short model of entity for mapping forms data from POST to entity.
 * use methods of conversion on Wrapper Object.
 */
public class ShortBill {

    private String title;
    private String status;
    private String comment;
    private BigDecimal price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
