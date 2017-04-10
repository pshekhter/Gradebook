package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author allis
 */
public class AssignmentHelper {

    Session session = null;

    public AssignmentHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public List getAssignments() {
        
        
        List<Assignment> assignmentList = null;
        
        
        String sql = "SELECT * FROM assignment";
        
        try {
            // Initialize transaction if not already initialized
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            SQLQuery query = session.createSQLQuery(sql);
            
            query.addEntity(Assignment.class);
            
            assignmentList = (List<Assignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return assignmentList;
    }
    
    public int getAssignmentNumber(){
        
        List<Assignment> assignmentList = null;
        
        String sql = "select * from assignment";
        
        try {
            
           
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Assignment.class);
            
            assignmentList = (List<Assignment>) q.list();
            
        } catch(Exception e){
            e.printStackTrace();
        }
        
        return assignmentList.size();
    }
    
    public int getAssignmentID() {
        int assignmentID = 0;

        String sql = "select * from assignment where assignment_id = :id ORDER BY ASSIGNMENT_ID DESC LIMIT 1";

        try {

            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Student.class);

            q.setParameter("id", assignmentID);

            assignmentID = (int) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignmentID;
    }
    
    public Assignment getAssignmentID(String assignmentName) {

        Assignment assignment = null;
        int assignmentID = 0;
        
        
        String sql = "SELECT * FROM ASSIGNMENT WHERE ASSIGNMENT_NAME = :aName  "
                + "ORDER BY ASSIGNMENT_ID DESC LIMIT 1";

        try {

            //3 lines of code are always consistant 
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);

            q.addEntity(Student.class);

            q.setParameter("aName", assignmentName);

            assignment = (Assignment) q.uniqueResult();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return assignment;
    }

    public int insertAssignment(String assignmentName) {
        int result = 0;

        String sql = "insert into assignment(assignment_name)"
                + "values (:name)";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity(Assignment.class);
            q.setParameter("name", assignmentName);

            result = q.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}