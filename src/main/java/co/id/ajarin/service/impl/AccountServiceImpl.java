package co.id.ajarin.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.entity.NotificationEntity;
import co.id.ajarin.entity.PrivateDiscEntity;
import co.id.ajarin.entity.StudentCourseEntity;
import co.id.ajarin.entity.StudentDiscEntity;
import co.id.ajarin.entity.composite.StudentCourseKey;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.entity.composite.StudentDiscKey;
import co.id.ajarin.mapper.CourseMapper;
import co.id.ajarin.mapper.TeacherMapper;
import co.id.ajarin.mapper.UserMapper;
import co.id.ajarin.model.account.AccountLoginModel;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import co.id.ajarin.model.auth.AuthenticationModel;
import co.id.ajarin.model.dashboard.CourseModel;
import co.id.ajarin.model.dashboard.DiscussionModel;
import co.id.ajarin.model.dashboard.PrivateDiscModel;
import co.id.ajarin.model.dashboard.StudentCourseModel;
import co.id.ajarin.model.dashboard.StudentDiscModel;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.CourseRepository;
import co.id.ajarin.repository.DiscussionRepository;
import co.id.ajarin.repository.NotificationRepository;
import co.id.ajarin.repository.StudentCourseRepository;
import co.id.ajarin.repository.StudentDiscRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.repository.PrivateDiscRepository;
import co.id.ajarin.security.jwt.JwtService;
import co.id.ajarin.service.AccountService;

