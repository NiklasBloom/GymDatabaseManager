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
        Somerville,
    }


    public Member(String fname, String lname,
                  Date dob, Date expire, Location location) {
        this.location = location;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
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




    @Override
    public int compareTo(Member member) {
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Member) {
            Member student = (Member) obj; //casting
            if (student.fname.equals(this.fname)&& student.lname.equals(this.lname) &&
                    student.dob.equals(this.dob)){
                return true;
            }
            else {
                return false;
            }

        }
        return false;
    }

    @Override
    public String toString() {
        String s=this.fname + " " + this.lname + ", " + "DOB: "+ this.dob.toString() + "," + " Membership expires "
                + this.expire.toString() + "," + " Location: " + this.fullLocation();


        return s;
    }
    //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX

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
    public static void main(String[] args) {
        Member nik=new Member("Niklas", "Bloom", new Date("6/2/2000"),new Date("6/30/2023"),Location.Piscataway);
        System.out.println(nik.toString());
        System.out.println(nik.getLocation());



    }
}

