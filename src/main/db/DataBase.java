package main.db;

import main.model.FitnessClub;
import main.model.Group;
import main.model.ClubCoach;
import main.model.ClubUser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

//klasa odpowiedzialna za przechowywanie glownej informacji o jaka jest klub fitnes | pole jest statyczne by moc wygodnie korzystac z niego w dowolnym miejscu w aplikacji
public class DataBase {

    //sciezki do plikow tekstowych w ktorych magazynujemy informacje
    private static final String CLUB_COACH = "clubCoach.txt";
    private static final String CLUB_USER = "clubUser.txt";
    private static final String GROUP = "group.txt";
    private static final String GROUP_CLUB_USER = "groupClubUser.txt";

    //domyslnie przy starcie aplikacji tworzymy nowy obiekt typu fitness club | aplikacja pracuje zawsze na tym samym klubie
    // zmieniają się jedynie jego dane takie jak lista trenerów, grup, uzytkownikow ... 
    public static FitnessClub fitnessClub = new FitnessClub(1, "Fitness Club", new ArrayList<>(), new ArrayList<>(), new ArrayList<>());

    public static FitnessClub getFitnessClub() {
        return fitnessClub;
    }

    //metoda zbierajaca w jedno miejsce wszystkiemetody sluzace do zapisu danych | zapis danych odbywa sie stopniowo
    //jako pierwszy zapisuje sie lista trenerow | jego lista uzytkownikow | lista grup | i lista uzytkownikow znajdujacych sie w grupie
    public static void saveAll() throws IOException {
        saveClubCoachList();
        saveClubUserList();
        saveGroupList();
        saveGroupClubUser();
    }

