package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by trot on 08.01.17.
 */

@Entity
@Table(name = "status")
//@JsonIdentityInfo(
//        generator = ObjectIdGenerators.PropertyGenerator.class,
//        property = "id")
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id")
    private Integer id;

    @NotEmpty
    @Column(length = 50, unique = true)
    private String name;

//    @JsonIgnore
//    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL)
//    private Set<BillsEntity> billsEntity;

    private String statusColour;

    public StatusEntity() {
    }

    public StatusEntity(String name) {
        this.name = name;
    }

//    public Set<BillsEntity> getBillsEntity() {
//        return billsEntity;
//    }

//    public void setBillsEntity(Set<BillsEntity> billsEntity) {
//        this.billsEntity = billsEntity;
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatusColour() {
        return statusColour;
    }

    public void setStatusColour(String statusColour) {
        this.statusColour = statusColour;
    }
}
