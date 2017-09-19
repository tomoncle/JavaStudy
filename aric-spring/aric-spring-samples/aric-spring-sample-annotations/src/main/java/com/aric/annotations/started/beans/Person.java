package com.aric.annotations.started.beans;

/**
 * Created by tom.lee on 2016/3/16.
 */
//@Component
public class Person {

    private int  id;

    private String name;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}


