package co.id.ajarin.service.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.service.DiscussionService;

@Service
public class DiscussionServiceImpl implements DiscussionService {

    @Autowired
    private DiscussionRepository discussionRepository;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    private CategoryRepository categoryRepository; 

    @Autowired
    private TeacherRepository teacherRepository; 

    @Override
    @Transactional
    public List<Discussion> getAllDiscussion() {
        List<DiscussionEntity> discussions = discussionRepository.findAll();

        System.out.println("yey");
        // Query query= em.crea("SELECT COUNT(*) FROM DISCUSSION");
        System.out.println("lewat query");
        // int testest = ((Number) query.getSingleResult()).intValue();
        System.out.println("lewat test");
        // System.out.println(testest + "ehehe");
        System.out.println("siu lewat");
        Long test =  discussionRepository.count();
        System.out.println(test + "wuttt");

        
        // DiscussionEntity discussion = discussionRepository.getById(1);

        return discussions.stream().map( (discussion) -> DiscussionMapper.maptoDiscussionModel(discussion)).collect(Collectors.toList());
        // return null;
    }

    @Override
    public String addDiscussion(String title, String categoryName, String level, String description, Date startDate,
            Date endDate, String maxParticipant, String price, String urlLink, MultipartFile file, Long userId) throws IOException {

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
            System.out.println(categoryEntity.getCategory_name().equals(categoryName));
            if(categoryEntity.getCategory_name().equals(categoryName)) {
                category = categoryEntity;
                break;
            }
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
            file.getBytes(),
            level,
            category,
            teacher
        );

        discussionRepository.save(discussionEntity);
        return "Success";
    }

    @Override
    public DiscussionEntity getImage(String name) throws UnsupportedEncodingException {
        List<DiscussionEntity> discussions = discussionRepository.findAll();

        for (DiscussionEntity discussionEntity : discussions) {
            if(discussionEntity.getDisc_url() == null) {
                continue;
            }
            System.out.println(name);
            String url = URLDecoder.decode(discussionEntity.getDisc_url(), "ISO-8859-1");
            System.out.println(url);
            if(url.contains(name) ) {
                return discussionEntity;
            }
        }

        return null;
    }

    
}
