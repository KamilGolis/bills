package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Created by trot on 08.01.17.
 */
@Entity
@Table(name = "bills")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class BillsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 200)
    private String comment;

    @NumberFormat(style = NumberFormat.Style.CURRENCY)
    private BigDecimal price;

    @Column(length = 50)
    private String title;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @ManyToOne //(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;

    @ManyToOne  //(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "status_id")
    private StatusEntity status;

    @ManyToOne //(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "loan_holder_id")
    private LoanHolderEntity loanHolder;

    public BillsEntity() {
    }

    public BillsEntity(String comment, BigDecimal price, String title, LocalDate date, CategoryEntity category, StatusEntity status, LoanHolderEntity loanHolder) {
        this.comment = comment;
        this.price = price;
        this.title = title;
        this.date = date;
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
        return title + " - " + price;
    }
}
