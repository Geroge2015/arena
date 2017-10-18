package com.example.cm.testrv.basic;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cm on 2017/10/18.
 */

public class Person implements Parcelable {

    private String name;
    private int age;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }


    public static final Parcelable.Creator<Person> CREATOR = new Parcelable.Creator<Person>() {

        @Override
        public Person createFromParcel(Parcel source) {
            Person person = new Person();
            person.name = source.readString();
            person.age = source.readInt();
            return person;
        }

        @Override
        public Person[] newArray(int size) {
            return new Person[size];
        }
    };


}
