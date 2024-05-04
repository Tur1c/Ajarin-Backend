package co.id.ajarin.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@SuppressWarnings("null")
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "ajarin_user")
public class AccountRegisterEntity {

    // public AccountRegisterEntity(Long id2, String firstName2, String lastName2, String email2, String password2,
    //         String role2, String gender2, String city2, String country2, String school2, Integer age2,
    //         String phoneNumber2, String education2, List<StudentDiscEntity> studentdisc_list2, List<StudentCourseEntity> studentcourse_list2, 
    //         Integer coin2, String pic_name2, String pic_type2) {
    //     this.id = id2;
    //     this.age = age2;
    //     this.firstName = firstName2;
    //     this.lastName = lastName2;
    //     this.email = email2;
    //     this.password = password2;
    //     this.role = role2;
    //     this.gender = gender2;
    //     this.coin = coin2;
    //     this.city = city2;
    //     this.country = country2;
    //     this.school = school2;
    //     this.phoneNumber = phoneNumber2;
    //     this.education = education2;
    //     this.studentdisc_list = studentdisc_list2;
    //     this.studentcourse_list = studentcourse_list2;
    //     // this.pic_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/").path(id.toString()).toUriString();
    //     this.pic_name = pic_name2;
    //     this.pic_type = pic_type2;
    // }

    public AccountRegisterEntity(Long id2, String firstName2, String lastName2, String email2, String password2,
            String role2, String gender2, String city2, String country2, String school2, Integer age2,
            String phoneNumber2, String education2, List<StudentDiscEntity> studentdisc_list2, List<StudentCourseEntity> studentcourse_list2, 
            Integer coin2) {
        this.id = id2;
        this.age = age2;
        this.firstName = firstName2;
        this.lastName = lastName2;
        this.email = email2;
        this.password = password2;
        this.role = role2;
        this.gender = gender2;
        this.coin = coin2;
        this.city = city2;
        this.country = country2;
        this.school = school2;
        this.phoneNumber = phoneNumber2;
        this.education = education2;
        this.studentdisc_list = studentdisc_list2;
        this.studentcourse_list = studentcourse_list2;
        // if(pic_data != null) {
        //     this.pic_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/").path(id.toString()).toUriString();
        // }
        // this.pic_name = pic_name2;
        // this.pic_type = pic_type2;
    }

    //constructor without relational data

    public AccountRegisterEntity(Long id, String firstName, String lastName, String email, String password, String role, String gender, String city, String country,
    String school, Integer age, String phoneNumber, String education, Integer coin, String profile_pic){
        this.id = id;
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.gender = gender;
        this.coin = coin;
        this.city = city;
        this.country = country;
        this.school = school;
        this.phoneNumber = phoneNumber;
        this.education = education;
        this.profile_pic = profile_pic;
        // if(pic_data != null) {
        //     this.pic_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/").path(id.toString()).toUriString();
        // }
        // this.pic_name = pic_name;
        // this.pic_type = pic_type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long id;

    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email")
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "role")
    private String role;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "city")
    private String city;
    
    @Column(name = "country")
    private String country;
    
    @Column(name = "school")
    private String school;
    
    @Column(name = "age")
    private Integer age;
    
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Column(name = "education")
    private String education;

    @OneToMany(mappedBy = "user")
    private List<StudentDiscEntity> studentdisc_list;

    @OneToMany(mappedBy = "user")
    private List<StudentCourseEntity> studentcourse_list;

    @ManyToMany()
    // @JsonIgnore
    @JoinTable(
        name = "subscribed_lecturer",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "teacher_id")
    )
    private List<TeacherEntity> subscribed_lecturer;

    @Column(name = "coin")
    private Integer coin;

    // @Column(name = "profile_pic_data")
    // @JsonIgnore
    // @Lob
    // private byte[] data;

    // @Column(name = "profile_pic_url")
    // @Transient
    // private String pic_url;

    // @Column(name = "profile_pic_name")
    // private String pic_name;

    // @Column(name = "profile_pic_type")
    // private String pic_type;

    @Column(name = "profile_pic")
    private String profile_pic;


}
