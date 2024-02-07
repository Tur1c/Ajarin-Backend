package co.id.ajarin.entity;

import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseid;

    @Column(name = "coursename")
    private String coursename;

    @Column(name = "price")
    private Integer price;

    @Column(name = "coursetime")
    private Time coursetime;

    @Column(name = "coursedate")
    private Date coursedate;

    @Column(name = "description")
    private String description;

    @Column(name = "coursetype")
    private String coursetype;

    @Column(name = "categoryid")
    private String categoryid;
}
