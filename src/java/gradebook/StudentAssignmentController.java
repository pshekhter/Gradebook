/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.model.DataModel;

/**
 *
 * @author allis
 */
@Named(value = "studentAssignmentController")
@SessionScoped
public class StudentAssignmentController implements Serializable {

    /**
     * Creates a new instance of StudentAssignmentController
     */
    DataModel studentValues;
    DataModel assignmentValues;

    StudentAssignmentHelper helper;

    int studentID;
    int student;
    int assignment;
    String studentFName;
    String studentLName;
    String assignmentName;
    String response;

    public StudentAssignmentController() {

        helper = new StudentAssignmentHelper();
    }

    public DataModel getStudentValues() {
        return studentValues;
    }

    public void setStudentValues(DataModel studentValues) {
        this.studentValues = studentValues;
    }

    public DataModel getAssignmentValues() {
        return assignmentValues;
    }

    public void setAssignmentValues(DataModel assignmentValues) {
        this.assignmentValues = assignmentValues;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public int getStudent() {
        return student;
    }

    public void setStudent(int student) {
        this.student = student;
    }

    public int getAssignment() {
        return assignment;
    }

    public void setAssignment(int assignment) {
        this.assignment = assignment;
    }

    public String getStudentFName() {
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentLName() {
        return studentLName;
    }

    public void setStudentLName(String studentLName) {
        this.studentLName = studentLName;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getResponse() {
        
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    
}
