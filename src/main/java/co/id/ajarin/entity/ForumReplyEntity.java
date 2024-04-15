package co.id.ajarin.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "forum_reply")
public class ForumReplyEntity {

    public ForumReplyEntity(Long fr_id, Long question_id, Long user_id, String fr_reply, Long fr_likes, Timestamp fr_replied_at) {
        this.fr_id = fr_id;
        this.question_id = question_id;
        // this.user_id = user_id;
        this.fr_reply = fr_reply;
        this.fr_likes = fr_likes;
        this.fr_replied_at = fr_replied_at;
    }

    public ForumReplyEntity(Long forum_id, String reply, Long likes, AccountRegisterEntity byId, Timestamp timestamp) {
        this.question_id = forum_id;
        this.fr_reply = reply;
        this.fr_likes = likes;
        this.user = byId;
        this.fr_replied_at = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "fr_id")
    private Long fr_id;

    // @Column(name = "question_id")
    private Long question_id;

    // @Column(name = "user_id")
    // private Long user_id;

    @Column(name = "fr_reply")
    private String fr_reply;

    @Column(name = "fr_likes")
    private Long fr_likes;

    // @JsonIgnore
    // @ManyToOne
    // @JoinColumn(name="question_id")
    // private ForumEntity forum;

    @JsonBackReference(value = "user_reply")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountRegisterEntity user;
    
    @Column(name = "fr_replied_at")
    private Timestamp fr_replied_at;
}
