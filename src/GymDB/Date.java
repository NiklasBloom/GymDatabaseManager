package GymDB;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;


    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    public Date() { //idk what to do wit this


    } //create an object with today’s date (see Calendar class)
    public Date(String date) { //take “mm/dd/yyyy” and create a Date object
        String[] tokens=date.split("/");
        String month = tokens[0];
        String day = tokens[1];
        String year = tokens[2];
        int intMonth = Integer.parseInt(month);
        int intDay = Integer.parseInt(day);
        int intYear= Integer.parseInt(year);
        this.month = intMonth;
        this.day = intDay;
        this.year = intYear;
    }


    /*
    Step 1. If the year is evenly divisible by 4, go to step 2. Otherwise, go to step 5.
    Step 2. If the year is evenly divisible by 100, go to step 3. Otherwise, go to step 4.
    Step 3. If the year is evenly divisible by 400, go to step 4. Otherwise, go to step 5.
    Step 4. The year is a leap year.
    Step 5. The year is not a leap year.
    February has 28 days in a non-leap year, and 29 days in a leap year.
     */
    public boolean leapYearCheck(String date){
        int year = getDateYear(date); //used a static getter method
        if(year % QUADRENNIAL == 0){
            if(year % CENTENNIAL == 0){
                if(year % QUATERCENTENNIAL == 0){
                    return true;
                }
            }
        }
        return false;
    }

    public static int getDateYear(String date){
        String[] tokens=date.split("/");
        String year = tokens[2];
        int intYear= Integer.parseInt(year);
        return intYear;
    }

    public static int getDateMonth(String date){
        String[] tokens=date.split("/");
        String year = tokens[0];
        int intMonth= Integer.parseInt(year);
        return intMonth;
    }
    public static int getDateDay(String date){
        String[] tokens=date.split("/");
        String year = tokens[1];
        int intDay= Integer.parseInt(year);
        return intDay;
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

    /*
    April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX
    Returns the string for a date object
    Output should look like "6/30/2023" I believe
     */
    @Override
    public String toString() {
        String month=Integer.toString(this.month);
        String day=Integer.toString(this.day);
        String year=Integer.toString(this.year);
        String date = month + "/" + day + "/" + year;
        return date;
    }

    /*
    Must be in  "MM/DD/YYYY" format

    test Cases:
    DOB 13/8/1977: invalid calendar date!
    DOB 2/29/2003: invalid calendar date!
    DOB 4/31/2003: invalid calendar date!
    DOB 13/31/2003: invalid calendar date!
    DOB 3/32/2003: invalid calendar date!
    DOB -1/31/2003: invalid calendar date!
    Expiration date 4/31/2022: invalid calendar date!
    Expiration date 2/30/2011: invalid calendar date!
     */
    public boolean isValid() { //mildly difficult
        return false;
    } //check if a date is a valid calendar date
}
