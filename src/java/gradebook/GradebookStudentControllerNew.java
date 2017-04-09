/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author allis
 */
@Named(value = "gradebookStudentControllerNew")
@SessionScoped
public class GradebookStudentControllerNew implements Serializable {

    
    // Helpers
    GradebookHelper gradebookHelper;
    StudentHelper studentHelper;
    InstructorHelper instructorHelper;
    GradebookStudentHelper gradebookStudentHelper;

    // Lists
    List<Gradebook> gradebooks;
    List<Student> students;

    // Components]
    int instructorID;
    int gradebookID;
    int studentID;
    String firstName;
    String lastName;
    String response;
    String selectedEmail = "";
 
    
    /**
     * Creates a new instance of GradebookStudentControllerNew
     */
    public GradebookStudentControllerNew() {
        
        instructorHelper = new InstructorHelper();
        gradebookHelper = new GradebookHelper();
        studentHelper = new StudentHelper();
        instructorID = 0;
        students = studentHelper.getStudents();
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

    public int getGradebookID() {
        return gradebookID;
    }

    public void setGradebookID(int gradebookID) {
        this.gradebookID = gradebookID;
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
                   }
            }

        } catch (NumberFormatException nfe) {
            nfe.printStackTrace();
        }

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

    public String getResponse() {
        if (firstName != null && lastName != null) {

            if (studentHelper.insertStudent(firstName, lastName) == 1) {
                
                studentID = studentHelper.getStudentID(firstName, lastName);
                gradebookID = 4419;

                if (gradebookStudentHelper.insertStudentToGradebook(studentID, gradebookID) == 1) {
                    firstName = null;
                    lastName = null;
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
