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
                case "A": //add member
                    addMember(lineTokens);
                    break;
                case "R": //cancel membership
                    rmMember(lineTokens);
                    break;
                case "P":
                    System.out.println("\n-list of members-");
                    DB.print();
                    System.out.println("-end of list-\n");
                    break;
                case "PC":
                    System.out.println("\n-list of members sorted by county and zipcode-");
                    DB.printByCounty();
                    System.out.println("-end of list-\n");
                    break;
                case "PN":
                    System.out.println("\n-list of members sorted by last name, and first name-");
                    DB.printByName();
                    System.out.println("-end of list-\n");
                    break;
                case "PD":
                    System.out.println("\n-list of members sorted by membership expiration date-");
                    DB.printByExpirationDate();
                    System.out.println("-end of list-\n");
                    break;
                default:
                    System.out.println(command + " is an invalid command!");
                    break;
                    /*
                    Commands still gotta do:
                    P: display list of members w/o sorting
                    PC: Display database sorted by county name then zip code
                    PN: Display Database sorted by last name then first name
                    PD: Display list of members in database sorted by Expiry dates
                    S: To display the Fitness Class Schedule
                    C: For members to check into Fitness Class
                    D: For Members to drop fitness class after checking in
                     */
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
    private void addMember(StringTokenizer dataTokens){
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken());
        if(!dob.isValid()){ //returns false if general errors in date.
            System.out.println("DOB" + dob.toString() + ": invalid calendar date!");
            return;
        }
        if(!dob.over18()){ //returns true if 18 or older
            System.out.println("DOB" + dob.toString() + ": must be 18 or older to join!");
            return;
        }
        if(dob.futureDateCheck()){ //return true if this.date > current date
            System.out.println("DOB" + dob.toString() + ": cannot be today or a future date!");
            return;
        }

        Date expire = new Date(dataTokens.nextToken());
        if(!expire.isValid()){ //returns false if general errors in date.
            System.out.println("Expiration date " + expire.toString() + ": invalid calendar date!");
            return;
        }
        if(!expire.futureDateCheck()){ //if expiry isn't in the future
            System.out.println("Expiration date " + expire.toString() + ": must be a future date!");
            return;
        }

        String locParam = dataTokens.nextToken();
        String locNormalized = locParam.toLowerCase();
        Member.Location location;
        switch(locNormalized) {  //TODO: maybe make this its own method
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
                return;
        }

        Member newMem = new Member(fname, lname, dob, expire, location);
        if(!DB.add(newMem)) {
            System.out.println(fname + " " + lname + " is already in the database.");
        }
    }

    private void rmMember(StringTokenizer dataTokens){
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken());
        //check if date is valid
        //check if 18 or older
        //can maybe make separate methods in Date class to see if:
            //member is over 18
            //Valid leap year
            //Valid month value or day value or year value
        //Can maybe make a new method in the GymManager class which checks all these Date things automatically


        Member rmMem = new Member(fname, lname, dob, null, null);
        if(!DB.remove(rmMem)) {
            System.out.println(fname + " " + lname + " is not in the database.");
        }
    }


}
