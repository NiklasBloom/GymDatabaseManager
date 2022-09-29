package GymDB;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GymManager {
    private MemberDatabase DB;
    public void run(){
        System.out.println("Gym Manager running...");
        DB = new MemberDatabase();

        Scanner sc = new Scanner(System.in);
        String currentLine = sc.nextLine();

        quit:
        while(true){
            StringTokenizer lineTokens = new StringTokenizer(currentLine);

            String command = lineTokens.nextToken();
            switch(command){
                case "Q":
                    break quit;
                case "A":
                    addMember(lineTokens);
                    break;
                case "R":
                    rmMember(lineTokens);
                    break;
                default:
                    System.out.println(command + " is an invalid command!");
                    break quit;
            }

            //regex to match one or two uppercase characters: \b[A-Z]\b|\b[A-Z][A-Z]\b
            currentLine = sc.nextLine();
        }
        System.out.println("Gym Manager terminated.");
    }

    //rejects if:
    // Any date that is not a valid calendar date
    // The date of birth is today or a future date
    // A member who is less than 18 years old
    // An invalid city name, that is, the gym location doesn’t exist
    private boolean addMember(StringTokenizer dataTokens){
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken());
        //check if date is valid
        //check if 18 or older

        Date expire = new Date(dataTokens.nextToken());
        //check if date is valid
        //check if expired

        String locParam = dataTokens.nextToken();
        String locNormalized = locParam.toLowerCase();
        Member.Location location;
        switch(locNormalized) {
            case "piscataway":
                location = Member.Location.Piscataway;
                break;
            case "bridgewater":
                location = Member.Location.Bridgewater;
                break;
            case "edison":
                location = Member.Location.Edison;
                break;
            case "franklin":
                location = Member.Location.Franklin;
                break;
            case "somerville":
                location = Member.Location.Somerville;
                break;
            default:
                System.out.println(locParam + ": invalid location!");
                return false;
        }

        Member newMem = new Member(fname, lname, dob, expire, location);
        if(!DB.add(newMem)) {
            System.out.println(fname + " " + lname + " is already in the database.");
            return false;
        }
        return true;
    }

    private boolean rmMember(StringTokenizer dataTokens){
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken());
        //check if date is valid
        //check if 18 or older

        Member rmMem = new Member(fname, lname, dob, null, null);
        if(!DB.remove(rmMem)) {
            System.out.println(fname + " " + lname + " is not in the database.");
            return false;
        }
        return true;
    }
}
