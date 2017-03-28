package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author Pavel Shekhter
 */
@Named(value = "courseController")
@SessionScoped
public class CourseController implements Serializable {

    // The data model storing the course's values
    DataModel courseValues;

    // Handles SQL between the app and the Course table
    CourseHelper courseHelper;
    GradebookHelper gradebookHelper;
    InstructorHelper instructorHelper;
    SemesterHelper semesterHelper;

    // Map to components
    Course course;
    String courseName;
    String response;
    String gradebookResponse;
    List<Course> courses;
    int courseId;

    int activeSemesterId;
    int activeInstructorId;
    Gradebook activeGradebook;
    int activeGradebookId;

    String semesterName;

    String activeInstructorEmail;

    String selectedEmail;

    /**
     * Creates a new instance of CourseController
     */
    public CourseController() {
        courseHelper = new CourseHelper();
        gradebookHelper = new GradebookHelper();
        instructorHelper = new InstructorHelper();
        semesterHelper = new SemesterHelper();
        courses = courseHelper.getCourses();

    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getResponse() {
        if (courseName != null) {
            course = new Course(courseName);
            if (courseHelper.insertCourse(courseName) == 1) {
                courseName = null;
                response = "Course added.";
            } else {
                courseName = null;
                response = "Course not added.";
            }
        } else {
            // Don't display message
            response = " ";
        }
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public DataModel getCourseValues() {

        if (courseValues == null) {
            courseValues = new ListDataModel(courseHelper.getCourses());
        }

        return courseValues;

    }

    public void setCourseValues(DataModel courseValues) {
        this.courseValues = courseValues;
    }

    public int getActiveSemesterId() {
        return activeSemesterId;
    }

    public void setActiveSemesterId(int activeSemesterId) {
        this.activeSemesterId = activeSemesterId;
    }

    public String selectCourse() {
        return "selectCourse";
    }

    public void courseIdValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.courseId = Integer.parseInt(newVal);
            Course course = courses.get(0);
            for (int courseCounter = 0; courseCounter < courses.size(); courseCounter++) {
                if (courses.get(courseCounter).getCourseId() == courseId) {
                    course = courses.get(courseCounter);
                    courseName = course.getCourseName();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public String selectGradebook() {
        return "addStudent";
    }

    public Gradebook getActiveGradebook() {
        return activeGradebook;
    }

    public void setActiveGradebook(Gradebook activeGradebook) {
        this.activeGradebook = activeGradebook;
    }

    public void setSelectedEmail(String selectedEmail) {
        this.selectedEmail = selectedEmail;
    }

    public int getActiveInstructorId() {
        return activeInstructorId;
    }

    public void setActiveInstructorId(int activeInstructorId) {
        this.activeInstructorId = activeInstructorId;
    }

    public String getActiveInstructorEmail() {
        if (activeInstructorId > 0) {
            return instructorHelper.getInstructor(activeInstructorId).getInstructorEmail();
        } else {
            return "";
        }
    }

    public void setActiveInstructorEmail(String activeInstructorEmail) {
        this.activeInstructorEmail = activeInstructorEmail;
    }

    public String getGradebookResponse() {
        if ((activeInstructorEmail != null) && (courseName != null) && (semesterName != null)) {

            courseId = courseHelper.getCourse(courseName).getCourseId();
            activeSemesterId = semesterHelper.getSemester(semesterName).getSemesterId();
            activeInstructorId = instructorHelper.getInstructor(activeInstructorEmail).getInstructorId();

            if (gradebookHelper.getGradebookAtSemesterAndCourseId(courseId, activeSemesterId, activeInstructorId) != null) {
                activeGradebook = (Gradebook) gradebookHelper.getGradebookAtSemesterAndCourseId(courseId, activeSemesterId, activeInstructorId);
                activeGradebookId = activeGradebook.getGradebookId();
                courseName = null;
                courseId = 0;
                activeSemesterId = 0;
                gradebookResponse = "Gradebook Found: "
                        + activeGradebookId + "\n"
                        + activeGradebook.getCourse().getCourseName() + "\n"
                        + activeGradebook.getSemester().getSemesterName();
            } else {
                courseName = null;
                courseId = 0;
                activeSemesterId = 0;
                gradebookResponse = "Gradebook not found.";
            }
        } else {
            // Don't display message
            gradebookResponse = " ";
        }
        return gradebookResponse;
    }

    public void setGradebookResponse(String gradebookResponse) {
        this.gradebookResponse = gradebookResponse;
    }

    public String submit() {
        return "selectCourse";
    }

    public String getSemesterName() {
        return semesterName;
    }

    public void setSemesterName(String semesterName) {
        this.semesterName = semesterName;
    }

    public int getActiveGradebookId() {
        return activeGradebookId;
    }

    public void setActiveGradebookId(int activeGradebookId) {
        this.activeGradebookId = activeGradebookId;
    }

    public String addStudent() {
        return "addStudent";
    }
}
