package gradebook;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 * Contains SQL Database connection to Gradebook as well as related tables.
 * (2/24/2017)
 *
 * @author Pavel Shekhter
 */
public class GradebookHelper {

    Session session = null;

    /**
     * Default constructor to initialize Session - Pavel Shekhter (2/24/2017)
     */
    public GradebookHelper() {
        // 
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a List of Gradebooks belonging to the Instructor Return null if
     * transaction failed. - Pavel Shekhter (2/24/2017)
     *
     * @param instructorId Instructor ID
     * @return gradebookList
     */
    public List getGradebooks(int instructorId) {
        // Create the list
        List<Gradebook> gradebookList = null;

        String sql = "SELECT * FROM gradebook WHERE INSTRUCTOR_ID = :ins ";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Gradebook.class);

            // Binding parameters
            query.setParameter("ins", instructorId);

            // Execute query
            gradebookList = (List<Gradebook>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return gradebookList;
    }

    /**
     * Returns the Instructor who owns the given Gradebook. Return null if
     * transaction failed. - Pavel Shekhter (2/24/2017)
     *
     * @param gradebookID Gradebook ID
     * @return instructorList
     */
    public Instructor getGradeBookInstructor(int gradebookID) {
        // Create list
        List<Instructor> instructorList = null;

        // Select the instructor name
        String sql = "SELECT * FROM gradebook "
                + "INNER JOIN instructor ON gradebook.INSTRUCTOR_ID = :id";

        try {
            // Begin transaction if inactive
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add entities
            query.addEntity(Instructor.class);

            // Bind parameter
            query.setParameter("id", gradebookID);

            // Execute query and cast
            instructorList = (List<Instructor>) query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (instructorList.size() != 0) {
            return instructorList.get(0);
        } else {
            return null;
        }
    }

    /**
     * Create a Gradebook based on the active instructorID, a selected courseID,
     * and a selected semesterID. - Pavel Shekhter (2/24/2017)
     *
     * @param course Course
     * @param semester Semester semester
     * @param instructor Instructor
     * @return result
     */
    public int insertGradebook(Course course, Semester semester, Instructor instructor) {

        // Initialize a result value
        int result = 0;

        // Set SQL Insertion String
        String sql = "INSERT INTO gradebook "
                + "(COURSE_ID, SEMESTER_ID, INSTRUCTOR_ID) "
                + "VALUES (:courseID, :semesterID, :instructorID)";

        try {
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create query
            SQLQuery query = session.createSQLQuery(sql);

            // Configure query
            query.addEntity(Gradebook.class);

            query.setParameter("courseID", course.getCourseId());
            query.setParameter("semesterID", semester.getSemesterId());
            query.setParameter("instructorID", instructor.getInstructorId());

            // Execute update
            result = query.executeUpdate();

            // Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public Instructor getInstructorById(int id) {
        // Create list
        List<Instructor> instructorList = null;

        // Select the instructor name
        String sql = "SELECT * FROM instructor "
                + "WHERE INSTRUCTOR_ID = :id LIMIT 1";
        try {
            // Begin transaction if inactive
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add entities
            query.addEntity(Instructor.class);

            // Bind parameter
            query.setParameter("id", id);

            // Execute query and cast
            instructorList = (List<Instructor>) query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((instructorList != null) && (instructorList.isEmpty())) {
            return instructorList.get(0);
        } else {
            return null;
        }

    }

    public Course getCourseById(int id) {
        // Create list
        List<Course> courseList = null;

        // Select the instructor name
        String sql = "SELECT * FROM course "
                + "WHERE COURSE_ID = 1 LIMIT 1";
        try {
            // Begin transaction if inactive
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add entities
            query.addEntity(Course.class);

            // Bind parameter
            query.setParameter("id", id);

            // Execute query and cast
            courseList = (List<Course>) query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((courseList != null) && (courseList.isEmpty())) {
            return courseList.get(0);
        } else {
            return null;
        }

    }

    public List<Gradebook> getSemesterNameAtId(int id) {
        // Create list
        List<Gradebook> list = null;

        // Select the instructor name
        String sql = "SELECT * FROM gradebook "
                + "WHERE SEMESTER_ID = :id LIMIT 1";
        try {
            // Begin transaction if inactive
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add entities
            query.addEntity(Gradebook.class);

            // Bind parameter
            query.setParameter("id", id);

            // Execute query and cast
            list = (List<Gradebook>) query.list();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if ((list != null) && (list.isEmpty())) {
            return list;
        } else {
            return null;
        }

    }

    public List getCourses() {
        // Create the list
        List<Course> courseList = null;

        String sql = "SELECT * FROM course";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Course.class);

            // Execute query
            courseList = (List<Course>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return courseList;

    }

    public List getSemesters() {
        // Create the list
        List<Semester> semesterList = null;

        String sql = "SELECT * FROM semester";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Semester.class);

            // Execute query
            semesterList = (List<Semester>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return semesterList;

    }

    public List getInstructors() {
        // Create the list
        List<Instructor> instructorList = null;

        String sql = "SELECT * FROM instructor";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Instructor.class);

            // Execute query
            instructorList = (List<Instructor>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return instructorList;

    }

    public List getGradebooks() {
        // Create the list
        List<Gradebook> gradebookList = null;

        String sql = "SELECT * FROM gradebook";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Gradebook.class);

            // Execute query
            gradebookList = (List<Gradebook>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return gradebookList;

    }

    public Gradebook getGradebookAtSemesterAndCourseId(int sid, int cid, int ins) {
        // Create the list
        List<Gradebook> gradebookList = null;

        String sql = "SELECT * FROM gradebook WHERE SEMESTER_ID = :sid AND COURSE_ID = :cid AND INSTRUCTOR_ID = :ins";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Gradebook.class);

            query.setParameter("sid", sid);
            query.setParameter("cid", cid);
            query.setParameter("ins", ins);

            // Execute query
            gradebookList = (List<Gradebook>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (gradebookList.size() != 0) {
            return (Gradebook) gradebookList.get(0);
        } else {
            return null;
        }

    }

    public List getGradebooksOfInstructor(int instructorId) {
        // Create the list
        List<Gradebook> gradebookList = null;

        String sql = "SELECT * FROM gradebook WHERE INSTRUCTOR_ID = :id";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(Gradebook.class);

            query.setParameter("id", instructorId);

            // Execute query
            gradebookList = (List<Gradebook>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return gradebookList;

    }
    
}
