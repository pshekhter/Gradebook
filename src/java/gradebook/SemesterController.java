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
@Named(value = "semesterController")
@SessionScoped
public class SemesterController implements Serializable {

    // The data model storing the semester's values
    DataModel semesterValues;

    // Handles SQL between the app and the Semester table
    SemesterHelper semesterHelper;
    InstructorHelper instructorHelper;

    List<Semester> semesters;

    // Map to components
    Semester semester;
    String semesterName;
    String response;
    int semesterId;

    String activeInstructorEmail;

    int activeInstructorId;

    /**
     * Creates a new instance of SemesterController
     */
    public SemesterController() {
        semesterHelper = new SemesterHelper();
        instructorHelper = new InstructorHelper();
        semesters = semesterHelper.getSemesters();
        
    }

    public DataModel getSemesterValues() {
        if (semesterValues == null) {
            semesterValues = new ListDataModel(semesterHelper.getSemesters());
        }

        return semesterValues;
    }

    public void setSemesterValues(DataModel semesterValues) {
        this.semesterValues = semesterValues;
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getSemesterId() {
        return semesterId;
    }

    public void setSemesterId(int semesterId) {
        this.semesterId = semesterId;
    }

    public String selectSemester() {
        return "selectSemester";
    }

    public void semesterIdValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.semesterId = Integer.parseInt(newVal);
            Semester semester = semesters.get(0);
            for (int semesterCounter = 0; semesterCounter < semesters.size(); semesterCounter++) {
                if (semesters.get(semesterCounter).getSemesterId() == semesterId) {
                    semester = semesters.get(semesterCounter);
                    semesterName = semester.getSemesterName();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    public int getActiveInstructorId() {
        return activeInstructorId;
    }

    public void setActiveInstructorId(int activeInstructorId) {
        this.activeInstructorId = activeInstructorId;
    }

    public String getActiveInstructorEmail() {
        if ((activeInstructorEmail != null) && (instructorHelper.getInstructor(activeInstructorEmail) != null)) {
            activeInstructorId = instructorHelper.getInstructor(activeInstructorEmail).getInstructorId();
        }
        return activeInstructorEmail;
    }

    public void setActiveInstructorEmail(String activeInstructorEmail) {
        this.activeInstructorEmail = activeInstructorEmail;
    }

}
