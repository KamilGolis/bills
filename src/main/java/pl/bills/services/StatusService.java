package pl.bills.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.bills.entities.StatusEntity;
import pl.bills.repository.StatusRepository;

import java.util.Collection;
import java.util.Optional;

/**
 * Created by trot on 22.01.17.
 */
@Service
public class StatusService {

    private StatusRepository statusRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Optional<Collection<StatusEntity>> getAllStatuses() {
        return Optional.ofNullable(statusRepository.findAll());
    }

    public Optional<StatusEntity> getStatus(String name) {
        return statusRepository.findByName(name);
    }
}
