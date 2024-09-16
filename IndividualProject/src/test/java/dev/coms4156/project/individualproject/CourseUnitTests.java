package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains the unit tests for the Course class
 * to validate the behavior of the methods within the Course class.
 */
@SpringBootTest
@ContextConfiguration
public class CourseUnitTests {

  @BeforeEach
  public void setupCourseForTesting() {
    testCourse = new Course("Griffin Newbold", "417 IAB", "11:40-12:55", 250);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "\nInstructor: Griffin Newbold; Location: 417 IAB; Time: 11:40-12:55";
    assertEquals(expectedResult, testCourse.toString());
  }

  @Test
  public void getInstructorNameTest() {
    assertEquals("Griffin Newbold", testCourse.getInstructorName());
  }

  @Test
  public void getCourseLocationTest() {
    assertEquals("417 IAB", testCourse.getCourseLocation());
  }

  @Test
  public void getCourseTimeSlotTest() {
    assertEquals("11:40-12:55", testCourse.getCourseTimeSlot());
  }

  @Test
  public void getEnrollmentCapacityTest() {
    assertEquals(250, testCourse.getEnrollmentCapacity());
  }

  @Test
  public void getEnrolledStudentCountTest() {
    assertEquals(0, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void setEnrolledStudentCountTest() {
    testCourse.setEnrolledStudentCount(50);
    assertEquals(50, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void enrollStudentSuccessTest() {
    testCourse.setEnrolledStudentCount(0);
    assertTrue(testCourse.enrollStudent());
    assertEquals(1, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void enrollStudentFailureTest() {
    testCourse.setEnrolledStudentCount(250);
    assertFalse(testCourse.enrollStudent());
    assertEquals(250, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void dropStudentSuccessTest() {
    testCourse.setEnrolledStudentCount(1);
    assertTrue(testCourse.dropStudent());
    assertEquals(0, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void dropStudentFailureTest() {
    testCourse.setEnrolledStudentCount(0);
    assertFalse(testCourse.dropStudent());
    assertEquals(0, testCourse.getEnrolledStudentCount());
  }

  @Test
  public void isCourseFullTrueTest() {
    testCourse.setEnrolledStudentCount(250);
    assertTrue(testCourse.isCourseFull());
  }

  @Test
  public void isCourseFullFalseTest() {
    testCourse.setEnrolledStudentCount(0);
    assertFalse(testCourse.isCourseFull());
  }

  @Test
  public void reassignInstructorTest() {
    testCourse.reassignInstructor("Adam Cannon");
    assertEquals("Adam Cannon", testCourse.getInstructorName());
  }

  @Test
  public void reassignLocationTest() {
    testCourse.reassignLocation("309 HAV");
    assertEquals("309 HAV", testCourse.getCourseLocation());
  }

  @Test
  public void reassignTimeTest() {
    testCourse.reassignTime("2:40-3:55");
    assertEquals("2:40-3:55", testCourse.getCourseTimeSlot());
  }

  /** The test course instance used for testing. */
  public static Course testCourse;
}

