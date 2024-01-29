package co.id.ajarin.service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.model.account.AccountRegistrationModel;

public interface AccountService {
    
    AccountRegisterEntity save(AccountRegistrationModel account);

    String login(String email, String password);

    Boolean findByEmail(String email);
}
