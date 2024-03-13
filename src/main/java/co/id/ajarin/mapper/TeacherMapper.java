package co.id.ajarin.mapper;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.category.CategoryModel;

public class TeacherMapper {
    
    public static TeacherModel.Teacher mapToTeacherModel (TeacherEntity teacherEntity) {
        AccountRegisterEntity acc = teacherEntity.getUser();
        acc = new AccountRegisterEntity(acc.getId(), acc.getFirstName(), acc.getLastName(), acc.getEmail(), acc.getPassword(), acc.getRole(), acc.getGender(), acc.getCity(), acc.getCountry(), acc.getSchool(), acc.getAge(), acc.getPhoneNumber(), acc.getEducation(), acc.getCoin(), acc.getPic_name(), acc.getPic_type(), acc.getData());
        return new TeacherModel.Teacher(
            teacherEntity.getTeacher_id(),
            teacherEntity.getProfile_description(),
            teacherEntity.getAchievement(),
            teacherEntity.getExperience(),
            teacherEntity.getEducation(),
            ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/cv/").path(teacherEntity.getTeacher_id().toString()).toUriString(),
            teacherEntity.getRating(),
            teacherEntity.getUser()
            // teacherEntity.getTeacher_image(),
            // teacherEntity.getTeacher_name(),
        );
    }
}
