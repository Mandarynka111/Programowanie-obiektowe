package main.model;

//klasa model okreslajaca szkielet uzytkownika klubu
public class ClubUser extends User {

    private int id;

    public ClubUser(String firstName, String lastName, String personalNumber, int id) {
        super(firstName, lastName, personalNumber);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String introduce() {
        return "Club user - " + getFirstName() + " " + getLastName();
    }
}
