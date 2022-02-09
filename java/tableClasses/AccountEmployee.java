package tableClasses;

import java.io.Serializable;

public class AccountEmployee implements Serializable {
    private int idEmployee;
    private String position;
    private int salary;
    private String surnameEmployee;
    private String nameEmployee;
    private String lastnameEmployee;
    private String phone;
    private int idUser;
    private String login;
    private String password;
    private int role;
    private int online;
    public AccountEmployee() {}

    public int getIdEmployee() {
        return idEmployee;
    }

    public String getPosition() {
        return position;
    }

    public int getSalary() {
        return salary;
    }

    public String getSurnameEmployee() {
        return surnameEmployee;
    }

    public String getNameEmployee() {
        return nameEmployee;
    }

    public String getLastnameEmployee() {
        return lastnameEmployee;
    }

    public String getPhone() {
        return phone;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public int getRole() {
        return role;
    }

    public int getOnline() {
        return online;
    }

    public void setIdEmployee(int idEmployee) {
        this.idEmployee = idEmployee;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setSurnameEmployee(String surnameEmployee) {
        this.surnameEmployee = surnameEmployee;
    }

    public void setNameEmployee(String nameEmployee) {
        this.nameEmployee = nameEmployee;
    }

    public void setLastnameEmployee(String lastnameEmployee) {
        this.lastnameEmployee = lastnameEmployee;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}