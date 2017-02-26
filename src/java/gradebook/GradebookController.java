
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
    DataModel semesterValues;
    
    // Create CourseHelper instance
    CourseHelper courseHelper;
       
    // Create SemesterHelper instance
    SemesterHelper semesterHelper;
    
    // Maps to components
    // Represents selected values
    String course;
    int courseID;
    String semester;
    int semesterID;
    int instructorID;    

    /**
     * Creates a new instance of GradebookController
     */
    public GradebookController() {
        courseHelper = new CourseHelper();
        semesterHelper = new SemesterHelper();
    }
    
    /**
     * Get Course values
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

    /**
     * Gets semesters.
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
     * @param semesterValues 
     */
    public void setSemesterValues(DataModel semesterValues) {
        this.semesterValues = semesterValues;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    
    
}
