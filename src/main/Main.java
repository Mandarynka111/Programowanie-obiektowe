package main;

import main.controller.FitnessClubController;
import main.db.DataBase;

import java.io.IOException;

public class Main {

    private static boolean flag = true;
    private static FitnessClubController fitnessClub = new FitnessClubController();

    public static void main(String[] args) throws IOException {
        start();
    }

    //metoda uruchamiajaca cala aplikacje
    //w pierwszej kolejnosci wczytuje dane aplikacji z plikow tekstowych
    // kolejno petla while ciagle drukuje nam glowna legende aplikacji az do momentu wyjscia ( opcja w interfejsie - tylko ona pozwala poprawnie zapisac dane do plikow)
    // kolejno zapisuje wszystkie informacje do plikow ( tylko jesli wyjdziemy poprawnie z aplikacji uzywajac interfejsu )
    private static void start() throws IOException {
        DataBase.loadAll();
        while (flag) {
            fitnessClub.legend();
        }
        DataBase.saveAll();
    }

    public static void setFlag(boolean flag) {
        Main.flag = flag;
    }
}
