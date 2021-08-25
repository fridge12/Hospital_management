import javax.print.Doc;
import java.util.LinkedList;

public class Patient {

    public static LinkedList<Patient> totalPatients= new LinkedList<Patient>();

    String name;
    int ID;
    Doctor doctor;
    int checkInTime;

    Patient(String name){
        this.name= name;
        this.ID = totalPatients.size();
        totalPatients.addLast(this);
        this.setDoctor();
    }


    private void setDoctor(){
        //setting doctor to first in linked list
        this.doctor = Doctor.totalDoctors.getFirst();

        //checking which doctor has the least amount of patients and then assigning that doctor
        for(Doctor d: Doctor.totalDoctors){
            if(this.doctor.patients.size()>d.patients.size()){
                this.doctor = d;
            }
        }
        //adding patient in doctor
        this.doctor.addPatient(this);

        //printing doctors name
        System.out.println(this.doctor.name);
    }

}
