package main.controller;

import main.Main;
import main.db.DataBase;
import main.model.FitnessClub;

import java.util.Scanner;

//klasa odpowiadajaca za interfejs tekstowy | przedstawia mozliwosci w postaci pozycji do wyboru | odpowiada tez za odbieranie informacji od uzytkownika
//komunikuje sie z serwisem w ktorym znajduje sie logika aplikacji
public class FitnessClubController {

    private ClubCoachController clubCoachController = new ClubCoachController();
    private GroupController groupController = new GroupController();
    private ClubUserController clubUserController = new ClubUserController();
    private FindGroupController findGroupController = new FindGroupController();
    private Scanner scanner = new Scanner(System.in);

    //drukuje glowna legende aplikacji
    public void legend() {
        int option;
        FitnessClub fitnessClub = DataBase.getFitnessClub();

        System.out.println("===========================");
        System.out.println("Fitness club options");
        System.out.println("===========================");
        System.out.println("1 - Club coach options");
        System.out.println("2 - Group options");
        System.out.println("3 - Club user options");
        System.out.println("4 - Find group");
        System.out.println("5 - quit");
        System.out.println("===========================");
        if (scanner.hasNextInt()) {
            option = scanner.nextInt();
            switch (option) {
                case 1:
                    clubCoachController.legend(fitnessClub);
                    break;
                case 2:
                    groupController.legend(fitnessClub);
                    break;
                case 3:
                    clubUserController.legend(fitnessClub);
                    break;
                case 4:
                    findGroupController.findGroup(fitnessClub);
                    break;
                case 5:
                    setFlag();
                    break;
            }
        }
    }

    private void setFlag() {
        Main.setFlag(false);
    }
}
