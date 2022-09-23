package GymDB;

public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;
    public Date() {} //create an object with today’s date (see Calendar class)
    public Date(String date) {} //take “mm/dd/yyyy” and create a Date object
    @Override
    public int compareTo(Date date) {
        return -1;
    }
    public boolean isValid() {
        return false;
    } //check if a date is a valid calendar date
}
