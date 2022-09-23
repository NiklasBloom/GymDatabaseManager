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
    public boolean equals(Object Obj ) {
        return true;
    }

    @Override
    public String toString() {
        return "yeah";
    }

}

