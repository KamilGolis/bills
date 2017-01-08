package pl.bills.services;

import pl.bills.entities.BillsEntity;
import pl.bills.models.BillsModel;

import java.util.List;

/**
 * Created by trot on 08.01.17.
 */
public interface IBillsService {

    public List<BillsModel> getBillsByTitle(String title);
}
