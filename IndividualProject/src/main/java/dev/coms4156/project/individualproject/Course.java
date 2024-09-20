package dev.coms4156.project.individualproject;

import java.io.Serial;
import java.io.Serializable;

/**
 * Represents a course within a department.
 * This class stores information about the course, including the instructor,
 * location, time slot, capacity, and the number of enrolled students.
 */
public class Course implements Serializable {

  /**
   * Constructs a new Course object with the given parameters. Initial count starts at 0.
   *
   * @param instructorName The name of the instructor teaching the course.
   * @param courseLocation The location where the course is held.
   * @param timeSlot       The time slot of the course.
   * @param capacity       The maximum number of students that can enroll in the course.
   */
  public Course(String instructorName, String courseLocation, String timeSlot, int capacity) {
    if (instructorName == null || instructorName.trim().isEmpty()) {
      this.instructorName = "TBD";
      System.out.println("Warning: Invalid instructor name. Default value TBD assigned.");
    } else {
      this.instructorName = instructorName;
    }

    if (courseLocation == null || courseLocation.trim().isEmpty()) {
      this.courseLocation = "TBD";
      System.out.println("Warning: Invalid location. Default value TBD assigned.");
    } else {
      this.courseLocation = courseLocation;
    }

    if (timeSlot == null || timeSlot.trim().isEmpty()) {
      this.courseTimeSlot = "TBD";
      System.out.println("Warning: Invalid time slot. Default value TBD assigned.");
    } else {
      this.courseTimeSlot = timeSlot;
    }

    if (capacity <= 0) {
      this.enrollmentCapacity = 10; // Assuming default capacity is 10
      System.out.println("Warning: Invalid enrollment capacity. Default value 10 assigned.");
    } else {
      this.enrollmentCapacity = capacity;
    }

    this.enrolledStudentCount = 0;
  }

  /**
   * Enrolls a student in the course if there is space available.
   *
   * @return true if the student is successfully enrolled, false otherwise.
   */
  public boolean enrollStudent() {
    if (!isCourseFull()) {
      enrolledStudentCount++;
      return true;
    }
    return false;
  }

  /**
   * Drops a student from the course if a student is enrolled.
   *
   * @return true if the student is successfully dropped, false otherwise.
   */
  public boolean dropStudent() {
    if (enrolledStudentCount > 0) {
      enrolledStudentCount--;
      return true;
    }
    return false;
  }

  public String getCourseLocation() {
    return this.courseLocation;
  }

  public String getInstructorName() {
    return this.instructorName;
  }

  public String getCourseTimeSlot() {
    return this.courseTimeSlot;
  }

  public int getEnrollmentCapacity() {
    return this.enrollmentCapacity;
  }

  public int getEnrolledStudentCount() {
    return this.enrolledStudentCount;
  }

  public String toString() {
    return "\nInstructor: " + instructorName + "; Location: " + courseLocation
        + "; Time: " + courseTimeSlot;
  }

  /**
   * Reassigns the instructor for the course.
   * If the provided instructor name is null or empty, the assignment is not made.
   *
   * @param newInstructorName the new instructor for the course
   */
  public void reassignInstructor(String newInstructorName) {
    if (newInstructorName == null || newInstructorName.trim().isEmpty()) {
      return;
    }
    this.instructorName = newInstructorName;
  }

  /**
   * Reassigns the location for the course.
   * If the provided location is null or empty, the assignment is not made.
   *
   * @param newLocation the new location for the course
   */
  public void reassignLocation(String newLocation) {
    if (newLocation == null || newLocation.trim().isEmpty()) {
      return;
    }
    this.courseLocation = newLocation;
  }

  /**
   * Reassigns the time slot for the course.
   * If the provided time slot is null or empty, the assignment is not made.
   *
   * @param newTime the new time slot for the course
   */
  public void reassignTime(String newTime) {
    if (newTime == null || newTime.trim().isEmpty()) {
      return;
    }
    this.courseTimeSlot = newTime;
  }

  /**
   * Sets the number of enrolled students for the course.
   * If the provided count is negative, the assignment is not made.
   *
   * @param count the new enrollment count for the course
   */
  public void setEnrolledStudentCount(int count) {
    if (count < 0) {
      return;
    }
    this.enrolledStudentCount = count;
  }

  public boolean isCourseFull() {
    return enrollmentCapacity <= enrolledStudentCount;
  }

  @Serial
  private static final long serialVersionUID = 123456L;
  private final int enrollmentCapacity;
  private int enrolledStudentCount;
  private String courseLocation;
  private String instructorName;
  private String courseTimeSlot;
}
