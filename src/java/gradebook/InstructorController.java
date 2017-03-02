package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author pshekhter
 */
@Named(value = "instructorController")
@SessionScoped
public class InstructorController implements Serializable {

    // Data values
    DataModel instructorValues;

    // Instructor Helper
    InstructorHelper instructorHelper;

    // Maps to components in template
    Instructor instructor;
    String instructorEmail;
    int instructorID = 0;
    int previousInstructorID;
    String response;

    /**
     * Creates a new instance of InstructorController
     */
    public InstructorController() {
        instructorHelper = new InstructorHelper();
        previousInstructorID = instructorID;
        response = "The selected instructor is: ";
    }

    public DataModel getInstructorValues() {
        if (instructorValues == null) {
            instructorValues = new ListDataModel(instructorHelper.getInstructors());
        }
        return instructorValues;
    }

    public void setInstructorValues(DataModel instructorValues) {
        this.instructorValues = instructorValues;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }

    public int getInstructorID() {
        if (instructorID != previousInstructorID) {
            previousInstructorID = instructorID;
        }
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.previousInstructorID = this.instructorID;
        this.instructorID = instructorID;
    }

    public String getResponse() {
        
        if (instructorEmail != null) {

            if (instructorHelper.insertInstructor(instructorEmail) == 1) {
                instructorEmail = null;
                response = "Instructor Added.";
                return response;
            } else {
                instructorEmail = null;
                response = "Instructor Not Added.";
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

    public int getPreviousInstructorID() {
        return previousInstructorID;
    }

    public void setPreviousInstructorID(int previousInstructorID) {
        this.previousInstructorID = previousInstructorID;
    }

}
