
package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;


/**
 *
 * @author Pavel Shekhter (2/25/2017)
 */
public class InstructorHelper {

    /**
     * Contains SQL Database connection to Instructor as well as related tables.
     * (2/24/2017)
     *
     * @author Pavel Shekhter
     */
    Session session = null;

    /**
     * Default constructor to initialize Session - Pavel Shekhter (2/25/2017)
     */
    public InstructorHelper() {
        // 
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List getInstructors() {
        
        // Create course list
        List instructorList = null;
        
        // Get all courses in table
        String sql = "SELECT * FROM instructor";
        
        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            SQLQuery query = session.createSQLQuery(sql);
            
            query.addEntity(Instructor.class);
            
            instructorList = (List<Instructor>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return instructorList;
    }

}