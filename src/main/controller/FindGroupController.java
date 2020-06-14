package main.controller;

import main.model.FitnessClub;
import main.model.Group;
import main.model.ClubCoach;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//klasa odpowiadajaca za interfejs tekstowy | przedstawia mozliwosci w postaci pozycji do wyboru | odpowiada tez za odbieranie informacji od uzytkownika
//komunikuje sie z serwisem w ktorym znajduje sie logika aplikacji
public class FindGroupController {

    private Scanner scanner = new Scanner(System.in);

    //drukuje proces wyszukiwania grupy
    public void findGroup(FitnessClub fitnessClub) {
        System.out.println("===========================");
        System.out.println("Find group");
        System.out.println("===========================");
        System.out.println("Choose club coach");

        if (fitnessClub.getClubCoachList().isEmpty() || fitnessClub.getGroupList().isEmpty()) {
            System.out.println("List is empty");
        } else {
            for (int i = 0; i < fitnessClub.getClubCoachList().size(); i++) {
                ClubCoach clubCoach = fitnessClub.getClubCoachList().get(i);
                System.out.println((i + 1) + ". " + clubCoach.introduce());
            }
            if (scanner.hasNextInt()) {
                int index = scanner.nextInt() - 1;
                ClubCoach clubCoach = fitnessClub.getClubCoachList().get(index);
                List<Group> coachGroup = new ArrayList<>();
                for (Group element : fitnessClub.getGroupList()) {
                    if (element.getClubCoach() != null && element.getClubCoach().getId() == clubCoach.getId())
                        coachGroup.add(element);
                }

                if (coachGroup.isEmpty()){
                    System.out.println("Coach don't have any group");
                }else{
                    for (Group element : coachGroup){
                        System.out.println("Group id = " + element.getId() + " specialization = " + element.getGroupSpecialization());
                    }
                }
            }
        }
    }
}
