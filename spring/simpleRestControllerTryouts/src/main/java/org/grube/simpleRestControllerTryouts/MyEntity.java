package org.grube.simpleRestControllerTryouts;

public class MyEntity {
    public String stringParam;
    public int intParam;

    public MyEntity() {
    }

    public MyEntity(String stringParam, int intParam) {
        this.stringParam = stringParam;
        this.intParam = intParam;
    }

    public String getStringParam() {
        return stringParam;
    }

    public void setStringParam(String stringParam) {
        this.stringParam = stringParam;
    }

    public int getIntParam() {
        return intParam;
    }

    public void setIntParam(int intParam) {
        this.intParam = intParam;
    }

    @Override
    public String toString() {
        return "MyEntity{" +
                "stringParam='" + stringParam + '\'' +
                ", intParam=" + intParam +
                '}';
    }
}
