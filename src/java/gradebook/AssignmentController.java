
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
    
    DataModel assignmentValues;
    
    
    int assignmentID;
    String assignmentName;
        
    AssignmentHelper helper;
    
    public AssignmentController() {
        
        helper = new AssignmentHelper();
        
    }

    public int getAssignmentID() {
        return assignmentID;
    }

    public void setAssignmentID(int assignmentID) {
        this.assignmentID = assignmentID;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }
    
    
    
}
