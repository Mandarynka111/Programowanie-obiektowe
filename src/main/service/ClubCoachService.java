package main.service;

import main.db.DataBase;
import main.model.FitnessClub;
import main.model.Group;
import main.model.ClubCoach;

//serwis odpowiedzialny za logike trenera | odbiera informacje od interfejsu tekstowego trenera
public class ClubCoachService {

    //odpowiada za mechanizm pozwalajacy na dodanie nowego trenera
    public ClubCoach addNewClubCoach(FitnessClub fitnessClub, String firstName, String lastName, String personalNumber, String specialization) {
        int id = generateClubCoachId(fitnessClub);
        ClubCoach clubCoach = new ClubCoach(firstName, lastName, personalNumber, id, specialization);
        fitnessClub.getClubCoachList().add(clubCoach);
        return clubCoach;
    }

    //odpowiada za usuniecie trenera
    public void removeClubCoach(int clubCoachId) {
        DataBase.getFitnessClub().getClubCoachList().remove(clubCoachId);
    }

    //odpowiada za przypisanie trenera do wskazanej grupy
    public boolean assignCoachToGroup(int groupId, int coachId) {
        Group group = null;
        ClubCoach clubCoach = null;

        for (Group element : DataBase.getFitnessClub().getGroupList()) {
            if (element.getId() == groupId) group = element;
        }

        for (ClubCoach element : DataBase.getFitnessClub().getClubCoachList()) {
            if (element.getId() == coachId) clubCoach = element;
        }

        if (group != null && clubCoach != null) {
            group.setClubCoach(clubCoach);
            return true;
        }
        return false;
    }

    //pozwala automatycznie wygenerowac id trenera
    private int generateClubCoachId(FitnessClub fitnessClub) {
        return fitnessClub.getClubCoachList().size() + 1;
    }
}
