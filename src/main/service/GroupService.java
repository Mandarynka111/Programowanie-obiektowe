package main.service;

import main.db.DataBase;
import main.model.Group;
import main.model.ClubUser;

import java.util.ArrayList;

//serwis odpowiedzialny za logike grupy | odbiera informacje od interfejsu tekstowego grupy
public class GroupService {

    //pozwala na dodanie nowej grupy
    public Group addNewGroup(String groupSpecialization) {
        Group group = new Group(generateId(), null, groupSpecialization, new ArrayList<>());
        DataBase.getFitnessClub().getGroupList().add(group);
        return group;
    }

    //pozwala na dodanie uzytkownika klubu do danej grupy
    public boolean assignClubUser(int clubUserId, int groupId) {
        Group group = null;
        ClubUser clubUser = null;

        for (Group element : DataBase.getFitnessClub().getGroupList()) {
            if (element.getId() == groupId) group = element;
        }

        for (ClubUser element : DataBase.getFitnessClub().getClubUserList()) {
            if (element.getId() == clubUserId) clubUser = element;
        }

        if (group != null && clubUser != null) {
            group.getGroupUserList().add(clubUser);
            return true;
        }
        return false;
    }

    //pozwala na usuniecie uzytkownika z danej grupy
    public boolean removeClubUserFromGroup(int clubUserId, int groupId) {
        Group group = null;
        ClubUser clubUser = null;

        for (Group element : DataBase.getFitnessClub().getGroupList()) {
            if (element.getId() == groupId) group = element;
        }

        if (group != null) {
            for (ClubUser element : group.getGroupUserList()) {
                if (element.getId() == clubUserId) clubUser = element;
            }
        }

        if (clubUser != null) {
            group.getGroupUserList().remove(clubUser);
            return true;
        }
        return false;
    }

    //pozwala na automatyczne wygenerowanie id grupy
    public int generateId() {
        return DataBase.getFitnessClub().getGroupList().size() + 1;
    }

    //pozwala na usuniecie grupy
    public void removeGroup(int groupId) {
        DataBase.getFitnessClub().getGroupList().remove(groupId);
    }
}
