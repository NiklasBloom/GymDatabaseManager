package GymDB;

/*
You must include this Java class, which define a fitness class the members can check in. You can define the
instance variables and methods needed. You must use the enum class Time in this class or lose 2 points.
Your software shall not allow a member to check in if
o the membership has expired
o the member does not exist
o the date of birth is invalid
o the fitness class does not exist
o there is a time conflict with other fitness classes
o the member has already checked in

Your software shall not allow the member to drop the class if the member is not checked in, or the date of birth is
invalid, or the fitness class does not exist.

S command, display the fitness class schedule. A fitness class shall include the fitness class name, instructor’s
name, the time of the class, and the list of members who have already checked in today. For simplicity, assuming
the schedule is for “today” only, you do not need to handle a multiple-day schedule.
 */

public class FitnessClass extends MemberDatabase{
    //In GymManager I think we have to make another DB for Fitness classes?
    //and then we add fitness class objects??
    //or? should it also have the member information?
    //do lines with member checkins
    //first, for members to check into fitness class must already be a member
    private Time time;
    private Instructor instructor;
    private String className;
    //how would we test time conflicts?
    //that would be between objects

    public enum Time{
        PILATES(9 ,30),
        SPINNING(14 , 0),
        CARDIO(14 ,0);

        private final int hour;
        private final int minutes;

        Time(int hour, int minutes) {
            this.hour = hour;
            this.minutes = minutes;
        }
    } //define the time of a fitness class in hh:mm

    public enum Instructor{
        JENNIFER,
        DENISE,
        KIM,
    }

    public FitnessClass(String fitnessClass) {
        super();
        this.className = fitnessClass;
        this.instructor = setInstructor(fitnessClass);
        this.time = setTime(fitnessClass);
        //method which returns instructor according to fitness class
        //method which returns time according to fitness class
    }
    public Instructor setInstructor(String fitnessClass){
        String fitnessClassLowerCase = fitnessClass.toLowerCase();
        switch(fitnessClassLowerCase){
            case "pilates":
                return Instructor.JENNIFER;
            case "cardio":
                return Instructor.KIM;
            case "spinning":
                return Instructor.DENISE;
            default:
                return null; // can change this to whatever else u desire if wanna change
            //just to check if null, then print error
        }
    }

    public Time setTime(String fitnessClass){
        String fitnessClassLowerCase = fitnessClass.toLowerCase();
        switch(fitnessClassLowerCase){
            case "pilates":
                return Time.PILATES;
            case "cardio":
                return Time.CARDIO;
            case "spinning":
                return Time.SPINNING;
            default:
                return null; // can change this to whatever else u desire if wanna change
            //just to check if null, then print error
        }
    }
}
