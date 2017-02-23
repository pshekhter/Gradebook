package gradebook;
// Generated Feb 22, 2017 3:30:16 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Course generated by hbm2java
 */
public class Course  implements java.io.Serializable {


     private Integer courseId;
     private String courseName;
     private Set<Gradebook> gradebooks = new HashSet<Gradebook>(0);
     private Set<SemesterCourse> semesterCourses = new HashSet<SemesterCourse>(0);

    public Course() {
    }

	
    public Course(String courseName) {
        this.courseName = courseName;
    }
    public Course(String courseName, Set<Gradebook> gradebooks, Set<SemesterCourse> semesterCourses) {
       this.courseName = courseName;
       this.gradebooks = gradebooks;
       this.semesterCourses = semesterCourses;
    }
   
    public Integer getCourseId() {
        return this.courseId;
    }
    
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    public String getCourseName() {
        return this.courseName;
    }
    
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public Set<Gradebook> getGradebooks() {
        return this.gradebooks;
    }
    
    public void setGradebooks(Set<Gradebook> gradebooks) {
        this.gradebooks = gradebooks;
    }
    public Set<SemesterCourse> getSemesterCourses() {
        return this.semesterCourses;
    }
    
    public void setSemesterCourses(Set<SemesterCourse> semesterCourses) {
        this.semesterCourses = semesterCourses;
    }




}

