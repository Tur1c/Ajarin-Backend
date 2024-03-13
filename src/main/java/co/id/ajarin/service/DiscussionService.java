package co.id.ajarin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import co.id.ajarin.entity.DiscussionEntity;
import co.id.ajarin.model.dashboard.DiscussionModel;
import co.id.ajarin.model.dashboard.DiscussionModel.Discussion;

public interface DiscussionService {
    List<DiscussionModel.Discussion> getAllDiscussion();

    // String addDiscussion(DiscussionModel.Discussion discussion, MultipartFile file) throws IOException;

    String addDiscussion(String title, String categoryName, String level, String description, Date startDate,
            Date endDate, String maxParticipant, String price, String urlLink, MultipartFile file, Long userId) throws IOException;

    DiscussionEntity getImage(String name) throws UnsupportedEncodingException;
}
