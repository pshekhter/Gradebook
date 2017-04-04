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
    GradebookAssignmentHelper gradebookAssignmentHelper;

    // Lists
    DataModel assignments;
    DataModel students;

    // Stores gradebookId for passing
    int gradebookId;

    // Stores assignmentId for passing
    int assignmentId;

    // Stores response
    String response;
    
    GradebookAssignment selected;
    
    // Stores assignment grade
    int assignmentGrade;

    /**
     * Creates a new instance of GradebookAssignmentController
     */
    public GradebookAssignmentController() {
        studentHelper = new StudentHelper();
        gradebookAssignmentHelper = new GradebookAssignmentHelper();
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
        return response;
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
}
