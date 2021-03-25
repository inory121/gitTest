package com.inory.entity;

/**
 * @author inory
 * @create 2021-03-17 20:07
 */
public class Student {
    private String name;
    private Integer age;

    public Student() {
        System.out.println("Student的无参构造");
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("setName:"+name);
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        System.out.println("setAge:"+age);
        this.age = age;
    }


}
