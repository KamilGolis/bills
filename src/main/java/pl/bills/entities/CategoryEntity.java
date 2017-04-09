package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by trot on 09.01.17.
 */

@Entity
@Table(name = "category")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "categoryId",
        scope = CategoryEntity.class)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Integer categoryId;
    @NotNull
    @Column(unique = true)
    private String name;

    private String icon;

    @JsonIgnore
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<BillsEntity> billsEntity;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, String icon, List<BillsEntity> billsEntity) {
        this.name = name;
        this.icon = icon;
        this.billsEntity = billsEntity;
    }

    public List<BillsEntity> getBillsEntity() {
        return billsEntity;
    }

    public void setBillsEntity(List<BillsEntity> billsEntity) {
        this.billsEntity = billsEntity;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CategoryEntity that = (CategoryEntity) o;

        if (getCategoryId() != null ? !getCategoryId().equals(that.getCategoryId()) : that.getCategoryId() != null)
            return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getIcon() != null ? !getIcon().equals(that.getIcon()) : that.getIcon() != null) return false;
        return getBillsEntity() != null ? getBillsEntity().equals(that.getBillsEntity()) : that.getBillsEntity() == null;
    }

    @Override
    public int hashCode() {
        int result = getCategoryId() != null ? getCategoryId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getIcon() != null ? getIcon().hashCode() : 0);
        result = 31 * result + (getBillsEntity() != null ? getBillsEntity().hashCode() : 0);
        return result;
    }
}
