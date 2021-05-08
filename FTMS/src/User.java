/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asir
 */
class User {
    private int sno;
    private String Fname,Lname,address,city,country;

    public User(int sno, String Fname, String Lname, String address, String city, String country) {
        this.sno = sno;
        this.Fname = Fname;
        this.Lname = Lname;
        this.address = address;
        this.city = city;
        this.country = country;
    }
    
    public int getsno(){
        return sno;
    }

    public String getFname() {
        return Fname;
    }

    public String getLname() {
        return Lname;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }
    
}
