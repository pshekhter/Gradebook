package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author allis
 */
@Named(value = "studentController")
@SessionScoped
public class StudentController implements Serializable {

    /**
     * Creates a new instance of StudentController
     */
    public StudentController() {
    }
    
}
