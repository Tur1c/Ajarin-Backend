package co.id.ajarin.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.mapper.ForumMapper;
import co.id.ajarin.model.forum.ForumModel.Forum;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.CategoryRepository;
import co.id.ajarin.repository.ForumRepository;
import co.id.ajarin.service.ForumService;

@Service
public class ForumServiceImpl implements ForumService{

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    AccountRegistrationRepository accountRepository;  

    @Autowired
    CategoryRepository categoryRepository;  

    @Override
    @Transactional
    public List<Forum> getAllForum() {
        List<ForumEntity> forums = forumRepository.findAll();
        // TODO Auto-generated method stub
        return forums.stream().map((forum) -> 
                ForumMapper.maptoForumModel(forum))
                .collect(Collectors.toList());
    }
    
    @Transactional
    @Override
    public ForumEntity inputForum(String question_title, String question, String question_category, String question_level, String user_email) {
        AccountRegisterEntity account = accountRepository.findByEmail(user_email);

        CategoryEntity category = categoryRepository.findByName(question_category);

        long timesNow = Instant.now().toEpochMilli();
        Timestamp timestamp = new Timestamp(timesNow);

        ForumEntity forum = new ForumEntity();
        forum.setQuestion_title(question_title);
        forum.setQuestion_desc(question);
        forum.setQuestion_level(question_level);
        forum.setQuestion_image(null);
        forum.setTotal_comment(0);
        forum.setCreated_date(timestamp);
        forum.setCategory(category);
        forum.setUser(account);
        forum.setForum_replies(null);

        forumRepository.save(forum);

        return forum;
    }
}
