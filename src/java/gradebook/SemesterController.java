
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
@Named(value = "semesterController")
@SessionScoped
public class SemesterController implements Serializable {
    
    
    // The data model storing the semester's values
    DataModel semesterValues;
    
    // Handles SQL between the app and the Semester table
    SemesterHelper semesterHelper;
   
    // Map to components
    Semester semester;
    String semesterName;
    String response;

    /**
     * Creates a new instance of SemesterController
     */
    public SemesterController() {
        semesterHelper = new SemesterHelper();
    }

    public DataModel getSemesterValues() {
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
    
    
    
}
