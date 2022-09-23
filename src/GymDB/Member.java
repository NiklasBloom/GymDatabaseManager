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


    public Member(Location location) {
        this.location = location;
    }

    public Member(String fname, Location location, String lname,
                  Date dob, Date expire) {
        this.location = location;
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
        this.expire = expire;
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
        String s="";
        s.concat((this.fname + "" + this.lname + ", "));
        s.concat(("DOB: "+ this.dob + ","));
        s.concat("Membership expires "+ this.expire + ",");
        s.concat("Location: " + this.location);

        return s;
    }
    //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX

}

