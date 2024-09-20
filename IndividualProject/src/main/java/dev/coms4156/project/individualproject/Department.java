package dev.coms4156.project.individualproject;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


/**
 * Represents a department within an educational institution.
 * This class stores information about the department, including its code,
 * courses offered, department chair, and number of majors.
 */
public class Department implements Serializable {

  /**
   * Constructs a new Department object with the given parameters.
   *
   * @param deptCode        The code of the department.
   * @param courses         A HashMap containing courses offered by the department.
   * @param departmentChair The name of the department chair.
   * @param numberOfMajors  The number of majors in the department.
   */
  public Department(String deptCode, HashMap<String, Course> courses, String departmentChair,
                    int numberOfMajors) {
    if (deptCode == null || deptCode.trim().isEmpty()) {
      this.deptCode = "TBD";
      System.out.println("Warning: Invalid department code. Default value TBD assigned.");
    } else {
      this.deptCode = deptCode;
    }

    if (courses == null) {
      this.courses = new HashMap<>();
      System.out.println("Warning: Courses map is null. Empty map assigned.");
    } else {
      this.courses = courses;
    }

    if (departmentChair == null || departmentChair.trim().isEmpty()) {
      this.departmentChair = "TBD";
      System.out.println("Warning: Invalid department chair name. Default value TBD assigned.");
    } else {
      this.departmentChair = departmentChair;
    }

    if (numberOfMajors < 0) {
      this.numberOfMajors = 0;
      System.out.println("Warning: Invalid number of majors. Default value 0 assigned.");
    } else {
      this.numberOfMajors = numberOfMajors;
    }
  }

  /**
   * Gets the number of majors in the department.
   *
   * @return The number of majors.
   */
  public int getNumberOfMajors() {
    return this.numberOfMajors;
  }

  /**
   * Gets the name of the department chair.
   *
   * @return The name of the department chair.
   */
  public String getDepartmentChair() {
    return this.departmentChair;
  }

  /**
   * Gets the courses offered by the department.
   *
   * @return A HashMap containing courses offered by the department.
   */
  public HashMap<String, Course> getCourseSelection() {
    return this.courses;
  }

  /**
   * Increases the number of majors in the department by one.
   */
  public void addPersonToMajor() {
    numberOfMajors++;
  }

  /**
   * Decreases the number of majors in the department by one if it's greater than zero.
   */
  public void dropPersonFromMajor() {
    if (numberOfMajors > 0) {
      numberOfMajors--;
    }
  }

  /**
   * Adds a new course to the department's course selection.
   *
   * @param courseId The ID of the course to add.
   * @param course   The Course object to add.
   */
  public void addCourse(String courseId, Course course) {
    if (courseId == null || courseId.trim().isEmpty()) {
      return;
    }
    if (course == null) {
      return;
    }

    courses.put(courseId, course);
  }

  /**
   * Creates and adds a new course to the department's course selection.
   *
   * @param courseId       The ID of the new course.
   * @param instructorName The name of the instructor teaching the course.
   * @param courseLocation The location where the course is held.
   * @param courseTimeSlot The time slot of the course.
   * @param capacity       The maximum number of students that can enroll in the course.
   */
  public void createCourse(String courseId, String instructorName, String courseLocation,
                           String courseTimeSlot, int capacity) {
    boolean isCourseValid = true;

    if (courseId == null || courseId.trim().isEmpty()) {
      isCourseValid = false;
    }
    if (instructorName == null || instructorName.trim().isEmpty()) {
      isCourseValid = false;
    }
    if (courseLocation == null || courseLocation.trim().isEmpty()) {
      isCourseValid = false;
    }
    if (courseTimeSlot == null || courseTimeSlot.trim().isEmpty()) {
      isCourseValid = false;
    }
    if (capacity <= 0) {
      isCourseValid = false;
    }

    if (isCourseValid) {
      Course newCourse = new Course(instructorName, courseLocation, courseTimeSlot, capacity);
      addCourse(courseId, newCourse);
    } else {
      System.out.println("Invalid course. Course not created nor added.");
    }
  }

  /**
   * Returns a string representation of the department, including its code and the courses offered.
   *
   * @return A string representing the department.
   */
  public String toString() {
    StringBuilder result = new StringBuilder();
    for (Map.Entry<String, Course> entry : courses.entrySet()) {
      String key = entry.getKey();
      Course value = entry.getValue();
      result.append(deptCode).append(" ").append(key).append(": ").append(value.toString())
              .append("\n");
    }
    return result.toString();
  }

  @Serial
  private static final long serialVersionUID = 234567L;
  private HashMap<String, Course> courses;
  private String departmentChair;
  private String deptCode;
  private int numberOfMajors;
}
