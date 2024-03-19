package co.id.ajarin.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.CourseDetailEntity;
import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.mapper.CourseMapper;
import co.id.ajarin.model.dashboard.CourseModel;
import co.id.ajarin.repository.CategoryRepository;
import co.id.ajarin.repository.CourseDetailRepository;
import co.id.ajarin.repository.CourseRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository; 

    @Autowired
    private TeacherRepository teacherRepository; 

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Override
    @Transactional
    public List<CourseModel.Course> getAllCourse() {
        // TODO Auto-generated method stub
       List<CourseEntity> courses = courseRepository.findAll();

       return courses.stream().map( (course) -> CourseMapper.mapToCourseModel(course)).collect(Collectors.toList());
    }

    @Override
    public CourseEntity addNewCourse(String title, String categoryName, String level, String description, String chapter,
            String price, Long userId, MultipartFile file, String url) throws NumberFormatException, IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        
        List<CategoryEntity> categories = categoryRepository.findAll();
        CategoryEntity category = new CategoryEntity();

        TeacherEntity teacher = new TeacherEntity();

       List<TeacherEntity> teachers = teacherRepository.findAll();
        for (TeacherEntity teacherEntity : teachers) {
            if(teacherEntity.getUser().getId() == userId) {
                teacher = teacherEntity;
                break;
            }
        }

        for (CategoryEntity categoryEntity : categories) {
            if(categoryEntity.getCategory_name().equals(categoryName)) {
                category = categoryEntity;
                break;
            }
        }

        CourseEntity course = new CourseEntity(Integer.parseInt(price), chapter, title, description, level, filename, url, file.getBytes(), 0, category, teacher);

        courseRepository.save(course);
        return course;
    }

    @Override
    @Transactional
    public String addCourseDetail(Long id, String title, String video, String thumbnail) {
        CourseEntity course = courseRepository.getById(id);
        Long chapter = (long) course.getCourse_details().size();
        CourseDetailEntity courseDetail = new CourseDetailEntity(course.getCourse_id(), chapter + 1, title, video, thumbnail);
        System.out.println(course.getCourse_chapter());
        courseDetailRepository.save(courseDetail);
        return "Success";
    }

    

}
