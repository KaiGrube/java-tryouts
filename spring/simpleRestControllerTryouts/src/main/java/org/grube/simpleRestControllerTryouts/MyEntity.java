package org.grube.simpleRestControllerTryouts;

public class MyEntity {
    public String name;
    public int age;

    public MyEntity() {
    }

    public MyEntity(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getStringParam() {
        return name;
    }

    public void setStringParam(String name) {
        this.name = name;
    }

    public int getIntParam() {
        return age;
    }

    public void setIntParam(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
