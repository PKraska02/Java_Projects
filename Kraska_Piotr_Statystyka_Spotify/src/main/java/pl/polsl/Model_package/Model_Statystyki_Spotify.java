package pl.polsl.Model_package;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author Piotr
 */
/**
 * Opis Klasy Model_Statystyki_Spotify
 */
public class Model_Statystyki_Spotify {
    /**
     * Imie autora
     * @param authorName - Imie autora
     */
    private String authorName;
    private String authorLastName;
    private String title;
    private String continent;
    private int playsCount = 0;
    private double songTime = 0;
    
    public List<Model_Statystyki_Spotify> readFile(String filePath) throws FileNotFoundException {
        List<Model_Statystyki_Spotify> spotifyStatsList = new ArrayList<>();

        try  {
            Scanner scanner = new Scanner (new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" "); 

                if (data.length == 6) {
                    Model_Statystyki_Spotify spotifyStats = new Model_Statystyki_Spotify();
                    spotifyStats.setAuthorName(data[0]);
                    spotifyStats.setAuthorLastName(data[1]);
                    spotifyStats.setTitle(data[2]);
                    spotifyStats.setContinent(data[3]);
                    spotifyStats.setPlaysCount(Integer.parseInt(data[4]));
                    spotifyStats.setSongTime(Double.parseDouble(data[5]));
                    spotifyStatsList.add(spotifyStats);
                } else {
                    throw new RuntimeException("Bledny szablon lub zle zapisane dane w pliku");
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Plik nie został otwarty");
        }

        return spotifyStatsList;
    }
    
    public void saveDataToFile(List<Model_Statystyki_Spotify> spotifystatslist, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Model_Statystyki_Spotify stats : spotifystatslist) {
                writer.write(stats.getAuthorName() + " " + stats.getAuthorLastName() + " " + stats.getTitle() + " " + stats.getContinent() + " " + stats.getPlaysCount() + " " + stats.getSongTime());
                writer.newLine(); // Nowa linia po każdym zapisie
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public List<Map.Entry<String, Integer>> mostPopularArtist(List<Model_Statystyki_Spotify> spotifystatslist) {
        List<Map.Entry<String, Integer>> popularArtists = new ArrayList<>();
        Map<String, Integer> artistPlaysCount = new HashMap<>();

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            String artist = stats.getAuthorName() + " " + stats.getAuthorLastName();
            int playsCounts = stats.getPlaysCount();

            artistPlaysCount.put(artist, artistPlaysCount.getOrDefault(artist, 0) + playsCounts);
        }

        int maxPlays = 0;
        for (Map.Entry<String, Integer> entry : artistPlaysCount.entrySet()) {
            if (entry.getValue() > maxPlays) {
                popularArtists.clear(); 
                maxPlays = entry.getValue();
            }

            if (entry.getValue() == maxPlays) {
                popularArtists.add(entry);
            }
        }

        return popularArtists;
    }
    
        public List<Map.Entry<String, Integer>> leastPopularArtist(List<Model_Statystyki_Spotify> spotifystatslist) {
        List<Map.Entry<String, Integer>> leastPopularArtists = new ArrayList<>();
        Map<String, Integer> artistPlaysCount = new HashMap<>();

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            String artist = stats.getAuthorName() + " " + stats.getAuthorLastName();
            int playsCounts = stats.getPlaysCount();

            artistPlaysCount.put(artist, artistPlaysCount.getOrDefault(artist, 0) + playsCounts);
        }

        int minPlays = Integer.MAX_VALUE; // Ustaw wartość początkową na maksymalną wartość int
        for (Map.Entry<String, Integer> entry : artistPlaysCount.entrySet()) {
            if (entry.getValue() < minPlays) {
                leastPopularArtists.clear(); // Wyczyść listę, jeśli znaleziono artystę z mniejszą ilością odsłuchań
                minPlays = entry.getValue();
            }

            if (entry.getValue() == minPlays) {
                leastPopularArtists.add(entry);
            }
        }

        return leastPopularArtists;
    }
        
