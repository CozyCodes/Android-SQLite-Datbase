package project.cozycodes.sqlite;

/**
 * Created by Cozycodes on 3/18/2017.
 */

public class SqliteModel {

    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;


    public SqliteModel(String id, String name, String email, String phone, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public SqliteModel() {
    }



    public String getId(){
        return id;
    }

    public void setId(String id ){
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public void  setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String address){
        this.address = address;
    }
}
