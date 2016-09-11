package pl.polsl.hdised.sourceIqScores;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Wookie on 2016-09-07.
 */
public class IqScoresList {

    private static IqScoresList instance = null;

    private IqScoresList() {}

    public static IqScoresList getInstance() {
        if (instance == null) {
            instance = new IqScoresList();
        }
        return instance;
    }

    private List<String> lines = new ArrayList<>();

    private List<IqScoreVector> iqLines = new ArrayList<>();

    private static String defaultSystemLineSeparator = System.getProperty("line.separator");

    public String fileWithIqScoresData;

    public void setFileWithIqScoresData(String fileWithIqScoresData) {
        this.fileWithIqScoresData = fileWithIqScoresData;
    }

    public void getDataFromFile() {
        File file = null;
        try {
            file = new File(this.fileWithIqScoresData);
        } catch (NullPointerException npe) {
            System.out.println("[ERROR] W ogóle nie podano ścieżki do pliku.");
            npe.printStackTrace();
        }
        try {
            lines = Files.readAllLines(file.toPath(),
                    StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("[ERROR] Błąd czytania pliku.");
            e.printStackTrace();
        }

        try {
            for (String line : lines) {
                String[] array = line.split(",");
                iqLines.add(new IqScoreVector(array[0], Integer.parseInt(array[1]), Integer.parseInt(array[2]), Integer.parseInt(array[3]), Integer.parseInt(array[4])));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("[ERROR] Błąd czytania danych z pliku (za mało wartości w linii albo coś takiego).");
            e.printStackTrace();
        }
    }

    public void printAll() {
        for(IqScoreVector iq : iqLines) {
            iq.printMe();
        }
    }
}
