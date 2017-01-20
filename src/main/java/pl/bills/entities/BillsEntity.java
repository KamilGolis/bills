package pl.bills.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

/**
 * Created by trot on 08.01.17.
 */
@Entity
@Table(name = "bills")
public class BillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(length = 200)
    private String comment;
    private BigDecimal price;
    @Column(length = 50)
    private String title;
    @DateTimeFormat
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_holder_id")
    private LoanHolderEntity loanHolder;

    public BillsEntity() {
    }

    public BillsEntity(String title, CategoryEntity category, StatusEntity status, LoanHolderEntity loanHolder) {
        this.title = title;
        this.category = category;
        this.status = status;
        this.loanHolder = loanHolder;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LoanHolderEntity getLoanHolder() {
        return loanHolder;
    }

    public void setLoanHolder(LoanHolderEntity loanHolder) {
        this.loanHolder = loanHolder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public CategoryEntity getCategory() {
        return category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }


    public StatusEntity getStatus() {
        return status;
    }

    public void setStatus(StatusEntity status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BillsEntity{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", price=" + price +
                ", title='" + title + '\'' +
                ", category='" + category.getName() + '\'' +
                ", status=" + status.getName() +
                ", loanHolder=" + loanHolder.getName() +
                '}';
    }
}
