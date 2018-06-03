package com.example.aaron.nutrisportbetaversion.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aaron on 02/05/2018.
 */

public class User implements Parcelable {
    String email;
    String password;
    String key;
    String uid;

    public User(String email, String password, String key, String uid){
        this.email = email;
        this.password = password;
        this.key = key;
        this.uid = uid;
    }
    public User(){

    }

    protected User(Parcel in){
        email = in.readString();
        password = in.readString();
        key = in.readString();
        uid = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>(){


        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getKey() {return key;}

    public void setKey(String key) {this.key = key;}

    public String getUid() {return uid;}

    public void setUid(String uid) {this.uid = uid;}

    @Override
    public int describeContents() {return 0;}

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(key);
        dest.writeString(uid);
    }
}