package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by trot on 09.01.17.
 */

@Entity
@Table(name = "loan_holders")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "loanHolderId",
        scope = LoanHolderEntity.class)
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
    @OneToMany(mappedBy = "loanHolder", fetch = FetchType.EAGER)
    private List<BillsEntity> billsEntity;

    public LoanHolderEntity() {
    }

    public LoanHolderEntity(String name, String address, String description, String bankAccountNumber, List<BillsEntity> billsEntity) {
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

    public List<BillsEntity> getBillsEntity() {
        return billsEntity;
    }

    public void setBillsEntity(List<BillsEntity> billsEntity) {
        this.billsEntity = billsEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final LoanHolderEntity that = (LoanHolderEntity) o;

        if (getLoanHolderId() != null ? !getLoanHolderId().equals(that.getLoanHolderId()) : that.getLoanHolderId() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        if (getBankAccountNumber() != null ? !getBankAccountNumber().equals(that.getBankAccountNumber()) : that.getBankAccountNumber() != null)
            return false;
        return getBillsEntity() != null ? getBillsEntity().equals(that.getBillsEntity()) : that.getBillsEntity() == null;
    }

    @Override
    public int hashCode() {
        int result = getLoanHolderId() != null ? getLoanHolderId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getBankAccountNumber() != null ? getBankAccountNumber().hashCode() : 0);
        result = 31 * result + (getBillsEntity() != null ? getBillsEntity().hashCode() : 0);
        return result;
    }
}
