package gradebook;
// Generated Feb 22, 2017 3:30:16 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Assignment generated by hbm2java
 */
public class Assignment  implements java.io.Serializable {


     private Integer assignmentId;
     private String assignmentName;
     private Set<StudentAssignment> studentAssignments = new HashSet<StudentAssignment>(0);
     private Set<GradebookAssignment> gradebookAssignments = new HashSet<GradebookAssignment>(0);

    public Assignment() {
    }

	
    public Assignment(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    public Assignment(String assignmentName, Set<StudentAssignment> studentAssignments, Set<GradebookAssignment> gradebookAssignments) {
       this.assignmentName = assignmentName;
       this.studentAssignments = studentAssignments;
       this.gradebookAssignments = gradebookAssignments;
    }
   
    public Integer getAssignmentId() {
        return this.assignmentId;
    }
    
    public void setAssignmentId(Integer assignmentId) {
        this.assignmentId = assignmentId;
    }
    public String getAssignmentName() {
        return this.assignmentName;
    }
    
    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    public Set<StudentAssignment> getStudentAssignments() {
        return this.studentAssignments;
    }
    
    public void setStudentAssignments(Set<StudentAssignment> studentAssignments) {
        this.studentAssignments = studentAssignments;
    }
    public Set<GradebookAssignment> getGradebookAssignments() {
        return this.gradebookAssignments;
    }
    
    public void setGradebookAssignments(Set<GradebookAssignment> gradebookAssignments) {
        this.gradebookAssignments = gradebookAssignments;
    }




}


