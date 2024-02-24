package co.id.ajarin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.composite.StudentDiscKey;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.DiscussionRepository;
import co.id.ajarin.repository.StudentDiscRepository;
import co.id.ajarin.security.jwt.JwtService;
import co.id.ajarin.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRegistrationRepository repository;
    @Autowired
    DiscussionRepository discussionRepository;
    @Autowired
    StudentDiscRepository studentDiscRepository;

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
            "ROLE_Student",
            account.getGender(),
            account.getCity(),
            account.getCountry(),
            account.getSchool(),
            account.getAge(),
            account.getPhoneNumber(),
            account.getEducation(),
            account.getStudentdisc_list()
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

        System.out.println(account.getStudentdisc_list().get(0).getStatus());
        AccountRegistrationModel accounts = new AccountRegistrationModel(account);

        return accounts;
    }

    @Override
    public AccountRegistrationModel findById(Long id) {
        AccountRegisterEntity account = repository.getById(id);

        return new AccountRegistrationModel(account);
    }

    @Transactional
    @Override
    public String joinDiscussion(String email, Long disc_id) {

        AccountRegisterEntity account = repository.findByEmail(email);

        Long user_id = account.getId();

        DiscussionEntity disc =  discussionRepository.getById(disc_id);

        StudentDiscKey key = new StudentDiscKey(user_id, disc_id);
        
        StudentDiscEntity student_disc = new StudentDiscEntity(key, account,disc,"Ongoing");

        studentDiscRepository.save(student_disc);

        return "Joined Success";
        
        
    }

    
    
    
    
}