    //metoda zapisujaca liste trenerow
    private static void saveClubCoachList() throws IOException {
        Path path = Paths.get(CLUB_COACH);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            Iterator<ClubCoach> iter = fitnessClub.getClubCoachList().iterator();
            while (iter.hasNext()) {
                ClubCoach clubCoach = iter.next();
                bw.write("" + clubCoach.getId());
                bw.newLine();
                bw.write(clubCoach.getSpecialization());
                bw.newLine();
                bw.write(clubCoach.getFirstName());
                bw.newLine();
                bw.write(clubCoach.getLastName());
                bw.newLine();
                bw.write(clubCoach.getPersonalNumber());
                bw.newLine();
            }
        }
    }

    //metoda zapisujaca liste uzytkownikow
    private static void saveClubUserList() throws IOException {
        Path path = Paths.get(CLUB_USER);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            Iterator<ClubUser> iter = fitnessClub.getClubUserList().iterator();
            while (iter.hasNext()) {
                ClubUser clubUser = iter.next();
                bw.write("" + clubUser.getId());
                bw.newLine();
                bw.write(clubUser.getFirstName());
                bw.newLine();
                bw.write(clubUser.getLastName());
                bw.newLine();
                bw.write(clubUser.getPersonalNumber());
                bw.newLine();
            }
        }
    }

    //metoda zapisujaca liste grup
    private static void saveGroupList() throws IOException {
        Path path = Paths.get(GROUP);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            Iterator<Group> iter = fitnessClub.getGroupList().iterator();
            while (iter.hasNext()) {
                Group group = iter.next();
                bw.write("" + group.getId());
                bw.newLine();

                if (group.getClubCoach() != null) {
                    bw.write("" + group.getClubCoach().getId());
                    bw.newLine();
                } else {
                    bw.write("null");
                    bw.newLine();
                }

                bw.write(group.getGroupSpecialization());
                bw.newLine();
            }
        }
    }

    //metoda zapisujaca liste uzytkownikow w danej grupie
    private static void saveGroupClubUser() throws IOException {
        Path path = Paths.get(GROUP_CLUB_USER);

        try (BufferedWriter bw = Files.newBufferedWriter(path)) {
            for (Group group : fitnessClub.getGroupList()) {
                List<ClubUser> groupUserList = group.getGroupUserList();

                if (!groupUserList.isEmpty()) {
                    for (ClubUser user : groupUserList) {
                        bw.write("" + group.getId());
                        bw.newLine();
                        bw.write("" + user.getId());
                        bw.newLine();
                    }
                }
            }
        }
    }

    //metoda uruchamiajaca wszystkie motody wczytujaca po kolei
    public static void loadAll() throws IOException {
        loadClubCoachList();
        loadClubUserList();
        loadGroupList();
        loadGroupClubUser();
    }

    //metoda wczytuje liste trenerow z pliku tekstowego, tworzy ich jako obiekty i dodaje do Arraylist po czym arraylist jest ustawiona do pola statycznego fitness club
    private static void loadClubCoachList() throws IOException {
        Path path = Paths.get(CLUB_COACH);

        int id;
        String specialization;
        String firstName;
        String lastName;
        String personalNumber;

        List<ClubCoach> clubCoachList = new ArrayList<>();
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String input;
            while ((input = bf.readLine()) != null) {
                id = Integer.parseInt(input);
                specialization = bf.readLine();
                firstName = bf.readLine();
                lastName = bf.readLine();
                personalNumber = bf.readLine();

                ClubCoach clubCoach = new ClubCoach(firstName, lastName, personalNumber, id, specialization);
                clubCoachList.add(clubCoach);
            }
        }
        fitnessClub.setClubCoachList(clubCoachList);
    }

    //wczytuje uzytkownikow fitness club
    private static void loadClubUserList() throws IOException {
        Path path = Paths.get(CLUB_USER);

        int id;
        String firstName;
        String lastName;
        String personalNumber;

        List<ClubUser> clubUserList = new ArrayList<>();
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String input;
            while ((input = bf.readLine()) != null) {
                id = Integer.parseInt(input);
                firstName = bf.readLine();
                lastName = bf.readLine();
                personalNumber = bf.readLine();

                ClubUser clubUser = new ClubUser(firstName, lastName, personalNumber, id);
                clubUserList.add(clubUser);
            }
        }
        fitnessClub.setClubUserList(clubUserList);
    }

    //wczytuje grupy w fitness club
    private static void loadGroupList() throws IOException {
        Path path = Paths.get(GROUP);

        int id;
        ClubCoach clubCoach;
        String groupSpecialization;

        List<Group> groupList = new ArrayList<>();
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String input;
            while ((input = bf.readLine()) != null) {

                id = Integer.parseInt(input);

                input = bf.readLine();
                if (input.equals("null")) {
                    clubCoach = null;
                } else {
                    int clubCoachId = Integer.parseInt(input);
                    clubCoach = findClubCoachById(clubCoachId);
                }

                groupSpecialization = bf.readLine();

                Group group = new Group(id, clubCoach, groupSpecialization, new ArrayList<>());
                groupList.add(group);
            }
        }
        fitnessClub.setGroupList(groupList);
    }

    //wczytuje uzytkownikow przypisanych do grupy w fitness club
    private static void loadGroupClubUser() throws IOException {
        Path path = Paths.get(GROUP_CLUB_USER);

        int groupId;
        int clubUserId;

        try (BufferedReader bf = Files.newBufferedReader(path)) {
            String input;
            while ((input = bf.readLine()) != null) {
                groupId = Integer.parseInt(input);
                input = bf.readLine();
                clubUserId = Integer.parseInt(input);

                Group group = findGroupById(groupId);
                ClubUser clubUser = findClubUserById(clubUserId);
                if (group != null && clubUser != null) {
                    group.getGroupUserList().add(clubUser);
                }
            }
        }
    }

    //pozwala odnalezc trenera po id
    private static ClubCoach findClubCoachById(int id) {
        for (ClubCoach element : fitnessClub.getClubCoachList()) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    //pozwala odnalezc grupe po id
    private static Group findGroupById(int id) {
        for (Group element : fitnessClub.getGroupList()) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    //pozwala znalezc uzytkownika klubu po id
    private static ClubUser findClubUserById(int id) {
        for (ClubUser element : fitnessClub.getClubUserList()) {
            if (element.getId() == id) return element;
        }
        return null;
    }
}
