package GymDB;

public class Member implements Comparable<Member>{
    private String fname;
    private String lname;
    private Date dob;
    private Date expire;
    private Location location;

    enum Location{
        Bridgewater,
        Edison,
        Franklin,
        Piscataway,
        Somerville;

        public static Location parseLocation(String locStr){
            String locNormalized = locStr.toLowerCase();
            Member.Location location;
            return switch (locNormalized) {  //TODO: maybe make this its own method
                case "piscataway" -> Location.Piscataway;
                case "bridgewater" -> Location.Bridgewater;
                case "edison" -> Location.Edison;
                case "franklin" -> Location.Franklin;
                case "somerville" -> Location.Somerville;
                default -> null;
            };
        }
    }

    /*
    constructor for Member Class with each parameter given
     */
    public Member(String fname, String lname,
                  Date dob, Date expire, Location location) {
        this.location = location;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
    }



    /*
    constructor method if only fname, lname, and DOB is given.
    Used for the checkIn method when only those dat fields are available
     */
    public Member(String fname, String lname,
                  Date dob) {

        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.location = null;
        this.expire = null;
    }

    public String getFname(){
        return this.fname;
    }

    public String getLname(){
        return this.lname;
    }

    public Date getDob(){
        return this.dob;
    }

    public Date getExpire(){
        return this.expire;
    }

    public String getLocation(){
        return this.location.toString();
    }


    /*
     compareTo() method is used when sorting by names
     if s1 > s2, it returns 1
     if s1 < s2, it returns -1
     if s1 == s2, it returns 0
    */
    @Override
    public int compareTo(Member member) {
        if(this.getLname().compareTo(member.getLname()) > 0){ //this last name is greater
            return 1;
            }
        if(this.getLname().compareTo(member.getLname()) < 0){ //this last name is lesser
            return -1;
        }
        if(this.getLname().compareTo(member.getLname()) == 0){ //this last name is equal
            if(this.getFname().compareTo(member.getFname()) > 0){//this first name greater
                return 1;
            }
        }
        if(this.getLname().compareTo(member.getLname()) == 0){ //this last name is equal
            if(this.getFname().compareTo(member.getFname()) < 0){//this first name lesser
                return -1;
            }
        }
        if(this.getLname().compareTo(member.getLname()) == 0){ //this last name is equal
            if(this.getFname().compareTo(member.getFname()) == 0){//this first name equal
                return 0;
            }
        }
        return 0;
    }

    /*
    to see if two Members are equal to eachother.
    Tests if Members are equal by seeing if the fname, lname and DOB all match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Member) {
            Member student = (Member) obj; //casting
            return student.fname.equalsIgnoreCase(this.fname.toLowerCase())
                    && student.lname.equalsIgnoreCase(this.lname)
                    && student.dob.equals(this.dob);
        }
        return false;
    }

    /*
    to print Member toString, prints fname, lname, DOB, expire, and location
     */
    @Override
    public String toString() {
        //checks if membership is expired, and change wording from "expires" to "expired" accordingly
        String s = this.fname + " " + this.lname + ", DOB: " + this.dob.toString()
                + ((this.expire.futureDateCheck()) ? ", Membership expires " : ", Membership expired ")
                + this.expire.toString() + ", Location: " + this.fullLocation();
        return s;
    }

    /*
    Based off the Members location, this returns the full String location for the toString() method
     */
    public String fullLocation() {
        String s ="";
        switch (location) {
            case Edison:
                s = ("EDISON, 08837, MIDDLESEX");
                break;
            case Piscataway:
                s = ("PISCATAWAY, 08854, MIDDLESEX");
                break;
            case Bridgewater:
                s = ("BRIDGEWATER, 08807, SOMERSET");
                break;
            case Franklin:
                s = ("FRANKLIN, 08873, SOMERSET");
                break;
            case Somerville:
                s = ("SOMERVILLE, 08876, SOMERSET");
                break;
        }
        return s;
    }
    /*
    returns a numeric value for each county/zip code for sorting purposes
     1) Edison, 08837, Middlesex County
    2) Piscataway, 08854, Middlesex County
    3) Bridgewater, 08807, Somerset County
    4) Franklin, 08873, Somerset County
    5) Somerville, 08876, Somerset County
     */
    public int locationNumeric() {
        int locationNumber = -1;
        switch (this.location) {
            case Edison:
                locationNumber = 1;
                break;
            case Piscataway:
                locationNumber = 2;
                break;
            case Bridgewater:
                locationNumber = 3;
                break;
            case Franklin:
                locationNumber = 4;
                break;
            case Somerville:
                locationNumber = 5;
                break;
        }
        return locationNumber;
    }


    /*
    testbed method for CompareTo Test Cases
     */
    public static void main(String[] args) { //testbed main for compareto Method
        Member member1 = new Member("Niklas", "Bloom", new Date("1/2/2000"), new Date("1/2/2022"), Location.Piscataway);
        Member member2 = new Member("Niklas", "Cloom", new Date("1/2/2000"));
        System.out.println(member1.compareTo(member2));

        Member member3 = new Member("Niklas", "A", new Date("1/2/2000"));
        Member member4 = new Member("Niklas", "Z", new Date("1/2/2000"));
        System.out.println(member3.compareTo(member4));

        Member member5 = new Member("Niklas", "Z", new Date("1/2/2000"));
        Member member6 = new Member("Niklas", "A", new Date("1/2/2000"));
        System.out.println(member5.compareTo(member6));

        Member member7 = new Member("A", "A", new Date("1/2/2000"));
        Member member8 = new Member("A", "Z", new Date("1/2/2000"));
        System.out.println(member7.compareTo(member8));

        Member member9 = new Member("A", "A", new Date("1/2/2000"));
        Member member10 = new Member("B", "Z", new Date("1/2/2000"));
        System.out.println(member9.compareTo(member10));
    }
}

