package co.id.ajarin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.id.ajarin.entity.StudentCourseEntity;
import co.id.ajarin.entity.composite.StudentCourseKey;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourseEntity, StudentCourseKey> {

    @Modifying
    @Query(value = "UPDATE STUDENT_COURSE SET COMPLETED_CHAP = :complete WHERE user_id = :userid AND course_id = :courseid", nativeQuery = true)
    int setCompleteChap(@Param("complete") String complete, @Param("courseid") Long courseid, @Param("userid") Long userid);
    // @Modifying
    // @Query(value = "UPDATE STUDENT_COURSE SET COMPLETED_CHAP = 'duarrr' WHERE user_id = 26 AND course_id = 1", nativeQuery = true)
    // int setCompleteChap();

    @Modifying
    @Query(value = "UPDATE STUDENT_COURSE SET STATUS = 'Completed' WHERE user_id = :userid AND course_id = :courseid", nativeQuery = true)
    int setCourseCompleted(@Param("courseid") Long courseid, @Param("userid") Long userid);

    @Query(value = "SELECT * FROM STUDENT_COURSE WHERE USER_ID = :userid AND COURSE_ID = :courseid", nativeQuery = true)
    StudentCourseEntity getData(@Param("userid") Long userid, @Param("courseid") Long courseid);

    @Modifying
    @Query(value = "UPDATE STUDENT_COURSE SET RATING = :rating, COMMENT = :comment WHERE user_id = :userid AND course_id = :courseid", nativeQuery = true)
    int setRatingComment(@Param("userid") Long userid, @Param("courseid") Long courseid, @Param("rating") Float rating, @Param("comment") String comment);
}
