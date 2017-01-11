package pl.bills.dao;

import org.springframework.data.repository.CrudRepository;
import pl.bills.entities.StatusEntity;

import java.util.List;

/**
 * Created by trot on 09.01.17.
 */
public interface StatusDao extends CrudRepository<StatusEntity, Integer> {


}
