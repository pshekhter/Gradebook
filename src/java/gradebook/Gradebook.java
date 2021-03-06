package gradebook;
// Generated Feb 22, 2017 3:30:16 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Gradebook generated by hbm2java
 */
public class Gradebook  implements java.io.Serializable {


     private Integer gradebookId;
     private Course course;
     private Instructor instructor;
     private Semester semester;
     private Set<GradebookAssignment> gradebookAssignments = new HashSet<GradebookAssignment>(0);
     private Set<GradebookStudent> gradebookStudents = new HashSet<GradebookStudent>(0);

    public Gradebook() {
    }

	
    public Gradebook(Course course, Instructor instructor, Semester semester) {
        this.course = course;
        this.instructor = instructor;
        this.semester = semester;
    }
    public Gradebook(Course course, Instructor instructor, Semester semester, Set<GradebookAssignment> gradebookAssignments, Set<GradebookStudent> gradebookStudents) {
       this.course = course;
       this.instructor = instructor;
       this.semester = semester;
       this.gradebookAssignments = gradebookAssignments;
       this.gradebookStudents = gradebookStudents;
    }
   
    public Integer getGradebookId() {
        return this.gradebookId;
    }
    
    public void setGradebookId(Integer gradebookId) {
        this.gradebookId = gradebookId;
    }
    public Course getCourse() {
        return this.course;
    }
    
    public void setCourse(Course course) {
        this.course = course;
    }
    public Instructor getInstructor() {
        return this.instructor;
    }
    
    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
    public Semester getSemester() {
        return this.semester;
    }
    
    public void setSemester(Semester semester) {
        this.semester = semester;
    }
    public Set<GradebookAssignment> getGradebookAssignments() {
        return this.gradebookAssignments;
    }
    
    public void setGradebookAssignments(Set<GradebookAssignment> gradebookAssignments) {
        this.gradebookAssignments = gradebookAssignments;
    }
    public Set<GradebookStudent> getGradebookStudents() {
        return this.gradebookStudents;
    }
    
    public void setGradebookStudents(Set<GradebookStudent> gradebookStudents) {
        this.gradebookStudents = gradebookStudents;
    }




}


