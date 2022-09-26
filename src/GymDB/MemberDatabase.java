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
        for (int i = 0; i < size; i++){
            if(this.mlist[i].equals(member)){
                return 1;
            }

        }
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

    /*
    The remove() method remove a member from the list. This method maintains the relative order of the
    members in the list after the remove, -3 points if this is not done correctly.
     */
    public boolean remove(Member member) {//basically given index
        return false;
    }
    public void print () {
        for(int i = 0; i < this.size; i++){
            this.mlist[i].toString(); // I think this should work?
            System.out.println();
        }

    } //print the array contents as is



    public void bubbleSort(int arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
    }
    /*
    PC command, to display the list of members in the database ordered by the county names and then the zip codes;
    that is, if the locations are in the same county, ordered by the zip codes.
    Ordered by

    1) Edison, 08837, Middlesex County
    2) Piscataway, 08854, Middlesex County
    3) Bridgewater, 08807, Somerset County
    4) Franklin, 08873, Somerset County
    5) Somerville, 08876, Somerset County
     */

    public void printByCounty() {// I was thinking maybe just doing bubblesort? since in place and ez
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if(this.mlist[j].locationNumeric() > this.mlist[j+1].locationNumeric()){
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j+1];
                    this.mlist[j+1] = temp;
                }
            }
        }
    } //sort by county and then zipcode

    /*
    PD command, display the list of members in the database ordered by the expiration dates. If two expiration dates
    are the same, their order doesn’t matter.
     */
    public void printByExpirationDate() { } //sort by the expiration date

    /*
    PN command, display the list of membersin the database ordered by the members’ last names and then first names;
    that is, if two members have the same last name, ordered by the first name.
     */
    public void printByName() {
        for (int i = 0; i < size - 1; i++){
            for (int j = 0; j < size - i - 1; j++){
                if(this.mlist[j].compareTo(this.mlist[j+1])>0){
                    Member temp = this.mlist[j];
                    this.mlist[j] = this.mlist[j+1];
                    this.mlist[j+1] = temp;
                }
                else if(this.mlist[j].compareTo(this.mlist[j+1])==0){
                    if(this.mlist[j].compareTo(this.mlist[j+1])>0){
                        //if last names are the same, then compare first names
                        Member temp = this.mlist[j];
                        this.mlist[j] = this.mlist[j+1];
                        this.mlist[j+1] = temp;
                    }
                }

            }
        }
    } //sort by last name and then first name

}
