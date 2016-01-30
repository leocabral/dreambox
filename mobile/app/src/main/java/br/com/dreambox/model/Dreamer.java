package br.com.dreambox.model;

import com.google.gson.JsonObject;

import org.parceler.Parcel;

import java.util.Date;

import br.com.dreambox.api.DreamboxApi;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

@Parcel
public class Dreamer {

    long code;
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

    public void nameFromJson(long dreamer) {
        DreamboxApi.get().getDreamer(dreamer, new Callback<JsonObject>() {
            @Override
            public void success(JsonObject jsonObject, Response response) {
                String n = jsonObject.get("name").getAsString();
                nameLess(n);
            }

            @Override
            public void failure(RetrofitError error) {
                System.out.println("Deu erro de get dentro da classe Dreamer");
            }
        });
    }

    private void nameLess(String n) {
        this.name = n;
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }
}