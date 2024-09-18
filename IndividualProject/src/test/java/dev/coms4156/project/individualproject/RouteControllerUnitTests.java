package dev.coms4156.project.individualproject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

/**
 * This class contains the tests for the RouteController class
 * to validate the behavior of the methods within the RouteController class.
 */
@SpringBootTest
@ContextConfiguration
public class RouteControllerUnitTests {

  /** The test RouteController instance used for testing. */
  public static RouteController testRouteController;

  /**
   * This sets up the test instances for testing methods in the RouteController class.
   */
  @BeforeAll
  public static void setupRouteControllerForTesting() {
    // Use real data from data.txt to set up the test instance and disable saving data
    IndividualProjectApplication.myFileDatabase = new MyFileDatabase(0, "./data.txt");
    IndividualProjectApplication.overrideDatabase(IndividualProjectApplication.myFileDatabase);
    testRouteController = new RouteController();
  }

  @Test
  public void retrieveDepartmentSuccessTest() {
    ResponseEntity<?> response = testRouteController.retrieveDepartment("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().toString().contains("COMS"));
  }

  @Test
  public void retrieveDepartmentNotFoundTest() {
    ResponseEntity<?> response = testRouteController.retrieveDepartment("ACCT");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void retrieveCourseSuccessTest() {
    ResponseEntity<?> response = testRouteController.retrieveCourse("COMS", 1004);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().toString().contains("Adam Cannon"));
  }

  @Test
  public void retrieveCourseNotFoundTest() {
    ResponseEntity<?> response = testRouteController.retrieveCourse("COMS", 7777);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void retrieveCoursesFoundTwoTest() {
    ResponseEntity<?> response = testRouteController.retrieveCourses(1004);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().toString().contains("COMS 1004"));
    assertTrue(response.getBody().toString().contains("ECON 1004"));
  }

  @Test
  public void retrieveCoursesFoundOneTest() {
    ResponseEntity<?> response = testRouteController.retrieveCourses(4156);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(response.getBody());
    assertTrue(response.getBody().toString().contains("COMS 4156"));
  }

  @Test
  public void retrieveCoursesNotFoundTest() {
    ResponseEntity<?> response = testRouteController.retrieveCourses(9999);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void retrieveCoursesInvalidCourseCodeTest() {
    ResponseEntity<?> response = testRouteController.retrieveCourses(-1004);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Invalid course code. Course codes must be positive integers.",
        response.getBody());
  }

  @Test
  public void isCourseFullTrueTest() {
    ResponseEntity<?> response = testRouteController.isCourseFull("IEOR", 2500);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertTrue((boolean) response.getBody());
  }

  @Test
  public void isCourseFullFalseTest() {
    ResponseEntity<?> response = testRouteController.isCourseFull("COMS", 1004);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertFalse((Boolean) response.getBody());
  }

  @Test
  public void getMajorCtFromDeptSuccessTest() {
    ResponseEntity<?> response = testRouteController.getMajorCtFromDept("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("There are: 2700 majors in the department", response.getBody());
  }

  @Test
  public void getMajorCtFromDeptNotFoundTest() {
    ResponseEntity<?> response = testRouteController.getMajorCtFromDept("ACCT");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void identifyDeptChairSuccessTest() {
    ResponseEntity<?> response = testRouteController.identifyDeptChair("COMS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Luca Carloni is the department chair.", response.getBody());
  }

  @Test
  public void identifyDeptChairNotFoundTest() {
    ResponseEntity<?> response = testRouteController.identifyDeptChair("ACCT");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void findCourseLocationSuccessTest() {
    ResponseEntity<?> response = testRouteController.findCourseLocation("COMS", 1004);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("417 IAB is where the course is located.", response.getBody());
  }

  @Test
  public void findCourseLocationNotFoundTest() {
    ResponseEntity<?> response = testRouteController.findCourseLocation("COMS", 7777);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void findCourseInstructorSuccessTest() {
    ResponseEntity<?> response = testRouteController.findCourseInstructor("COMS", 1004);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Adam Cannon is the instructor for the course.", response.getBody());
  }

  @Test
  public void findCourseInstructorNotFoundTest() {
    ResponseEntity<?> response = testRouteController.findCourseInstructor("COMS", 7777);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void findCourseTimeSuccessTest() {
    ResponseEntity<?> response = testRouteController.findCourseTime("COMS", 1004);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("The course meets at: 11:40-12:55", response.getBody());
  }

  @Test
  public void findCourseTimeNotFoundTest() {
    ResponseEntity<?> response = testRouteController.findCourseTime("COMS", 7777);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void addMajorToDeptSuccessTest() {
    ResponseEntity<?> response = testRouteController.addMajorToDept("ECON");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attribute was updated successfully", response.getBody());
  }

  @Test
  public void addMajorToDeptNotFoundTest() {
    ResponseEntity<?> response = testRouteController.addMajorToDept("ACCT");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void removeMajorFromDeptSuccessTest() {
    ResponseEntity<?> response = testRouteController.removeMajorFromDept("ELEN");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attribute was updated or is at minimum", response.getBody());
  }

  @Test
  public void removeMajorFromDeptNotFoundTest() {
    ResponseEntity<?> response = testRouteController.removeMajorFromDept("BIOL");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Department Not Found", response.getBody());
  }

  @Test
  public void dropStudentSuccessTest() {
    ResponseEntity<?> response = testRouteController.dropStudent("ECON", 1105);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Student has been dropped.", response.getBody());
  }

  @Test
  public void dropStudentFailureTest() {
    IndividualProjectApplication.myFileDatabase.getDepartmentMapping()
        .get("ELEN").getCourseSelection().get(Integer.toString(4702)).setEnrolledStudentCount(0);
    ResponseEntity<?> response = testRouteController.dropStudent("ELEN", 4702);
    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    assertEquals("Student has not been dropped.", response.getBody());
  }

  @Test
  public void setEnrollmentCountSuccessTest() {
    ResponseEntity<?> response = testRouteController.setEnrollmentCount("CHEM", 1403, 115);
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  @Test
  public void setEnrollmentCountNotFoundTest() {
    ResponseEntity<?> response = testRouteController.setEnrollmentCount("CHEM", 8888, 10);
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void changeCourseTimeSuccessTest() {
    ResponseEntity<?> response = testRouteController.changeCourseTime("PHYS", 4018, "4:10-5:25");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  @Test
  public void changeCourseTimeNotFoundTest() {
    ResponseEntity<?> response = testRouteController.changeCourseTime("PHYS", 9999, "10:10-11:25");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void changeCourseTeacherSuccessTest() {
    ResponseEntity<?> response = testRouteController.changeCourseTeacher(
        "ELEN", 4830, "Irving Kalet");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  @Test
  public void changeCourseTeacherNotFoundTest() {
    ResponseEntity<?> response = testRouteController.changeCourseTeacher(
        "ELEN", 2222, "Kenneth Shepard");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }

  @Test
  public void changeCourseLocationSuccessTest() {
    ResponseEntity<?> response = testRouteController.changeCourseLocation("ECON", 4415, "142 URIS");
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals("Attributed was updated successfully.", response.getBody());
  }

  @Test
  public void changeCourseLocationNotFoundTest() {
    ResponseEntity<?> response = testRouteController.changeCourseLocation("ECON", 1000, "428 PUP");
    assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    assertEquals("Course Not Found", response.getBody());
  }
}
