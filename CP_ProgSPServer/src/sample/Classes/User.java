package sample.Classes;

import java.io.Serializable;

public class User implements Serializable {
        private int id;
        private String login;
        private String password;
        private int role;
        private int online;

        public User() {}
        public User(String login, String password) {}

        public int getId() {return id;}
        public void setId(int id) {
            this.id = id;
        }
        public String getLogin() {return login;}
        public void setLogin(String login) {
            this.login = login;
        }
        public String getPassword() {return password;}
        public void setPassword(String password) {
            this.password = password;
        }
        public int getRole() {return role;}
        public void setRole(int role) {
            this.role = role;
        }

        public void setOnline(int online) {
                this.online = online;
        }

        public int getOnline() {
                return online;
        }
}
