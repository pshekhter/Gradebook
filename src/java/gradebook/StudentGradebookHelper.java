
package gradebook;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 *
 * @author Pavel Shekhter
 */
public class StudentGradebookHelper {
    
    Session session = null;
    
    public StudentGradebookHelper() {
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
}
