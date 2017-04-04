package gradebook;

import java.util.List;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

/**
 *
 * @author Pavel Shekhter
 */
public class GradebookAssignmentHelper {

    Session session = null;

    public GradebookAssignmentHelper() {
        try {
            // Initialize session and begin transaction with MySQL
            this.session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<GradebookAssignment> getAssignmentsFromGradebook(int gid) {
        // Create the list
        List<GradebookAssignment> gradebookAssignmentList = null;

        String sql = "SELECT * FROM gradebook_assignment WHERE GRADEBOOK_ID = :gid ";

        try {
            // Begin new transaction if we have an inactive one
            if (!this.session.getTransaction().isActive()) {
                session.beginTransaction();
            }

            // Create an SQL query from the SQL string
            SQLQuery query = session.createSQLQuery(sql);

            // Add an entity
            query.addEntity(GradebookAssignment.class);

            // Binding parameters
            query.setParameter("gid", gid);

            // Execute query
            gradebookAssignmentList = (List<GradebookAssignment>) query.list();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return gradebooks
        return gradebookAssignmentList;

    }
}
