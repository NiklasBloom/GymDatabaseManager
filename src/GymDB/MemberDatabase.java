package GymDB;

public class MemberDatabase {
    private Member [] mlist;
    private int size;

    public MemberDatabase() {
        this.size=4;
        this.mlist=new Member[4];
    }


    /* The find() method searches a member in the list and returns the index if it is found, it returns -1 if the
    member is not in the list. You must define a constant identifier “NOT_FOUND” for the value -1. */
    private int find(Member member) {
        return -1;
    }

    /*
    copy array into new array with array size +4
     */
    private void grow(){
        Member[] arr = new Member[this.size+4];
        System.arraycopy(this.mlist, 0, arr, 0, this.mlist.length);
    }

    public boolean add(Member member){// dont know why this method returns boolean and isnt void
        if(this.checkCapacity()==false){
            this.grow();
        }
        //get index of first null element in array
        int firstNull = -1;
        for (int i = 0; i < this.mlist.length; i++) {
            if (this.mlist[i] == null) {
                firstNull = i;
                break;
            }
        }
        mlist[firstNull] = member; // I think this method should work?

        return true;
    }

    /*
    helper method for add method to check whether there is room in mlist array to add another element.
    If there is no room, the add element will grow() the array.
     */
    public boolean checkCapacity(){

        boolean arrCapacity = false;

        for(int i = 0; i < this.size; i++){
            if(this.mlist[i] == null){
                arrCapacity = true;//is any element null? if so then not full
            }
        }

        if(arrCapacity == false){
            return false; //the array is full
        }
        else {
            return true; //the array has room for more elements
        }
    }
    public boolean remove(Member member) {
        return false;
    }
    public void print () {
        for(int i = 0; i < this.size; i++){
            this.mlist[i].toString(); // I think this should work?
            System.out.println();
        }

    } //print the array contents as is

    /*
    PC command, to display the list of members in the database ordered by the county names and then the zip codes;
    that is, if the locations are in the same county, ordered by the zip codes.
    Ordered by
    1) Edison
    2) Piscataway
    3) Bridgewater
    4) Franklin
    5) Somerville
     */
    public void printByCounty() { } //sort by county and then zipcode

    /*
    PD command, display the list of members in the database ordered by the expiration dates. If two expiration dates
    are the same, their order doesn’t matter.
     */
    public void printByExpirationDate() { } //sort by the expiration date

    /*
    PN command, display the list of membersin the database ordered by the members’ last names and then first names;
    that is, if two members have the same last name, ordered by the first name.
     */
    public void printByName() { } //sort by last name and then first name

}
