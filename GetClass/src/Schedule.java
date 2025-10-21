
/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Schedule{
    
    // assignment of general attributes
    private int Id;
    public boolean available;

    /**
     * Schedule constructor
     * @param hourlydate 
     * @param subject 
     * @param tutorName 
     * @param studentName 
     * @param startTime 
     * @param finishTime 
     * @param modality 
     */
    public Schedule(boolean available){

        this.available = available;

    }


    /**
     * That method determines if the time is available or not
     * @param schedule
     * @param elemento
     * @return
     */
    public boolean available(List<RequestClass> schedule, RequestClass elemento){
        if(schedule.isEmpty()){
            return true;
        }
        else{
            for(RequestClass s : schedule){
                if(elemento.getClassDate().equals(s.getClassDate())){  //that compares only classes on the same day
                    if(elemento.getStartTime().isBefore(s.getFinishTime()) && elemento.getFinishTime().isAfter(s.getStartTime())){ //That helps to detect overlapping classes
                        return false;
                    }
                }
            }
            return true;
        }

    }

    public boolean getAvailable(){
        return available;
    }
}
    

