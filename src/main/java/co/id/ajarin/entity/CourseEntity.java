package co.id.ajarin.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
 
    public CourseEntity(Integer course_price, String course_chapter, String course_title, String course_description,
            String course_level, String filename,  Integer total_course_sold, CategoryEntity category,
            TeacherEntity teacher) {
        this.course_price = course_price;
        this.course_chapter = course_chapter;
        this.course_title = course_title;
        this.course_description = course_description;
        this.course_level = course_level;
        this.course_image = filename;
        this.total_course_sold = total_course_sold;
        this.category = category;
        this.teacher = teacher;
    }

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

    // @Lob
    // @JsonIgnore
    // @Column(name = "course_image_data")
    // private byte[] course_image_data;

    @Column(name = "course_sold")
    private Integer total_course_sold;

    @ManyToOne
    @JoinColumn(name="course_category")
    private CategoryEntity category;

    // @JsonBackReference(value = "course_detail")
    @OneToMany(mappedBy = "course_id")
    private List<CourseDetailEntity> course_details;

    @ManyToOne
    @JsonBackReference(value = "teacher_course")
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

}
