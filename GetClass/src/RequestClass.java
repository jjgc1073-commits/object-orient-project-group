/*
 * Name Project: getClasses
 * Members group:
 * Jhon Gonzalez 20251020087
 * Alejandro escobar 20251020094
 * Sebastian Zambrano 20251020102
 * 
 */

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RequestClass {

    private int id;
    public int numberAccept;
    protected double price;
    protected boolean accept;
    protected LocalDate requestDate;
    protected LocalDate classDate;
    protected LocalTime startTime;
    protected LocalTime finishTime;
    protected String subject;
    protected String tutorName;
    protected String studentName;
    protected String modality;
    protected String status;
    public RequestClass elemento;

    /**
     * This is the constructor of the request
     * @param hourlydate
     * @param subject
     * @param tutorName
     * @param studentName
     * @param startTime
     * @param finishTime
     */
    public RequestClass(double hourlydate, String subject, String tutorName, String studentName, LocalTime startTime, LocalDate classDate, LocalTime finishTime, String modality){

        this.requestDate = LocalDate.now();
        this.finishTime = finishTime;
        this.tutorName = tutorName; 
        this.studentName = studentName;
        this.classDate = classDate;  
        this.startTime = startTime;  
        this.subject = subject;
        this.price = setPrice(hourlydate, startTime, finishTime);
        this.modality = modality;
        this.status = "Pending";
        this.id = 0;
    }


    /**
     * This method calculates the class price depending on the time
     * @param hourlydate
     * @param startTime
     * @param finishTime
     * @return
     */
    public double setPrice(double hourlydate, LocalTime startTime, LocalTime finishTime){
        if(hourlydate>0){
            return Duration.between(startTime, finishTime).toHours() * hourlydate;
        }
        else{
            System.out.println("Data error. contact with support");
            return 0;
        }

    }

       

    /**
     * This method shows the request infor to confirm and send
     * @return
     */
    public String getInfoRequest(){
        return "Name Class: " + subject + " | Modality: " + modality + " | Date: " + classDate + " | Start: " + startTime +
                " | Tutor: " + tutorName + " | Estudent: " + studentName +
                " | Status: " + status;
    }

    
    protected static List<RequestClass> request = new ArrayList<>();

    
    /**
     * This method sends the request, add to the requests list of the teacher
     */
    public void sendRequest(){
        request.add(this);
        this.id = request.indexOf(this);
    } 
        
    /**
     * This method shows to the tutor the request
     */
    public void getRequest(){
    if(request.isEmpty()){
            System.out.println("There are not requests pending approval");
        }
        else{
            for(RequestClass r : request){
                System.out.println(r.getInfoRequest());
            }
        }    
    }
    
    /**
     * This method determines the tutor’s response to the request
     * @param numberAccept
     * @param answer
     * @return
     */
    public String setAnswer(int numberAccept, int answer, UserTutor tutor, Schedule available, List<RequestClass> schedule){
      if( 1 == answer){
        this.accept = true;
        if(numberAccept > id){
            return "Class request not found";
        }
        else{
            elemento = request.get(numberAccept);
            if(available.available(schedule, elemento))
                tutor.addClass(elemento);
                request.remove(numberAccept - 1);
                this.status = "Accept";
                return "Class accepted, please check your shcdule";
        }
      }
      else{
        this.accept = false;
        if(numberAccept > id){
            return "Class request not found";
        }
        else{
            request.remove(numberAccept + 1);
            this.status = "Rejected";
            return "Class rejected";
        }
      }
    }

    public LocalDate getClassDate(){
        return classDate;
    }
    public LocalTime getStartTime(){
        return startTime;
    }
    public LocalTime getFinishTime(){
        return finishTime;
    }

    public String getStatus(){
        return status;
    }
 
    
}
