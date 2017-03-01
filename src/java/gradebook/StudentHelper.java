package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author allis
 */
public class StudentHelper {
    
    Session session = null;

    public StudentHelper() {
        try {
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public List getStudents() {
        
        
        List<Student> studentList = null;
        
        
        String sql = "SELECT * FROM student";
        
        try {
            
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }
            
            SQLQuery query = session.createSQLQuery(sql);
            
            query.addEntity(Assignment.class);
            
            studentList = (List<Student>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return studentList;
    }

}
