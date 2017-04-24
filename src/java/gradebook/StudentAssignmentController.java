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
import java.util.ArrayList;
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

    int student;
    int assignment;
    int modifyGrade;
    String studentFName;
    String studentLName;
    String assignmentName;
    String response;
    int grade;

    int changeGradeResult = 0;

    String gradeResponse;

    int gradebookId;
    int previousGradebookId = 0;
    int resultGrade;

    boolean readyToSubmit;

    // String for addStudentAssignment form
    String studentName;

    int gaid;

    List<Student> assignmentStudents;
    List<Student> gradebookStudents;

    boolean readyToRefresh;
    boolean firstRun = true;
    boolean isModified = false;

    public StudentAssignmentController() {
        helper = new StudentAssignmentHelper();
        sh = new StudentHelper();
        gah = new GradebookAssignmentHelper();
        modifyGrade = 0;
        readyToRefresh = false;
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
            assignmentStudentValues = new ListDataModel(this.getGradebookStudents());
        }

        return assignmentStudentValues;
    }

    public void setAssignmentStudentValues(DataModel assignmentStudentValues) {
        this.assignmentStudentValues = assignmentStudentValues;
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
        if (sh.getStudent(student) != null) {
            studentFName = sh.getStudent(student).getStudentFname();
        }
        return studentFName;
    }

    public void setStudentFName(String studentFName) {
        this.studentFName = studentFName;
    }

    public String getStudentLName() {
        if (sh.getStudent(student) != null) {
            studentLName = sh.getStudent(student).getStudentLname();
        }
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
                gaid = helper.getGradebookAssignmentId(gradebookId, assignment);
                if (gaid != 0) {
                    if (helper.insertStudentToAssignment(student, gaid, grade) == 1) {
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
                } else {
                    studentName = null;
                    student = 0;
                    response = "Student not added to response.";
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
        gaid = helper.getGradebookAssignmentId(gradebookId, assignment);
        if (gaid != 0) {
            List<StudentAssignment> stua = helper.getStudentAssignmentList(gaid);
            List<Student> studentList = null;
            studentList = helper.getStudentsFromAssignment(gaid);
            if (studentList.size() != 0) {
                this.assignmentStudents = studentList;
                return this.assignmentStudents;
            } else {
                this.assignmentStudents = null;
                return this.assignmentStudents;
            }
        } else {
            this.assignmentStudents = null;
            return this.assignmentStudents;
        }
    }

    public void setAssignmentStudents(List<Student> assignmentStudents) {
        this.assignmentStudents = assignmentStudents;
    }

    public void studentIdValueChanged(ValueChangeEvent e) {
        if (gradebookStudents != null) {
            String newVal = e.getNewValue().toString();
            try {
                this.student = Integer.parseInt(newVal);
                if (gradebookStudents != null) {
                    Student stu = gradebookStudents.get(0);
                    for (int studentCounter = 0; studentCounter < gradebookStudents.size(); studentCounter++) {
                        if (gradebookStudents.get(studentCounter).getStudentId() == student) {
                            stu = gradebookStudents.get(studentCounter);
                            studentName = stu.getStudentFname() + stu.getStudentLname();
                            readyToSubmit = false;
                        }
                    }
                }

            } catch (NumberFormatException nfe) {
                nfe.printStackTrace();
            }
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
        List<Student> stu = helper.getStudentsFromGradebook(gradebookId);

        if (stu.size() != 0) {
            if ((gradebookStudentValues == null) || !(stu.equals(gradebookStudents))) {
                gradebookStudents = stu;
                gradebookStudentValues = new ListDataModel(gradebookStudents);
            }
        }
        return gradebookStudentValues;
    }

    public void setGradebookStudentValues(DataModel gradebookStudentValues) {
        this.gradebookStudentValues = gradebookStudentValues;
    }

    public List<Student> getGradebookStudents() {
        return this.gradebookStudents;
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
        if (isModified && (grade != modifyGrade)) {
            grade = modifyGrade;
        }

        if (firstRun || (readyToRefresh && isModified)) {
            gaid = helper.getGradebookAssignmentId(gradebookId, assignment);
            StudentAssignment said = helper.getStudentAssignment(student, gaid);
            if (said != null) {
                int saId = said.getStudentAssignmentId();
                // grade = helper.getGrade(saId).getStudentAssignmentGrade();
            }

        }
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public boolean isReadyToRefresh() {
        return readyToRefresh;
    }

    public void setReadyToRefresh(boolean readyToRefresh) {
        this.readyToRefresh = readyToRefresh;
    }

    public int getPreviousGradebookId() {
        return previousGradebookId;
    }

    public void setPreviousGradebookId(int previousGradebookId) {
        this.previousGradebookId = previousGradebookId;
    }

    public String getGradeResponse() {
        if ((readyToRefresh) && (isModified)) {
            if ((modifyGrade < 0) || (modifyGrade > 100)) {
                gradeResponse = "Error: invalid grade.";
            } else {
                firstRun = true;

                changeGradeResult = changeGrade(modifyGrade, student);
                if (changeGradeResult == -1) {
                    gradeResponse = "";
                    readyToRefresh = false;
                } else {
                    gradeResponse = "Grade updated.";
                    gaid = helper.getGradebookAssignmentId(gradebookId, assignment);
                    readyToRefresh = false;
                    firstRun = true;
                    if (isModified) {
                        isModified = false;
                    }
                }
            }

        } else {
            gradeResponse = "";
        }
        return this.gradeResponse;
    }

    public void setGradeResponse(String gradeResponse) {
        this.gradeResponse = gradeResponse;
    }

    public int getGaid() {
        setGaid(helper.getGradebookAssignmentId(gradebookId, assignment));
        return gaid;
    }

    public void setGaid(int gaid) {
        this.gaid = gaid;
    }

    public String viewModifyGrade() {
        gaid = helper.getGradebookAssignmentId(gradebookId, assignment);

        StudentAssignment said = helper.getStudentAssignment(student, gaid);
        if (said != null) {
            int saId = said.getStudentAssignmentId();
            grade = helper.getGrade(saId).getStudentAssignmentGrade();
        }

        return "inputGrades";
    }

    public int returnStudentGrade(int student) {
        if (firstRun) {
            grade = getInitialGrade(student);
        } else {
            if (readyToRefresh && isModified) {
                gaid = helper.getGradebookAssignmentId(gradebookId, assignment);
                StudentAssignment said = helper.getStudentAssignment(student, gaid);
                if (said != null) {
                    int saId = said.getStudentAssignmentId();
                    grade = helper.getGrade(saId).getStudentAssignmentGrade();
                }
            }
        }
        return grade;

    }

    public int changeGrade(int newGrade, int student) {
        gaid = helper.getGradebookAssignmentId(gradebookId, assignment);

        if (readyToRefresh) {
            StudentAssignment sa = helper.getStudentAssignment(student, gaid);
            int said = sa.getStudentAssignmentId();
            changeGradeResult = helper.changeGrade(said, modifyGrade);
        } else {
            changeGradeResult = -1;
        }
        return changeGradeResult;
    }

    public String inputGrade() {
        readyToRefresh = true;
        firstRun = false;
        isModified = true;
        grade = modifyGrade;
        return "inputGrades";
    }

    public int getModifyGrade() {
        return modifyGrade;
    }

    public void setModifyGrade(int modifyGrade) {
        this.modifyGrade = modifyGrade;
    }

    public String returnToStudentList() {
        firstRun = false;
        readyToRefresh = true;
        isModified = true;
        return "viewStudents";
    }

    public boolean isFirstRun() {
        return firstRun;
    }

    public void setFirstRun(boolean firstRun) {
        this.firstRun = firstRun;
    }

    public boolean isIsModified() {
        return isModified;
    }

    public void setIsModified(boolean isModified) {
        this.isModified = isModified;
    }

    public int getInitialGrade(int sid) {
        gaid = helper.getGradebookAssignmentId(gradebookId, assignment);

        StudentAssignment said = helper.getStudentAssignment(sid, gaid);
        if (said != null) {
            int saId = said.getStudentAssignmentId();
            grade = helper.getGrade(saId).getStudentAssignmentGrade();

        }

        return grade;

    }

    public int getResultGrade(int student) {
        if (firstRun) {
            resultGrade = returnStudentGrade(student);
            gaid = helper.getGradebookAssignmentId(gradebookId, assignment);

            StudentAssignment said = helper.getStudentAssignment(student, gaid);
            if (said != null) {
                int saId = said.getStudentAssignmentId();
                int val = helper.getGrade(saId).getStudentAssignmentGrade();
                if ((val != modifyGrade) && isModified) {
                    resultGrade = modifyGrade;
                }
            }

        } else {
            resultGrade = grade;
        }
        return resultGrade;
    }

    public void setResultGrade(int resultGrade) {
        this.resultGrade = resultGrade;
    }

}
