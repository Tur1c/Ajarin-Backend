package co.id.ajarin.model.account;

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
}
