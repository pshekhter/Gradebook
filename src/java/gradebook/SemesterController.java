/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

/**
 *
 * @author iGenius
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
    
}
