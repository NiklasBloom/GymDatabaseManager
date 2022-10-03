package GymDB;

/*
You must include this Java class, which define a fitness class the members can check in. You can define the
instance variables and methods needed. You must use the enum class Time in this class or lose 2 points.
Your software shall not allow a member to check in if
o the membership has expired //just have to use the futureDateCheck() method
o the member does not exist //just the find method
o the date of birth is invalid //just the isvalid method
o the fitness class does not exist //idk just has to match spelling
o there is a time conflict with other fitness classes // find method in other two fitness classes?
o the member has already checked in // just the find method in the fitness class

Your software shall not allow the member to drop the class if the member is not checked in, or the date of birth is
invalid, or the fitness class does not exist.

S command, display the fitness class schedule. A fitness class shall include the fitness class name, instructor’s
name, the time of the class, and the list of members who have already checked in today. For simplicity, assuming
the schedule is for “today” only, you do not need to handle a multiple-day schedule.
 */

public class FitnessClass extends MemberDatabase{
    private Time time;
    private Instructor instructor;
    private String className;

    public enum Time{  //define the time of a fitness class in hh:mm
        PILATES(9 ,30),
        SPINNING(14 , 00),
        CARDIO(14 ,00);

        private final int hour;
        private final int minutes;

        Time(int hour, int minutes) {
            this.hour = hour;
            this.minutes = minutes;
        }
        @Override
        public String toString(){
            return this.hour + ":" + this.minutes;
        }
    }

    public enum Instructor{
        JENNIFER,
        DENISE,
        KIM,
    }

    public FitnessClass() {
        super();
    }
    public void setFitnessClassData(String fitnessClass){
        this.className = fitnessClass;
        this.instructor = setInstructor(fitnessClass);
        this.time = setTime(fitnessClass);
    }

    public FitnessClass(String fitnessClass) {
        super();
        this.className = fitnessClass;
        this.instructor = setInstructor(fitnessClass);
        this.time = setTime(fitnessClass);
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
                return null; //just to detect and raise an error
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
                return null; //just to detect and raise an error
        }


    }

    /*
    Pilates - JENNIFER 9:30
        ** participants **
            Jane Doe, DOB: 5/1/1996, Membership expires 3/30/2023, Location: EDISON, 08837, MIDDLESEX
     */
    @Override
    public void print() {
        System.out.println(this.className + " - " + this.instructor + " " + this.time);
        if(!checkEmpty()) {
            System.out.println("\t** participants **");
            super.print();
        }
    }
    @Override
    public boolean remove(Member member) {
        return super.remove(member);
    }

    @Override
    public boolean add(Member member) {
        return super.add(member);
    }

    @Override
    public boolean CheckInList(Member member) {
        return super.CheckInList(member);
    }
}
