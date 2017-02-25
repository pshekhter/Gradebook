
package gradebook;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 * Contains SQL Database connection to Gradebook as well as related tables. (2/24/2017)
 * @author Pavel Shekhter
 */
public class GradebookHelper {
    
    Session session = null;
    
    /**
     *  Default constructor to initialize Session
     *  - Pavel Shekhter (2/24/2017)
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
     *  Returns a List of Gradebooks belonging to the Instructor based on a 
     *  startID. Return null if transaction failed.
     *  - Pavel Shekhter (2/24/2017)
     * @param instructorId Instructor ID       
     * @param startId Start ID       
     * @return gradebookList       
    */
    public List getGradebooks(int instructorId, int startId) {
        // Create the list
        List <Gradebook> gradebookList = null;
        
        String sql = "SELECT * FROM gradebook WHERE INSTRUCTOR_ID = :ins "
                + "ORDER BY SEMESTER_ID LIMIT :start, :end";
        
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
            query.setParameter("start", startId);
            query.setParameter("end", 50);
            
            // Execute query
            gradebookList = (List<Gradebook>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Return gradebooks
        return gradebookList;
    }
    
    /**
     *   Returns the Instructor who owns the given Gradebook. 
     *   Return null if transaction failed.
     *  - Pavel Shekhter (2/24/2017)
     *  @param gradebookID Gradebook ID
     *  @return instructorList
    */
    public Instructor getGradeBookInstructor(int gradebookID) {
        // Create list
        List<Instructor> instructorList = null;
        
        // Select the instructor name
        String sql = "SELECT * FROM instructor, gradebook "
                + "WHERE instructor.INSTRUCTOR_ID = gradebook.INSTRUCTOR_ID "
                + "AND gradebook.GRADEBOOK_ID = :gradebookID";
        
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
            query.setParameter("gradebookID", gradebookID);
            
            // Execute query and cast
            instructorList = (List<Instructor>) query.list();
            
        } catch(Exception e) {
            e.printStackTrace();
        }
        
        return instructorList.get(0);
    }
    
    /**
     * Create a Gradebook based on the active instructorID, a selected courseID,
     * and a selected semesterID.
     * - Pavel Shekhter (2/24/2017)
     * @param courseID Course ID
     * @param semesterID Semester ID
     * @param instructorID Instructor ID
     * @return result
     */
    public int insertGradebook(int courseID, int semesterID, int instructorID) {
        
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
            
            query.setParameter("courseID", courseID);
            query.setParameter("semesterID", semesterID);
            query.setParameter("instructorID", instructorID);
            
            // Execute update
            result = query.executeUpdate();
            
            // Commit
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return result;
    }
    
}
