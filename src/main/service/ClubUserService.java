package main.service;

import main.db.DataBase;
import main.model.ClubUser;

//serwis odpowiedzialny za logike uzytkownika klubu | odbiera informacje od interfejsu tekstowego uzytkownika klubu
public class ClubUserService {

    //pozwala na dodanie nowego uzytkownika
    public ClubUser createClubUser(String firstName, String lastName, String personalNumber) {
        int id = generateId();
        ClubUser clubUser = new ClubUser(firstName, lastName, personalNumber, id);
        DataBase.getFitnessClub().getClubUserList().add(clubUser);
        return clubUser;
    }

    //pozwala na automatyczne wygenerowanie id uzytkownika
    private int generateId() {
        return DataBase.getFitnessClub().getClubUserList().size() + 1;
    }

    //pozwala na usuniecia uzytkownika
    public void removeClubUser(int clubUserIndex) {
        DataBase.getFitnessClub().getClubUserList().remove(clubUserIndex);
    }

    //pozwala na edycje danych uzytkownika
    public boolean editData(int id, String firstName, String lastName, String personalNumber) {
        ClubUser clubUser = null;
        for (ClubUser element : DataBase.getFitnessClub().getClubUserList()) {
            if (element.getId() == id) clubUser = element;
        }

        if (clubUser != null) {
            clubUser.setFirstName(firstName);
            clubUser.setLastName(lastName);
            clubUser.setPersonalNumber(personalNumber);
            return true;
        }
        return false;
    }
}
