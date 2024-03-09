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
        public Teacher(Long teacher_id, String profile_description, String achievement, String experience,
                String education, String uriString, String rating, AccountRegisterEntity user) {
            this.id = teacher_id;
            this.teacher_achievement = achievement;
            this.teacher_experience = experience;
            this.teacher_cv_url = uriString;
            this.teacher_education = education;
            this.teacher_rating = rating;
            this.account = new AccountRegistrationModel(user);
        }
        private Long id;
        private String teacher_description;
        private String teacher_achievement;
        private String teacher_experience;
        private String teacher_education;
        private String teacher_cv_url;
        private String teacher_rating;
        private AccountRegistrationModel account;

    }

}
