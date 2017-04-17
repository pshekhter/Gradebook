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
    DataModel gradebookStudentValues;
    DataModel assignmentStudentValues;

    StudentAssignmentHelper helper;
    StudentHelper sh;
    GradebookAssignmentHelper gah;

    int studentID;
    int student;
    int assignment;
    String studentFName;
    String studentLName;
    String assignmentName;
    String response;
    int grade;

    int gradebookId;

    boolean readyToSubmit;

    // String for addStudentAssignment form
    String studentName;

    List<Student> assignmentStudents;
    List<Student> gradebookStudents;

    public StudentAssignmentController() {
        helper = new StudentAssignmentHelper();
        sh = new StudentHelper();
        gah = new GradebookAssignmentHelper();
        if (gah.getGradebookAssignmentsFromGradebookId(gradebookId).size() != 0) {
            int gaid = gah.getGradebookAssignmentsFromGradebookId(gradebookId).get(0).getGradebookAssignmentId();
            assignmentStudents = helper.getStudentsFromAssignment(assignment, gaid);
        }
        readyToSubmit = false;
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

    public DataModel getAssignmentStudentValues() {
        if (assignmentStudentValues == null) {
            assignmentStudentValues = new ListDataModel(assignmentStudents);
        }

        return assignmentStudentValues;
    }

    public void setAssignmentStudentValues(DataModel assignmentStudentValues) {
        this.assignmentStudentValues = assignmentStudentValues;
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
        if (helper.getAssignmentAtID(assignment) != null) {
            return helper.getAssignmentAtID(assignment).getAssignmentName();
        }
        return "";
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public String getResponse() {

        if ((student != 0) && (readyToSubmit)) {

            if ((gah.getGradebookAssignmentsFromGradebookId(gradebookId) != null) && (gah.getGradebookAssignmentsFromGradebookId(gradebookId).size() != 0)) {
                int gaid = gah.getGradebookAssignmentsFromGradebookId(gradebookId).get(0).getGradebookAssignmentId();
                if (helper.insertStudentToAssignment(student, gaid) == 1) {
                    studentName = null;
                    student = 0;
                    response = "Student added to assignment";
                    return response;
                } else {
                    studentName = null;
                    student = 0;
                    response = "Student not added to assignment.";
                    return response;
                }
            }
        } else {
            response = " ";
            return response;
        }
        response = "Student not added to assignment.";
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<Student> getAssignmentStudents() {
        if (gah.getGradebookAssignmentsFromGradebookId(gradebookId).size() != 0) {
            int gaid = gah.getGradebookAssignmentsFromGradebookId(gradebookId).get(0).getGradebookAssignmentId();
            if (helper.getStudentsFromAssignment(assignment, gaid) != null) {
                return helper.getStudentsFromAssignment(assignment, gaid);
            } else {
                return null;
            }
        }
        return null;
    }

    public void setAssignmentStudents(List<Student> assignmentStudents) {
        this.assignmentStudents = assignmentStudents;
    }

    public void studentIdValueChanged(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.student = Integer.parseInt(newVal);
            Student stu = gradebookStudents.get(0);
            for (int studentCounter = 0; studentCounter < gradebookStudents.size(); studentCounter++) {
                if (gradebookStudents.get(studentCounter).getStudentId() == student) {
                    stu = gradebookStudents.get(studentCounter);
                    studentName = stu.getStudentFname() + stu.getStudentLname();
                    readyToSubmit = false;
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

    public String submitAssignToStudent() {
        readyToSubmit = true;
        return "assignToStudent";
    }

    public String returnToAssignment() {
        return "viewAssignments";
    }

    public boolean isReadyToSubmit() {
        return readyToSubmit;
    }

    public void setReadyToSubmit(boolean readyToSubmit) {
        this.readyToSubmit = readyToSubmit;
    }

    public DataModel getGradebookStudentValues() {
        if (gradebookStudentValues == null) {
            gradebookStudentValues = new ListDataModel(gradebookStudents);
        }

        return gradebookStudentValues;
    }

    public void setGradebookStudentValues(DataModel gradebookStudentValues) {
        this.gradebookStudentValues = gradebookStudentValues;
    }

    public List<Student> getGradebookStudents() {
        if (helper.getStudentsFromGradebook(gradebookId).size() != 0) {
            this.gradebookStudents = helper.getStudentsFromGradebook(gradebookId);
            return this.gradebookStudents;
        }
        return null;
    }

    public void setGradebookStudents(List<Student> gradebookStudents) {
        this.gradebookStudents = gradebookStudents;
    }

    public int getGradebookId() {
        return gradebookId;
    }

    public void setGradebookId(int gradebookId) {
        this.gradebookId = gradebookId;
    }

    public int getGrade() {
        if (helper.getStudentAssignmentId(student, assignment) != 0) {
            grade = helper.getGrade(helper.getStudentAssignmentId(student, assignment));
        }
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

}
