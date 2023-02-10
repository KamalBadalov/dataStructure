package people;

import enums.SexType;

public class Student extends Human {
    private String universityAddress;
    private int course;
    private int group;
    private double avgRate;

    public Student(String name, String surname, String birthDate,
                   SexType sexType, String residentialAddress,
                   String universityAddress, int course, int group, double avgRate) {
        super(name, surname, birthDate, sexType, residentialAddress);
        this.universityAddress = universityAddress;
        this.course = course;
        this.group = group;
        this.avgRate = avgRate;
    }

    public String getUniversityAddress() {
        return universityAddress;
    }

    public int getCourse() {
        return course;
    }

    public int getGroup() {
        return group;
    }

    public double getAvgRate() {
        return avgRate;
    }

    public void setUniversityAddress(String universityAddress) {
        this.universityAddress = universityAddress;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setAvgRate(double avgRate) {
        this.avgRate = avgRate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + getName() + '\'' +
                ", surname='" + getSurname() + '\'' +
                ", birthDate='" + getBirthDate() + '\'' +
                ", sexType=" + getSexType() +
                ", residentialAddress='" + getResidentialAddress() + '\'' +
                "universityAddress='" + universityAddress + '\'' +
                ", course=" + course +
                ", group=" + group +
                ", avgRate=" + avgRate +
                '}';
    }
}
