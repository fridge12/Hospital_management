import java.util.LinkedList;

public class Doctor {

    public static LinkedList<Doctor> totalDoctors= new LinkedList<Doctor>();


    String name;
    int ID;
    LinkedList<Patient> patients = new LinkedList<Patient>();

    Doctor(String name){
        this.name = name;
        this.ID = totalDoctors.size();
        totalDoctors.add(this);
    }

    public void addPatient(Patient p){
        this.patients.addLast(p);
    }

}
