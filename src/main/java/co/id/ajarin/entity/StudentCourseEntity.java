package co.id.ajarin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import co.id.ajarin.entity.composite.StudentCourseKey;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "student_course")
public class StudentCourseEntity {

    @EmbeddedId
    @JsonIgnore
    private StudentCourseKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id")
    private AccountRegisterEntity user;

    @ManyToOne
    @MapsId("course_id")
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "status")
    private String status;

    @Column(name = "completed_chap")
    private String completed_chap;

    @Column(name = "rating")
    private Float rating;

    @Column(name = "comment")
    private String comment;

    @Column(name = "joined_date")
    private Date joined_date;

}
