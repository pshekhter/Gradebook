package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Pavel Shekhter (2/25/2017)
 */
public class CourseHelper {

    /**
     * Contains SQL Database connection to Course as well as related tables.
     * (2/24/2017)
     *
     * @author Pavel Shekhter
     */
    Session session = null;

    /**
     * Default constructor to initialize Session - Pavel Shekhter (2/25/2017)
     */
    public CourseHelper() {
        // 
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getCourses() {

        // Create course list
        List courseList = null;

        // Get all courses in table
        String sql = "SELECT * FROM course";

        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Course.class);

            courseList = (List<Course>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return courseList;
    }


}
