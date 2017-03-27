package pl.bills.entities;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import javax.persistence.Column;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDate;

public class BillDTO {

    @NotBlank
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

    @Valid
    private CategoryEntity category;

    @Valid
    private StatusEntity status;

    @Valid
    private LoanHolderEntity loanHolder;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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

    public LoanHolderEntity getLoanHolder() {
        return loanHolder;
    }

    public void setLoanHolder(LoanHolderEntity loanHolder) {
        this.loanHolder = loanHolder;
    }
}
