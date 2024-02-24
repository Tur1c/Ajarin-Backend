package co.id.ajarin.model.account;

import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegistrationModel extends OutputRepositoryModel {

    public AccountRegistrationModel(AccountRegisterEntity account) {
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.email = account.getEmail();
        this.password = account.getPassword();
        this.role = account.getRole();
        this.gender = account.getGender();
        this.city = account.getCity();
        this.country = account.getCountry();
        this.school = account.getSchool();
        this.age = account.getAge();
        this.phoneNumber = account.getPhoneNumber();
        this.education = account.getEducation();
        this.studentdisc_list = account.getStudentdisc_list();
    }

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String role;
    private String gender;
    private String city;
    private String country;
    private String school;
    private Integer age;
    private String phoneNumber;
    private String education;
    private List<StudentDiscEntity> studentdisc_list;
}
