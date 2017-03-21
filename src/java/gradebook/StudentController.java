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
    
    StudentHelper helper;
    
    Student student;
    
    public StudentController() {
        
        helper = new StudentHelper();
        
    }

    public DataModel getStudentValues() {
        
        if (studentValues == null) {
            studentValues = new ListDataModel(helper.getStudents());
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
            student = new Student(studentFName, studentLName);

            if (helper.insertStudent(studentFName, studentLName) == 1) {
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
    
    
    
}
