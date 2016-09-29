package model;

/**
 * Created by Austin Adams on 9/19/16.
 */
public class User {
    protected String user;
    protected String password;
    protected boolean isblock = false;

    public User(){

        this("user","pass");
    }
    public User(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public boolean authenticate(String user, String password) {
        return this.user.equals(user) && this.password.equals(password);
    }
    public void reportWaterAvail(String report){

    }
    public String viewWaterSources(){
        return null;
    }
    public void block() {
        this.isblock = true;
    }
    public void unblock(){
        this.isblock = false;
    }
    public boolean ifblock(){
        return this.isblock;
    }

}
