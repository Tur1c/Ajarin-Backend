package co.id.ajarin.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.security.jwt.JwtService;
import co.id.ajarin.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRegistrationRepository repository;

    private final JwtService jwtService;

    public AccountServiceImpl(AccountRegistrationRepository repository, JwtService jwtService) {
        super();
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @Override
    public AccountRegisterEntity save(AccountRegistrationModel account) {
        AccountRegisterEntity newAccount = new AccountRegisterEntity(
            account.getId(),
            account.getFirstName(),
            account.getLastName(),
            account.getEmail(),
            account.getPassword(),
            "Student",
            account.getGender(),
            account.getCity(),
            account.getCountry(),
            account.getSchool(),
            account.getAge(),
            account.getPhoneNumber(),
            account.getEducation()
        );

        return repository.save(newAccount);
    }

    @Override
    public String login(String email, String password) {
        AccountRegisterEntity account = repository.findByEmail(email);
        if(account != null) {
            Boolean isPasswordTrue = password.matches(account.getPassword());
            if(isPasswordTrue) {
                return "Login Success";
            }
        }
        return "Login Failed";
    }

    @Override
    public AccountLoginModel findByEmail(String email) {

        AccountRegisterEntity account = repository.findByEmail(email);
        
        if(account != null) {
            AccountLoginModel accountLogin = new AccountLoginModel(account);
            return accountLogin;
        }
        return null;
    }

    @Override
    public AuthenticationModel authenticated(String email) {
        AccountRegisterEntity account = repository.findByEmail(email);

        
        if(account != null) {
            AccountLoginModel accountLogin = new AccountLoginModel(account);
            String jwtToken = jwtService.generateToken(accountLogin);
            return AuthenticationModel.builder().token(jwtToken).build();
        }
        return null;
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AccountRegistrationModel getAccountbyEmail(String email) {
        // TODO Auto-generated method stub
        AccountRegisterEntity account = repository.findByEmail(email);

        System.out.println(account);
        AccountRegistrationModel accounts = new AccountRegistrationModel(account);

        return accounts;
    }
    
    
    
}
