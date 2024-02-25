package co.id.ajarin.entity;

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
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "course")
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_id;

    @Column(name = "course_price")
    private Integer course_price;

    @Column(name = "course_chapter")
    private String course_chapter;

    @Column(name = "course_title")
    private String course_title;

    @Column(name = "course_description")
    private String course_description;

    @Column(name = "course_level")
    private String course_level;

    @Column(name = "course_image")
    private String course_image;

    @ManyToOne
    @JoinColumn(name="course_category")
    private CategoryEntity category;

}
