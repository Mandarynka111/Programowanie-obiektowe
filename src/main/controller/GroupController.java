package main.controller;

import main.model.FitnessClub;
import main.model.Group;
import main.model.ClubUser;
import main.service.GroupService;

import java.util.List;
import java.util.Scanner;

//klasa odpowiadajaca za interfejs tekstowy | przedstawia mozliwosci w postaci pozycji do wyboru | odpowiada tez za odbieranie informacji od uzytkownika
//komunikuje sie z serwisem w ktorym znajduje sie logika aplikacji
public class GroupController {

    private GroupService groupService = new GroupService();
    private Scanner scanner = new Scanner(System.in);

    //drukuje legende grupy
    public void legend(FitnessClub fitnessClub) {
        int result;

        while (true) {
            System.out.println("===========================");
            System.out.println("Select options");
            System.out.println("1 - Add new group");
            System.out.println("2 - Remove group");
            System.out.println("3 - assign club user");
            System.out.println("4 - remove club user");
            System.out.println("5 - Back to fitness club");

            result = scanner.nextInt();
            switch (result) {
                case 1:
                    addNewGroup();
                    break;
                case 2:
                    removeGroup(fitnessClub);
                    break;
                case 3:
                    assignClubUser(fitnessClub);
                    break;
                case 4:
                    removeClubUserFromGroup(fitnessClub);
                    break;
                case 5:
                    return;
            }
        }
    }

    //drukuje proces dodawania grupy
    private void addNewGroup() {
        System.out.println("===========================");
        System.out.println("Create new group");
        System.out.println("===========================");


        String groupSpecialization;
        scanner.nextLine();
        System.out.print("Group specialization: ");
        groupSpecialization = scanner.nextLine();

        if (groupService.addNewGroup(groupSpecialization) != null) {
            System.out.println("group added");
        } else {
            System.out.println("can't create");
        }
    }

    //drukuje proces usuwania grupy
    private void removeGroup(FitnessClub fitnessClub) {
        List<Group> groupList = fitnessClub.getGroupList();
        int result;

        System.out.println("Group remove: ");

        for (int i = 0; i < groupList.size(); i++) {
            Group group = groupList.get(i);
            System.out.println((i + 1) + ". Group id = " + group.getId());
        }

        if (groupList.isEmpty()) {
            System.out.println("List is empty");
        } else if (scanner.hasNextInt()) {
            result = scanner.nextInt();
            groupService.removeGroup(result - 1);
        }
    }

    //drukuje proces przypisywania do wskazanej grupy uzytkownika klubu
    private void assignClubUser(FitnessClub fitnessClub) {
        List<Group> groupList = fitnessClub.getGroupList();
        List<ClubUser> clubUserList = fitnessClub.getClubUserList();

        System.out.println("===========================");
        System.out.println("Assign club user");
        System.out.println("===========================");

        if (groupList.isEmpty() || clubUserList.isEmpty()) {
            System.out.println("list is empty");
            return;
        }
        for (int i = 0; i < groupList.size(); i++) {
            Group group = groupList.get(i);
            System.out.println((i + 1) + ". group id = " + group.getId());
        }
        int groupId = scanner.nextInt();

        for (int i = 0; i < clubUserList.size(); i++) {
            ClubUser clubUser = clubUserList.get(i);
            System.out.println((i + 1) + ". Club user id = " + clubUser.getId());
        }
        int clubUserId = scanner.nextInt();

        if (groupService.assignClubUser(clubUserId, groupId)) {
            System.out.println("Club user assigned to group");
        } else {
            System.out.println("Can't assigment");
        }
    }

    //drukuje proces usuwania z wskazanej grupy uzytkownika klubu
    private void removeClubUserFromGroup(FitnessClub fitnessClub) {
        List<Group> groupList = fitnessClub.getGroupList();

        System.out.println("===========================");
        System.out.println("Remove user from group");
        System.out.println("===========================");
        System.out.println("Choose Group");

        if (groupList.isEmpty()) {
            System.out.println("Group list is empty");
            return;
        } else {
            for (int i = 0; i < groupList.size(); i++) {
                Group group = groupList.get(i);
                System.out.println((i + 1) + ". group id = " + group.getId());
            }
        }
        int groupIndex = scanner.nextInt();
        int groupId = groupList.get(groupIndex -1).getId();


        List<ClubUser> groupUserList = groupList.get(groupIndex - 1).getGroupUserList();
        if (groupUserList.isEmpty()) {
            System.out.println("Group user list is empty");
            return;
        } else {
            System.out.println("Choose user:");
            for (int i = 0; i < groupUserList.size(); i++) {
                ClubUser clubUser = groupUserList.get(i);
                System.out.println((i + 1) + ". Club user id = " + clubUser.getId());
            }
        }
        int clubUserIndex = scanner.nextInt();
        int clubUserId = groupUserList.get(clubUserIndex -1).getId();

        if (groupService.removeClubUserFromGroup(clubUserId, groupId)){
            System.out.println("Removed");
        }else{
            System.out.println("Can't remove");
        }
    }
}
