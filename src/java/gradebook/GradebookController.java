
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
 * @author iGenius
 */
@Named(value = "gradebookController")
@SessionScoped
public class GradebookController implements Serializable {
    
    // Fields mapping to select options in createBook.xhtml
    DataModel courseValues;
    
    // Create CourseHelper instance
    CourseHelper courseHelper;
    
    // Maps to components
    // Represents selected values
    String course;
    int courseID;
    int semesterID;
    int instructorID;    

    /**
     * Creates a new instance of GradebookController
     */
    public GradebookController() {
        courseHelper = new CourseHelper();
    }
    
    /**
     * Get Course values
     */
    public DataModel getCourseValues() {
        if (courseValues == null) {
            courseValues = new ListDataModel(courseHelper.getCourses());
        }
        
        return courseValues;
    }
    
    /**
     * Sets course values.
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
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
    
    
    
}
