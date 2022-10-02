package GymDB;
import java.util.Calendar;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;


    public static final int QUADRENNIAL = 4;
    public static final int CENTENNIAL = 100;
    public static final int QUATERCENTENNIAL = 400;


    /*
    create an object with today’s date using the calendar class
     */
    public Date() {
        Calendar c = Calendar.getInstance();
        this.year = c.get(Calendar.YEAR);
        this.day = c.get(Calendar.DATE);
        this.month = c.get(Calendar.MONTH);
    }
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

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        if (obj instanceof Date) {
            Date dat = (Date) obj; //casting
            if (dat.day == this.day && dat.month == this.month && dat.year == this.year){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
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

    expiry not less than current day
    DOB cannot be greater than current day
    day must not be greater than month value, CHECK
    month value must be in 1-12 range, CHECK
    day value in correct range according to month and leap year, CHECK
    year must be >= 1900, CHECK
     */
    public boolean isValid() { //called on date obj
        boolean LeapYear = leapYearCheck(this.toString());
        int month= this.month;
        if(this.year < 1900){
            return false;
        }
        int numberDaysInMonth = correspondingDaysInMonth(month,leapYearCheck(this.toString()));
        if(numberDaysInMonth == -1){
            return false; //ensures month number is 1 < x < 12
        }
        //how many days in month?
        int day=this.day;
        //does day value fit in month range
        if(day < 1 || day > numberDaysInMonth){ //ensures # days is 1 < x < numberDaysInMonth
            return false;
        }
        return true;
    }

    public boolean over18() { //check if member is over 18
        Calendar c = Calendar.getInstance();
        c.add(Calendar.YEAR, -18);
        int CalendarYear = c.get(Calendar.YEAR);
        int CalendarDay = c.get(Calendar.DATE);
        int CalendarMonth = c.get(Calendar.MONTH);
        if (this.year >= CalendarYear){ // checks if DOB is > than limit, meaning under 18
            if(this.month >= CalendarMonth){
                if(this.day > CalendarDay){
                    return false;
                }
            }
        }
        return true;
    }

    /*
    checks if the date is greater than the current date, which does not make sense for DOB/join date/other
    also Expire dates must be greater than the current date I assume
    returns true is this.date > current date
     */
    public boolean futureDateCheck() {
        Calendar c = Calendar.getInstance();
        int CalendarYear = c.get(Calendar.YEAR);
        int CalendarDay = c.get(Calendar.DATE);
        int CalendarMonth = c.get(Calendar.MONTH); //get the current date
        if (this.year == CalendarYear){
            if(this.month == CalendarMonth){
                if(this.day > CalendarDay){
                    return true;
                }
            } else if(this.month > CalendarMonth){
                return true;
            }
        } else if (this.year > CalendarYear){
            return true;
        }
        return false;
    }

    public int correspondingDaysInMonth(int month, boolean Leapyear){
        switch(month){
            case 1:
                return MonthValue.January;
            case 2:// february
                if(Leapyear == true){
                    return MonthValue.FebruaryLeapyear;
                }
                else{
                    return MonthValue.FebruaryNonLeapyear;
                }
            case 3:
                return MonthValue.March;
            case 4:
                return MonthValue.April;
            case 5:
                return MonthValue.May;
            case 6:
                return MonthValue.June;
            case 7:
                return MonthValue.July;
            case 8:
                return MonthValue.August;
            case 9:
                return MonthValue.September;
            case 10:
                return MonthValue.October;
            case 11:
                return MonthValue.November;
            case 12:
                return MonthValue.December;
            default:
                return -1;
        }
    }

}
