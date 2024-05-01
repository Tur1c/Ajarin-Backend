package co.id.ajarin.model.account;

import java.util.List;

import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.dashboard.StudentCourseModel;
import co.id.ajarin.model.dashboard.StudentDiscModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class AccountModel extends OutputRepositoryModel {
    public AccountModel(Long id2, String firstName2, String lastName2, String email2, String password2, String role2,
            String gender2, String city2, String country2, String school2, Integer age2, String phoneNumber2,
            String education2, List<StudentDiscModel> studentdisc_list2, List<StudentCourseModel> studentcourse,
            List<TeacherModel.Teacher> subscribed_lecturer2,Integer coin2, String profile_pic2, List<NotificationModel> notif2) {
        
            this.id = id2;
            this.firstName = firstName2;
            this.lastName = lastName2;
            this.email = email2;
            this.password = password2;
            this.role = role2;
            this.gender = gender2;
            this.city = city2;
            this.country = country2;
            this.school = school2;
            this.age = age2;
            this.phoneNumber = phoneNumber2;
            this.education = education2;
            this.studentdisc_list = studentdisc_list2;
            this.studentcourse_list = studentcourse;
            this.subscribed_lecturer = subscribed_lecturer2;
            this.coin = coin2;
        //     this.pic_name = pic_name2;
        //     this.pic_url = pic_url2;
        //     this.pic_type = pic_type2;
            this.profile_pic = profile_pic2;
            this.notifs = notif2;
    }

    public AccountModel(Long id2, String firstName2, String lastName2, String email2, String password2, String role2,
            String gender2, String city2, String country2, String school2, Integer age2, String phoneNumber2,
            String education2, Integer coin2, String profile_pic2) {
            this.id = id2;
            this.firstName = firstName2;
            this.lastName = lastName2;
            this.email = email2;
            this.password = password2;
            this.role = role2;
            this.gender = gender2;
            this.city = city2;
            this.country = country2;
            this.school = school2;
            this.age = age2;
            this.phoneNumber = phoneNumber2;
            this.education = education2;
            this.studentdisc_list = null;
            this.studentcourse_list = null;
            this.subscribed_lecturer = null;
            this.coin = coin2;
        //     this.pic_name = null;
        //     this.pic_url = null;
        //     this.pic_type = null;
            this.profile_pic = profile_pic2;
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
    private List<StudentDiscModel> studentdisc_list;
    private List<StudentCourseModel> studentcourse_list;
    private List<TeacherModel.Teacher> subscribed_lecturer;
    private Integer coin;
//     private String pic_name;
//     private String pic_url;
//     private String pic_type;
    private String profile_pic;
    private List<NotificationModel> notifs;
}
