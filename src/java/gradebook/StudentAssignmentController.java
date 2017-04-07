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
import java.util.List;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

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
    StudentHelper sh;

    int studentID;
    int student;
    int assignment;
    String studentFName;
    String studentLName;
    String assignmentName;
    String response;

    // String for addStudentAssignment form
    String studentName;

    List<Student> assignmentStudents;

    public StudentAssignmentController() {
        helper = new StudentAssignmentHelper();
        sh = new StudentHelper();
        assignmentStudents = helper.getStudentsFromAssignment(assignment);
    }

    public DataModel getStudentValues() {
        if (studentValues == null) {
            studentValues = new ListDataModel(assignmentStudents);
        }

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
        return helper.getAssignmentAtID(assignment).get(0).getAssignmentName();
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getResponse() {
        
        if (student != 0) {

            if (helper.insertStudentToAssignment(student, assignment) == 1) {
                studentName = null;
                student = 0;
                response = "Student added to assignment";
                return response;
            } else {
                studentName = null;
                student = 0;
                response = "Student not added to response.";
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

    public List<Student> getAssignmentStudents() {
        if (helper.getStudentsFromAssignment(assignment) != null) {
            return helper.getStudentsFromAssignment(assignment);
        } else {
            return null;
        }

    }

    public void setAssignmentStudents(List<Student> assignmentStudents) {
        this.assignmentStudents = assignmentStudents;
    }

    public void studentIdValueChanged(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.student = Integer.parseInt(newVal);
            Student stu = assignmentStudents.get(0);
            for (int studentCounter = 0; studentCounter < assignmentStudents.size(); studentCounter++) {
                if (assignmentStudents.get(studentCounter).getStudentId() == student) {
                    stu = assignmentStudents.get(studentCounter);
                    studentName = stu.getStudentFname() + stu.getStudentLname();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }
    
    public String assignToStudent() {
        return "assignToStudent";
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    
    


}
