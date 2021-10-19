import java.util.LinkedList;

public class Doctor {

    //list which contains all the doctors
    public static LinkedList<Doctor> totalDoctors= new LinkedList<Doctor>();

    //each doctor has a name, unique ID number and a list of patients
    String name;
    int ID;
    LinkedList<Patient> patients = new LinkedList<Patient>();

    //constructor which takes name as input
    Doctor(String name){
        //seting name
        this.name = name;
        //setting ID equal to the total number of doctors in the list
        this.ID = totalDoctors.size();
        //adding doctor to the list which contains all the doctors
        totalDoctors.add(this);
    }

    //method to add patient into the list of patients of the doctor
    public void addPatient(Patient p){
        this.patients.addLast(p);
    }

}