@Service
@Transactional
@SuppressWarnings({ "deprecation", "null" })
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRegistrationRepository repository;
    @Autowired
    TeacherRepository repositoryTeacher;
    @Autowired
    DiscussionRepository discussionRepository;
    @Autowired
    StudentDiscRepository studentDiscRepository;
    @Autowired
    CourseRepository courseRepository;
    @Autowired
    StudentCourseRepository studentCourseRepository;
    @Autowired
    PrivateDiscRepository PrivateDiscRepository;
    @Autowired
    NotificationRepository notificationRepository;

    private final JwtService jwtService;

    //god
    // private static final String UPLOAD_PATH ="C:/Users/Ivander/OneDrive/Documents/Ajarin/web-react/public/assets/";

    //bv
    private static final String UPLOAD_PATH ="C:/Users/Lenovo/OneDrive/Desktop/React/Ajarin-Web-React/public/assets/";

    public AccountServiceImpl(AccountRegistrationRepository repository, JwtService jwtService) {
        super();
        this.repository = repository;
        this.jwtService = jwtService;
    }

    @Override
    public AccountRegisterEntity save(AccountRegistrationModel account) {
        AccountRegisterEntity newAccount = new AccountRegisterEntity(
            account.getId(),
            account.getFirstName(),
            account.getLastName(),
            account.getEmail(),
            account.getPassword(),
            "ROLE_Student",
            account.getGender(),
            account.getCity(),
            account.getCountry(),
            account.getSchool(),
            account.getAge(),
            account.getPhoneNumber(),
            account.getEducation(),
            account.getStudentdisc_list(),
            account.getStudentcourse_list(),
            null,
            null,
            null
        );

        return repository.save(newAccount);
    }

    @Override
    public AccountRegisterEntity update(AccountModel account) {
        AccountRegisterEntity accountOld = repository.getById(account.getId());
        accountOld.setId(account.getId());
        accountOld.setFirstName(account.getFirstName());
        accountOld.setLastName(account.getLastName());
        accountOld.setEmail(account.getEmail());
        accountOld.setPassword(account.getPassword());
        accountOld.setRole(account.getRole());
        accountOld.setGender(account.getGender());
        accountOld.setCity(account.getCity());
        accountOld.setCountry(account.getCountry());
        accountOld.setSchool(account.getSchool());
        accountOld.setAge(account.getAge());
        accountOld.setPhoneNumber(account.getPhoneNumber());
        accountOld.setEducation(account.getEducation());
        // accountOld.setStudentdisc_list(account.getStudentdisc_list());
        accountOld.setCoin(account.getCoin());
        // accountOld.setPic_name(account.getPic_name());
        // accountOld.setPic_type(account.getPic_type());

        return repository.save(accountOld);
    }

    @Override
    public String login(String email, String password) {
        AccountRegisterEntity account = repository.findByEmail(email);
        if(account != null) {
            Boolean isPasswordTrue = password.matches(account.getPassword());
            if(isPasswordTrue) {
                return "Login Success";
            }
        }
        return "Login Failed";
    }

    @Override
    public AccountLoginModel findByEmail(String email) {

        AccountRegisterEntity account = repository.findByEmail(email);
        
        if(account != null) {
            AccountLoginModel accountLogin = new AccountLoginModel(account);
            return accountLogin;
        }
        return null;
    }

    @Override
    public AuthenticationModel authenticated(String email) {
        AccountRegisterEntity account = repository.findByEmail(email);

        
        if(account != null) {
            AccountLoginModel accountLogin = new AccountLoginModel(account);
            String jwtToken = jwtService.generateToken(accountLogin);
            return AuthenticationModel.builder().token(jwtToken).build();
        }
        return null;
    }

    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public AccountModel getAccountbyEmail(String email) {
        AccountRegisterEntity account = repository.findByEmail(email);

        List<NotificationEntity> notif = notificationRepository.getNotifData(account.getId());

        List<PrivateDiscEntity> private_disc = PrivateDiscRepository.getPrivateDisc(account.getId());

        AccountModel accounts = UserMapper.mapToAccountModel(account, notif, private_disc);


        for(StudentDiscModel disc : accounts.getStudentdisc_list()){
            if(disc.getDiscussion().getCategory().getCategory_id() != 99){
                Long id = disc.getDiscussion().getDisc_id();
                disc.getDiscussion().setJoinedParticipant(studentDiscRepository.getCountDisc(id));
            }
        }

        return accounts;
    }

    @Override
    public AccountModel findById(Long id) {
        AccountRegisterEntity account = repository.findById(id).get();

        // return new AccountModel(account);
        return UserMapper.mapToAccountModel(account, null, null);
    }

    @Transactional
    @Override
    public String joinDiscussion(String email, Long disc_id) {

        AccountRegisterEntity account = repository.findByEmail(email);

        Long user_id = account.getId();

        DiscussionEntity disc =  discussionRepository.getById(disc_id);

        if(disc.getTeacher().getUser().getId() == account.getId()) {
            return "Cannot Join Your Created Discussion";    
        }
        disc.getTeacher().getUser().setCoin(disc.getTeacher().getUser().getCoin() + disc.getDisc_price());
        

        account.setCoin(account.getCoin() - disc.getDisc_price());

        StudentDiscKey key = new StudentDiscKey(user_id, disc_id);

        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("Asia/Jakarta")).toInstant());

        
        StudentDiscEntity student_disc = new StudentDiscEntity(key, account,disc,"Ongoing", date);

        studentDiscRepository.save(student_disc);

        return "Joined Success";
        
        
    }

    @Transactional
    @Override
    public String joinCourse(String email, Long course_id) {

        AccountRegisterEntity account = repository.findByEmail(email);

        Long user_id = account.getId();

        CourseEntity course =  courseRepository.getById(course_id);

        System.out.println(courseRepository.getById(course_id).getTeacher().getUser().getId());

        if(course.getTeacher().getUser().getId() == account.getId()) {
            return "Failed";    
        }

        course.getTeacher().getUser().setCoin(course.getTeacher().getUser().getCoin() + course.getCourse_price());

        System.out.println(course.getTotal_course_sold());
        if(course.getTotal_course_sold() == null) {
            course.setTotal_course_sold(1);
        } else {
            course.setTotal_course_sold(course.getTotal_course_sold() + 1);
        }

        courseRepository.save(course);

        account.setCoin(account.getCoin() - course.getCourse_price());

        StudentCourseKey key = new StudentCourseKey(user_id, course_id);

        Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.of("Asia/Jakarta")).toInstant());
        
        StudentCourseEntity student_course = new StudentCourseEntity(key, account,course,"Ongoing", null,null,null,date);

        studentCourseRepository.save(student_course);

        return "Joined Success";
        
        
    }

    @Override
    public AccountRegisterEntity store(String email, MultipartFile file) throws IOException {
        AccountRegisterEntity account = repository.findByEmail(email);


        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        Boolean exist = new File(UPLOAD_PATH + filename).exists();
        if(!exist) {
            try{
                file.transferTo(new File(UPLOAD_PATH + filename));
                System.out.println("lewat brohhh");
                repository.saveProfilepic(filename, email);
                System.out.println("lewat query");
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }
        
        account.setProfile_pic(filename);
        return repository.save(account);
    }

    // @Override
    // public AccountRegisterEntity getFile(Long id) {
    //     return repository.findById(id).get();
    // }

    @Override
    public String registerTeacher(AccountModel account, MultipartFile file, String achievement,
            String education, String experience, String description) throws IOException {
        
        AccountRegisterEntity accountEntity = repository.findByEmail(account.getEmail());

        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        TeacherEntity teacher = new TeacherEntity();
        teacher.setAchievement(achievement);
        teacher.setUser(accountEntity);
        teacher.setData(file.getBytes());
        teacher.setProfile_description(description);
        teacher.setEducation(education);
        teacher.setExperience(experience);
        teacher.setPoints(0);
        repositoryTeacher.save(teacher);

        return "Success";
    }

    @Override
    public List<TeacherModel.Teacher> getAllTeacher() {
        List<TeacherEntity> teachers = repositoryTeacher.findAll();
        List<TeacherModel.Teacher> usedTeachers= teachers.stream().map((teacher) -> TeacherMapper.mapToTeacherModel(teacher,null)).collect(Collectors.toList());
        for(TeacherModel.Teacher teach : usedTeachers){
            List<Long> courseId = teach.getCourses().stream().map( (course) -> course.getCourse_id()).filter((x) -> x != null ).collect(Collectors.toList());

            if(!courseId.isEmpty()){
                Double ratings = studentCourseRepository.getRatingData(courseId);
                List<StudentCourseEntity> studentCourse = studentCourseRepository.getCourseData(courseId);
                List<StudentCourseModel> studentcourse = new ArrayList<>();
                if(!studentCourse.isEmpty()){
                    for (StudentCourseEntity studentCourseEntity : studentCourse){
                        TeacherModel.Teacher teacher = TeacherMapper.mapToTeacherModelNoR(studentCourseEntity.getCourse().getTeacher());
                        CourseEntity course = studentCourseEntity.getCourse();
                        CourseModel.Course courseModel = CourseMapper.mapToCourseModelNoR(course,teacher);
                        AccountModel account = UserMapper.mapToAccountModelNoR(studentCourseEntity.getUser());
                        studentcourse.add(new StudentCourseModel( 
                            courseModel,
                            studentCourseEntity.getStatus(),
                            studentCourseEntity.getCompleted_chap(),
                            studentCourseEntity.getRating(),
                            studentCourseEntity.getComment(),
                            studentCourseEntity.getJoined_date(),
                            account
                        ));
                    }
                    teach.setCourse_list(studentcourse);       
                } 
                System.out.println(ratings);
                teach.setTeacher_rating(ratings);
            }
        }

        return usedTeachers;
    }

    @Override
    public Teacher getTeacherByUserEmail(String email) {
        Long user = repository.findByEmail(email).getId();
        TeacherEntity test = repositoryTeacher.getTeacherById(user);

        // boolean isAlreadyRegisteredAsTeacher = false;
        // Teacher teacher = null;
        // List<TeacherEntity> teachers = repositoryTeacher.findAll();
        // for (TeacherEntity teacherEntity : teachers) {
        //     if(teacherEntity.getUser().getId() == id) {
        //         isAlreadyRegisteredAsTeacher = true;
        //         teacher = TeacherMapper.mapToTeacherModel(teacherEntity);
        //         break;
        //     }
        // }
        // System.out.println("walaw e");
        return TeacherMapper.mapToTeacherModel(test,null);
    }

    @Override
    public TeacherEntity getTeacher(Long id) {
       return repositoryTeacher.getReferenceById(id);
    }

    @Override
    public TeacherEntity getCvFile(Long id) {
        return repositoryTeacher.findById(id).get();
    }

    @Override
    public String subscribedLecturer(Long id, String email) {
        boolean newSubscribed = true;
        TeacherEntity teacher = repositoryTeacher.findById(id).get();
        AccountRegisterEntity account = repository.findByEmail(email);

        if(account.getId() == teacher.getUser().getId()) {
            newSubscribed = false;
        }

        for (TeacherEntity teacherList : account.getSubscribed_lecturer()) {
            if(teacherList.getTeacher_id() == teacher.getTeacher_id()) {
                newSubscribed = false;
            }
            if(account.getId() == teacher.getUser().getId()) {
                newSubscribed = false;
            }
        }
        if(newSubscribed) {
            teacher.getSubscribed_student().add(account);
            return "Success";
        } else {
            return "Failed";
        }
    }

    @Override
    public String unSubscribedLecturer(Long id, String email) {
        TeacherEntity teacher = repositoryTeacher.findById(id).get();
        AccountRegisterEntity account = repository.findByEmail(email);

        teacher.getSubscribed_student().remove(account);

        return "Success";
    }

    @Override
    public StudentCourseModel getStudentCourseData(Long userid, Long courseid) {
        // StudentCourseKey key = new StudentCourseKey(userid, courseid);
        StudentCourseEntity studentcourse = studentCourseRepository.getData(userid, courseid);

        System.out.println(studentcourse + "eheheheh");

        TeacherModel.Teacher teacher = TeacherMapper.mapToTeacherModelNoR(studentcourse.getCourse().getTeacher());
        CourseEntity course = studentcourse.getCourse();
        CourseModel.Course courseModel = CourseMapper.mapToCourseModelNoR(course,teacher);

        StudentCourseModel studentcourse_used = new StudentCourseModel(courseModel, studentcourse.getStatus(), studentcourse.getCompleted_chap(), studentcourse.getRating(), studentcourse.getComment(), studentcourse.getJoined_date(),null);
        return studentcourse_used;
    }

    @Override
    public String addPrivateDisc(PrivateDiscModel disc, Long userid, Long teacherid) {
        AccountRegisterEntity user = repository.getById(userid);
        TeacherEntity teacher = repositoryTeacher.getById(teacherid);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-DD");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");

        SimpleDateFormat sdf =  new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a");
        String today = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a").format(new java.util.Date());

        // try {
        //     java.util.Date dateToday = sdf.parse(today);
        //     java.util.Date utilDate = format.parse(disc.getDate());
        //     if(dateToday.compareTo(utilDate) == 0) {
        //         java.util.Date utilStart_time = timeFormat.parse(disc.getStart_time()+":00");
        //         if(dateToday.getTime() >= utilStart_time.getTime()) {
        //             return "error";
        //         }
        //     } else if(dateToday.compareTo(utilDate) > 0) {
        //         return "error";
        //     }
        // } catch (ParseException e) {
        //     e.printStackTrace();
        //     return "Error";
        // }
        // TODO Auto-generated method stub
        PrivateDiscEntity privateDisc = new PrivateDiscEntity();
        privateDisc.setTitle(disc.getTitle());
        privateDisc.setSubject(disc.getSubject());
        privateDisc.setEducation(disc.getEducation());
        privateDisc.setDifficulty(disc.getDifficulty());

        try {
            System.out.println(disc.getDate() + "date 0");
            java.util.Date utilDate = format.parse(disc.getDate());
            System.out.println(utilDate + "date pertama");
            java.sql.Date date = new java.sql.Date(utilDate.getTime());
            System.out.println(date + "date dua");
            privateDisc.setPrivate_date(date);

            java.util.Date utilStart_time = timeFormat.parse(disc.getStart_time()+":00");
            Time start_time = new java.sql.Time(utilStart_time.getTime());
            privateDisc.setDate_start(start_time);

            java.util.Date utilEnd_time = timeFormat.parse(disc.getEnd_time()+":00");
            Time end_time = new java.sql.Time(utilEnd_time.getTime());

            System.out.println(start_time + " " + end_time);
            privateDisc.setDate_end(end_time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        privateDisc.setUser(user);
        privateDisc.setStatus("Requested");
        privateDisc.setOffered_coin(disc.getCoin());
        privateDisc.setTeacher(teacher);
        privateDisc.setLink("https://zoom");

        PrivateDiscRepository.save(privateDisc);
        
        return "Private Discussion Requested!";
    }

    @Transactional
    @Override
    public String acceptOrReject(Long privateid, String status, String subject) {

        AccountRegisterEntity user = PrivateDiscRepository.getReferenceById(privateid).getUser();
        String message = "";
        if(status.equals("Accepted")){
            PrivateDiscRepository.setStatus(privateid, status);
            message = "Request Accepted!";
        }
        else if(status.equals("Rejected")){
            PrivateDiscRepository.deleteById(privateid);
            message = "Request Rejected Successfully!";
        }

        NotificationEntity notif = new NotificationEntity();
        notif.setMessage("Your private request about " + subject.replaceAll("\"", "") + " has been " + status.toLowerCase());
        notif.setUser(user);
        notif.setIsRead(false);
        notificationRepository.save(notif);

        return message;
    }

    @Override
    public Teacher updateTeacher(Long id, MultipartFile file, String achievement, String education, String experience,
            String profileDescription) throws IOException {
        TeacherEntity teacher = repositoryTeacher.getTeacherById(id);

        if(achievement != null) {
            teacher.setAchievement(achievement);
        }
        teacher.setUser(teacher.getUser());
        if(file != null) {
            teacher.setData(file.getBytes());
        }
        if(profileDescription != null) {
            teacher.setProfile_description(profileDescription);
        }
        if(education != null) {
            teacher.setEducation(education);
        }
        if(experience != null) {
            teacher.setExperience(experience);
        }

        // teacher.setAchievement(achievement);
        // teacher.setUser(accountEntity);
        // teacher.setData(file.getBytes());
        // teacher.setProfile_description(description);
        // teacher.setEducation(education);
        // teacher.setExperience(experience);
        // teacher.setRating("0");
        // teacher.setPoints(0);
        repositoryTeacher.save(teacher);

        return TeacherMapper.mapToTeacherModel(teacher,null);
    }

    @Override
    public String deleteNotif(Long notif) {
        notificationRepository.deleteById(notif);
        return "Success";
    }
    
    @Transactional
    @Override
    public String readNotif(Long notif) {
        notificationRepository.setReadById(notif);
        return "Success";
    }

    
    
    
    
}
