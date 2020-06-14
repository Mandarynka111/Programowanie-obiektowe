package main.model;

import java.util.List;

//glowny model aplikacji | to on trzyma wszystkie pozostale informacje razem
public class FitnessClub {

    private int id;
    private String name;
    private List<ClubCoach> clubCoachList;
    private List<Group> groupList;
    private List<ClubUser> clubUserList;

    public FitnessClub(int id, String name, List<ClubCoach> clubCoachList, List<Group> groupList, List<ClubUser> clubUserList) {
        this.id = id;
        this.name = name;
        this.clubCoachList = clubCoachList;
        this.groupList = groupList;
        this.clubUserList = clubUserList;
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

    public List<ClubCoach> getClubCoachList() {
        return clubCoachList;
    }

    public void setClubCoachList(List<ClubCoach> clubCoachList) {
        this.clubCoachList = clubCoachList;
    }

    public List<Group> getGroupList() {
        return groupList;
    }

    public void setGroupList(List<Group> groupList) {
        this.groupList = groupList;
    }

    public List<ClubUser> getClubUserList() {
        return clubUserList;
    }

    public void setClubUserList(List<ClubUser> clubUserList) {
        this.clubUserList = clubUserList;
    }
}
