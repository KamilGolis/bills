package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by trot on 09.01.17.
 */

@Entity
@Table(name = "category")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "categoryId")
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
    @OneToMany(mappedBy = "category")
    private Set<BillsEntity> billsEntity;

    public CategoryEntity() {
    }

    public CategoryEntity(String name, String icon, Set<BillsEntity> billsEntity) {
        this.name = name;
        this.icon = icon;
        this.billsEntity = billsEntity;
    }

    public Set<BillsEntity> getBillsEntity() {
        return billsEntity;
    }

    public void setBillsEntity(Set<BillsEntity> billsEntity) {
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
}
