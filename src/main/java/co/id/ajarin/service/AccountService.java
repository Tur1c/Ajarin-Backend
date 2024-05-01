package co.id.ajarin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.model.dashboard.PrivateDiscModel;
import co.id.ajarin.model.dashboard.StudentCourseModel;

public interface AccountService extends UserDetailsService {
    
    AccountRegisterEntity save(AccountRegistrationModel account);

    AccountRegisterEntity update(AccountModel account);

    String login(String email, String password);

    AccountModel getAccountbyEmail(String email);

    AccountLoginModel findByEmail(String email);

    AuthenticationModel authenticated(String email);

    AccountModel findById(Long id);

    String joinDiscussion(String email, Long id);

    String joinCourse(String email, Long id);

    AccountRegisterEntity store(String email, MultipartFile file) throws IOException;

    // AccountRegisterEntity getFile(Long id);

    String registerTeacher(AccountModel account, MultipartFile file, String achievement, String education, String experience, String description) throws IOException;

    List<TeacherModel.Teacher> getAllTeacher();

    Teacher getTeacherByUserEmail(String email);

    TeacherEntity getTeacher(Long id);

    TeacherEntity getCvFile(Long id);

    String subscribedLecturer(Long id, String email);

    String unSubscribedLecturer(Long id, String email);

    StudentCourseModel getStudentCourseData(Long userid, Long courseid);

    String addPrivateDisc(PrivateDiscModel disc,Long userid, Long teacherid);

    String acceptOrReject(Long privateid, String status, String subject);

    Teacher updateTeacher(Long id, MultipartFile file, String achievement, String education, String experience,
            String profileDescription) throws IOException;

    String deleteNotif(Long notif);

    String readNotif(Long notif);
}
