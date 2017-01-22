package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.StatusEntity;
import pl.bills.repository.StatusRepository;

import java.util.Collection;

/**
 * Created by trot on 22.01.17.
 */
@Service
public class StatusService {

    @Autowired
    StatusRepository statusRepository;

    public Collection<StatusEntity> getAllStatuses() {
        return statusRepository.findAll();
    }
}
