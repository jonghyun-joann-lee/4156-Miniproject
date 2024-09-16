package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains the unit tests for the Department class
 * to validate the behavior of the methods within the Department class.
 */
@SpringBootTest
@ContextConfiguration
public class DepartmentUnitTests {

  /** The test instances used for testing. */
  public static Department testDepartment;
  public static HashMap<String, Course> testCourses;

  /**
   * This sets up the test instances for testing methods in the Department class.
   */
  @BeforeEach
  public void setupDepartmentForTesting() {
    testCourses = new HashMap<>();
    Course testCourse = new Course("Tony Dear", "402 CHANDLER", "1:10-3:40", 125);
    testCourses.put("3251", testCourse);
    testDepartment = new Department("COMS", testCourses, "Luca Carloni", 2700);
  }

  @Test
  public void toStringTest() {
    String expectedResult = "COMS 3251: \nInstructor: Tony Dear; Location: 402 CHANDLER;"
        + " Time: 1:10-3:40\n";
    assertEquals(expectedResult, testDepartment.toString());
  }

  @Test
  public void getNumberOfMajorsTest() {
    assertEquals(2700, testDepartment.getNumberOfMajors());
  }

  @Test
  public void getDepartmentChairTest() {
    assertEquals("Luca Carloni", testDepartment.getDepartmentChair());
  }

  @Test
  public void getCourseSelectionTest() {
    assertEquals(testCourses, testDepartment.getCourseSelection());
  }

  @Test
  public void addPersonToMajorTest() {
    testDepartment.addPersonToMajor();
    assertEquals(2701, testDepartment.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorSuccessTest() {
    testDepartment.dropPersonFromMajor();
    assertEquals(2699, testDepartment.getNumberOfMajors());
  }

  @Test
  public void dropPersonFromMajorFailureTest() {
    testDepartment = new Department("COMS", testCourses, "Luca Carloni", 0);
    testDepartment.dropPersonFromMajor();
    assertEquals(0, testDepartment.getNumberOfMajors());
  }

  @Test
  public void addCourseTest() {
    Course newCourse = new Course("Gail Kaiser", "501 NWC", "10:10-11:25", 120);
    testDepartment.addCourse("4156", newCourse);
    assertEquals(2, testDepartment.getCourseSelection().size());
    assertTrue(testDepartment.getCourseSelection().containsKey("4156"));
    assertEquals(newCourse, testDepartment.getCourseSelection().get("4156"));
  }

  @Test
  public void createCourseTest() {
    testDepartment.createCourse("3157", "Jae Lee", "417 IAB", "11:40-12:55", 400);
    assertEquals(2, testDepartment.getCourseSelection().size());
    assertTrue(testDepartment.getCourseSelection().containsKey("3157"));
    assertNotNull(testDepartment.getCourseSelection().get("3157"));
  }

}
