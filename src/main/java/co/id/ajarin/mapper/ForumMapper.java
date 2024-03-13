package co.id.ajarin.mapper;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.model.forum.ForumModel;

public class ForumMapper {

    public static ForumModel.Forum maptoForumModel (ForumEntity forum){
        AccountRegisterEntity acc = forum.getUser();
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
            acc
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
            forumModel.getCategory_id(),
            forumModel.getUser_id()
        );
    }
}
