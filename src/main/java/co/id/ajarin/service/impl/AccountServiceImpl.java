package co.id.ajarin.service.impl;

import org.springframework.stereotype.Service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRegistrationRepository repository;

    public AccountServiceImpl(AccountRegistrationRepository repository) {
        super();
        this.repository = repository;
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
    public Boolean findByEmail(String email) {
        AccountRegisterEntity account = repository.findByEmail(email);

        if(account != null) {
            return true;
        }
        return false;
    }

    

    
    
    
}
