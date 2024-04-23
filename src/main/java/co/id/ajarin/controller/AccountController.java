package co.id.ajarin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.model.dashboard.JoinDiscCourseModel;
import co.id.ajarin.model.dashboard.PrivateDiscModel;
import co.id.ajarin.model.dashboard.StudentCourseModel;
import co.id.ajarin.service.AccountService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/account")
public class AccountController {
    
    private AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping("register")
    public ResponseEntity<ResponseWrapperModel<AccountRegistrationModel>> registerAccount(@RequestBody AccountRegistrationModel account) {
        ResponseWrapperModel<AccountRegistrationModel> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        
        if(service.findByEmail(account.getEmail()) != null) {
            error.setMessage("Failed. Email has been taken!");
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } else {
            service.save(account);
        }
        
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PostMapping("login")
    public ResponseEntity<ResponseWrapperModel<AuthenticationModel>> loginAccount(@RequestBody AccountLoginModel account) {
        String message = service.login(account.getEmail(), account.getPassword());

        ResponseWrapperModel<AuthenticationModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        
        if(message != "Login Success") {
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setMessage(message);
            wrapperModel.setErrorSchema(error);
        } else {
            // System.out.println(service.authenticated(account.getEmail()));
            wrapperModel.setOutputSchema(service.authenticated(account.getEmail()));
        }

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @GetMapping("check-email/{email}")
    public ResponseEntity<ResponseWrapperModel> checkEmail(@PathVariable(name = "email") String email) {
        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        
        if(service.findByEmail(email) != null) {
            error.setMessage("Failed. Email has been taken!");
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        } 
        
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @GetMapping("change-role")
    public ResponseEntity<ResponseWrapperModel<AuthenticationModel>> changeRoleAccount(@RequestParam(name = "email") String email) {

        AccountModel account = service.getAccountbyEmail(email);
        System.out.println(account.getRole());
        if(account.getRole().equals("ROLE_Student")) {
            account.setRole("ROLE_Teacher");
        } else {
            account.setRole("ROLE_Student");
        }
        service.update(account);

        ResponseWrapperModel<AuthenticationModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
    
            wrapperModel.setOutputSchema(service.authenticated(account.getEmail()));

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<AccountModel>> getAccountbyEmail(@RequestParam(name = "email") String email){
        AccountModel account = service.getAccountbyEmail(email);

        ResponseWrapperModel<AccountModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(account);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PutMapping("{id}")
    public ResponseEntity<ResponseWrapperModel<AccountModel>> updateAccount(@PathVariable Long id, @RequestBody AccountRegistrationModel account){

        AccountModel accountOld = service.findById(id);

        accountOld.setFirstName(account.getFirstName());
        accountOld.setAge(account.getAge());
        accountOld.setCity(account.getCity());
        accountOld.setCountry(account.getCountry());
        accountOld.setEducation(account.getEducation());
        accountOld.setEmail(account.getEmail());
        accountOld.setGender(accountOld.getGender());
        accountOld.setLastName(account.getLastName());
        accountOld.setPhoneNumber(account.getPhoneNumber());
        accountOld.setSchool(account.getSchool());
        // accountOld.setStudentdisc_list(account.getStudentdisc_list());
        // accountOld.setStudentcourse_list(account.getStudentcourse_list());
        accountOld.setCoin(account.getCoin());

        service.update(accountOld);

        ResponseWrapperModel<AccountModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(accountOld);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

     @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PutMapping("/teacher/update/{id}")
    public ResponseEntity<ResponseWrapperModel<Teacher>> updateAccountTeacher(@PathVariable Long id,
                @RequestParam(name = "file", required = false) MultipartFile file, 
                @RequestParam(name = "achievement", required = false) String achievement, @RequestParam(name = "education", required = false) String education,
                @RequestParam(name = "experience" , required = false) String experience, @RequestParam(name = "profile_description", required = false) String profileDescription) throws IOException
    {

        Teacher teacher = service.updateTeacher(id, file, achievement, education, experience, profileDescription);

        ResponseWrapperModel<Teacher> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(teacher);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

     @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PutMapping("{id}/{coin}")
    public ResponseEntity<ResponseWrapperModel<AccountModel>> updateAccountWithWithdrawCoin(@PathVariable Long id, @PathVariable("coin") String coin){

        AccountModel accountOld = service.findById(id);

        accountOld.setCoin(accountOld.getCoin() - Integer.parseInt(coin));

        service.update(accountOld);

        ResponseWrapperModel<AccountModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(accountOld);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

     @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
     @PostMapping("join")
     public ResponseEntity<ResponseWrapperModel<JoinDiscCourseModel>> createJoinDiscussion(@RequestBody JoinDiscCourseModel data){
        System.out.println(data.getEmail());
        System.out.println(data.getId());
        String message = service.joinDiscussion(data.getEmail(), data.getId());
        
        ResponseWrapperModel<JoinDiscCourseModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        if(message != "Joined Success") {
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setMessage(message);
            wrapperModel.setErrorSchema(error);
        }

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }
    
     @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
     @PostMapping("joincourse")
     public ResponseEntity<ResponseWrapperModel<JoinDiscCourseModel>> createJoinCourse(@RequestBody JoinDiscCourseModel data){
        System.out.println(data.getEmail());
        System.out.println(data.getId());
        String message = service.joinCourse(data.getEmail(), data.getId());
        
        ResponseWrapperModel<JoinDiscCourseModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        if(message != "Joined Success") {
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            error.setMessage(message);
            wrapperModel.setErrorSchema(error);
        }

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }


    
    @PostMapping("/upload")
    public ResponseEntity<ResponseWrapperModel> uploadFile(@RequestParam(name = "email") String email, @RequestParam("file") MultipartFile file) {
        String message = "";

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        try {
            service.store(email, file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            error.setMessage(message);
            error.setErrorCode("00");
            error.setHttpCode(HttpStatus.OK.value());
            wrapperModel.setErrorSchema(error);
            return ResponseEntity.status(HttpStatus.OK).body(wrapperModel);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            error.setMessage(message);
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            wrapperModel.setErrorSchema(error);
            return ResponseEntity.status(HttpStatus.OK).body(wrapperModel);
        }
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
        AccountRegisterEntity account = service.getFile(id);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + account.getPic_name() + "\"")
            .body(account.getData());
    }

    @PostMapping("/register/teacher")
    public ResponseEntity<ResponseWrapperModel> registerTeacher(@RequestParam(name = "email") String email,
            @RequestParam("file") MultipartFile file, 
            @RequestParam("achievement") String achievement, @RequestParam("education") String education,
            @RequestParam("experience") String experience, @RequestParam("profile_description") String profileDescription
    ) {
        String message = "";

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        AccountModel account = service.getAccountbyEmail(email);
        boolean flagNew = false;
        try {
            System.out.println(account.getId());
            List<TeacherModel.Teacher> teachers = service.getAllTeacher();
            for (TeacherModel.Teacher teacher : teachers) {
                if(teacher.getUser().getId() == account.getId()) flagNew = true;
                System.out.println(teacher.getUser().getId());
                System.out.println(account.getId());
            }
            if(!flagNew) {
                message = service.registerTeacher(account, file, achievement, education, experience, profileDescription);
                error.setMessage(message);
                error.setErrorCode("00");
                error.setHttpCode(HttpStatus.OK.value());
                wrapperModel.setErrorSchema(error);
                return ResponseEntity.status(HttpStatus.OK).body(wrapperModel);
            } else {
                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
                error.setMessage(message);
                error.setErrorCode("500");
                error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
                wrapperModel.setErrorSchema(error);
                return ResponseEntity.status(HttpStatus.OK).body(wrapperModel);
            }

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            error.setMessage(message);
            error.setErrorCode("500");
            error.setHttpCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
            wrapperModel.setErrorSchema(error);
            return ResponseEntity.status(HttpStatus.OK).body(wrapperModel);
        }
    }

    @GetMapping("inquiry/teacher")
    public ResponseEntity<ResponseWrapperModel> getAllTeacher() {
        System.out.println("masa masuk sini");
        List<TeacherModel.Teacher> teachers = service.getAllTeacher();

        TeacherModel.Response response = new TeacherModel.Response(teachers);

        ResponseWrapperModel<TeacherModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);

    }

    @GetMapping("inquiry/teacher/{email}")
    public ResponseEntity getTeacher(@PathVariable String email) {
        System.out.println("masuk sini brohh");
        Teacher teacher = service.getTeacherByUserEmail(email);

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(teacher);

        return ResponseEntity.status(HttpStatus.OK.value()).body(wrapperModel);

    }
    
    @GetMapping("/files/cv/{id}")
    public ResponseEntity<byte[]> getCVFile(@PathVariable Long id) {
        TeacherEntity teacher = service.getCvFile(id);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + teacher.getUser().getPic_name() + "\"")
            .body(teacher.getData());
    }

    @GetMapping("/subscribe")
    public ResponseEntity<ResponseWrapperModel> subscribedLecturer(@RequestParam(name = "teacher-id") Long id, @RequestParam("email") String email) {

        String message = service.subscribedLecturer(id, email);

        ResponseWrapperModel<TeacherModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(null);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @GetMapping("/unsubscribe")
    public ResponseEntity<ResponseWrapperModel> unSubscribedLecturer(@RequestParam(name = "teacher-id") Long id, @RequestParam("email") String email) {

        String message = service.unSubscribedLecturer(id, email);

        ResponseWrapperModel<TeacherModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(null);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @GetMapping("/course")
    public ResponseEntity<ResponseWrapperModel> getStudentCourse(@RequestParam(name = "account") Long userid, @RequestParam("course") Long course){
        StudentCourseModel studentcourse = service.getStudentCourseData(userid, course);

        ResponseWrapperModel<StudentCourseModel> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(studentcourse);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }
    
    @PostMapping("/private")
    public ResponseEntity<ResponseWrapperModel> addPrivateDisc(@RequestBody PrivateDiscModel data, @RequestParam("account") Long userid, @RequestParam("teacher") Long teacherid){
        // System.out.println(data);
        // System.out.println(userid);
        // System.out.println(teacherid);

        String message = service.addPrivateDisc(data, userid, teacherid);
        ResponseWrapperModel<PrivateDiscModel> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(null);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PostMapping("/private/request")
    public ResponseEntity<ResponseWrapperModel> acceptRejectPrivate(@RequestParam("status") String status, @RequestParam("id") Long id, @RequestBody String subject){
        String message = service.acceptOrReject(id, status, subject);
        ResponseWrapperModel<PrivateDiscModel> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(null);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }
}
