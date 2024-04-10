package co.id.ajarin.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.entity.PrivateDiscEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.dashboard.PrivateDiscModel;
import co.id.ajarin.model.dashboard.CourseModel.Course;
import co.id.ajarin.model.dashboard.DiscussionModel.Discussion;

 @SuppressWarnings("null")
public class TeacherMapper {
    
    public static TeacherModel.Teacher mapToTeacherModel (TeacherEntity teacherEntity, List<PrivateDiscModel> private_disc) {
        AccountRegisterEntity acc = teacherEntity.getUser();
        List<PrivateDiscEntity> disc = teacherEntity.getPrivateDisc();
        List<Discussion> discussion = new ArrayList<>();
        List<Course> course = new ArrayList<>();
        for (DiscussionEntity discussionEntity : teacherEntity.getDiscussions()) {
            Discussion discussionModel = new Discussion(discussionEntity.getDisc_id(), 
            discussionEntity.getDisc_title(), 
            discussionEntity.getDisc_participant(), 
            discussionEntity.getDisc_price(), 
            discussionEntity.getDisc_starttime(), 
            discussionEntity.getDisc_endtime(), 
            discussionEntity.getDisc_date(), 
            discussionEntity.getDisc_description(), 
            discussionEntity.getDisc_level(), 
            discussionEntity.getDisc_image(), 
            discussionEntity.getDisc_url(), 
            discussionEntity.getCategory(), 
            new Teacher(discussionEntity.getTeacher().getTeacher_id(), discussionEntity.getTeacher().getProfile_description(), discussionEntity.getTeacher().getAchievement(), discussionEntity.getTeacher().getExperience(), discussionEntity.getTeacher().getEducation(), discussionEntity.getTeacher().getRating(), discussionEntity.getTeacher().getUser()), 
            new Long(0));
            discussion.add(discussionModel);
        }
        for (CourseEntity courseEntity : teacherEntity.getCourse()) {
            Course courseModel = new Course(courseEntity.getCourse_id(), 
            courseEntity.getCourse_price(), 
            courseEntity.getCourse_chapter(), 
            courseEntity.getCourse_title(), 
            courseEntity.getCourse_description(), 
            courseEntity.getCourse_level(), 
            courseEntity.getCourse_image(), 
            courseEntity.getTotal_course_sold(), 
            courseEntity.getCategory(), 
            courseEntity.getCourse_details(), 
            new Teacher(courseEntity.getTeacher().getTeacher_id(), courseEntity.getTeacher().getProfile_description(), courseEntity.getTeacher().getAchievement(), courseEntity.getTeacher().getExperience(), courseEntity.getTeacher().getEducation(), courseEntity.getTeacher().getRating(), courseEntity.getTeacher().getUser()));
            course.add(courseModel);
        }
        acc = new AccountRegisterEntity(acc.getId(), acc.getFirstName(), acc.getLastName(), acc.getEmail(), acc.getPassword(), acc.getRole(), acc.getGender(), acc.getCity(), acc.getCountry(), acc.getSchool(), acc.getAge(), acc.getPhoneNumber(), acc.getEducation(), acc.getCoin(), acc.getPic_name(), acc.getPic_type(), acc.getData());

        List<PrivateDiscModel> teacher_private_disc = new ArrayList<>();
        for (PrivateDiscEntity privateDiscEntity : teacherEntity.getPrivateDisc()){
            System.out.println(privateDiscEntity);
            if(privateDiscEntity.getStatus().equals("Accepted")) continue;

            AccountRegisterEntity account = privateDiscEntity.getUser();
            AccountModel usedAcc = UserMapper.mapToAccountModelNoR(account);

            PrivateDiscModel privatedisc = new PrivateDiscModel(
                privateDiscEntity.getPrivate_id(),
                privateDiscEntity.getTitle(), 
                privateDiscEntity.getSubject(), 
                privateDiscEntity.getEducation(), 
                privateDiscEntity.getDifficulty(), 
                privateDiscEntity.getPrivate_date(), 
                privateDiscEntity.getDate_start(), 
                privateDiscEntity.getDate_end(), 
                privateDiscEntity.getOffered_coin(), 
                usedAcc, 
                null);
            teacher_private_disc.add(privatedisc);
        }


        return new TeacherModel.Teacher(
            teacherEntity.getTeacher_id(),
            teacherEntity.getProfile_description(),
            teacherEntity.getAchievement(),
            teacherEntity.getExperience(),
            teacherEntity.getEducation(),
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/cv/").path(teacherEntity.getTeacher_id().toString()).toUriString(),
            teacherEntity.getRating(),
            // teacherEntity.getUser()
            new AccountRegistrationModel(teacherEntity.getUser()),
            discussion,
            course,
            teacher_private_disc
            // teacherEntity.getTeacher_image(),
            // teacherEntity.getTeacher_name(),
        );
    }



    public static TeacherModel.Teacher mapToTeacherModelNoR(TeacherEntity teacherEntity){
        return new TeacherModel.Teacher(
            teacherEntity.getTeacher_id(),
            teacherEntity.getProfile_description(),
            teacherEntity.getAchievement(),
            teacherEntity.getExperience(),
            teacherEntity.getEducation(),
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/cv/").path(teacherEntity.getTeacher_id().toString()).toUriString(),
            teacherEntity.getRating(),
            new AccountRegistrationModel(teacherEntity.getUser()),
            null,
            null,
            null
        );
    }
}
