package com.zh.server;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * create by zj on 2019/6/4
 */
public final class People implements Parcelable {

    public int sno;
    public String name;
    public int sex;
    public int age;
    public People() {

    }
    protected People(Parcel in) {
        sno = in.readInt();
        name = in.readString();
        sex = in.readInt();
        age = in.readInt();
    }

    public static final Creator<People> CREATOR = new Creator<People>() {
        @Override
        public People createFromParcel(Parcel in) {
            return new People(in);
        }

        @Override
        public People[] newArray(int size) {
            return new People[size];
        }
    };




    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(sno);
        dest.writeString(name);
        dest.writeInt(sex);
        dest.writeInt(age);
    }


}
