/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9;

import com.zs.assignment9.dao.StudentDao;
import com.zs.assignment9.entity.Student;
import com.zs.assignment9.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

/**
 * Tests the student service class.
 */
@RunWith(MockitoJUnitRunner.class)
class StudentServiceTest {
    @Mock
    private StudentService underTest;
    private StudentDao studentDao;

    @BeforeEach
    void setUp() {
        studentDao=new StudentDao();
        underTest=new StudentService();
    }

    /**
     * Tests whether the student gets passed to the student dao class or not.
     */
    @Test
    void canCreateStudent() {
        Student expectedStudent = new Student(101 , "ishtmeet" , "arora");
        underTest.createStudent(expectedStudent);
        ArgumentCaptor<Student>studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
        verify(studentDao).save(studentArgumentCaptor.capture());
        Student value= studentArgumentCaptor.getValue();
        assertEquals(expectedStudent,value);
    }

    /**
     * Tests whether the student id gets passed to the student dao class or not.
     */
    @Test
    void getStudent() {
        int id=5;
        underTest.getStudent(id);
        ArgumentCaptor<Integer>studentArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        verify(studentDao).getById(studentArgumentCaptor.capture());
        int value= studentArgumentCaptor.getValue();
        assertEquals(id,value);
    }
}