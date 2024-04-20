package co.id.ajarin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "likes")
public class LikesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likes_id;

//     @ManyToOne
//     @JoinColumn(name = "fr_id")
//     private ForumReplyEntity reply;

//     @ManyToOne
//     @JoinColumn(name = "user_id")
//     private AccountRegisterEntity user;
    private Long fr_id;

    private String email;
}
