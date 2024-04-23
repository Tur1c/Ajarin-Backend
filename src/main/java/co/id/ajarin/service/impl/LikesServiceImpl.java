package co.id.ajarin.service.impl;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.entity.LikesEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.mapper.ForumMapper;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.LikesModel;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.ForumRepository;
import co.id.ajarin.repository.LikesRepository;
import co.id.ajarin.repository.ReplyRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.service.LikesService;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Autowired
    private LikesRepository likesRepository;

    @Autowired 
    private TeacherRepository teacherRepository;

    @Autowired 
    private AccountRegistrationRepository accountRepository;

    @Transactional
    @Override
    public ForumReplyEntity likeReply(Long fr_id, String email) {
        ForumReplyEntity reply = replyRepository.findById(fr_id).orElseThrow(null);
        // AccountRegisterEntity user = userRepository.findByEmail(email);

        // Optional<LikesEntity> existingLike = likesRepository.findByReplyAndUser(reply, user);

        // long currentLikes = reply.getLikes().size();
        
        // if (existingLike.isPresent()) {
        //     likesRepository.delete(existingLike.get());
        //     reply.getLikes().remove(existingLike.get()); 
        //     reply.setFr_likes(currentLikes - 1);
        //     System.out.println("udah ada");
        // } else {
        //     LikesEntity like = new LikesEntity();
        //     like.setFr_id(fr_id);
        //     like.setEmail(email);
        //     likesRepository.save(like);
        //     reply.getLikes().add(like);
        //     reply.setFr_likes(currentLikes + 1);
        //     System.out.println("belum ada");
        // }

        return replyRepository.save(reply);
    }

    @Transactional
    @Override
    public String newLikes(Long fr_id, String email) {
        LikesEntity likes = new LikesEntity();
        likes.setFr_id(fr_id);
        likes.setEmail(email);

        Long userId = accountRepository.findByEmail(email).getId();

        TeacherEntity teacher = teacherRepository.getTeacherById(userId);

        teacher.setPoints(teacher.getPoints() + 1);

        likesRepository.save(likes);
        return "Done";
    }

    @Override
    @Transactional
    public String deleteLikes(Long fr_id, String email) {

        LikesEntity likesToRemove = likesRepository.findByFrId(fr_id, email);
        likesRepository.delete(likesToRemove);

        System.out.println("dapet apa coba " + likesToRemove);

        Long userId = accountRepository.findByEmail(email).getId();

        TeacherEntity teacher = teacherRepository.getTeacherById(userId);

        teacher.setPoints(teacher.getPoints() - 1);

        return "done";
    }

    @Override
    @Transactional
    public ForumModel.Forum findForumRelated(Long question_id) {
        Optional<ForumEntity> forumRelated = forumRepository.findById(question_id);

        ForumEntity forumEntity = new ForumEntity();

        if(forumRelated.isPresent()){
            forumEntity = forumRelated.get();
        }

        return ForumMapper.maptoForumModel(forumEntity);
    }

    
}
