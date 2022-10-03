package GymDB;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GymManager {
    private MemberDatabase DB;
    private FitnessClass[] classes;

    public void run() {
        System.out.println("Gym Manager running...");
        DB = new MemberDatabase();
        classes = new FitnessClass[]{new FitnessClass("Pilates"), new FitnessClass("Spinning"), new FitnessClass("Cardio")};

        Scanner sc = new Scanner(System.in);
        String currentLine = sc.nextLine();

        quit:
        while (true) {
            StringTokenizer lineTokens = new StringTokenizer(currentLine);

            if (lineTokens.hasMoreTokens()) {
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
                    case "C":
                        checkInMember(lineTokens);
                        break;
                    case "D":
                        break;
                    default:
                        System.out.println(command + " is an invalid command!");
                        break;
                        /*
                        TODO:
                        D: For Members to drop fitness class after checking in
                         */
                }
            }
            do {
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
    private void addMember(StringTokenizer dataTokens) {
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken());
        if (!dob.isValid()) { //returns false if general errors in date.
            System.out.println("DOB " + dob.toString() + ": invalid calendar date!");
            return;
        }
        if (!dob.over18()) { //returns true if 18 or older
            System.out.println("DOB " + dob.toString() + ": must be 18 or older to join!");
            return;
        }
        if (dob.futureDateCheck()) { //return true if this.date > current date
            System.out.println("DOB " + dob.toString() + ": cannot be today or a future date!");
            return;
        }

        Date expire = new Date(dataTokens.nextToken());
        if (!expire.isValid()) { //returns false if general errors in date.
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
        if (!DB.add(newMem)) {
            System.out.println(fname + " " + lname + " is already in the database.");
        }
    }

    private void rmMember(StringTokenizer dataTokens) {
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        // since we are removing a member in the database or fitness class
        // and if it is in the database didnt we already check that the dates are valid
        //so Idk if we have to check those again in remove I think we can just see if it is in the Database
        //and if so just use the DB remove method

        Date dob = new Date(dataTokens.nextToken());
        Member rmMem = new Member(fname, lname, dob, null, null);
    }


    /*
    Your software shall not allow a member to check in if
o the membership has expired //just have to use the futureDateCheck() method
o the member does not exist //just the find method
o the date of birth is invalid //just the isvalid method
o the fitness class does not exist //idk just has to match spelling
o there is a time conflict with other fitness classes // find method in other two fitness classes?
o the member has already checked in // just the find method in the fitness class
     */
    private void checkInMember(StringTokenizer dataTokens) {
        String classStr = dataTokens.nextToken().toUpperCase();

        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();

        Date dob = new Date(dataTokens.nextToken()); //check if dob is valid
        if (!dob.isValid()) { //returns false if general errors in date.
            System.out.println("DOB " + dob.toString() + ": invalid calendar date!");
            return;
        }
        if (!dob.over18()) { //returns true if 18 or older
            System.out.println("DOB " + dob.toString() + ": must be 18 or older to join!");
            return;
        }
        if (dob.futureDateCheck()) { //return true if this.date > current date
            System.out.println("DOB " + dob.toString() + ": cannot be today or a future date!");
            return;
        }

        Member testMember = new Member(fname, lname, dob); //use given info to search for member in DB

        //get the reference to the member in the DB, if it matches
        Member dbMember = DB.getMember(testMember);
        if(dbMember == null){
            System.out.println(fname + " " + lname + " " + dob.toString() + " is not in the database.");
            return;
        }

        Date expire = dbMember.getExpire();
        if (!expire.futureDateCheck()) {
            System.out.println(fname + " " + lname + " " + dob.toString() + " membership expired.");
            return;
        }

        FitnessClass choiceClass = null;
        for(FitnessClass aClass : classes){ //finds the chosen class and checks if already a member
            if(aClass.getClassName().toUpperCase().equals(classStr)){
                if (aClass.getMember(dbMember) != null) {
                    System.out.println(fname + " " + lname + " has already checked in " + aClass.getClassName());
                    return;
                } else {
                    choiceClass = aClass;
                    break;
                }
            }
        }
        if(choiceClass == null){ //checks if class exists
            System.out.println(classStr + " class does not exist.");
            return;
        }

        for (FitnessClass aClass : classes) { //checks for time conflict
            if (aClass != choiceClass) {
                if (aClass.getMember(dbMember) != null && aClass.getTime().equals(choiceClass.getTime())){
                    System.out.println(classStr + " time conflict -- " +
                            fname + " " + lname + " has already checked in " + aClass.getClassName() + ".");
                    return;
                }
            }
        }

        //having passed all the above checks, adds the member to the chosen class
        choiceClass.add(dbMember);
        System.out.println(fname + " " + lname + " checked in " + choiceClass.getClassName());
    }
}
