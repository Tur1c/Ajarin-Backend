package co.id.ajarin.model.account;

import java.util.ArrayList;
import java.util.List;


import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.StudentCourseEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.TeacherEntity;
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

    @SuppressWarnings("null")
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
        this.studentcourse_list = account.getStudentcourse_list();
        this.coin = account.getCoin();
        // if(account.getData() != null) {
        //     this.pic_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/").path(account.getId().toString()).toUriString();
        // }
        this.subscribed_lecturer = new ArrayList<TeacherEntity>();
        for (TeacherEntity teacher : account.getSubscribed_lecturer()) {
            this.subscribed_lecturer.add(new TeacherEntity(teacher.getTeacher_id(), teacher.getProfile_description(), teacher.getEducation(), teacher.getExperience(), teacher.getAchievement(), null, null, teacher.getUser()));
        }
        // this.pic_name = account.getPic_name();
        // this.pic_type = account.getPic_type();

        System.out.println(account.getStudentcourse_list() + " ASJBAJSBAJS");
        System.out.println(account.getStudentdisc_list() + " ASJBAJSBAJS");

        for (TeacherEntity studentCourseEntity : account.getSubscribed_lecturer()) {
            System.out.println(studentCourseEntity.getUser());
        }
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
    private List<StudentCourseEntity> studentcourse_list;
    private List<TeacherEntity> subscribed_lecturer;
    private Integer coin;
    // private String pic_name;
    // private String pic_url;
    // private String pic_type;
}
