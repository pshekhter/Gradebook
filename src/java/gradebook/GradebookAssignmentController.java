/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Pavel Shekhter
 */
@Named(value = "gradebookAssignmentController")
@SessionScoped
public class GradebookAssignmentController implements Serializable {

    // Helpers
    StudentHelper studentHelper;
    AssignmentHelper assignmentHelper;
    GradebookAssignmentHelper gradebookAssignmentHelper;
    StudentAssignmentHelper studentAssignmentHelper;

    // Lists
    DataModel assignments;
    DataModel students;

    // Stores gradebookId for passing
    int gradebookId;

    // Stores assignmentId for passing
    int assignmentId;

    // Store the assignment's name
    String assignmentName;

    // Stores response
    String response;

    GradebookAssignment selected;

    // Stores assignment grade
    int assignmentGrade;

    List<Assignment> instructorAssignments;
    List<GradebookAssignment> gradebookAssignments;
    List<Student> assignmentStudents;

    /**
     * Creates a new instance of GradebookAssignmentController
     */
    public GradebookAssignmentController() {
        studentAssignmentHelper = new StudentAssignmentHelper();
        studentHelper = new StudentHelper();
        assignmentHelper = new AssignmentHelper();
        gradebookAssignmentHelper = new GradebookAssignmentHelper();
        instructorAssignments = gradebookAssignmentHelper.getAssignmentsFromGradebook(gradebookId);
    }

    public DataModel getAssignments() {
        if (assignments == null) {
            assignments = new ListDataModel(gradebookAssignmentHelper.getAssignmentsFromGradebook(gradebookId));
        }

        return assignments;
    }

    public void setAssignments(DataModel assignments) {
        this.assignments = assignments;
    }

    public DataModel getStudents() {
        if (students == null) {
            students = new ListDataModel(studentHelper.getStudents());
        }

        return students;
    }

    public void setStudents(DataModel students) {
        this.students = students;
    }

    public int getGradebookId() {
        return gradebookId;
    }

    public void setGradebookId(int gradebookId) {
        this.gradebookId = gradebookId;
    }

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getResponse() {

        if (assignmentName != null) {

            if (assignmentHelper.insertAssignment(assignmentName) == 1) {

                assignmentId = assignmentHelper.getAssignmentID(assignmentName).getAssignmentId();
                //gradebookId = 4419;

                if (gradebookAssignmentHelper.insertAssignmentToGradebook(assignmentId, gradebookId) == 1) {
                    assignmentName = null;
                    response = "Assignment Added.";
                    return response;

                } else {
                    assignmentName = null;
                    response = "Assignment Not Added.";
                    return response;
                }

            } else {
                response = "Insert assignment failed!";
                assignmentName = null;
                return response;
            }

        } else {
            response = " ";
            return response;
        }
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getAssignmentGrade() {
        return assignmentGrade;
    }

    public void setAssignmentGrade(int assignmentGrade) {
        this.assignmentGrade = assignmentGrade;
    }

    public String viewAssignments() {
        return "viewAssignments";
    }

    public List<Assignment> getInstructorAssignments() {
        if (gradebookAssignmentHelper.getAssignmentsFromGradebook(gradebookId) != null) {
            return gradebookAssignmentHelper.getAssignmentsFromGradebook(gradebookId);
        } else {
            return null;
        }
    }

    public void setInstructorAssignments(List<Assignment> instructorAssignments) {
        this.instructorAssignments = instructorAssignments;
    }

    public String viewStudents() {
        return "viewStudents";
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public List<GradebookAssignment> getGradebookAssignments() {
        if (gradebookAssignmentHelper.getGradebookAssignmentsFromGradebookId(gradebookId) != null) {
            this.gradebookAssignments = gradebookAssignmentHelper.getGradebookAssignmentsFromGradebookId(gradebookId);
        }
        return null;
    }

    public void setGradebookAssignments(List<GradebookAssignment> gradebookAssignments) {
        this.gradebookAssignments = gradebookAssignments;
    }

    public List<Student> getAssignmentStudents() {
        if (studentAssignmentHelper.getStudentsFromGradebook(gradebookId) != null) {
            this.assignmentStudents = studentAssignmentHelper.getStudentsFromGradebook(gradebookId);
        }
        return null;
    }

    public void setAssignmentStudents(List<Student> assignmentStudents) {
        this.assignmentStudents = assignmentStudents;
    }

}
