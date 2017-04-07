package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.Valid;
import java.util.Set;

/**
 * Created by trot on 09.01.17.
 */

@Entity
@Table(name = "loan_holders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "loanHolderId")
public class LoanHolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "loan_holder_id")
    private Integer loanHolderId;

    @Column(unique = true, length = 50)
    @NotEmpty
    private String name;

    @Column(length = 200)
    private String address;

    @Column(length = 200)
    private String description;

    @Column(length = 26)
    private String bankAccountNumber;

    @JsonIgnore
    @OneToMany(mappedBy = "loanHolder")
    private Set<BillsEntity> billsEntity;

    public LoanHolderEntity() {
    }

    public LoanHolderEntity(String name, String address, String description, String bankAccountNumber, Set<BillsEntity> billsEntity) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.bankAccountNumber = bankAccountNumber;
        this.billsEntity = billsEntity;
    }

    public LoanHolderEntity(String name, String bankAccountNumber) {
        this.name = name;
        this.bankAccountNumber = bankAccountNumber;
    }

    public Integer getLoanHolderId() {
        return loanHolderId;
    }

    public void setLoanHolderId(Integer loanHolderId) {
        this.loanHolderId = loanHolderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public Set<BillsEntity> getBillsEntity() {
        return billsEntity;
    }

    public void setBillsEntity(Set<BillsEntity> billsEntity) {
        this.billsEntity = billsEntity;
    }
}
