package pl.bills.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by trot on 09.01.17.
 */

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private Integer categoryId;
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<BillsEntity> billsEntity;

    public CategoryEntity() {
    }

    public CategoryEntity(String name) {
        this.name = name;
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
}
