package main.controller;

import main.model.FitnessClub;
import main.model.ClubUser;
import main.service.ClubUserService;

import java.util.List;
import java.util.Scanner;

//klasa odpowiadajaca za interfejs tekstowy | przedstawia mozliwosci w postaci pozycji do wyboru | odpowiada tez za odbieranie informacji od uzytkownika
//komunikuje sie z serwisem w ktorym znajduje sie logika aplikacji
public class ClubUserController {

    private ClubUserService clubUserService = new ClubUserService();
    private Scanner scanner = new Scanner(System.in);

    //drukuje legende interfejsu
    public void legend(FitnessClub fitnessClub) {
        int result;

        while (true) {
            System.out.println("===========================");
            System.out.println("Select options");
            System.out.println("===========================");
            System.out.println("1 - Add new club user");
            System.out.println("2 - Remove club user");
            System.out.println("3 - Edit data");
            System.out.println("4 - Back to fitness club");

            result = scanner.nextInt();
            switch (result) {
                case 1:
                    addNewClubUser();
                    break;
                case 2:
                    removeClubUser(fitnessClub);
                    break;
                case 3:
                    editData(fitnessClub);
                    break;
                case 4:
                    return;
            }
        }
    }

    //drukuje proces dodawania uzytkownika klubu
    private void addNewClubUser() {
        String firstName;
        String lastName;
        String personalNumber;

        scanner.nextLine();
        System.out.print("Enter first name: ");
        firstName = scanner.nextLine();

        System.out.print("Enter last name: ");
        lastName = scanner.nextLine();

        System.out.print("Enter personal number: ");
        personalNumber = scanner.nextLine();

        if (clubUserService.createClubUser(firstName, lastName, personalNumber) != null) {
            System.out.println("Club user created");
        } else {
            System.out.println("Can't create");
        }
    }

    //drukuje proces usuwania uzytkownika klubu
    private void removeClubUser(FitnessClub fitnessClub) {
        List<ClubUser> clubUserList = fitnessClub.getClubUserList();
        System.out.println("===========================");
        System.out.println("Club user remove: ");
        System.out.println("===========================");

        if (clubUserList.isEmpty()) {
            System.out.println("List is empty");
        } else {
            for (int i = 0; i < clubUserList.size(); i++) {
                ClubUser clubUser = clubUserList.get(i);
                System.out.println((i + 1) + ". " + clubUser.introduce());
            }
            int index;
            if (scanner.hasNextInt()) {
                index = scanner.nextInt();
                clubUserService.removeClubUser(index - 1);
            }
        }
    }

    //drukuje proces edycji uzytkownika klubu
    private void editData(FitnessClub fitnessClub) {
        System.out.println("===========================");
        System.out.println("Edit data");
        System.out.println("===========================");

        if (fitnessClub.getClubUserList().isEmpty()) {
            System.out.println("List is empty");
        } else {
            for (int i = 0; i < fitnessClub.getClubUserList().size(); i++) {
                ClubUser clubUser = fitnessClub.getClubUserList().get(i);
                System.out.println((i + 1) + ". " + clubUser.introduce() + " " + clubUser.getPersonalNumber());
            }
            int id;
            if (scanner.hasNextInt()) {
                id = scanner.nextInt();

                scanner.nextLine();
                System.out.print("First name: ");
                String firstName = scanner.nextLine();
                System.out.print("Last name: ");
                String lastName = scanner.nextLine();
                System.out.println("Personal number: ");
                String personalNumber = scanner.nextLine();

                if (clubUserService.editData(id, firstName, lastName, personalNumber)) {
                    System.out.println("Edit success");
                } else {
                    System.out.println("Can't edit");
                }
            }
        }
    }
}
