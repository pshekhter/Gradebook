
package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author allis
 */
@Named(value = "assignmentController")
@SessionScoped
public class AssignmentController implements Serializable {

    /**
     * Creates a new instance of AssignmentController
     */
    
    String assignmentName;

    String response;

  
    AssignmentHelper helper;

    Assignment assignment;

    public AssignmentController() {
        helper = new AssignmentHelper();

    }

    public String getAssignmentName() {
        return assignmentName;

    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;

    }
    public String getResponse() {

        if (assignmentName != null) {
            assignment = new Assignment(assignmentName);

            if (helper.insertAssignment(assignmentName) == 1) {
                assignmentName = null;
                response = "Assignment Added.";

            } else {
                assignmentName = null;
                response = "Assignment Not Added.";
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
