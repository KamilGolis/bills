package pl.bills.entities;

import javax.persistence.*;

/**
 * Created by trot on 08.01.17.
 */

@Entity
@Table(name = "status")
public class StatusEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "status_id")
    private Integer id;
    private String name;

    @OneToOne(mappedBy = "status")
    private BillsEntity billsEntity;

    public BillsEntity getBillsEntity() {
        return billsEntity;
    }

    public void setBillsEntity(BillsEntity billsEntity) {
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
}