    public List<Model_Statystyki_Spotify> mostPopularSong(List<Model_Statystyki_Spotify> spotifystatslist) {
        List<Model_Statystyki_Spotify> popularSongs = new ArrayList<>();
        int maxPlays = 0;

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            if (stats.getPlaysCount() > maxPlays) {
                maxPlays = stats.getPlaysCount();
            }
        }

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            if (stats.getPlaysCount() == maxPlays) {
                popularSongs.add(stats);
            }
        }

        return popularSongs;
    }
    
    public List<Model_Statystyki_Spotify> leastPopularSong(List<Model_Statystyki_Spotify> spotifystatslist) {
        List<Model_Statystyki_Spotify> leastPopularSongs = new ArrayList<>();
        int minPlays = Integer.MAX_VALUE;
        boolean isFirst = true;

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            if (isFirst) {
                minPlays = stats.getPlaysCount();
                isFirst = false;
            }

            if (stats.getPlaysCount() < minPlays) {
                minPlays = stats.getPlaysCount();
            }
        }

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            if (stats.getPlaysCount() == minPlays) {
                leastPopularSongs.add(stats);
            }
        }

        return leastPopularSongs;
    }
    
    public List<Model_Statystyki_Spotify> artistSorter(List<Model_Statystyki_Spotify> spotifystatslist) {
        List<Model_Statystyki_Spotify> sortedList = new ArrayList<>(spotifystatslist);

        Collections.sort(sortedList, new Comparator<Model_Statystyki_Spotify>() {
            @Override
            public int compare(Model_Statystyki_Spotify x, Model_Statystyki_Spotify y) {
                return Integer.compare(y.getPlaysCount(), x.getPlaysCount());
            }
        });

        return sortedList;
    }
    
    public int arithmeticMeanOfListens(List<Model_Statystyki_Spotify> spotifystatslist) {
        int sum = 0;

        for (Model_Statystyki_Spotify stats : spotifystatslist) {
            sum += stats.getPlaysCount();
        }

        if (spotifystatslist.isEmpty()) {
            return 0; // Zabezpieczenie przed dzieleniem przez zero
        }

        int value = sum / spotifystatslist.size();
        return value;
    }
    
    
    public double spearmanKorelation(List<Model_Statystyki_Spotify> spotifystatslist) {
    List<StringDoublePair> xRegion = new ArrayList<>();
    List<IntegerDoublePair> yPlaysCount = new ArrayList<>();
    double RangX = 0;
    double RangY = 1;
    String EURegName = "EU";
    String NARegName = "NA";
    String ASRegName = "AS";
    String AFRegName = "AF";
    int EUCount = 0;
    int NACount = 0;
    int ASCount = 0;
    int AFCount = 0;
    int count = 0;

    for (Model_Statystyki_Spotify stats : spotifystatslist) {
        xRegion.add(new StringDoublePair(stats.getContinent(), 0.0));
        yPlaysCount.add(new IntegerDoublePair(stats.getPlaysCount(), 0.0));
    }
     // Find value of duplicates of regions
    for (Model_Statystyki_Spotify stats : spotifystatslist) {
        if (EURegName.equals(stats.getContinent())) {
            EUCount++;
        }
    }
    for (Model_Statystyki_Spotify stats : spotifystatslist) {
        if (NARegName.equals(stats.getContinent())) {
            NACount++;
        }
    }
    for (Model_Statystyki_Spotify stats : spotifystatslist) {
        if (ASRegName.equals(stats.getContinent())) {
            ASCount++;
        }
    }
    for (Model_Statystyki_Spotify stats : spotifystatslist) {
        if (AFRegName.equals(stats.getContinent())) {
            AFCount++;
        }
    }

    // Write Ranks for regions

    // AF
    double sum = 0;
    int bufCount = 0;
    for (int i = 1; i <= AFCount; i++) {
        sum = sum + i;
    }
    RangX = sum / AFCount;
    for (int i = 0; i < xRegion.size(); i++) {
        if (AFRegName.equals(xRegion.get(i).getKey())) {
            xRegion.get(i).setValue(RangX);
        }
    }
    RangX = 0;
    sum = 0;
    bufCount = ASCount + AFCount;

    // AS
    for (int i = AFCount; i < bufCount; i++) {
        sum = sum + i;
    }
    RangX = sum / ASCount;
    for (int i = 0; i < xRegion.size(); i++) {
        if (ASRegName.equals(xRegion.get(i).getKey())) {
            xRegion.get(i).setValue(RangX);
        }
    }
    RangX = 0;
    sum = 0;

    // NA
    for (int i = bufCount; i < bufCount + NACount; i++) {
        sum = sum + i;
    }
    RangX = sum / NACount;
    for (int i = 0; i < xRegion.size(); i++) {
        if (NARegName.equals(xRegion.get(i).getKey())) {
            xRegion.get(i).setValue(RangX);
        }
    }
    RangX = 0;
    sum = 0;
    bufCount = bufCount + NACount;

    // EU
    for (int i = bufCount; i <= EUCount + bufCount; i++) {
        sum = sum + i;
    }
    RangX = sum / EUCount;
    for (int i = 0; i < xRegion.size(); i++) {
        if (EURegName.equals(xRegion.get(i).getKey())) {
            xRegion.get(i).setValue(RangX);
        }
    }

    // Write Ranks for playscount

    double sum2 = 0;
    double buff = 0;
    double buff2 = 0;

    for (int i = 0; i < yPlaysCount.size(); i++) {
        for (int j = 0; j < yPlaysCount.size() - 1; j++) {
            if (yPlaysCount.get(i).getKey() == yPlaysCount.get(j).getKey() + 1) {
                count++;
                buff = yPlaysCount.get(i).getKey();
            }
        }

        if (count == 0) {
            yPlaysCount.get(i).setValue(RangY);
            RangY++;
        } else {
            for (int k = (int) RangY; k <= RangY + count; k++) {
                sum2 = sum2 + k;
            }
            buff2 = RangY + count;
            RangY = sum2 / count;
            for (int j = 0; j < yPlaysCount.size(); j++) {
                if (yPlaysCount.get(j).getKey() == buff) {
                    yPlaysCount.get(j).setValue(RangY);
                }
            }
            RangY = buff2;
        }
        count = 0;
        sum2 = 0;
    }
     // Get Values of di^2
    List<Integer> di2 = new ArrayList<>();

    if (yPlaysCount.size() != xRegion.size()) {
        throw new RuntimeException("Arrays with parameters and ranks are of different lengths!");
    } else {
        for (int i = 0; i < yPlaysCount.size(); i++) {
            di2.add((int) Math.pow((xRegion.get(i).getValue() - yPlaysCount.get(i).getValue()), 2));
        }
    }
    // Get value of di^2
    double sumDi2 = 0;

    for (int i = 0; i < di2.size(); i++) {
        sumDi2 = sumDi2 + di2.get(i);
    }

    double ri = 0;

    double denominator = (yPlaysCount.size() * (Math.pow(yPlaysCount.size(), 2) - 1));
    ri = 1 - ((6 * sumDi2) / denominator);

    return ri;
}
    
    
    public void generateReport(List<Model_Statystyki_Spotify> spotifystatslist, String fl, String reg) throws IOException {
        try (BufferedWriter savefile = new BufferedWriter(new FileWriter(fl))) {
            savefile.write("Report from " + reg + "\n\n");

            List<Model_Statystyki_Spotify> v_mls = mostPopularSong(spotifystatslist);
            List<Model_Statystyki_Spotify> v_lps = leastPopularSong(spotifystatslist);
            List<Map.Entry<String, Integer>> v_buf = mostPopularArtist(spotifystatslist);
            List<Map.Entry<String, Integer>> v_buf2 = leastPopularArtist(spotifystatslist);
            double x = spearmanKorelation(spotifystatslist);
            List<Model_Statystyki_Spotify> list_buf = new ArrayList<>();

            if (!reg.equals("WorldWide")) {
                savefile.write("Author/s with the most listens: \n");
                for (Map.Entry<String, Integer> entry : v_buf) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nAuthor/s with least listens: \n");
                for (Map.Entry<String, Integer> entry : v_buf2) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nSong with most listens: \n");
                for (Model_Statystyki_Spotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens: \n");
                for (Model_Statystyki_Spotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nArithmetic mean of listens: " + arithmeticMeanOfListens(spotifystatslist) + "\n");
            } else {
                savefile.write("Author/s with the most listens: \n");
                for (Map.Entry<String, Integer> entry : v_buf) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nAuthor/s with least listens: \n");
                for (Map.Entry<String, Integer> entry : v_buf2) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nSong with most listens: \n");
                for (Model_Statystyki_Spotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens: \n");
                for (Model_Statystyki_Spotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                // EU
                savefile.write("Author/s with the most listens in EU: \n");
                for (Model_Statystyki_Spotify stats : spotifystatslist) {
                    if ("EU".equals(stats.getContinent())) {
                        list_buf.add(stats);
                    }
                }

                savefile.write("\n");
                v_buf = mostPopularArtist(list_buf);
                v_buf2 = leastPopularArtist(list_buf);
                v_mls = mostPopularSong(list_buf);
                v_lps = leastPopularSong(list_buf);

                for (Map.Entry<String, Integer> entry : v_buf) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nAuthor/s with least listens in EU: \n");
                for (Map.Entry<String, Integer> entry : v_buf2) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nSong with most listens in EU: \n");
                for (Model_Statystyki_Spotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in EU: \n");
                for (Model_Statystyki_Spotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                list_buf.clear();

                // NA
                savefile.write("Author/s with the most listens in NA: \n");
                for (Model_Statystyki_Spotify stats : spotifystatslist) {
                    if ("NA".equals(stats.getContinent())) {
                        list_buf.add(stats);
                    }
                }

                savefile.write("\n");
                v_buf = mostPopularArtist(list_buf);
                v_buf2 = leastPopularArtist(list_buf);
                v_mls = mostPopularSong(list_buf);
                v_lps = leastPopularSong(list_buf);

                for (Map.Entry<String, Integer> entry : v_buf) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nAuthor/s with least listens in NA: \n");
                for (Map.Entry<String, Integer> entry : v_buf2) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nSong with most listens in NA: \n");
                for (Model_Statystyki_Spotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in NA: \n");
                for (Model_Statystyki_Spotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                list_buf.clear();

                // AS
                savefile.write("Author/s with the most listens in AS: \n");
                for (Model_Statystyki_Spotify stats : spotifystatslist) {
                    if ("AS".equals(stats.getContinent())) {
                        list_buf.add(stats);
                    }
                }

                savefile.write("\n");
                v_buf = mostPopularArtist(list_buf);
                v_buf2 = leastPopularArtist(list_buf);
                v_mls = mostPopularSong(list_buf);
                v_lps = leastPopularSong(list_buf);

                for (Map.Entry<String, Integer> entry : v_buf) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nAuthor/s with least listens in AS: \n");
                for (Map.Entry<String, Integer> entry : v_buf2) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nSong with most listens in AS: \n");
                for (Model_Statystyki_Spotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in AS: \n");
                for (Model_Statystyki_Spotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                list_buf.clear();

                // AF
                savefile.write("Author/s with the most listens in AF: \n");
                for (Model_Statystyki_Spotify stats : spotifystatslist) {
                    if ("AF".equals(stats.getContinent())) {
                        list_buf.add(stats);
                    }
                }

                savefile.write("\n");
                v_buf = mostPopularArtist(list_buf);
                v_buf2 = leastPopularArtist(list_buf);
                v_mls = mostPopularSong(list_buf);
                v_lps = leastPopularSong(list_buf);

                for (Map.Entry<String, Integer> entry : v_buf) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nAuthor/s with least listens in AF: \n");
                for (Map.Entry<String, Integer> entry : v_buf2) {
                    savefile.write("Author: " + entry.getKey() + "  Number of listens: " + entry.getValue() + "\n");
                }

                savefile.write("\nSong with most listens in AF: \n");
                for (Model_Statystyki_Spotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in AF: \n");
                for (Model_Statystyki_Spotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\n");

                savefile.write("Arithmetic mean of listens: " + arithmeticMeanOfListens(spotifystatslist) + "\n");
                savefile.write("Spearman's rank correlation coefficient: " + x + "\n\n");
            }

            savefile.write("Sorted list:\n");
            for (Model_Statystyki_Spotify stats : artistSorter(spotifystatslist)) {
                savefile.write(stats.getAuthorName() + " " + stats.getAuthorLastName() + " " + stats.getTitle()
                        + " " + stats.getContinent() + " " + stats.getPlaysCount() + " " + stats.getSongTime() + "\n");
            }
        }
    }
    public List<Model_Statystyki_Spotify> setStatSpotify(String reg,List<Model_Statystyki_Spotify> list){
        
        List<Model_Statystyki_Spotify> substatspotifylist = new ArrayList<>();
        for (Model_Statystyki_Spotify stats : list) {
            if (stats.getContinent().trim().equals(reg.trim())) {
                substatspotifylist.add(stats);
            }
        }
        if(reg.trim().equals("WorldWide")){
            for (Model_Statystyki_Spotify stats : list) {
                 substatspotifylist.add(stats);
            }
        }

        return substatspotifylist;
    }
    
    
    
    public String getAuthorName() {
        /**
         * Metoda zwraca imie autora
         * @return authorName
         */
        return authorName;
    }  
    
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getPlaysCount() {
        return playsCount;
    }

    public void setPlaysCount(int playsCount) {
        this.playsCount = playsCount;
    }

    public double getSongTime() {
        return songTime;
    }

    public void setSongTime(double songTime) {
        this.songTime = songTime;
    }

}
