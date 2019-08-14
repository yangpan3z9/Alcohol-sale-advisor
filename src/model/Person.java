package model;

public class Person {
    private static int count = 0;
    private int id;
    private String name;
    private String occupation;

    public Person(String name, String occupation){
        this.name = name;
        this.occupation = occupation;

        this.id = count;
        count++;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }




}
