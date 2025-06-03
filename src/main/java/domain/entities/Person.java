package domain.entities;

import java.util.Date;

public abstract class Person {
    protected String name;
    protected int age;
    protected Date birthDate;
    protected String address;

    public Person(String name, int age, Date birthDate, String address) {
        this.name = name;
        this.age = age;
        this.birthDate = birthDate;
        this.address = address;
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
