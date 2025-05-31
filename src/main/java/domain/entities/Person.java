package domain.entities;

import java.util.Date;

public abstract class Person {
    protected String name;
    protected int age;
    protected String address;
    protected Date birthadte;

    public Person(String name, int age, String address, Date birthadte) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.birthadte = birthadte;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }
}
