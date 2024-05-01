package co.id.ajarin.model.account;

import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.mapper.UserMapper;
import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.dashboard.PrivateDiscModel;
import co.id.ajarin.model.dashboard.StudentCourseModel;
import co.id.ajarin.model.dashboard.CourseModel.Course;
import co.id.ajarin.model.dashboard.DiscussionModel.Discussion;
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
    public static class Teacher extends OutputRepositoryModel {
        // untuk list discussion yg dimiliki teacher
        public Teacher(Long teacher_id, String profile_description, String achievement, String experience,
                String education, AccountModel user) {
            this.id = teacher_id;
            this.profile_description = profile_description;
            this.achievement = achievement;
            this.experience = experience;
            this.education = education;
            this.user = user;
        }

        public Teacher(Long teacher_id, String profile_description2, String achievement2, String experience2,
                String education2, String uriString, Integer points, AccountModel accountRegistrationModel, List<Discussion> discussion, List<PrivateDiscModel> private_disc) {
            this.id = teacher_id;
            this.profile_description = profile_description2;
            this.achievement = achievement2;
            this.experience = experience2;
            this.education = education2;
            this.cv_data = uriString;
            this.points = points;
            this.user = accountRegistrationModel;
            this.discussion = discussion;
            this.private_disc = private_disc;
        }

        private Long id;
        private String profile_description;
        private String achievement;
        private String experience;
        private String education;
        private String cv_data;
        private Integer points;
        private AccountModel user;
        private List<Discussion> discussion;
        private List<Course> courses;
        private List<PrivateDiscModel> private_disc;
        private List<StudentCourseModel> course_list;
        private Double teacher_rating;
    }
    

}
