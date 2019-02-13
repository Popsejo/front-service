package kdg.microservices.client.model;

import java.io.Serializable;



public class Student {

    private int id;
    private String name;
    private String study;

    private School school;

    public Student(){}

    public Student(String name, String study, School school) {
        this.name = name;
        this.study = study;
        this.school = school;
    }
    public Student(String name, String study) {
        this.name = name;
        this.study = study;
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

    public String getStudy() {
        return study;
    }

    public void setStudy(String study) {
        this.study = study;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }
}
