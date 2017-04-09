package gradebook;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import java.util.List;

/**
 *
 * @author Pavel Shekhter
 */
public class GradebookStudentHelper {

    Session session = null;

    public GradebookStudentHelper() {
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Returns number of students in the gradebook
    public int getNumStudents(int gradebookID) {
        int result = 0;
        List<GradebookStudent> gradebookStudentList = null;

        String sql = "SELECT * FROM GRADEBOOK_STUDENT WHERE GRADEBOOK_ID = :g";

        try {
            if (!this.session.getTransaction().isActive()) {
                this.session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(GradebookStudent.class);

            gradebookStudentList = (List<GradebookStudent>) query.list();
            result = gradebookStudentList.size();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    // Insert student into gradebook
    public int insertStudentToGradebook(int studentID, int gradebookID) {
        int result = 0;

        String sql = "INSERT INTO Gradebook_Student(STUDENT_ID, GRADEBOOK_ID) "
                + "VALUES (:student_ID, :gradebook_ID)";

        try {

            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(GradebookStudent.class);
            query.setParameter("student_ID", studentID);
            query.setParameter("gradebook_ID", gradebookID);

            result = query.executeUpdate();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
