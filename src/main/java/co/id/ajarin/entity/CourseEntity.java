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
@Table(name = "course")
public class CourseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseid;

    @Column(name = "courseprice")
    private Integer courseprice;

    @Column(name = "columnchapter")
    private String coursechapter;

    @Column(name = "coursetitle")
    private String coursetitle;

    @Column(name = "coursedescription")
    private String coursedescription;

    @Column(name = "coursecategory")
    private String coursecategory;
}
