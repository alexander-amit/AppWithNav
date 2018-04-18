package aph.com.appwithnav.model;

import java.util.List;

public class Student {

    private String id;
    private String name;
    private int phNumber;
    private String year;
    private int coins;
    private List<Subject> subjectList;

    public Student(String id, String name, int phNumber, String year, int coins, List<Subject> subjectList) {
        this.id = id;
        this.name = name;
        this.phNumber = phNumber;
        this.year = year;
        this.coins = coins;
        this.subjectList = subjectList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(int phNumber) {
        this.phNumber = phNumber;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }
}
