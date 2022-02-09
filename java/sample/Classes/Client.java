package sample.Classes;

import java.io.Serializable;

public class Client implements Serializable {
    private int idClient;
    private String email;
    private String surnameClient;
    private String nameClient;
    private String lastnameClient;
    private String address;
    private String phone;
    private int user_idUser;

    public Client() {}

    public int getIdClient() {return idClient;}
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getEmail() {return email;}
    public void setEmail(String email) {
        this.email = email;
    }
    public int getUser_idUser() {return user_idUser;}
    public void setUser_idUser(int user_idUser) {this.user_idUser = user_idUser;   }
    public String getSurnameClient() {return surnameClient;}
    public void setSurnameClient(String surnameClient) {
        this.surnameClient = surnameClient;
    }
    public String getNameClient() {return nameClient;}
    public void  setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }
    public String getLastnameClient() {return lastnameClient;}
    public void setLastnameClient(String lastnameClient) { this.lastnameClient = lastnameClient; }
    public String getAddress() {return address;}
    public void setAddress(String address) {this.address = address;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}
}
