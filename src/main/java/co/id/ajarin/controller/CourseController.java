package co.id.ajarin.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

// import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
// import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.dashboard.CompleteChapterModel;
import co.id.ajarin.model.dashboard.CourseModel;
// import co.id.ajarin.model.dashboard.CourseModel.Course;
import co.id.ajarin.model.dashboard.RateCourseModel;
import co.id.ajarin.service.CourseService;

@SuppressWarnings("rawtypes")
@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    
    //Get All Course
    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<CourseModel.Response>> getAllCourse(){
        List<CourseModel.Course> courses = courseService.getAllCourse();

        CourseModel.Response response = new CourseModel.Response(courses);

        ResponseWrapperModel<CourseModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    
    @SuppressWarnings("unchecked")
    @PostMapping("/add")
    public ResponseEntity addNewCourse(
                    @RequestParam("title") String title, 
                    @RequestParam("category") String category, 
                    @RequestParam("education_level") String level,
                    @RequestParam("description") String description, 
                    @RequestParam("chapter") String chapter, @RequestParam("price") String price,
                    @RequestParam("teacher_id") Long userId,
                    @RequestParam("image_link") String url,
                    @RequestParam("file") MultipartFile file) throws NumberFormatException, IOException {
        

        CourseModel.Course course = courseService.addNewCourse(title, category, level, description, chapter, price, userId, file, url);

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Success");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(course);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PostMapping("/add/course/{id}")
    public ResponseEntity addCourseChapter(
                        @PathVariable(name = "id") Long id, @RequestParam("title") String title,
                        @RequestParam(name = "video") MultipartFile video, 
                        @RequestParam(name = "thumbnail") MultipartFile imageThumbnail,
                        @RequestParam(required = false, name = "pdf") MultipartFile pdf) {

        
                        System.out.println(pdf);
                        System.out.println(video);
                        System.out.println(imageThumbnail);
        
        String message = courseService.addCourseDetail(id, title, video, imageThumbnail, pdf);

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PostMapping("/complete")
    public ResponseEntity completeChapter(@RequestBody CompleteChapterModel data){
        System.out.println(data);
        String message = courseService.completeChapter(data.getCompleted(),data.getCourseid(),data.getUserid(), data.getTotal_chap());

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PostMapping("/ratecourse")
    public ResponseEntity rateCourse(@RequestBody RateCourseModel data){
        System.out.println(data);
        String message = courseService.rateCourse(data.getUserid(),data.getCourseid(),data.getRating(),data.getComment());

        ResponseWrapperModel wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }
}
