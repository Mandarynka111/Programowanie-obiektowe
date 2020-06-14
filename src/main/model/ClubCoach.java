package main.model;

//kalsa model okreslajaca szkielet trenera
public class ClubCoach extends User {

    private int id;
    private String specialization;

    public ClubCoach(String firstName, String lastName, String personalNumber, int id, String specialization) {
        super(firstName, lastName, personalNumber);
        this.id = id;
        this.specialization = specialization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    @Override
    public String introduce() {
        return "Club coach - " + getFirstName() + " " + getLastName();
    }
}
