package pl.bills.other;

import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Created by trot on 29.04.17.
 */
@Component
public class UserSafeCodeGenerator {

    private String safeCode;

    public String getNewCode() {
        safeCode = UUID.randomUUID().toString().substring(0,5);
        return safeCode;
    }

    public String getSafeCode() {
        return safeCode;
    }
}

