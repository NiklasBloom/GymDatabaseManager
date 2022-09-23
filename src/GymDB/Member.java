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

    public Member(String fname, String lname,
                  Date dob, Date expire, Location location) {
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
        String s=this.fname + " " + this.lname + ", " + "DOB: "+ this.dob.toString() + "," + " Membership expires "
                + this.expire.toString() + "," + " Location: " + this.location;


        /*String name= (this.fname + "" + this.lname + ", ");
        String dob= ("DOB: "+ this.dob + ",");
        String expire= ("Membership expires "+ this.expire + ",");
        String location= ("Location: " + this.location);
        s.concat(name);
        s.concat(dob);
        s.concat(expire);
        s.concat(location);*/

        return s;
    }
    //April March, DOB: 3/31/1990, Membership expires 6/30/2023, Location: PISCATAWAY, 08854, MIDDLESEX
    public static void main(String[] args) {
        Member nik=new Member("Niklas", "Bloom", new Date("6/2/2000"),new Date("6/30/2023"),Location.Piscataway);
        System.out.print(nik.toString());

    }
}

