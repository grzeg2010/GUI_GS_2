package Dane;

import java.io.*;
import java.util.Map;

public class FileOperations {
    public static void safeToFile(Dane.BazaDanych db) {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(getFileName()))) {
            outputStream.writeObject(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFile(Dane.BazaDanych db) {
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(getFileName()))) {
            BazaDanych o = (BazaDanych) inputStream.readObject();
            db.setMapaBrygad(o.getMapaBrygad());
            db.setIloscBrygad(o.getIloscBrygad());

            db.setMapaBrygadzistow(o.getMapaBrygadzistow());
            db.setIloscBrygadzistow(o.getIloscBrygadzistow());

            db.setMapaDzialow(o.getMapaDzialow());
            db.setIloscDzialow(o.getIloscDzialow());

            db.setMapaPrac(o.getMapaPrac());
            db.setIloscPrac(o.getIloscPrac());

            db.setMapaPracownikow(o.getMapaPracownikow());
            db.setIloscPracownikow(o.getIloscPracownikow());

            db.setMapaUzytkownikow(o.getMapaUzytkownikow());
            db.setIloscUzytkownikow(o.getIloscUzytkownikow());

            db.setMapaZlecen(o.getMapaZlecen());
            db.setIloscZlecen(o.getIloscZlecen());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String getFileName() {
        return "./BazaDanych.bin";
    }
}
