
package gradebook;

import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author allis
 */
public class AssignmentHelper {
    
    Session session = null;
    
    public AssignmentHelper(){
        try{
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
    } catch (Exception e) {
        e.printStackTrace();
    }
    }
    
    public int insertAssignment(Assignment a){
        int result = 0;
        
        String sql = "insert into assignment(assignment_id, assignment_name)"
                + "values (:id, :name)";
                
        try {
            
            if (!this.session.getTransaction().isActive()){
                session.beginTransaction();
            }
            
            SQLQuery q = session.createSQLQuery(sql);
            q.addEntity(Assignment.class);
            q.setParameter("id", a.getAssignmentId());
            q.setParameter("name", a.getAssignmentName());
            
            result = q.executeUpdate();
            
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
