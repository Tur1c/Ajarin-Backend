package co.id.ajarin.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.entity.composite.StudentDiscKey;
import co.id.ajarin.mapper.TeacherMapper;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.DiscussionRepository;
import co.id.ajarin.repository.StudentDiscRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.security.jwt.JwtService;
import co.id.ajarin.service.AccountService;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRegistrationRepository repository;
    @Autowired
    TeacherRepository repositoryTeacher;
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
            account.getStudentdisc_list(),
            0,
            null,
            null,
            null
        );

        return repository.save(newAccount);
    }

    @Override
    public AccountRegisterEntity update(AccountRegistrationModel account) {
        AccountRegisterEntity accountOld = repository.getById(account.getId());
        accountOld.setId(account.getId());
        accountOld.setFirstName(account.getFirstName());
        accountOld.setLastName(account.getLastName());
        accountOld.setEmail(account.getEmail());
        accountOld.setPassword(account.getPassword());
        accountOld.setRole(account.getRole());
        accountOld.setGender(account.getGender());
        accountOld.setCity(account.getCity());
        accountOld.setCountry(account.getCountry());
        accountOld.setSchool(account.getSchool());
        accountOld.setAge(account.getAge());
        accountOld.setPhoneNumber(account.getPhoneNumber());
        accountOld.setEducation(account.getEducation());
        accountOld.setStudentdisc_list(account.getStudentdisc_list());
        accountOld.setCoin(account.getCoin());
        accountOld.setPic_name(account.getPic_name());
        accountOld.setPic_type(account.getPic_type());

        return repository.save(accountOld);
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

    @Override
    public AccountRegisterEntity store(String email, MultipartFile file) throws IOException {
       String filename = StringUtils.cleanPath(file.getOriginalFilename());
        AccountRegisterEntity account = repository.findByEmail(email);
        account.setData(file.getBytes());
        account.setPic_name(filename);
        account.setPic_type(file.getContentType());

        return repository.save(account);
    }

    @Override
    public AccountRegisterEntity getFile(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public String registerTeacher(AccountRegistrationModel account, MultipartFile file, String achievement,
            String education, String experience, String description) throws IOException {
        
        AccountRegisterEntity accountEntity = repository.findByEmail(account.getEmail());

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        TeacherEntity teacher = new TeacherEntity();
        teacher.setAchievement(achievement);
        teacher.setUser(accountEntity);
        teacher.setData(file.getBytes());
        teacher.setProfile_description(description);
        teacher.setEducation(education);
        teacher.setExperience(experience);
        teacher.setRating("0");
        repositoryTeacher.save(teacher);

        return "Success";
    }

    @Override
    public List<TeacherModel.Teacher> getAllTeacher() {
        List<TeacherEntity> teachers = repositoryTeacher.findAll();
        return teachers.stream().map((teacher) -> TeacherMapper.mapToTeacherModel(teacher)).collect(Collectors.toList());
    }

    @Override
    public TeacherEntity getTeacher(Long id) {
       return repositoryTeacher.getReferenceById(id);
    }

    @Override
    public TeacherEntity getCvFile(Long id) {
        return repositoryTeacher.findById(id).get();
    }
    
    

    
    
    
    
}
