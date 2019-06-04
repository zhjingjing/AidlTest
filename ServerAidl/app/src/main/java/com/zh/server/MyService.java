package com.zh.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

/**
 * create by zj on 2019/6/4
 */
public class MyService extends Service {

    private List<People> peopleList = new ArrayList<People>();

    @Override
    public void onCreate() {
        synchronized (peopleList) {
            for (int i = 0; i < 5; i++) {
                People people = new People();
                people.age=i+1;
                people.name="zh"+i;
                people.sex=0;
                people.sno=i;
                peopleList.add(people);
            }
        }
        super.onCreate();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private IMyAidlInterface.Stub mBinder=new IMyAidlInterface.Stub() {

        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
                               double aDouble, String aString){

        }

        @Override
        public List<People> getPeople() throws RemoteException {
            synchronized (peopleList) {
                return peopleList;
            }
        }

        @Override
        public void addPeople(People people) throws RemoteException {
            synchronized (peopleList) {
                if (!peopleList.contains(people)) {
                    peopleList.add(people);
                }
            }
        }
    };
}
