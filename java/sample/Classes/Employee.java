package sample.Classes;

import java.io.Serializable;

public class Employee implements Serializable {
    private int idEmployee;
    private String position;
    private int salary;
    private String surnameEmployee;
    private String nameEmployee;
    private String lastnameEmployee;
    private int idUser;
    private String phone;

    public Employee() {}
    public int getIdEmployee() {return idEmployee;}
    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }
    public String getPosition(){return position;}
    public void setPosition(String position) {
        this.position = position;
    }
    public int getSalary() {return salary;}
    public void setSalary(int salary) {
        this.salary = salary;
    }
    public String getSurnameEmployee() {return surnameEmployee;}
    public void setSurnameEmployee(String surnameEmployee) {this.surnameEmployee = surnameEmployee; }
    public String getNameEmployee() {return nameEmployee;}
    public void setNameEmployee(String nameEmployee) {this.nameEmployee = nameEmployee; }
    public String getLastnameEmployee() {return lastnameEmployee;}
    public void setLastnameEmployee(String lastnameEmployee) {
        this.lastnameEmployee = lastnameEmployee;
    }
    public int getIdUser() {return idUser;}
    public void setIdUser(int idUser) {this.idUser = idUser;}
    public String getPhone() {return phone;}
    public void setPhone(String phone) {this.phone = phone;}



}
