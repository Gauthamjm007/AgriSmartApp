package darshanNotification;

public class Ndataprovider {

    private String n_message;
    private String n_date;
    private String n_time;

    public String getN_message() {
        return n_message;
    }

    public void setN_message(String n_message) {
        this.n_message = n_message;
    }

    public String getN_date() {
        return n_date;
    }

    public void setN_date(String n_date) {
        this.n_date = n_date;
    }

    public String getN_time() {
        return n_time;
    }

    public void setN_time(String n_time) {
        this.n_time = n_time;
    }

    public Ndataprovider(String n_message, String n_date, String n_time){

   this.n_message=n_message;
   this.n_date=n_date;
   this.n_time=n_time;
   }
}