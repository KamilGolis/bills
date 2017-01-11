package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.dao.BillsDao;
import pl.bills.entities.BillsEntity;

import java.util.List;

/**
 * Created by trot on 09.01.17.
 */
@Service
public class BillService {

    @Autowired
    private BillsDao billsDao;

    public List<BillsEntity> getBillsByTitle(String title) {
        List<BillsEntity> billsEntityList = billsDao.findBillsByTitle(title);
        return billsEntityList;
    }

    public List<BillsEntity> getBillsByStatus(String status) {
        List<BillsEntity> billsEntityList = billsDao.findBillsByStatusName(status);
        return billsEntityList;
    }
}
