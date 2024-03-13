package co.id.ajarin.model.account;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class TeacherModel extends OutputRepositoryModel {
    
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response extends OutputRepositoryModel {
        public List<Teacher> teachers;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Teacher {
        // public Teacher(Long teacher_id, String profile_description, String achievement, String experience,
        //         String education, String uriString, String rating, String teacher_image, String teacher_name, AccountRegisterEntity user) {
        //     this.id = teacher_id;
        //     this.profile_description = profile_description;
        //     this.achievement = achievement;
        //     this.experience = experience;
        //     this.cv_data = uriString;
        //     this.education = education;
        //     this.rating = rating;
        //     this.teacher_image = teacher_image;
        //     this.teacher_name = teacher_name;
        //     this.account = user;
        // }
        private Long id;
        private String profile_description;
        private String achievement;
        private String experience;
        private String education;
        private String cv_data;
        private String rating;
        private String teacher_image;
        private String teacher_name;
        private AccountRegisterEntity user;

    }

}
