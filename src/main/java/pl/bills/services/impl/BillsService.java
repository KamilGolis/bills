package pl.bills.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.dao.BillsDao;
import pl.bills.entities.BillsEntity;
import pl.bills.models.BillsModel;
import pl.bills.models.StatusModel;
import pl.bills.services.IBillsService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trot on 08.01.17.
 */

@Service
public class BillsService implements IBillsService {

    @Autowired
    private BillsDao billsDao;

    @Override
    public List<BillsModel> getBillsByTitle(String title) {

        BillsModel billsModel = new BillsModel();

        List<BillsModel> bl = new ArrayList<>();
        List<BillsEntity> bills = billsDao.findBillsByTitle(title);
        for (BillsEntity b : bills) {
            billsModel.setCategory(b.getCategory());
            billsModel.setComment(b.getComment());
            billsModel.setPrice(b.getPrice());
            billsModel.setBillId(b.getId());
            StatusModel statusModel = new StatusModel();
            statusModel.setName(b.getStatus().getName());
            billsModel.setStatus(statusModel);
            bl.add(billsModel);
        }


        return bl;
    }
}
