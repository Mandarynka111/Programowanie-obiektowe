package main.controller;

import main.db.DataBase;
import main.model.FitnessClub;
import main.model.Group;
import main.model.ClubCoach;
import main.service.ClubCoachService;

import java.util.List;
import java.util.Scanner;

//klasa odpowiadajaca za interfejs tekstowy | przedstawia mozliwosci w postaci pozycji do wyboru | odpowiada tez za odbieranie informacji od uzytkownika
//komunikuje sie z serwisem w ktorym znajduje sie logika aplikacji
public class ClubCoachController {

    private ClubCoachService clubCoachService = new ClubCoachService();
    private static Scanner scanner = new Scanner(System.in);

    //drukuje legende interfejsu
    public void legend(FitnessClub fitnessClub) {
        int result;

        while (true) {
            System.out.println("===========================");
            System.out.println("Select options");
            System.out.println("1 - Add new club coach");
            System.out.println("2 - Remove club coach");
            System.out.println("3 - Assign to group");
            System.out.println("4 - Back to fitness club");

            result = scanner.nextInt();
            switch (result) {
                case 1:
                    addNewClubCoach(fitnessClub);
                    break;
                case 2:
                    removeClubCoach(fitnessClub);
                    break;
                case 3:
                    assignToGroup();
                    break;
                case 4:
                    return;
            }
        }
    }

    //drukuje proces dodawania nowego trenera
    private void addNewClubCoach(FitnessClub fitnessClub) {
        String firstName;
        String lastName;
        String personalNumber;
        String specialization;

        scanner.nextLine();
        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();

        System.out.print("Enter personal number: ");
        personalNumber = scanner.nextLine();

        System.out.print("Enter specialization: ");
        specialization = scanner.nextLine();

        clubCoachService.addNewClubCoach(fitnessClub, firstName, lastName, personalNumber, specialization);
    }

    //drukuje proces usuwania trenera
    private void removeClubCoach(FitnessClub fitnessClub) {
        List<ClubCoach> clubCoachList = fitnessClub.getClubCoachList();
        int result;

        System.out.println("Club coach remove: ");

        for (int i = 0; i < clubCoachList.size(); i++) {
            ClubCoach clubCoach = clubCoachList.get(i);
            System.out.println((i + 1) + ". " + clubCoach.introduce());
        }

        if (clubCoachList.isEmpty()) {
            System.out.println("List is empty");
        } else if (scanner.hasNextInt()) {
            result = scanner.nextInt();
            clubCoachService.removeClubCoach(result - 1);
        }
    }

    //drukuje proces przypisywania trenera do grupy
    private void assignToGroup() {
        System.out.println("===========================");
        System.out.println("Assign to group");
        System.out.println("Choose group: ");

        List<Group> groupList = DataBase.getFitnessClub().getGroupList();
        List<ClubCoach> clubCoachList = DataBase.getFitnessClub().getClubCoachList();

        if (groupList.isEmpty() || clubCoachList.isEmpty()) {
            System.out.println("List is empty");
            return;
        }

        for (int i = 0; i < groupList.size(); i++) {
            Group group = groupList.get(i);
            System.out.println((i + 1) + ". group id: " + group.getId());
        }
        int groupId = scanner.nextInt();

        System.out.println("Choose coach");
        for (int i = 0; i < clubCoachList.size(); i++) {
            ClubCoach clubCoach = clubCoachList.get(i);
            System.out.println((i + 1) + ". coach id: " + clubCoach.getId() + clubCoach.introduce());
        }
        int coachId = scanner.nextInt();

        if (clubCoachService.assignCoachToGroup(groupId, coachId)) {
            System.out.println("Coach assigment");
        } else {
            System.out.println("Can't assigment");
        }
    }
}
