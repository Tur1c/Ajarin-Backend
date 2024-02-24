package co.id.ajarin.mapper;

import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.model.forum.ForumModel;

public class ForumMapper {

    public static ForumModel.Forum maptoForumModel (ForumEntity forum){
        return new ForumModel.Forum(
            forum.getQuestion_id(),
            forum.getQuestion_title(),
            forum.getQuestion_desc(),
            forum.getQuestion_level(),
            forum.getQuestion_image(),
            forum.getTotal_comment(),
            forum.getCreated_date(),
            forum.getQuestion_category(),
            forum.getUser_id()
        );
    }

    public static ForumEntity maptoForumEntity (ForumModel.Forum forumModel){
        return new ForumEntity(
            forumModel.getQuestion_id(),
            forumModel.getQuestion_title(),
            forumModel.getQuestion_desc(),
            forumModel.getQuestion_level(),
            forumModel.getQuestion_image(),
            forumModel.getTotal_comment(),
            forumModel.getCreated_date(),
            forumModel.getQuestion_category(),
            forumModel.getUser_id()
        );
    }
}
