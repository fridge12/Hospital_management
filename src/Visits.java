import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

public class Visits {
    //string is for the name of the diagnosis and integer is the number of symptoms that match up with it
    //using a hashmap as I wanted to learn how to use it
    HashMap<Diagnosis,Integer> possibleDiagnosis = new HashMap<>();
    //time at which the patient checked in
    LocalDateTime enteranceTime;
    //for the most likely diagnosis
    Diagnosis confirmedDiagnosis ;
    //to find out which visit it is
    int visitNumber;
    Visits(int totalVisits){
        enteranceTime = LocalDateTime.now();
        System.out.println(enteranceTime);
        visitNumber= totalVisits+1;
    }
}
