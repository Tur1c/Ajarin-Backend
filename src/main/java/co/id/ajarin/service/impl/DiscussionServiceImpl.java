package co.id.ajarin.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.mapper.DiscussionMapper;
import co.id.ajarin.model.dashboard.DiscussionModel;
import co.id.ajarin.model.dashboard.DiscussionModel.Discussion;
import co.id.ajarin.repository.CategoryRepository;
import co.id.ajarin.repository.DiscussionRepository;
import co.id.ajarin.repository.StudentDiscRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.service.DiscussionService;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;

    @Autowired
    private StudentDiscRepository studentDiscRepository;

    @Autowired
    private CategoryRepository categoryRepository; 

    @Autowired
    private TeacherRepository teacherRepository; 

    // private static final String UPLOAD_PATH ="C:/Users/Lenovo/OneDrive/Desktop/React/Ajarin-Web-React/public/assets/";

    private static final String UPLOAD_PATH ="C:/Users/Ivander/OneDrive/Documents/Ajarin/web-react/public/assets/";

    @SuppressWarnings("unused")
    @Override
    @Transactional
    public List<Discussion> getAllDiscussion() {
        List<DiscussionEntity> discussions = discussionRepository.findAll();
        
        List<DiscussionModel.Discussion> usedDiscussions = discussions.stream().map( (discussion) -> DiscussionMapper.maptoDiscussionModel(discussion)).collect(Collectors.toList());
        
        Integer idx = 0;
        for(DiscussionModel.Discussion disc : usedDiscussions){
            Long id = usedDiscussions.get(idx).getDisc_id();
            usedDiscussions.get(idx).setJoinedParticipant(studentDiscRepository.getCountDisc(id));
            idx++;
        }

        return usedDiscussions;
    }

    @SuppressWarnings("null")
    @Override
    public String addDiscussion(String title, String categoryName, String level, String description, Date startDate,
            Date endDate, String maxParticipant, String price, String urlLink, MultipartFile file, Long userId) throws IOException {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        SimpleDateFormat sdf =  new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a");
        String today = new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a").format(new Date());

        // try {
        //     Date dateToday = sdf.parse(today);
        //     if(dateToday.compareTo(startDate) == 0) {
        //         if(dateToday.getTime() >= startDate.getTime()) {
        //             return "error";
        //         }
        //     } else if(dateToday.compareTo(startDate) > 0) {
        //         return "error";
        //     }
        // } catch (ParseException e) {
        //     e.printStackTrace();
        //     return "Error";
        // }

        List<CategoryEntity> categories = categoryRepository.findAll();
        CategoryEntity category = new CategoryEntity();

       TeacherEntity teacher = teacherRepository.getReferenceById(userId);
    //    System.out.println(userId + "data t eacher");

    //    List<TeacherEntity> teachers = teacherRepository.findAll();
    //     for (TeacherEntity teacherEntity : teachers) {
    //         if(teacherEntity.getUser().getId() == userId) {
    //             teacher = teacherEntity;
    //             break;
    //         }
    //     }

        
        for (CategoryEntity categoryEntity : categories) {
            System.out.println(categoryName);
            System.out.println(categoryEntity.getCategory_name().equals(categoryName));
            if(categoryEntity.getCategory_name().equals(categoryName)) {
                category = categoryEntity;
                break;
            }
        }

        
        try{
            file.transferTo(new File(UPLOAD_PATH + filename));
            
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

        DiscussionEntity discussionEntity = new DiscussionEntity(
            title,
            maxParticipant,
            price,
            startDate,
            endDate,
            startDate,
            description,
            filename,
            level,
            urlLink,
            category,
            teacher
        );

        discussionRepository.save(discussionEntity);
        return "Success";
    }

    // @Override
    // public DiscussionEntity getImage(String name) throws UnsupportedEncodingException {
    //     List<DiscussionEntity> discussions = discussionRepository.findAll();

    //     for (DiscussionEntity discussionEntity : discussions) {
    //         if(discussionEntity.getDisc_url() == null) {
    //             continue;
    //         }
    //         System.out.println(name);
    //         String url = URLDecoder.decode(discussionEntity.getDisc_url(), "ISO-8859-1");
    //         System.out.println(url);
    //         if(url.contains(name) ) {
    //             return discussionEntity;
    //         }
    //     }

    //     return null;
    // }

    
}
