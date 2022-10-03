package GymDB;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GymManager {
    private MemberDatabase DB;
    private FitnessClass[] classes;
    public void run(){
        System.out.println("Gym Manager running...");
        DB = new MemberDatabase();
        classes = new FitnessClass[]{new FitnessClass("Pilates"), new FitnessClass("Spinning"), new FitnessClass("Cardio")};

        Scanner sc = new Scanner(System.in);
        String currentLine = sc.nextLine();

        quit:
        while(true){
            StringTokenizer lineTokens = new StringTokenizer(currentLine);

            if(lineTokens.hasMoreTokens()) {
                String command = lineTokens.nextToken();
                switch (command) {
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
                    case "S":
                        System.out.println("\n-Fitness classes-");
                        for (FitnessClass aClass : classes) aClass.print();
                        break;
                    default:
                        System.out.println(command + " is an invalid command!");
                        break;
                        /*
                        TODO:
                        S: To display the Fitness Class Schedule // displays fitness classes with members in the DB
                        C: For members to check into Fitness Class, //same as add but just using FitnessClass DB?
                        D: For Members to drop fitness class after checking in
                         */
                }
            }
            do{
                currentLine = sc.nextLine();
            } while (currentLine.length() == 0);   //skips blank lines
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
            System.out.println("DOB " + dob.toString() + ": invalid calendar date!");
            return;
        }
        if(!dob.over18()){ //returns true if 18 or older
            System.out.println("DOB " + dob.toString() + ": must be 18 or older to join!");
            return;
        }
        if(dob.futureDateCheck()){ //return true if this.date > current date
            System.out.println("DOB " + dob.toString() + ": cannot be today or a future date!");
            return;
        }

        Date expire = new Date(dataTokens.nextToken());
        if(!expire.isValid()){ //returns false if general errors in date.
            System.out.println("Expiration date " + expire.toString() + ": invalid calendar date!");
            return;
        }

        String locParam = dataTokens.nextToken();
        String locNormalized = locParam.toLowerCase();
        Member.Location location;
        switch (locNormalized) {  //TODO: maybe make this its own method
            case "piscataway" -> location = Member.Location.Piscataway;
            case "bridgewater" -> location = Member.Location.Bridgewater;
            case "edison" -> location = Member.Location.Edison;
            case "franklin" -> location = Member.Location.Franklin;
            case "somerville" -> location = Member.Location.Somerville;
            default -> {
                System.out.println(locParam + ": invalid location!");
                return;
            }
        }

        Member newMem = new Member(fname, lname, dob, expire, location);
        if(!DB.add(newMem)) {
            System.out.println(fname + " " + lname + " is already in the database.");
        }
    }

    private void rmMember(StringTokenizer dataTokens){
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        // since we are removing a member in the database or fitness class
        // and if it is in the database didnt we already check that the dates are valid
        //so Idk if we have to check those again in remove I think we can just see if it is in the Database
        //and if so just use the DB remove method

        Date dob = new Date(dataTokens.nextToken());
        Member rmMem = new Member(fname, lname, dob, null, null);
        if(!DB.remove(rmMem)) {
            System.out.println(fname + " " + lname + " is not in the database.");
        }
    }

    private void checkInMember(StringTokenizer dataTokens){
        String fitClass = dataTokens.nextToken(); //check if class exists

        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        Date dob = new Date(dataTokens.nextToken()); //check if dob is valid
        //check if member exists
        //check if membership is expired

        //check if member is checked in this class
        //check for time conflict with another class


    }

}
