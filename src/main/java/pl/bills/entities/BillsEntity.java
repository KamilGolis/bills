package pl.bills.entities;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.*;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bills")
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

    @Valid
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    @Valid
    private CategoryEntity category;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @Valid
    private StatusEntity status;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_holder_id")
    @Valid
    private LoanHolderEntity loanHolder;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public BillsEntity() {
    }

    public BillsEntity(String comment, BigDecimal price, String title, LocalDate date, CategoryEntity category, StatusEntity status, LoanHolderEntity loanHolder, UserEntity user) {
        this.comment = comment;
        this.price = price;
        this.title = title;
        this.date = date;
        this.category = category;
        this.status = status;
        this.loanHolder = loanHolder;
        this.user = user;
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

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return title + " - " + price;
    }
}
