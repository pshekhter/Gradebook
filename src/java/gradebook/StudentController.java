package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author allis
 */
@Named(value = "studentController")
@SessionScoped
public class StudentController implements Serializable {

    /**
     * Creates a new instance of StudentController
     */
    
    DataModel studentValues;
    
    String studentFName;
    String studentLName; 
    String response;
    //String responseDelete;
    
    StudentHelper studenthelper;
    GradebookStudentHelper gradebookStudentHelper;
    
    Student student;
    
    public StudentController() {
        
        studenthelper = new StudentHelper();
        gradebookStudentHelper = new GradebookStudentHelper();
        
    }

    public DataModel getStudentValues() {
        
        if (studentValues == null) {
            studentValues = new ListDataModel(studenthelper.getStudents());
        }
        
        return studentValues;
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

    public String getResponse() {
        if (studentFName != null && studentLName != null) {
            
            int studentID = studenthelper.getStudentID();
            student = new Student(studentFName, studentLName);
             
            if (gradebookStudentHelper.insertStudentToGradebook(studentID, 4383) == 1) {
                studentFName = null;
                studentLName = null;
                response = "Student Added.";

            } else {
                studentFName = null;
                studentLName = null;
                response = "Student Not Added.";
            }

        } else {
            response = " ";
        }
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
    
    public String goHome() {
        return "index";
    }
}
