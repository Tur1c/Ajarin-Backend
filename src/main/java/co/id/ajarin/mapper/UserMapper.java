package co.id.ajarin.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.entity.NotificationEntity;
import co.id.ajarin.entity.PrivateDiscEntity;
import co.id.ajarin.entity.StudentCourseEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.account.NotificationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.dashboard.CourseModel;
import co.id.ajarin.model.dashboard.DiscussionModel;
import co.id.ajarin.model.dashboard.StudentCourseModel;
import co.id.ajarin.model.dashboard.StudentDiscModel;
import co.id.ajarin.model.dashboard.DiscussionModel.Discussion;


public class UserMapper {

    @SuppressWarnings("null")
    public static AccountModel mapToAccountModel(AccountRegisterEntity account, List<NotificationEntity> notif, List<PrivateDiscEntity> private_disc){
        List<StudentCourseModel> studentcourse = new ArrayList<>();

        for (StudentCourseEntity studentCourseEntity : account.getStudentcourse_list()){
                    TeacherModel.Teacher teacher = TeacherMapper.mapToTeacherModelNoR(studentCourseEntity.getCourse().getTeacher());
                    CourseEntity course = studentCourseEntity.getCourse();
                    CourseModel.Course courseModel = CourseMapper.mapToCourseModelNoR(course,teacher);
                    studentcourse.add(new StudentCourseModel( 
                        courseModel,
                        studentCourseEntity.getStatus(),
                        studentCourseEntity.getCompleted_chap(),
                        studentCourseEntity.getRating(),
                        studentCourseEntity.getComment()
                    ));
        }

        List<StudentDiscModel> studentDisc = new ArrayList<>();
        for (StudentDiscEntity studentDiscEntity : account.getStudentdisc_list()){
                // TeacherModel.Teacher teacher = TeacherMapper.mapToTeacherModel(studentDiscEntity.getDisc().getTeacher());
                DiscussionEntity disc = studentDiscEntity.getDisc();
                DiscussionModel.Discussion discModel = DiscussionMapper.maptoDiscussionModel(disc);
                
                studentDisc.add(new StudentDiscModel(
                    discModel,
                    studentDiscEntity.getStatus()
                    
                ));
        }

        for(PrivateDiscEntity privateDisc : private_disc){
            CategoryEntity category = new CategoryEntity(99, privateDisc.getSubject());
            DiscussionModel.Discussion discModel = new Discussion(privateDisc.getPrivate_id(), privateDisc.getTitle(), 1, privateDisc.getOffered_coin(), privateDisc.getDate_start(), privateDisc.getDate_end(), privateDisc.getPrivate_date(), privateDisc.getDifficulty(), privateDisc.getEducation(), null, category, TeacherMapper.mapToTeacherModel(privateDisc.getTeacher(), null), 1L);

            studentDisc.add(new StudentDiscModel(
                discModel,
                "Ongoing"
            ));
        }

        List<TeacherModel.Teacher> teacherModels = new ArrayList<>();
        for(TeacherEntity teacher : account.getSubscribed_lecturer() ){
            teacherModels.add(TeacherMapper.mapToTeacherModel(teacher,null));
        }

        List<NotificationModel> notifs = new ArrayList<>();
        for(NotificationEntity notificationEntity: notif){
            notifs.add(new NotificationModel(notificationEntity.getNotif_id(),notificationEntity.getMessage(), notificationEntity.getIsRead()));
        }

        String pic_url;
        if(account.getData() != null) {
           pic_url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/").path(account.getId().toString()).toUriString();
        }
        else{
            pic_url = null;
        }
        
        return new AccountModel(
            account.getId(), 
            account.getFirstName(), 
            account.getLastName(), 
            account.getEmail(), 
            account.getPassword(), 
            account.getRole(), 
            account.getGender(), 
            account.getCity(), 
            account.getCountry(), 
            account.getSchool(), 
            account.getAge(), 
            account.getPhoneNumber(), 
            account.getEducation(), 
            studentDisc, 
            studentcourse, 
            teacherModels, 
            account.getCoin(),
            account.getPic_name(), 
            pic_url, 
            account.getPic_type(),
            account.getProfile_pic(),
            notifs
            );
    }

    public static AccountModel mapToAccountModelNoR(AccountRegisterEntity acc){

       return new AccountModel(
            acc.getId(), 
            acc.getFirstName(), 
            acc.getLastName(), 
            acc.getEmail(), 
            acc.getPassword(), 
            acc.getRole(), 
            acc.getGender(), 
            acc.getCity(), 
            acc.getCountry(), 
            acc.getSchool(), 
            acc.getAge(), 
            acc.getPhoneNumber(), 
            acc.getEducation(), 
            acc.getCoin(),
            acc.getProfile_pic()
            );
    }
}
