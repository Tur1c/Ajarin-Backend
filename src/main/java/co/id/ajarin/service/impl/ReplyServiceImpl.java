package co.id.ajarin.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.entity.LikesEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.mapper.ForumMapper;
import co.id.ajarin.mapper.ReplyMapper;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.ReplyModel;
import co.id.ajarin.model.forum.ReplyModel.Reply;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.ForumRepository;
import co.id.ajarin.repository.ReplyRepository;
import co.id.ajarin.repository.TeacherRepository;
import co.id.ajarin.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService 
{
    @Autowired
    ReplyRepository replyRepository;
    @Autowired
    ForumRepository forumRepository;
    @Autowired
    AccountRegistrationRepository accountRepository;  
    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Reply> getAllReply() {
        List<ForumReplyEntity> replies = replyRepository.findAll();
        return replies.stream().map((reply)->ReplyMapper.maptoReplyModel(reply)).collect(Collectors.toList());
    }

    @Override
    public ForumModel.Forum findForumRelated(Long question_id) {
        Optional<ForumEntity> forumRelated = forumRepository.findById(question_id);

        ForumEntity forumEntity = new ForumEntity();

        if(forumRelated.isPresent()){
            forumEntity = forumRelated.get();
        }

        return ForumMapper.maptoForumModel(forumEntity);
    }

    @SuppressWarnings("deprecation")
    @Override
    public ReplyModel.Reply findById(Long fr_id) {
        ForumReplyEntity reply = replyRepository.getById(fr_id);
        System.out.println(reply+"awokwokwokwo");
        // return new AccountModel(account);
        return ReplyMapper.maptoReplyModel(reply);
    }
    
    @SuppressWarnings("deprecation")
    @Override
    public ForumReplyEntity update(ReplyModel.Reply reply) {
        ForumReplyEntity replyOld = replyRepository.getById(reply.getFr_id());
        replyOld.setFr_reply(reply.getFr_reply());
        return replyRepository.save(replyOld);
    }

    @Override
    public ForumEntity deleteReply(Long question_id, Long fr_id) {
        ForumEntity forum = forumRepository.findById(question_id).get();
        ForumReplyEntity reply = replyRepository.findById(fr_id).get();
        
        forum.getForum_replies().remove(reply);
        replyRepository.delete(reply);
        

        return forum;
    }

    // @Override
    // public String save (String reply, Long forum_id, Long user_id, Timestamp timestamp){
    //     ForumReplyEntity newReply = new ForumReplyEntity(
    //         forum_id,
    //         reply,
    //         new Long(0),
    //         accountRepository.getById(user_id),
    //         timestamp
    //     ); 
    //     replyRepository.save(newReply);
        
    //     return "success";
    // }

    @SuppressWarnings("deprecation")
    @Transactional
    @Override
    public ReplyModel.Reply inputReply(String reply, String email, Long forum_id) {
        
        AccountRegisterEntity account = accountRepository.findByEmail(email);

        ForumEntity forum = forumRepository.getById(forum_id);

        TeacherEntity teacher = teacherRepository.getTeacherById(account.getId());

        if(teacher != null) {
            teacher.setPoints(teacher.getPoints() + 5);
        }

        forum.setTotal_comment(forum.getTotal_comment() + 1);

        long timesNow = Instant.now().toEpochMilli();
        Timestamp timestamp = new Timestamp(timesNow);
        List<LikesEntity> likes = new ArrayList<>();

        ForumReplyEntity forum_reply = new ForumReplyEntity();
        forum_reply.setFr_likes((long) 0);
        forum_reply.setFr_replied_at(timestamp);
        forum_reply.setUser(account);
        forum_reply.setQuestion_id(forum_id);
        forum_reply.setFr_reply(reply);
        forum_reply.setLikes(likes);
        

        replyRepository.save(forum_reply);

        return ReplyMapper.maptoReplyModel(forum_reply);
    }

    
}
