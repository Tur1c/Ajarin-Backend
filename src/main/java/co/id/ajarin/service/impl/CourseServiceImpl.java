package co.id.ajarin.service.impl;

import java.io.File;
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
import co.id.ajarin.repository.StudentCourseRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.service.CourseService;



@Service
@SuppressWarnings({ "deprecation", "null" })
public class CourseServiceImpl implements CourseService{

    // private static final String UPLOAD_PATH ="C:/Users/Lenovo/OneDrive/Desktop/React/Ajarin-Web-React/public/assets/";
    // private static final String VIDEO_PATH ="C:/Users/Lenovo/OneDrive/Desktop/React/Ajarin-Web-React/public/video/";

    private static final String UPLOAD_PATH ="C:/Users/Ivander/OneDrive/Desktop/Ajarin/web/public/assets/";
    private static final String VIDEO_PATH ="C:/Users/Ivander/OneDrive/Desktop/Ajarin/web/public/video/";


    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CategoryRepository categoryRepository; 

    @Autowired
    private TeacherRepository teacherRepository; 

    @Autowired
    private CourseDetailRepository courseDetailRepository;

    @Autowired
    private StudentCourseRepository studentCourseRepository;

    @Override
    @Transactional
    public List<CourseModel.Course> getAllCourse() {
        // TODO Auto-generated method stub
       List<CourseEntity> courses = courseRepository.findAll();

       return courses.stream().map( (course) -> CourseMapper.mapToCourseModel(course)).collect(Collectors.toList());
    }


    @Transactional
    @Override
    public CourseModel.Course addNewCourse(String title, String categoryName, String level, String description, String chapter,
            String price, Long userId, MultipartFile file, String url) throws NumberFormatException, IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());


        CategoryEntity category = categoryRepository.findByName(categoryName);

        TeacherEntity teacher = teacherRepository.getReferenceById(userId);

        Boolean exist = new File(UPLOAD_PATH + filename).isFile();
        if(!exist){
            try{
                file.transferTo(new File(UPLOAD_PATH + filename));
                
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        CourseEntity course = new CourseEntity(Integer.parseInt(price), chapter, title, description, level, filename, 0, category, teacher);

        courseRepository.save(course);
        return CourseMapper.mapToCourseModel(course);
    }

  

    @Override
    @Transactional
    public String addCourseDetail(Long id, String title, MultipartFile video, MultipartFile thumbnail, MultipartFile pdf) {
        CourseEntity course = courseRepository.getById(id);
        Long chapter = (long) course.getCourse_details().size();

        String pdfName = "";

        String thumbnailName = StringUtils.cleanPath(thumbnail.getOriginalFilename());
        Boolean exist = new File(UPLOAD_PATH + thumbnailName).exists();
        String videoName = StringUtils.cleanPath(video.getOriginalFilename());
        Boolean exist2 = new File(VIDEO_PATH + videoName).exists();
            try{
                if(!exist){
                    thumbnail.transferTo(new File(UPLOAD_PATH + thumbnailName));
                }
                if(!exist2){
                    video.transferTo(new File(VIDEO_PATH + videoName));
                }
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }

        if(pdf != null){
            pdfName = StringUtils.cleanPath(pdf.getOriginalFilename());
            Boolean exist3 = new File(UPLOAD_PATH + pdfName).exists();
            try{
                if(!exist3){
                    pdf.transferTo(new File(UPLOAD_PATH + pdfName));
                }
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        CourseDetailEntity courseDetail = new CourseDetailEntity(course.getCourse_id(), chapter + 1, title, videoName, thumbnailName, pdfName);
        System.out.println(course.getCourse_chapter());
        courseDetailRepository.save(courseDetail);
        return "Success";
    }

    @Override
    @Transactional
    public String completeChapter(String complete, Long courseid, Long userid, Long total_chap) {
        System.out.println(courseid + " " + userid + " " + complete + "hiks");
        int x = studentCourseRepository.setCompleteChap(complete, courseid, userid);
        System.out.println(x);

        String[] array = complete.split("\\|");
        int completed_chap = array.length;

        System.out.println(completed_chap  + "aha");
        System.out.println(total_chap + "total");
        System.out.println((completed_chap == total_chap));
        if(completed_chap == total_chap){
            studentCourseRepository.setCourseCompleted(courseid, userid);
            return "Course Completed";
        }


        return "Chapter Completed";
    }

    @Override
    @Transactional
    public String rateCourse(String userid, Long courseid, Float rating, String comment) {
        // TODO Auto-generated method stub
        System.out.println(userid + " " + courseid + " " + rating + " " + comment + "ahahahaha");
        studentCourseRepository.setRatingComment(Long.parseLong(userid), courseid, rating, comment);

        return "Success Update";
    }

    

}
