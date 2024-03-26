package co.id.ajarin.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.time.Instant;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.mapper.ReplyMapper;
import co.id.ajarin.model.forum.ReplyModel;
import co.id.ajarin.model.forum.ReplyModel.Reply;
import co.id.ajarin.repository.AccountRegistrationRepository;
import co.id.ajarin.repository.ForumRepository;
import co.id.ajarin.repository.ReplyRepository;
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

    @Override
    public List<Reply> getAllReply() {
        List<ForumReplyEntity> replies = replyRepository.findAll();
        return replies.stream().map((reply)->ReplyMapper.maptoReplyModel(reply)).collect(Collectors.toList());
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

    @Transactional
    @Override
    public ForumReplyEntity inputReply(String reply, String email, Long forum_id) {
        
        AccountRegisterEntity account = accountRepository.findByEmail(email);

        long timesNow = Instant.now().toEpochMilli();
        Timestamp timestamp = new Timestamp(timesNow);

        ForumReplyEntity forum_reply = new ForumReplyEntity();
        forum_reply.setFr_likes((long) 0);
        forum_reply.setFr_replied_at(timestamp);
        forum_reply.setUser(account);
        forum_reply.setQuestion_id(forum_id);
        forum_reply.setFr_reply(reply);

        replyRepository.save(forum_reply);

        return forum_reply;
    }

    
}
