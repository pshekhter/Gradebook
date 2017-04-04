package gradebook;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedProperty;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author Pavel Shekhter
 */
@Named(value = "gradebookStudentController")
@SessionScoped
public class GradebookStudentController implements Serializable {

    // Mapping values
    DataModel semesterValues;
    DataModel studentValues;
    DataModel courseValues;

    // Helpers
    GradebookHelper gradebookHelper;
    StudentHelper studentHelper;
    SemesterHelper semesterHelper;
    CourseHelper courseHelper;
    InstructorHelper instructorHelper;
    GradebookStudentHelper gradebookStudentHelper;

    // Lists
    List<Gradebook> gradebooks;
    List<Student> students;
    List<Semester> semesters;
    List<Course> courses;

    // Components
    String gradebookName;

    int instructorID;
    String semesterName;
    String courseName;
    int gradebookID;
    String studentName;
    int studentID;
    String firstName;
    String lastName;
    int courseID;
    String response;
    String selectedEmail = "";

    String email;

    List<Gradebook> matchingGradebooks;

    /**
     * Creates a new instance of GradebookStudentController
     */
    public GradebookStudentController() {
        instructorHelper = new InstructorHelper();
        gradebookHelper = new GradebookHelper();
        studentHelper = new StudentHelper();
        semesterHelper = new SemesterHelper();
        courseHelper = new CourseHelper();
        instructorID = 0;
        students = studentHelper.getStudents();
        semesters = semesterHelper.getSemesters();
        courses = courseHelper.getCourses();
        /**
         * UIViewRoot root = FacesContext.getCurrentInstance().getViewRoot();
         * UIComponent selectedEmailComponent = findComponent(root,
         * "modify_form_input_instructor"); UIInput selectedEmailInput =
         * (UIInput)selectedEmailComponent;
         *
         * if (selectedEmailInput.getValue() != null) { selectedEmail =
         * selectedEmailInput.getValue().toString(); } else { selectedEmail =
         * null; }
         *
         * if (selectedEmail != null) { instructorID =
         * instructorHelper.getInstructor(selectedEmail).getInstructorId(); }
         */

        gradebooks = gradebookHelper.getGradebooks(instructorID);

        response = " ";
    }

    /**
     * Returns component containing the id string. Credit goes to
     * http://illegalargumentexception.blogspot.ca/2009/02/jsf-working-with-component-ids.html
     * Author: McDowell (February 16, 2009)
     *
     * @param c
     * @param id
     * @return
     */
    private UIComponent findComponent(UIComponent c, String id) {
        if (id.equals(c.getId())) {
            return (UIInput) c;
        }

        Iterator<UIComponent> children = c.getFacetsAndChildren();
        while (children.hasNext()) {
            UIComponent found = findComponent(children.next(), id);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public String getGradebook() {
        return gradebookName;
    }

    public void setGradebook(String gradebookName) {
        this.gradebookName = gradebookName;
    }

    public int getGradebookID() {
        return gradebookID;
    }

    public void setGradebookID(int gradebookID) {
        this.gradebookID = gradebookID;
    }

    public String getStudent() {
        return studentName;
    }

    public void setStudent(String studentName) {
        this.studentName = studentName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public void gradebookIDValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.gradebookID = Integer.parseInt(newVal);
            Gradebook gradebook = gradebooks.get(0);
            for (int gradebookCounter = 0; gradebookCounter < gradebooks.size(); gradebookCounter++) {
                if (gradebooks.get(gradebookCounter).getGradebookId() == gradebookID) {
                    gradebook = gradebooks.get(gradebookCounter);
                    gradebookName = gradebooks.get(gradebookCounter).getCourse().getCourseName() + " " + gradebooks.get(gradebookCounter).getSemester().getSemesterName();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    public void studentIDChangedValueListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.studentID = Integer.parseInt(newVal);
            Student student = students.get(0);
            for (int studentCounter = 0; studentCounter < students.size(); studentCounter++) {
                if (students.get(studentCounter).getStudentId() == studentID) {
                    student = students.get(studentCounter);
                    studentName = students.get(studentCounter).getStudentFname() + " " + students.get(studentCounter).getStudentLname();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

    }

    public DataModel getSemesterValues() {
        if (semesterValues == null) {
            semesterValues = new ListDataModel(gradebookHelper.getSemesters());
        }

        return semesterValues;
    }

    public void setSemesterValues(DataModel semesterValues) {
        this.semesterValues = semesterValues;
    }

    public DataModel getStudentValues() {
        if (studentValues == null) {
            studentValues = new ListDataModel(studentHelper.getStudents());
        }

        return studentValues;
    }

    public void setStudentValues(DataModel studentValues) {
        this.studentValues = studentValues;
    }

    public List<Gradebook> getGradebooks() {
        return gradebooks;
    }

    public void setGradebooks(List<Gradebook> gradebooks) {
        this.gradebooks = gradebooks;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getGradebookName() {
        return gradebookName;
    }

    public void setGradebookName(String gradebookName) {
        this.gradebookName = gradebookName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public DataModel getCourseValues() {
        if (courseValues == null) {
            courseValues = new ListDataModel(gradebookHelper.getCourses());
        }

        return courseValues;
    }

    public void setCourseValues(DataModel courseValues) {
        this.courseValues = courseValues;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void courseNameValueChangedListener(ValueChangeEvent e) {
        String newVal = e.getNewValue().toString();
        try {
            this.courseID = Integer.parseInt(newVal);
            Course course = courses.get(0);
            for (int courseCounter = 0; courseCounter < courses.size(); courseCounter++) {
                if (courses.get(courseCounter).getCourseId() == courseID) {
                    course = courses.get(courseCounter);
                    courseName = course.getCourseName();
                }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }
    }

    public String modifyBook() {

        if ((courseName != null) && (semesterName != null) && (email != null)) {

        }

        return "modifyBook";
    }

    public String getResponse() {
        if (firstName != null && lastName != null) {

            if (studentHelper.insertStudent(firstName, lastName) == 1) {
                firstName = null;
                lastName = null;
                studentID = studentHelper.getStudentID(firstName, lastName);

                if (gradebookStudentHelper.insertStudentToGradebook(studentID, 4419) == 1) {
                    response = "Student Added.";
                    return response;

                } else {
                    firstName = null;
                    lastName = null;
                    response = "Student Not Added.";
                    return response;
                }

            } else {
                response = "Insert student failed!";
                firstName = null;
                lastName = null;
                return response;
            }

        } else {
            response = " ";
            return response;
        }
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getInstructorID() {
        if (email != null) {
            instructorID = instructorHelper.getInstructor(email).getInstructorId();
        }
        return instructorID;
    }

    public void setInstructorID(int instructorID) {
        this.instructorID = instructorID;
    }

    public String getSelectedEmail() {
        return selectedEmail;
    }

    public void setSelectedEmail(String selectedEmail) {
        this.selectedEmail = selectedEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    
}
