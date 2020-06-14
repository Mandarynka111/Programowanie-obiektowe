package main.model;

//kalsa abstrakcyjna po ktorej dziedziczy trener i uzytkownik klubu | mieszcza sie w niej niezbedne informacje uzytkownika
public abstract class User implements UserInterface{

    private String firstName;
    private String lastName;
    private String personalNumber;

    public User(String firstName, String lastName, String personalNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public void setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
    }
}
