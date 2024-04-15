package co.id.ajarin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;

import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.ErrorRepository;
import co.id.ajarin.model.ResponseWrapperModel;
import co.id.ajarin.model.forum.InputReplyModel;
import co.id.ajarin.model.forum.ReplyModel;
import co.id.ajarin.service.ReplyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/reply")
public class ReplyController {
    
    private ReplyService service;

    public ReplyController(ReplyService service) {
        this.service = service;
    }
    
    @PreAuthorize("hasRole('Student')")
    @PostMapping("add")
    public ForumReplyEntity createInputReply(@RequestBody InputReplyModel inputReply) {
        System.out.println(inputReply.getReply() + "aaaaaaaaa");
        System.out.println(inputReply.getEmail());
        System.out.println(inputReply.getForum_id());

        ForumReplyEntity newReply = service.inputReply(inputReply.getReply(), inputReply.getEmail(), inputReply.getForum_id());

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
        List<ReplyModel.Reply> replies = service.getAllReply();

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
    
}
