package co.id.ajarin.mapper;

import java.util.ArrayList;
import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.ReplyModel;

public class ForumMapper {

    public static ForumModel.Forum maptoForumModel (ForumEntity forum){
        AccountRegisterEntity acc = forum.getUser();

        System.out.println(forum.getForum_replies());

        List<ReplyModel.Reply> forum_replies = new ArrayList<>();
        for(ForumReplyEntity reply : forum.getForum_replies()){
            ReplyModel.Reply forum_reply = ReplyMapper.maptoReplyModel(reply);

            forum_replies.add(forum_reply);
        }
        acc = new AccountRegisterEntity(acc.getId(), acc.getFirstName(), acc.getLastName(), acc.getEmail(), acc.getPassword(), acc.getRole(), acc.getGender(), acc.getCity(), acc.getCountry(), acc.getSchool(), acc.getAge(), acc.getPhoneNumber(), acc.getEducation(), acc.getCoin(), acc.getPic_name(), acc.getPic_type(), acc.getData());
        return new ForumModel.Forum(
            forum.getQuestion_id(),
            forum.getQuestion_title(),
            forum.getQuestion_desc(),
            forum.getQuestion_level(),
            forum.getQuestion_image(),
            forum.getTotal_comment(),
            forum.getCreated_date(),
            forum.getCategory(),
            forum_replies,
            acc
        );
    }

    // public static ForumEntity maptoForumEntity (ForumModel.Forum forumModel){
    //     return new ForumEntity(
    //         forumModel.getQuestion_id(),
    //         forumModel.getQuestion_title(),
    //         forumModel.getQuestion_desc(),
    //         forumModel.getQuestion_level(),
    //         forumModel.getQuestion_image(),
    //         forumModel.getTotal_comment(),
    //         forumModel.getCreated_date(),
    //         forumModel.getCategory_id(),
    //         forumModel.getForum_replies(),
    //         forumModel.getUser_id()
    //     );
    // }
}
