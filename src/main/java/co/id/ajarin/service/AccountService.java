package co.id.ajarin.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.auth.AuthenticationModel;

public interface AccountService extends UserDetailsService {
    
    AccountRegisterEntity save(AccountRegistrationModel account);

    String login(String email, String password);

    AccountRegistrationModel getAccountbyEmail(String email);

    AccountLoginModel findByEmail(String email);

    AuthenticationModel authenticated(String email);

    AccountRegistrationModel findById(Long id);
}
