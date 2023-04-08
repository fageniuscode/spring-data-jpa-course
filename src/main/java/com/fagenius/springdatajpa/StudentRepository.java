package com.fagenius.springdatajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s where s.email = ?1")
    Optional<Student> findStudentByEmail(String email);
    @Query("SELECT s FROM Student s WHERE s.firstName =?1 AND s.age = ?2")
    List<Student> findStudentByFirstNameEqualsAndAgeEquals(String firstName, int age);
    @Query("SELECT s FROM Student s WHERE s.firstName =?1 AND s.age > ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsGreaterThan(String firstName, int age);
    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age < ?2")
    List<Student> findStudentsByFirstNameEqualsAndAgeIsLessThan(String firstName, int age);
    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> findStudentByFirstNameEqualsAndAgeIsGreaterThanEqual(String firstName, int age);

    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.age >= ?2")
    List<Student> selectStudentWhereFirstNameAndAgeIsGreaterOrEquals(String firstName, int age);
    @Query("SELECT s FROM Student s WHERE s.firstName = ?1 AND s.lastName =?2")
    List<Student> findStudentByFirstNameEqualsAndLastNameEquals(String firstName, String lastName);

    @Query(value = "SELECT * FROM student  WHERE first_name = :firstName AND age >= :age", nativeQuery = true)
    List<Student> selectStudentWhereFirstNameAndAgeGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age") Integer age);

    @Transactional
    @Modifying
    @Query("DELETE FROM Student u WHERE u.id = ?1")
    int deleteStudentById(@Param("id") Long id);

}
