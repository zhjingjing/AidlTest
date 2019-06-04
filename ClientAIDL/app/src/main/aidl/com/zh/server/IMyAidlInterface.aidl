// MyService.aidl
package com.zh.server;

// Declare any non-default types here with import statements
import com.zh.server.People;
interface IMyAidlInterface {


    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

    List<People> getPeople();
    void addPeople(in People people);
}
