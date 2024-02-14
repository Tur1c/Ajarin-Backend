package co.id.ajarin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.auth.AuthenticationModel;
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
            System.out.println(service.authenticated(account.getEmail()));
            wrapperModel.setOutputSchema(service.authenticated(account.getEmail()));
        }

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

}
