package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
    String selectResponse;
    String createResponse;
    String createInstructorEmail;

    /**
     * Creates a new instance of InstructorController
     */
    public InstructorController() {
        instructorHelper = new InstructorHelper();
        previousInstructorID = instructorID;
        selectResponse = "The selected instructor is: ";
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

    public String getSelectResponse() {

        if (instructorEmail != null) {

            if (instructorHelper.getInstructor(instructorEmail) != null) {
                instructor = instructorHelper.getInstructor(instructorEmail);
                instructorEmail = null;
                selectResponse = "Instructor found. The currently active instructor is: " + instructor.getInstructorEmail();
                instructorID = instructor.getInstructorId();
                return selectResponse;
            } else {
                instructorEmail = null;
                selectResponse = "Instructor not found.";
                return selectResponse;
            }
        } else {
            selectResponse = " ";
            return selectResponse;
        }
    }

    public void setSelectResponse(String selectResponse) {
        this.selectResponse = selectResponse;
    }

    public int getPreviousInstructorID() {
        return previousInstructorID;
    }

    public void setPreviousInstructorID(int previousInstructorID) {
        this.previousInstructorID = previousInstructorID;
    }

    public String getCreateResponse() {

        if (createInstructorEmail != null) {

            if (instructorHelper.insertInstructor(instructorEmail) == 1) {
                createInstructorEmail = null;
                createResponse = "Instructor created.";
                return createResponse;
            } else {
                createInstructorEmail = null;
                createResponse = "Instructor not created.";
                return createResponse;
            }
        } else {
            createResponse = " ";
            return createResponse;
        }

    }

    public void setCreateResponse(String createResponse) {
        this.createResponse = createResponse;
    }

    public String getCreateInstructorEmail() {
        return createInstructorEmail;
    }

    public void setCreateInstructorEmail(String createInstructorEmail) {
        this.createInstructorEmail = createInstructorEmail;
    }
    
    public String submit() {
        return "index";
    }

     
}
