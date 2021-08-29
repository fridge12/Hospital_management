import javax.print.Doc;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Patient {

    public static LinkedList<Patient> totalPatients= new LinkedList<Patient>();

    String name;
    int ID;
    Doctor doctor;
    String symptomsShown = "";
    LinkedList<Visits> doctorVisits = new LinkedList<Visits>();


    Patient(String name){
        this.name= name;
        this.ID = totalPatients.size();
        totalPatients.addLast(this);
        this.setDoctor();
    }


    //this method is used to assign a doctor to the patient.
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

    //to find all possible diagnosis and to return the name of the most likely diagnosis.
    public String findDiagnosis(String symptoms){
        //takes the names of the symptoms as the input
        Scanner in = new Scanner(symptoms);
        doctorVisits.addLast(new Visits());
        while(in.hasNext()){
            String s =in.next();
            System.out.println(s);
            for (Diagnosis diagnosis : Diagnosis.diagnosisList) {
                if(diagnosis.symptoms.contains(s)) {
                    System.out.println(diagnosis.symptoms+" "+diagnosis.diagnosisName);
                    //doctorVisits.getLast().possibleDiagnosis.put(diagnosis, 1);
                    //adding 1 to the value if present, if not present then value is set to 1
                    if(doctorVisits.getLast().possibleDiagnosis.containsKey(diagnosis)) {
                        doctorVisits.getLast().possibleDiagnosis.computeIfPresent(diagnosis, (key, val) -> val + 1);
                    }
                    else{
                        doctorVisits.getLast().possibleDiagnosis.put(diagnosis, 1);
                    }
                }
            }


        }

        int mostMatchingSymptoms=0;
        for(Map.Entry<Diagnosis, Integer> ent: doctorVisits.getLast().possibleDiagnosis.entrySet()){
        if(ent.getValue()>mostMatchingSymptoms){
            System.out.println(ent.getValue());
            doctorVisits.getLast().confirmedDiagnosis=ent.getKey();
            mostMatchingSymptoms= ent.getValue();
        }
        }

        System.out.println(doctorVisits.getLast().possibleDiagnosis.toString());
        System.out.println(doctorVisits.getLast().confirmedDiagnosis);
        return doctorVisits.getLast().confirmedDiagnosis.diagnosisName;
    }

}
