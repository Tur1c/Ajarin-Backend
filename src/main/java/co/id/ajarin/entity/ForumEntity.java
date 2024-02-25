package co.id.ajarin.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "forum")
public class ForumEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long question_id;

    @Column(name = "question_title")
    private String question_title;

    @Column(name = "question_desc")
    private String question_desc;

    @Column(name = "question_level")
    private String question_level;
    
    @Column(name = "question_image")
    private String question_image;

    @Column(name = "total_comment")
    private Integer total_comment;

    @Column(name = "created_date")
    private Timestamp created_date;

    @ManyToOne
    //1 category many forum
    //1 forum one category
    @JoinColumn(name="question_category")
    private CategoryEntity category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AccountRegisterEntity user;

}
