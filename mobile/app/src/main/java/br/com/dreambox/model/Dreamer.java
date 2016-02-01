package br.com.dreambox.model;

import com.google.gson.JsonObject;

import org.parceler.Parcel;

import java.util.Calendar;
import java.util.Date;

@Parcel
public class Dreamer {

    int code;
    String name;
    String city;
    String state;
    String foundation;
    Date birthday;
    String lastName;
    String nickname;
    String password;
    String email;

    public Dreamer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFoundation() {
        return foundation;
    }

    public void setFoundation(String foundation) {
        this.foundation = foundation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public static Dreamer fromJson(JsonObject json) {
        Dreamer dreamer = new Dreamer();
        dreamer.name = json.get("name").getAsString();

        return dreamer;
    }

    public int getAge() {
        Calendar today = Calendar.getInstance();
        Calendar bday = Calendar.getInstance();
        bday.setTime(getBirthday());
        int age = today.get(Calendar.YEAR) - bday.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < bday.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == bday.get(Calendar.MONTH) &&
                        today.get(Calendar.DAY_OF_MONTH) <= bday.get(Calendar.DAY_OF_MONTH))) {
            age++;
        }
        return age;
    }
}