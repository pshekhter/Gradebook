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

    public int insertCourse(String courseName) {

        // Initialize a result value
        int result = 0;

        // Set SQL Insertion String
        String sql = "INSERT INTO Course "
                + "(COURSE_NAME) "
                + "VALUES (:courseName)";

        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create query
            SQLQuery query = session.createSQLQuery(sql);

            // Configure query
            query.addEntity(Course.class);

            query.setParameter("courseName", courseName);

            // Execute update
            result = query.executeUpdate();

            // Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Gradebook getGradebook(int semesterId, int courseId, int instructorId) {

        List<Gradebook> list = null;

        String sql = "SELECT * FROM gradebook "
                + "INNER JOIN semester ON semester.SEMESTER_ID = :sid "
                + "INNER JOIN course ON course.COURSE_ID = cid "
                + "WHERE INSTRUCTOR_ID = :ins";
        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);

            query.addEntity(Course.class);

            query.setParameter("sid", semesterId);
            query.setParameter("cid", courseId);
            query.setParameter("ins", instructorId);

            list = (List<Gradebook>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (0 != list.size()) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
