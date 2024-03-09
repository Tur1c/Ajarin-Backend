package co.id.ajarin.controller;

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
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.model.category.CategoryModel;
import co.id.ajarin.model.dashboard.StudentDiscModel;
import co.id.ajarin.service.AccountService;

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

    @PreAuthorize("hasRole('Student')")
    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<AccountRegistrationModel>> getAccountbyEmail(@RequestParam(name = "email") String email){
        AccountRegistrationModel account = service.getAccountbyEmail(email);

        ResponseWrapperModel<AccountRegistrationModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(account);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

     @PreAuthorize("hasRole('Student')")
    @PutMapping("{id}")
    public ResponseEntity<ResponseWrapperModel<AccountRegistrationModel>> updateAccount(@PathVariable Long id, @RequestBody AccountRegistrationModel account){

        AccountRegistrationModel accountOld = service.findById(id);

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
        accountOld.setStudentdisc_list(account.getStudentdisc_list());
        accountOld.setCoin(account.getCoin());

        service.update(accountOld);

        ResponseWrapperModel<AccountRegistrationModel> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(accountOld);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

     @PreAuthorize("hasRole('Student')")
     @PostMapping("join")
     public ResponseEntity<ResponseWrapperModel<StudentDiscModel>> createJoinDiscussion(@RequestBody StudentDiscModel data){
        System.out.println(data.getEmail());
        System.out.println(data.getId());
        String message = service.joinDiscussion(data.getEmail(), data.getId());
        
        ResponseWrapperModel<StudentDiscModel> wrapperModel = new ResponseWrapperModel<>();

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
    
     @PreAuthorize("hasRole('Student')")
     @PostMapping("joincourse")
     public ResponseEntity<ResponseWrapperModel<StudentDiscModel>> createJoinCourse(@RequestBody StudentDiscModel data){
        System.out.println(data.getEmail());
        System.out.println(data.getId());
        String message = service.joinCourse(data.getEmail(), data.getId());
        
        ResponseWrapperModel<StudentDiscModel> wrapperModel = new ResponseWrapperModel<>();

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

        AccountRegistrationModel account = service.getAccountbyEmail(email);
        boolean flagNew = false;
        try {
            System.out.println(account.getId());
            List<TeacherModel.Teacher> teachers = service.getAllTeacher();
            for (TeacherModel.Teacher teacher : teachers) {
                if(teacher.getAccount().getId() == account.getId()) flagNew = true;
                System.out.println(teacher.getAccount().getId());
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
    
    @GetMapping("/files/cv/{id}")
    public ResponseEntity<byte[]> getCVFile(@PathVariable Long id) {
        TeacherEntity teacher = service.getCvFile(id);

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + teacher.getUser().getPic_name() + "\"")
            .body(teacher.getData());
    }

}
