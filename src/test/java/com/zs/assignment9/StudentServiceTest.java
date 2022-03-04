/**
 * @author: Ishtmeet Singh Arora
 */
package com.zs.assignment9;

import com.zs.assignment9.dao.StudentDao;
import com.zs.assignment9.entity.Student;
import com.zs.assignment9.exceptions.InternalServerException;
import com.zs.assignment9.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Tests the student service class.
 */

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    private StudentService studentService;
    private StudentDao studentDao;

    @BeforeEach
    void setup() {

        studentDao = mock(StudentDao.class);
        studentService = new StudentService(studentDao);
    }

    /**
     * Tests whether the student dao class method runs or not.
     * @throws InternalServerException
     */
    @Test
    void canCreateStudent() throws InternalServerException {
        Student expectedStudent = new Student(101 , "ishtmeet" , "arora");
        studentService.createStudent(expectedStudent);
        verify(studentDao, times(1)).save(expectedStudent);
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerException
     */
    @Test
    void canCreateStudentExceptionTest() throws InternalServerException {
        Student expectedStudent = new Student(101 , "ishtmeet" , "arora");
        doThrow(InternalServerException.class).when(studentDao).save(expectedStudent);
        assertThrows(InternalServerException.class, ()-> studentService.createStudent(expectedStudent));
    }

    /**
     * Tests whether the exception gets thrown or not.
     * @throws InternalServerException
     */
    @Test
    void getStudent() throws InternalServerException {
        int id=101;
        doThrow(InternalServerException.class).when(studentDao).getById(id);
        assertThrows(InternalServerException.class, ()-> studentService.getStudent(id));

    }

    /**
     * Tests whether the student dao method runs or not.
     * @throws InternalServerException
     */
    @Test
    void testGetStudentException() throws InternalServerException {
        int id=101;
        studentService.getStudent(id);
        verify(studentDao, times(1)).getById(id);

    }
}