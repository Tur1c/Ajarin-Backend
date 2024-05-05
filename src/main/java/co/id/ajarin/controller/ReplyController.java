package co.id.ajarin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.forum.EditReplyModel;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.InputReplyModel;
import co.id.ajarin.model.forum.ReplyModel;
import co.id.ajarin.model.forum.ReplyModel.Reply;
import co.id.ajarin.service.ForumService;
import co.id.ajarin.service.ReplyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    
    private ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }
    
    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PostMapping("add")
    public ReplyModel.Reply createInputReply(@RequestBody InputReplyModel inputReply) {
        System.out.println(inputReply.getReply() + "aaaaaaaaa");
        System.out.println(inputReply.getEmail());
        System.out.println(inputReply.getForum_id());

        ReplyModel.Reply newReply = replyService.inputReply(inputReply.getReply(), inputReply.getEmail(), inputReply.getForum_id());

        newReply.getUser_id().setProfile_pic(newReply.getUser_id().getProfile_pic());

        ResponseWrapperModel<ReplyModel.Reply> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());

        // String message = service.save(reply,forum_id,user_id,timestamp);
        System.out.println(inputReply + "aaaaaaaa");

        // error.setMessage(message);

        wrapperModel.setErrorSchema(error);

        System.out.println(ResponseEntity.status(error.getHttpCode()).body(wrapperModel) + "ayonimacaw");

        return newReply;
    }
    

    @GetMapping("")
    public ResponseEntity<ResponseWrapperModel<ReplyModel.Response>> getReplyList() {
        List<ReplyModel.Reply> replies = replyService.getAllReply();

        ReplyModel.Response response = new ReplyModel.Response(replies);
        
        ResponseWrapperModel<ReplyModel.Response> wrapperModel = new ResponseWrapperModel<>();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
    }

    @PreAuthorize("hasRole('Student') or hasRole('Teacher')")
    @PutMapping("/edit/{fr_id}")
    public ResponseEntity<ResponseWrapperModel<ReplyModel.Response>> updateReply(@PathVariable Long fr_id, @RequestBody EditReplyModel editReplyModel){
        ReplyModel.Reply replyOld = replyService.findById(fr_id);

        replyOld.setFr_reply(editReplyModel.getReply());

        replyService.update(replyOld);

        ForumModel.Forum forumRelated = replyService.findForumRelated(editReplyModel.getQuestion_id());

        
        ReplyModel.Response response = new ReplyModel.Response(forumRelated.getForum_replies());

        for (Reply forum : response.replies) {
            forum.getUser_id().setProfile_pic(forum.getUser_id().getProfile_pic());
        }

        ResponseWrapperModel<ReplyModel.Response> wrapperModel = new ResponseWrapperModel<>();

        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }

     @GetMapping("/delete")
     public ResponseEntity<ResponseWrapperModel<ReplyModel.Response>> deleteReply(@RequestParam(name = "question_id") Long question_id, @RequestParam("fr_id") Long fr_id){
        // String message = service.deleteReply(question_id, fr_id);

        ForumEntity forumWithDeletedReply = replyService.deleteReply(question_id, fr_id);

        // ForumModel.Forum forumModel = new ForumModel.Forum();
        // forumModel = ForumMapper.maptoForumModel(forumWithDeletedReply);

        // System.out.println(forumModel);

        ForumModel.Forum forumRelated = replyService.findForumRelated(question_id);

        
        ReplyModel.Response response = new ReplyModel.Response(forumRelated.getForum_replies());

        for (Reply forum : response.replies) {
            forum.getUser_id().setProfile_pic(forum.getUser_id().getProfile_pic());
        }

        ResponseWrapperModel<ReplyModel.Response> wrapperModel = new ResponseWrapperModel<>();

        // List<ReplyModel.Reply> newReplies = new ArrayList<>();

        // newReplies = forumModel.getForum_replies();
        
        ErrorRepository error = new ErrorRepository();
        error.setMessage("Sukses");
        error.setErrorCode("00");
        error.setHttpCode(HttpStatus.OK.value());
        wrapperModel.setErrorSchema(error);
        wrapperModel.setOutputSchema(response);

        return ResponseEntity.status(error.getHttpCode()).body(wrapperModel);
     }
}
