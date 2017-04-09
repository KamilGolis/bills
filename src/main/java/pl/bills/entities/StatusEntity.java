package pl.bills.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by trot on 08.01.17.
 */

@Entity
@Table(name = "status")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id",
        scope = StatusEntity.class)
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id")
    private Integer id;

    @NotEmpty
    @Column(length = 50, unique = true)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "status", fetch = FetchType.EAGER)
    private List<BillsEntity> billsEntity;

    private String statusColour;

    public StatusEntity() {
    }

    public StatusEntity(String name, List<BillsEntity> billsEntity, String statusColour) {
        this.name = name;
        this.billsEntity = billsEntity;
        this.statusColour = statusColour;
    }

    public List<BillsEntity> getBillsEntity() {
        return billsEntity;
    }

    public void setBillsEntity(List<BillsEntity> billsEntity) {
        this.billsEntity = billsEntity;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final StatusEntity that = (StatusEntity) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getBillsEntity() != null ? !getBillsEntity().equals(that.getBillsEntity()) : that.getBillsEntity() != null)
            return false;
        return getStatusColour() != null ? getStatusColour().equals(that.getStatusColour()) : that.getStatusColour() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getBillsEntity() != null ? getBillsEntity().hashCode() : 0);
        result = 31 * result + (getStatusColour() != null ? getStatusColour().hashCode() : 0);
        return result;
    }
}
