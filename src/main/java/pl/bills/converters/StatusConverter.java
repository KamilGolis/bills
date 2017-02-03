package pl.bills.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.Converter;
import pl.bills.entities.StatusEntity;
import pl.bills.repository.StatusRepository;

/**
 * Created by trot on 03.02.17.
 */
public class StatusConverter implements Converter<String, StatusEntity> {

    @Autowired
    StatusRepository statusRepository;

    @Override
    public StatusEntity convert(String source) {
        StatusEntity status = statusRepository.findByName(source);
        if (status != null) {
            return status;
        } else {
            throw new ConversionFailedException(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(StatusEntity.class), source, null);
        }
    }
}
