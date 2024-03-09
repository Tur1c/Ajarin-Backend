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
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @Column(name = "category_name")
    private String category_name;

    // @ManyToMany
    // @JoinTable(
    //     name = "course_category",
    //     joinColumns = @JoinColumn(name="category_id"),
    //     inverseJoinColumns = @JoinColumn(name="course_id")
    // )
    // private List<CourseEntity> courses;
}
