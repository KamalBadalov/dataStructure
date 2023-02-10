package people;

import enums.SexType;

public  class Human {
    private String name;
    private String surname;
    private final String birthDate;
    private final SexType sexType;
    private String residentialAddress;

    protected Human(String name, String surname, String birthDate, SexType sexType, String residentialAddress) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.sexType = sexType;
        this.residentialAddress = residentialAddress;
    }
    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getResidentialAddress() {
        return residentialAddress;
    }

    public SexType getSexType() {
        return sexType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setResidentialAddress(String residentialAddress) {
        this.residentialAddress = residentialAddress;
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", sexType=" + sexType +
                ", residentialAddress='" + residentialAddress + '\'' +
                '}';
    }
}
