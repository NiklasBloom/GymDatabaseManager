package GymDB;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    public Date() {


    } //create an object with today’s date (see Calendar class)
    public Date(String date) { //take “mm/dd/yyyy” and create a Date object
        String[] tokens=date.split("/");
        String month = tokens[0];
        String day = tokens[1];
        String year = tokens[2];
        int intMonth= Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        int intYear= Integer.parseInt(year);
        this.month = intMonth;
        this.day = intDay;
        this.year = intYear;

    }

    public void leapYearCheck(String date){

    }

    /*
    compareTo() method is used when sorting by names
    if s1 > s2, it returns 1
    if s1 < s2, it returns -1
    if s1 == s2, it returns 0
   */
    @Override
    public int compareTo(Date date) {
        String[] tokens=date.toString().split("/");
        String month = tokens[0];
        String day = tokens[1];
        String year = tokens[2];
        int intMonth= Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        int intYear= Integer.parseInt(year);
        if(this.year > intYear){
            return 1;}
        if(this.year < intYear){
            return -1;}
        if(this.year == intYear){
            if(this.month > intMonth){
                return 1;}
        }
        if(this.year == intYear){
            if(this.month < intMonth){
                return -1;}
        }
        if(this.year == intYear){
            if(this.month == intMonth){
                if(this.day > intDay){
                    return 1;}
            }
        }
        if(this.year == intYear){
            if(this.month == intMonth){
                if(this.day < intDay){
                    return -1;}
            }
        }
        return -1;
    }

    @Override
    public String toString() {
        String month=Integer.toString(this.month);
        String day=Integer.toString(this.day);
        String year=Integer.toString(this.year);
        String date = month + "/" + day + "/" + year;
        return date;
    }
    //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX
    public boolean isValid() {
        return false;
    } //check if a date is a valid calendar date
}
