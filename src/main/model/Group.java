package main.model;

import java.util.List;

//model grupy zajeciowej
public class Group {

    private int id;
    private ClubCoach clubCoach;
    private String groupSpecialization;
    private List<ClubUser> groupUserList;

    public Group(int id, ClubCoach clubCoach, String groupSpecialization, List<ClubUser> groupUserList) {
        this.id = id;
        this.clubCoach = clubCoach;
        this.groupSpecialization = groupSpecialization;
        this.groupUserList = groupUserList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClubCoach getClubCoach() {
        return clubCoach;
    }

    public void setClubCoach(ClubCoach clubCoach) {
        this.clubCoach = clubCoach;
    }

    public String getGroupSpecialization() {
        return groupSpecialization;
    }

    public void setGroupSpecialization(String groupSpecialization) {
        this.groupSpecialization = groupSpecialization;
    }

    public List<ClubUser> getGroupUserList() {
        return groupUserList;
    }

    public void setGroupUserList(List<ClubUser> groupUserList) {
        this.groupUserList = groupUserList;
    }
}
