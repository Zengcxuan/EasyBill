package finalhomework.tcl.com.finalhomework.pojo;

import java.util.Objects;

public class Person {
    private int ID;
    private String NAME;
    private int PASSWORD;
    private int BID;

    public int getID() {
        return ID;
    }

    public String getNAME() {
        return NAME;
    }

    public int getPASSWORD() {
        return PASSWORD;
    }

    public int getBID() {
        return BID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public void setPASSWORD(int PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public void setBID(int BID) {
        this.BID = BID;
    }

    public Person(int ID, String NAME, int PASSWORD, int BID) {
        this.ID = ID;
        this.NAME = NAME;
        this.PASSWORD = PASSWORD;
        this.BID = BID;
    }

    @Override
    public String toString() {
        return "Person{" +
                "ID=" + ID +
                ", NAME='" + NAME + '\'' +
                ", PASSWORD=" + PASSWORD +
                ", BID=" + BID +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return ID == person.ID &&
                PASSWORD == person.PASSWORD &&
                BID == person.BID &&
                Objects.equals(NAME, person.NAME);
    }

    @Override
    public int hashCode() {

        return Objects.hash(ID, NAME, PASSWORD, BID);
    }
}
