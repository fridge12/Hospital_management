import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Patient {

    public static LinkedList<Patient> totalPatients= new LinkedList<Patient>();

    String name="";
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
        try{
            if(doctorVisits.getLast().confirmedDiagnosis!=null){
                //creates a new visit to the doctor
                doctorVisits.addLast(new Visits(doctorVisits.size()));
            }
        }
        catch(Exception e){
            //creates a new visit to the doctor
            doctorVisits.addLast(new Visits(doctorVisits.size()));
        }

        System.out.println(doctorVisits.getLast().visitNumber);
        // in this loop I take a symptom, check which all 'Diagnosis' have it in their symptom list
        //if a diagnosis does have it, I check if it is already in the hashmap of all the possible diagnosis
        //if it is not I add it to the hashmap and set its value to 1
        //if it is there I add 1 to its value

        while(in.hasNext()){
            //separating the symptoms
            String s =in.next();
            System.out.println(s);
            //iterating through every diagnosis
            for (Diagnosis diagnosis : Diagnosis.diagnosisList) {
                //checking if has the symptom
                if(diagnosis.symptoms.contains(s)) {
                    System.out.println(diagnosis.symptoms+" "+diagnosis.diagnosisName);
                    //adding 1 to the value if present, if not present then value is set to 1
                    if(doctorVisits.getLast().possibleDiagnosis.containsKey(diagnosis)) {
                        //adding 1 to the value
                        doctorVisits.getLast().possibleDiagnosis.computeIfPresent(diagnosis, (key, val) -> val + 1);
                    }
                    else{
                        //since the diagnosis was not there, it is added into the hashmap with a value of 1
                        doctorVisits.getLast().possibleDiagnosis.put(diagnosis, 1);
                    }
                }
            }


        }
        //this checks which diagnosis has the most matching symptoms to the symptoms the patients is showing
        int mostMatchingSymptoms=0;
        for(Map.Entry<Diagnosis, Integer> ent: doctorVisits.getLast().possibleDiagnosis.entrySet()){
        if(ent.getValue()>mostMatchingSymptoms){
            System.out.println(ent.getValue());
            //setting confirmed diagnosis to the diagnosis which has the most matching symptoms
            doctorVisits.getLast().confirmedDiagnosis=ent.getKey();
            mostMatchingSymptoms= ent.getValue();
        }
        }

        System.out.println(doctorVisits.getLast().possibleDiagnosis.toString());
        System.out.println(doctorVisits.getLast().confirmedDiagnosis);
        //returning the name of the diagnosis
        return doctorVisits.getLast().confirmedDiagnosis.diagnosisName;
    }

}
