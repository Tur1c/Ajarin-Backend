package co.id.ajarin.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.model.dashboard.CourseModel;


public interface CourseService {
    List<CourseModel.Course> getAllCourse();

    CourseEntity addNewCourse(String title, String categoryName, String level, String description, String chapter, String price, Long userId, MultipartFile file, String url) throws NumberFormatException, IOException;

    String addCourseDetail(Long id, String title, String video, String thumbnail);

    String completeChapter(String complete, Long courseid, Long userid, Long total_chap);

    String rateCourse(String userid, Long courseid, Float rating, String comment);
}
