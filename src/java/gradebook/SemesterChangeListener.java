
package gradebook;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;


/**
 *
 * @author Pavel Shekhter (3/1/2017)
 */
public class SemesterChangeListener  implements ValueChangeListener {

    @Override
    public void processValueChange(ValueChangeEvent event) throws AbortProcessingException {
        // Access Course bean directly
        GradebookController controller = new GradebookController();
        controller.setSemester(event.getNewValue().toString());
    }
    
}
