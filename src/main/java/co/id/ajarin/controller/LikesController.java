package co.id.ajarin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.InputLikesModel;
import co.id.ajarin.model.forum.ReplyModel;
import co.id.ajarin.service.LikesService;

@RestController
@PreAuthorize("hasRole('Student') or hasRole('Teacher')")
@RequestMapping("/api/likes")
public class LikesController {

    private LikesService likesService;

    public LikesController(LikesService likesService) {
        this.likesService = likesService;
    }

    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PostMapping("add")
    public ResponseEntity<ResponseWrapperModel<ReplyModel.Response>> addLikes(@RequestBody InputLikesModel inputLikesModel) {
        String message  = likesService.newLikes(inputLikesModel.getFr_id(), inputLikesModel.getEmail());

        ForumModel.Forum forumRelated = likesService.findForumRelated(inputLikesModel.getQuestion_id());

        ReplyModel.Response response = new ReplyModel.Response(forumRelated.getForum_replies());

        ResponseWrapperModel<ReplyModel.Response> wrapperModel = new ResponseWrapperModel<>();

        System.out.println("msggg"+message);
        System.out.println("forumnya"+forumRelated);
        System.out.println("responsenya"+response);
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage(message);
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PostMapping("/delete")
    public ResponseEntity<ResponseWrapperModel<ReplyModel.Response>> deleteLikes(@RequestBody InputLikesModel inputLikesModel) {
        
        String delete = likesService.deleteLikes(inputLikesModel.getFr_id(), inputLikesModel.getEmail());

        ForumModel.Forum forumRelated = likesService.findForumRelated(inputLikesModel.getQuestion_id());

        ReplyModel.Response response = new ReplyModel.Response(forumRelated.getForum_replies());

        ResponseWrapperModel<ReplyModel.Response> wrapperModel = new ResponseWrapperModel<>();

        System.out.println("forumnya"+forumRelated);
        System.out.println("responsenya"+response);
        
        ErrorRepository error = new ErrorRepository();
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PostMapping("/{fr_id}")
    public ResponseEntity<ForumReplyEntity> likeReply(@PathVariable Long fr_id, @RequestBody InputLikesModel likesModel) {
        ForumReplyEntity updatedReply = likesService.likeReply(fr_id, likesModel.getEmail());

        return ResponseEntity.ok(updatedReply);
    }
}
