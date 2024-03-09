package co.id.ajarin.entity.composite;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
@Embeddable
public class StudentCourseKey implements Serializable {
    @Column(name = "user_id")
    private Long student_id;

    @Column(name = "course_id")
    private Long course_id;
}
