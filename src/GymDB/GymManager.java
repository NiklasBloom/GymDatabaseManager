/**
 * GymManager keeps a MemberDatabase and an array of FitnessClass objects, processing commands to alter those from standard input.
 * Reads input using a Scanner object, tokenizing each line by groups of non-whitespace characters.
 * Uses a continuous while loop to read from standard input until a termination command is given.
 * Reports status and results by printing to standard output.
 * @author Maxim Yacun, Niklas Bloom
*/
package GymDB;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Defines a GymManager object with the main run() method, helpers to execute commands, and databases to store information.
 *
 */
public class GymManager {
    private MemberDatabase DB;
    private FitnessClass[] classes;

    public void run() {
        System.out.println("Gym Manager running...");
        DB = new MemberDatabase();
        classes = new FitnessClass[]{new FitnessClass("Pilates"), new FitnessClass("Spinning"), new FitnessClass("Cardio")};

        Scanner sc = new Scanner(System.in);
        String currentLine = sc.nextLine();

        while (true) {
            StringTokenizer lineTokens = new StringTokenizer(currentLine);

            if (lineTokens.hasMoreTokens()) {
                if(commandParser(lineTokens))
                    break;
            } else { //skip blank line and print a blank line
                System.out.println();
            }
            currentLine = sc.nextLine();
        }
        System.out.println("Gym Manager terminated.");
    }

    //returns true if we get a Q signal to signify termination of the program
    //returns false otherwise, signifying we should continue
    private boolean commandParser(StringTokenizer lineTokens){
        String command = lineTokens.nextToken();
        switch (command) {
            case "Q":
                return true;
            case "A": //add member
                addMember(lineTokens);
                return false;
            case "R": //cancel membership
                rmMember(lineTokens);
                return false;
            case "P":
                if(DB.isEmpty()){
                    System.out.println("Member database is empty!");
                    return false;
                }
                System.out.println("\n-list of members-");
                DB.print();
                System.out.println("-end of list-\n");
                return false;
            case "PC":
                if(DB.isEmpty()){
                    System.out.println("Member database is empty!");
                    return false;
                }
                System.out.println("\n-list of members sorted by county and zipcode-");
                DB.printByCounty();
                System.out.println("-end of list-\n");
                return false;
            case "PN":
                if(DB.isEmpty()){
                    System.out.println("Member database is empty!");
                    return false;
                }
                System.out.println("\n-list of members sorted by last name, and first name-");
                DB.printByName();
                System.out.println("-end of list-\n");
                return false;
            case "PD":
                if(DB.isEmpty()){
                    System.out.println("Member database is empty!");
                    return false;
                }
                System.out.println("\n-list of members sorted by membership expiration date-");
                DB.printByExpirationDate();
                System.out.println("-end of list-\n");
                return false;
            case "S":
                System.out.println("\n-Fitness classes-");
                for (FitnessClass aClass : classes) aClass.print();
                System.out.println();
                return false;
            case "C":
                checkInMember(lineTokens);
                return false;
            case "D":
                dropClass(lineTokens);
                return false;
            default:
                System.out.println(command + " is an invalid command!");
                return false;
        }
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
        Member.Location location = Member.Location.parseLocation(locParam);
        if(location == null) {
            System.out.println(locParam + ": invalid location!");
            return;
        }

        Member newMem = new Member(fname, lname, dob, expire, location);
        if (!DB.add(newMem)) {
            System.out.println(fname + " " + lname + " is already in the database.");
        } else {
            System.out.println(fname + " " + lname + " added.");
        }
    }

    private void rmMember(StringTokenizer dataTokens){
        String fname = dataTokens.nextToken();
        String lname = dataTokens.nextToken();
        Date dob = new Date(dataTokens.nextToken());
        Member rmMem = new Member(fname, lname, dob, null, null);
        if(!DB.remove(rmMem)) {
            System.out.println(fname + " " + lname + " is not in the database.");
        } else {
            System.out.println(fname + " " + lname + " removed.");
        }
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
        String classStr = dataTokens.nextToken();

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
            if(aClass.getClassName().equalsIgnoreCase(classStr)){
                if (aClass.getMember(dbMember) != null) {
                    System.out.println(fname + " " + lname + " has already checked in " + aClass.getClassName() + ".");
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
                    System.out.println(choiceClass.getClassName() + " time conflict -- " +
                            fname + " " + lname + " has already checked in " + aClass.getClassName() + ".");
                    return;
                }
            }
        }

        //having passed all the above checks, adds the member to the chosen class

        choiceClass.add(dbMember);
        System.out.println(fname + " " + lname + " checked in " + choiceClass.getClassName() + ".");
    }

    //Your software shall not allow the member to drop the class if:
    // the member is not checked in,
    // or the date of birth is invalid,
    // or the fitness class does not exist.
    private void dropClass(StringTokenizer dataTokens){
        String classStr = dataTokens.nextToken();

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

        Member classMember = new Member(fname, lname, dob); //use given info to search for member in class

        for(FitnessClass aClass : classes){ //finds the chosen class and checks if already a member
            if(aClass.getClassName().equalsIgnoreCase(classStr)){
                if(!aClass.remove(classMember)) {
                    System.out.println(fname + " " + lname + " is not a participant in " + classStr + ".");
                } else {
                    System.out.println(fname + " " + lname + " dropped " + classStr + ".");
                }
                return;
            }
        }
        //if we get here, then the chosen class did not exist.
        System.out.println(classStr + " class does not exist.");
    }
}
