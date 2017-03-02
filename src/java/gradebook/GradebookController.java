package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author iGenius
 */
@Named(value = "gradebookController")
@SessionScoped
public class GradebookController implements Serializable {

    // Fields mapping to select options in createBook.xhtml
    DataModel courseValues;
    DataModel semesterValues;
    DataModel instructorValues;

    // Create CourseHelper instance
    CourseHelper courseHelper;

    // Create SemesterHelper instance
    SemesterHelper semesterHelper;

    // Create InstructorHelper instance
    InstructorHelper instructorHelper;

    // Create GradebookHelper instance
    GradebookHelper gradebookHelper;

    // Maps to components
    // Represents selected values
    String courseName;
    int courseID;
    String semesterName;
    int semesterID;
    String instructorEmail;
    int instructorID;
    String response = " ";

    /**
     * Creates a new instance of GradebookController
     */
    public GradebookController() {
        courseHelper = new CourseHelper();
        semesterHelper = new SemesterHelper();
        instructorHelper = new InstructorHelper();
        gradebookHelper = new GradebookHelper();

    }

    /**
     * Get Course values
     *
     * @return courseValues
     */
    public DataModel getCourseValues() {
        if (courseValues == null) {
            courseValues = new ListDataModel(courseHelper.getCourses());
        }

        return courseValues;
    }

    /**
     * Sets course values.
     *
     * @param courseValues
     */
    public void setCourseValues(DataModel courseValues) {
        this.courseValues = courseValues;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getSemesterID() {
        return semesterID;
    }

    public void setSemesterID(int semesterID) {
        this.semesterID = semesterID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getCourse() {
        return courseName;
    }

    public void setCourse(String course) {
        this.courseName = course;
    }

    /**
     * Gets semesters.
     *
     * @return semester values
     */
    public DataModel getSemesterValues() {
        if (semesterValues == null) {
            semesterValues = new ListDataModel(semesterHelper.getSemesters());
        }

        return semesterValues;

    }

    /**
     * Sets semester values.
     *
     * @param semesterValues
     */
    public void setSemesterValues(DataModel semesterValues) {
        this.semesterValues = semesterValues;
    }

    public String getSemester() {
        return semesterName;
    }

    public void setSemester(String semester) {
        this.semesterName = semester;
    }

    /**
     * Get all instructors.
     *
     * @return instructorValues
     */
    public DataModel getInstructorValues() {
        if (instructorValues == null) {
            instructorValues = new ListDataModel(instructorHelper.getInstructors());
        }

        return instructorValues;
    }

    /**
     * Set instructor values.
     *
     * @param instructorValues
     */
    public void setInstructorValues(DataModel instructorValues) {
        this.instructorValues = instructorValues;
    }

    public String getInstructor() {
        return instructorEmail;
    }

    public void setInstructor(String instructor) {
        this.instructorEmail = instructor;
    }

    public String getResponse() {

        if (courseName != null && semesterName != null && instructorEmail != null) {
            Course course = (Course) gradebookHelper.getCourseById(courseID);
            Semester semester = (Semester) gradebookHelper.getSemesterNameAtId(semesterID);
            Instructor instructor = (Instructor) gradebookHelper.getGradeBookInstructor(instructorID);

            if ((course != null) && (semester != null) && (instructor != null)) {
                if (gradebookHelper.insertGradebook(course, semester, instructor) == 1) {
                    courseName = null;
                    semesterName = null;
                    instructorEmail = null;
                    response = "Gradebook Added to Database.";
                } else {
                    courseName = null;
                    semesterName = null;
                    instructorEmail = null;
                    response = "Gradebook Not Added to Database.";
                }
            }
        } else {
            response = " ";
        }

        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void courseNameValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.courseID = Integer.parseInt(newVal);
            this.courseName = (String) gradebookHelper.getCourseById(courseID).getCourseName();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public void semesterNameValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.semesterID = Integer.parseInt(newVal);
            this.semesterName = (String) gradebookHelper.getSemesterNameAtId(semesterID).getSemesterName();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    public void instructorEmailValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.instructorID = Integer.parseInt(newVal);
            this.instructorEmail = (String) gradebookHelper.getInstructorById(instructorID).getInstructorEmail();
        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

}
