package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
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

    // List of elements
    List<Course> courses;
    List<Semester> semesters;
    List<Instructor> instructors;
    List<Gradebook> gradebooks;
    List<Gradebook> instructorGradebooks;

    // Stores the currently selected gradebook ID for passing
    int gradebookID;

    // Maps to components
    // Represents selected values
    String courseName;
    int courseID;
    String semesterName;
    int semesterID;
    String instructorEmail;
    int instructorID;
    String response = " ";
    String selectedEmail;

    // Tracks change
    boolean isReadyForSubmit = false;

    /**
     * Creates a new instance of GradebookController
     */
    public GradebookController() {
        courseHelper = new CourseHelper();
        semesterHelper = new SemesterHelper();
        instructorHelper = new InstructorHelper();
        gradebookHelper = new GradebookHelper();
        courses = gradebookHelper.getCourses();
        semesters = gradebookHelper.getSemesters();
        gradebooks = gradebookHelper.getGradebooks();
        instructors = gradebookHelper.getInstructors();
        instructorID = instructors.get(0).getInstructorId();
        instructorEmail = instructors.get(0).getInstructorEmail();
        courseName = courses.get(0).getCourseName();
        courseID = courses.get(0).getCourseId();
        semesterName = semesters.get(0).getSemesterName();
        semesterID = semesters.get(0).getSemesterId();
        response = " ";
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

        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void semesterNameValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.semesterID = Integer.parseInt(newVal);
            Semester semester = semesters.get(0);
            for (int semesterCounter = 0; semesterCounter < semesters.size(); semesterCounter++) {
                if (semesters.get(semesterCounter).getSemesterId() == semesterID) {
                    semester = semesters.get(semesterCounter);
                    semesterName = semester.getSemesterName();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    public void instructorEmailValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.instructorID = Integer.parseInt(newVal);
            Instructor instructor = instructors.get(0);
            for (int instructorCounter = 0; instructorCounter < instructors.size(); instructorCounter++) {
                if (instructors.get(instructorCounter).getInstructorId() == instructorID) {
                    instructor = instructors.get(instructorCounter);
                    instructorEmail = instructor.getInstructorEmail();
                    instructorID = instructor.getInstructorId();
                    gradebooks = gradebookHelper.getGradebooks(instructorID);
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public String createBook() {
        if (courseName != null && semesterName != null && selectedEmail != null) {
            Course course = null;
            Semester semester = null;
            Instructor instructor = null;

            for (int courseCounter = 0; courseCounter < courses.size(); courseCounter++) {
                if (courses.get(courseCounter).getCourseId() == courseID) {
                    course = courses.get(courseCounter);
                }
            }

            for (int semesterCounter = 0; semesterCounter < semesters.size(); semesterCounter++) {
                if (semesters.get(semesterCounter).getSemesterId() == semesterID) {
                    semester = semesters.get(semesterCounter);
                }
            }

            instructor = instructorHelper.getInstructor(selectedEmail);

            isReadyForSubmit = true;

            if ((course != null) && (semester != null) && (instructor != null) && (isReadyForSubmit == true)) {
                if (gradebookHelper.insertGradebook(course, semester, instructor) == 1) {
                    courseName = null;
                    semesterName = null;
                    instructorEmail = null;
                    response = "Gradebook Added to Database.";
                    isReadyForSubmit = false;
                    this.courseID = course.getCourseId();
                    this.semesterID = semester.getSemesterId();
                    this.instructorID = instructor.getInstructorId();
                    return "createBook";
                } else {
                    courseName = null;
                    semesterName = null;
                    instructorEmail = null;
                    response = "Gradebook Not Added to Database.";
                    isReadyForSubmit = false;
                    return "createBook";
                }
            }
        } else if (isReadyForSubmit == false) {
            response = "Not ready for submit. Make sure you've changed all three fields.";
            return "createBook";
        }
        return response;
    }

    public void insert() {
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
        String idString = params.get("id");
        try {
            this.instructorID = Integer.parseInt(idString);
            this.instructorEmail = instructors.get(instructorID).getInstructorEmail();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    public String getEmail(int insId) {
        return instructors.get(insId - 1).getInstructorEmail();
    }

    public String getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(String selectedEmail) {
        this.selectedEmail = selectedEmail;
    }

    public int getGradebookID() {
        return gradebookID;
    }

    public void setGradebookID(int gradebookID) {
        this.gradebookID = gradebookID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void courseNameValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.courseID = Integer.parseInt(newVal);
            Course course = courses.get(0);
            for (int courseCounter = 0; courseCounter < courses.size(); courseCounter++) {
                if (courses.get(courseCounter).getCourseId() == courseID) {
                    course = courses.get(courseCounter);
                    courseName = course.getCourseName();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public List<Semester> getSemesters() {
        return semesters;
    }

    public void setSemesters(List<Semester> semesters) {
        this.semesters = semesters;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }

    public List<Gradebook> getGradebooks() {
        return gradebooks;
    }

    public void setGradebooks(List<Gradebook> gradebooks) {
        this.gradebooks = gradebooks;
    }

    public List<Gradebook> getInstructorGradebooks() {
        if (selectedEmail == null) {
            return null;
        }
        int insId = 0;
        if (instructorHelper.getInstructor(selectedEmail) != null) {
            insId = instructorHelper.getInstructor(selectedEmail).getInstructorId();
        }
        if (insId != 0) {
            return gradebookHelper.getGradebooks(insId);
        } else {
            return null;
        }
    }

    public void setInstructorGradebooks(List<Gradebook> instructorGradebooks) {
        this.instructorGradebooks = instructorGradebooks;
    }

    public String getInstructorEmail() {
        return instructorEmail;
    }

    public void setInstructorEmail(String instructorEmail) {
        this.instructorEmail = instructorEmail;
    }
    
    public String addStudents() {
        return "addStudent";
    }

}
