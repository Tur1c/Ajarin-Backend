package co.id.ajarin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.auth.AuthenticationModel;

public interface AccountService extends UserDetailsService {
    
    AccountRegisterEntity save(AccountRegistrationModel account);

    AccountRegisterEntity update(AccountRegistrationModel account);

    String login(String email, String password);

    AccountRegistrationModel getAccountbyEmail(String email);

    AccountLoginModel findByEmail(String email);

    AuthenticationModel authenticated(String email);

    AccountRegistrationModel findById(Long id);

    String joinDiscussion(String email, Long id);

    String joinCourse(String email, Long id);

    AccountRegisterEntity store(String email, MultipartFile file) throws IOException;

    AccountRegisterEntity getFile(Long id);

    String registerTeacher(AccountRegistrationModel account, MultipartFile file, String achievement, String education, String experience, String description) throws IOException;

    List<TeacherModel.Teacher> getAllTeacher();

    Teacher getTeacherByUserId(Long id);

    TeacherEntity getTeacher(Long id);

    TeacherEntity getCvFile(Long id);

    String subscribedLecturer(Long id, String email);

    String unSubscribedLecturer(Long id, String email);
}
