import java.time.LocalTime;
import java.util.HashMap;

public class Visits {
    //string is for the name of the diagnosis and integer is the number of symptoms that match up with it
    //using a hashmap as I wanted to learn how to use it
    HashMap<Diagnosis,Integer> possibleDiagnosis = new HashMap<>();
    //time at which the patient checked in
    LocalTime enteranceTime;
    //for the most likely diagnosis
    Diagnosis confirmedDiagnosis ;

    Visits(){
        enteranceTime = LocalTime.now() ;
        System.out.println(enteranceTime);
    }
}
