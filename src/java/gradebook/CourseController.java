
package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
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
   
    // Map to components
    Course course;
    String courseName;
    String response;

    /**
     * Creates a new instance of CourseController
     */
    public CourseController() {
        courseHelper = new CourseHelper();
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
    
    
    
}
