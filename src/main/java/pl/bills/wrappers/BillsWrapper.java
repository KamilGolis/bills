package pl.bills.wrappers;

import pl.bills.entities.BillsEntity;

import java.util.List;

/**
 * Created by trot on 09.01.17.
 */
public class BillsWrapper {

    private BillsEntity billsEntity;
    private ShortBill shortBill;

    public BillsWrapper(BillsEntity billsEntity) {
        this.billsEntity = billsEntity;
    }

    public ShortBill convertEntityToShortModel(BillsEntity billsEntity) {
        ShortBill shortBill = new ShortBill();
        if (billsEntity != null) {
            shortBill.setTitle(billsEntity.getTitle());
            shortBill.setComment(billsEntity.getComment());
            shortBill.setPrice(billsEntity.getPrice());
            shortBill.setStatus(billsEntity.getStatus().getName());
        }
        return shortBill;
    }

    public BillsEntity convertFromShortModelToEntity(ShortBill shortBill) {
        return new BillsEntity();
    }

    public ShortBill getShortBill() {
        return shortBill;
    }

    public void setShortBill(ShortBill shortBill) {
        this.shortBill = shortBill;
    }
}
