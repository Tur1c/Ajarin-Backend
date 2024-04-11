package co.id.ajarin.entity;

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
@Table(name = "course_detail")
public class CourseDetailEntity {
    
    public CourseDetailEntity(Long course_id, Long course_detail_chapter, String chapter_title, String chapter_video,
            String chapter_thumbnail, String chapter_pdf) {
        this.course_id = course_id;
        this.course_detail_chapter = course_detail_chapter;
        this.chapter_title = chapter_title;
        this.chapter_video = chapter_video;
        this.chapter_thumbnail = chapter_thumbnail;
        this.chapter_pdf = chapter_pdf;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long course_detail_id;

    private Long course_id;

    @Column(name = "course_detail_chapter")
    private Long course_detail_chapter;

    @Column(name = "chapter_title")
    private String chapter_title;

    @Column(name = "chapter_video")
    private String chapter_video;

    @Column(name = "chapter_pdf")
    private String chapter_pdf;

    @Column(name = "chapter_thumbnail")
    private String chapter_thumbnail;
}
