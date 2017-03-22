package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author pshekhter (2/25/2017)
 */
public class SemesterHelper {

    /**
     * Contains SQL Database connection to Semester as well as related tables.
     * (2/24/2017)
     *
     * @author Pavel Shekhter
     */
    Session session = null;

    /**
     * Default constructor to initialize Session - Pavel Shekhter (2/25/2017)
     */
    public SemesterHelper() {
        // 
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List getSemesters() {

        // Create course list
        List semesterList = null;

        // Get all courses in table
        String sql = "SELECT * FROM semester";

        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Semester.class);

            semesterList = (List<Semester>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return semesterList;
    }

    public Semester getSemester(int id) {
        // Create course list
        List semesterList = null;

        // Get all courses in table
        String sql = "SELECT * FROM semester WHERE id = :id";

        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Semester.class);

            query.setParameter("id", id);

            semesterList = (List<Semester>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (semesterList != null) {
            return (Semester) semesterList.get(0);
        }
        return null;
    }

}
