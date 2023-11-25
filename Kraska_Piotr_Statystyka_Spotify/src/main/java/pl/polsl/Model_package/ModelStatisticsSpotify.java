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
 * This class represents the data model for Spotify statistics.
 * It contains methods to read and process statistics data.
 * @author Piotr
 * @version 1.0
 */
public class ModelStatisticsSpotify {
    /**
     * The name of the author.
     */
    private String authorName;

    /**
     * The last name of the author.
     */
    private String authorLastName;
    /**
     * The title of the song.
     */
    private String title;
    /**
     * The continent where the author has their listeners.
     */
    private String continent;
    /**
     * Value of listens 
     * @param playsCount
     */
    private int playsCount = 0;
    /**
     * The number of listens for the song.
     */
    private double songTime = 0;
    /**
    * Reads the contents of a file and populates a List of Spotify statistics.
    *
    * This method reads the contents of a file specified by the filePath and populates a List with Spotify statistics.
    *
    * @param filePath The path to the file from which data will be read.
    * @return A List of Spotify statistics (ModelStatisticsSpotify).
    * @throws FileNotFoundException If the specified file is not found.
    */
    public List<ModelStatisticsSpotify> readFile(String filePath) throws FileNotFoundException {
        List<ModelStatisticsSpotify> spotifyStatsList = new ArrayList<>();

        try  {
            Scanner scanner = new Scanner (new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(" "); 

                if (data.length == 6) {
                    ModelStatisticsSpotify spotifyStats = new ModelStatisticsSpotify();
                    spotifyStats.setAuthorName(data[0]);
                    spotifyStats.setAuthorLastName(data[1]);
                    spotifyStats.setTitle(data[2]);
                    spotifyStats.setContinent(data[3]);
                    spotifyStats.setPlaysCount(Integer.parseInt(data[4]));
                    spotifyStats.setSongTime(Double.parseDouble(data[5]));
                    spotifyStatsList.add(spotifyStats);
                } else {
                    throw new FileNotFoundException("Bledny szablon lub zle zapisane dane w pliku");
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Plik nie został otwarty");
        }

        return spotifyStatsList;
    }
    /**
     * Saves data to a specified file path.
     *
     * This method takes a list of Spotify statistics and saves the data to a file located at the given file path.
     *
     * @param spotifystatslist A list of Spotify statistics to be saved.
     * @param filePath The path where the data will be stored.
     */
    public void saveDataToFile(List<ModelStatisticsSpotify> spotifystatslist, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (ModelStatisticsSpotify stats : spotifystatslist) {
                writer.write(stats.getAuthorName() + " " + stats.getAuthorLastName() + " " + stats.getTitle() + " " + stats.getContinent() + " " + stats.getPlaysCount() + " " + stats.getSongTime());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Finds the most popular artist(s) based on Spotify statistics.
     *
     * This method analyzes a list of Spotify statistics and identifies the artist(s) with the most plays.
     *
     * @param spotifystatslist A list of Spotify statistics to analyze.
     * @return A list of `Map.Entry` containing the artist's name (as a string) and their total plays count.
     */
    public List<Map.Entry<String, Integer>> mostPopularArtist(List<ModelStatisticsSpotify> spotifystatslist) {
        List<Map.Entry<String, Integer>> popularArtists = new ArrayList<>();
        Map<String, Integer> artistPlaysCount = new HashMap<>();

        for (ModelStatisticsSpotify stats : spotifystatslist) {
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
        /**
        * Finds the least popular artist(s) based on Spotify statistics.
        *
        * This method analyzes a list of Spotify statistics and identifies the artist(s) with the fewest plays.
        *
        * @param spotifystatslist A list of Spotify statistics to analyze.
        * @return A list of `Map.Entry` containing the artist's name (as a string) and their total plays count.
        */
        public List<Map.Entry<String, Integer>> leastPopularArtist(List<ModelStatisticsSpotify> spotifystatslist) {
        List<Map.Entry<String, Integer>> leastPopularArtists = new ArrayList<>();
        Map<String, Integer> artistPlaysCount = new HashMap<>();

        for (ModelStatisticsSpotify stats : spotifystatslist) {
            String artist = stats.getAuthorName() + " " + stats.getAuthorLastName();
            int playsCounts = stats.getPlaysCount();

            artistPlaysCount.put(artist, artistPlaysCount.getOrDefault(artist, 0) + playsCounts);
        }

        int minPlays = Integer.MAX_VALUE; 
        for (Map.Entry<String, Integer> entry : artistPlaysCount.entrySet()) {
            if (entry.getValue() < minPlays) {
                leastPopularArtists.clear(); 
                minPlays = entry.getValue();
            }

            if (entry.getValue() == minPlays) {
                leastPopularArtists.add(entry);
            }
        }

        return leastPopularArtists;
    }
     /**
     * Finds the most popular song(s) from a list of Spotify statistics.
     *
     * This method analyzes a list of Spotify statistics and identifies the song(s) with the highest number of plays.
     *
     * @param spotifystatslist A list of Spotify statistics to analyze.
     * @return A list of Spotify statistics representing the most popular song(s).
     */
    public List<ModelStatisticsSpotify> mostPopularSong(List<ModelStatisticsSpotify> spotifystatslist) {
        List<ModelStatisticsSpotify> popularSongs = new ArrayList<>();
        int maxPlays = 0;

        for (ModelStatisticsSpotify stats : spotifystatslist) {
            if (stats.getPlaysCount() > maxPlays) {
                maxPlays = stats.getPlaysCount();
            }
        }

        for (ModelStatisticsSpotify stats : spotifystatslist) {
            if (stats.getPlaysCount() == maxPlays) {
                popularSongs.add(stats);
            }
        }

        return popularSongs;
    }
    /**
    * This method finds the least popular songs based on the number of plays.
    *
    * @param spotifystatslist A list of Spotify statistics for songs.
    * @return A list of the least popular songs.
    */
    public List<ModelStatisticsSpotify> leastPopularSong(List<ModelStatisticsSpotify> spotifystatslist) {
        List<ModelStatisticsSpotify> leastPopularSongs = new ArrayList<>();
        int minPlays = Integer.MAX_VALUE;
        boolean isFirst = true;

        for (ModelStatisticsSpotify stats : spotifystatslist) {
            if (isFirst) {
                minPlays = stats.getPlaysCount();
                isFirst = false;
            }

            if (stats.getPlaysCount() < minPlays) {
                minPlays = stats.getPlaysCount();
            }
        }

        for (ModelStatisticsSpotify stats : spotifystatslist) {
            if (stats.getPlaysCount() == minPlays) {
                leastPopularSongs.add(stats);
            }
        }

        return leastPopularSongs;
    }
    /**
    * Sorts a list of Spotify statistics based on the number of plays in descending order.
    *
    * @param spotifystatslist A list of Spotify statistics for songs.
    * @return A sorted list of Spotify statistics with the most-played songs first.
    */
    public List<ModelStatisticsSpotify> artistSorter(List<ModelStatisticsSpotify> spotifystatslist) {
        List<ModelStatisticsSpotify> sortedList = new ArrayList<>(spotifystatslist);

        Collections.sort(sortedList, new Comparator<ModelStatisticsSpotify>() {
            @Override
            public int compare(ModelStatisticsSpotify x, ModelStatisticsSpotify y) {
                return Integer.compare(y.getPlaysCount(), x.getPlaysCount());
            }
        });

        return sortedList;
    }
    /**
    * Calculates the arithmetic mean (average) of the number of listens in a list of Spotify statistics.
    *
    * @param spotifystatslist A list of Spotify statistics for songs.
    * @return The arithmetic mean of the number of listens in the provided list.
    */
    public int arithmeticMeanOfListens(List<ModelStatisticsSpotify> spotifystatslist) {
        int sum = 0;

        for (ModelStatisticsSpotify stats : spotifystatslist) {
            sum += stats.getPlaysCount();
        }

        if (spotifystatslist.isEmpty()) {
            return 0; 
        }

        int value = sum / spotifystatslist.size();
        return value;
    }
    
    /**
    * Calculates the Spearman's rank correlation coefficient between the regions and the number of plays for songs.
    *
    * @param spotifystatslist A list of Spotify statistics for songs.
    * @return The Spearman's rank correlation coefficient.
    */
    public double spearmanKorelation(List<ModelStatisticsSpotify> spotifystatslist) {
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

    for (ModelStatisticsSpotify stats : spotifystatslist) {
        xRegion.add(new StringDoublePair(stats.getContinent(), 0.0));
        yPlaysCount.add(new IntegerDoublePair(stats.getPlaysCount(), 0.0));
    }
     // Find value of duplicates of regions
    for (ModelStatisticsSpotify stats : spotifystatslist) {
        if (EURegName.equals(stats.getContinent())) {
            EUCount++;
        }
    }
    for (ModelStatisticsSpotify stats : spotifystatslist) {
        if (NARegName.equals(stats.getContinent())) {
            NACount++;
        }
    }
    for (ModelStatisticsSpotify stats : spotifystatslist) {
        if (ASRegName.equals(stats.getContinent())) {
            ASCount++;
        }
    }
    for (ModelStatisticsSpotify stats : spotifystatslist) {
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
    
    /**
    * Generates a report of Spotify statistics and saves it to a file.
    *
    * @param spotifystatslist A list of Spotify statistics for songs.
    * @param fl The file path where the report will be saved.
    * @param reg The region for which the report is generated.
    * @throws IOException If an error occurs while writing the report to the file.
    */
    public void generateReport(List<ModelStatisticsSpotify> spotifystatslist, String fl, String reg) throws IOException {
        try (BufferedWriter savefile = new BufferedWriter(new FileWriter(fl))) {
            savefile.write("Report from " + reg + "\n\n");

            List<ModelStatisticsSpotify> v_mls = mostPopularSong(spotifystatslist);
            List<ModelStatisticsSpotify> v_lps = leastPopularSong(spotifystatslist);
            List<Map.Entry<String, Integer>> v_buf = mostPopularArtist(spotifystatslist);
            List<Map.Entry<String, Integer>> v_buf2 = leastPopularArtist(spotifystatslist);
            double x = spearmanKorelation(spotifystatslist);
            List<ModelStatisticsSpotify> list_buf = new ArrayList<>();

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
                for (ModelStatisticsSpotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens: \n");
                for (ModelStatisticsSpotify stats : v_lps) {
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
                for (ModelStatisticsSpotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens: \n");
                for (ModelStatisticsSpotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                // EU
                savefile.write("Author/s with the most listens in EU: \n");
                for (ModelStatisticsSpotify stats : spotifystatslist) {
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
                for (ModelStatisticsSpotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in EU: \n");
                for (ModelStatisticsSpotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                list_buf.clear();

                // NA
                savefile.write("Author/s with the most listens in NA: \n");
                for (ModelStatisticsSpotify stats : spotifystatslist) {
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
                for (ModelStatisticsSpotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in NA: \n");
                for (ModelStatisticsSpotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                list_buf.clear();

                // AS
                savefile.write("Author/s with the most listens in AS: \n");
                for (ModelStatisticsSpotify stats : spotifystatslist) {
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
                for (ModelStatisticsSpotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in AS: \n");
                for (ModelStatisticsSpotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                list_buf.clear();

                // AF
                savefile.write("Author/s with the most listens in AF: \n");
                for (ModelStatisticsSpotify stats : spotifystatslist) {
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
                for (ModelStatisticsSpotify stats : v_mls) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\nSong with least listens in AF: \n");
                for (ModelStatisticsSpotify stats : v_lps) {
                    savefile.write("Author: " + stats.getAuthorName() + " " + stats.getAuthorLastName()
                            + "  Number of listens: " + stats.getPlaysCount() + " Region: " + stats.getContinent() + "\n");
                }

                savefile.write("\n");

                savefile.write("Arithmetic mean of listens: " + arithmeticMeanOfListens(spotifystatslist) + "\n");
                savefile.write("Spearman's rank correlation coefficient: " + x + "\n\n");
            }

            savefile.write("Sorted list:\n");
            for (ModelStatisticsSpotify stats : artistSorter(spotifystatslist)) {
                savefile.write(stats.getAuthorName() + " " + stats.getAuthorLastName() + " " + stats.getTitle()
                        + " " + stats.getContinent() + " " + stats.getPlaysCount() + " " + stats.getSongTime() + "\n");
            }
        }
    }
    /**
    * Filters the list of Spotify statistics based on a specified region.
    *
    * @param reg The region to filter by. Pass "WorldWide" to include all regions.
    * @param list The list of Spotify statistics to filter.
    * @return A list of Spotify statistics that belong to the specified region or the entire list if "WorldWide" is selected.
    */
    public List<ModelStatisticsSpotify> setStatSpotify(String reg,List<ModelStatisticsSpotify> list){
        
        List<ModelStatisticsSpotify> substatspotifylist = new ArrayList<>();
        for (ModelStatisticsSpotify stats : list) {
            if (stats.getContinent().trim().equals(reg.trim())) {
                substatspotifylist.add(stats);
            }
        }
        if(reg.trim().equals("WorldWide")){
            for (ModelStatisticsSpotify stats : list) {
                 substatspotifylist.add(stats);
            }
        }

        return substatspotifylist;
    }
    
    
    
    /**
 * Get the author's first name.
 *
 * @return The author's first name.
 */
public String getAuthorName() {
    return authorName;
}

/**
 * Set the author's first name.
 *
 * @param authorName The author's first name to set.
 */
public void setAuthorName(String authorName) {
    this.authorName = authorName;
}

/**
 * Get the author's last name.
 *
 * @return The author's last name.
 */
public String getAuthorLastName() {
    return authorLastName;
}

/**
 * Set the author's last name.
 *
 * @param authorLastName The author's last name to set.
 */
public void setAuthorLastName(String authorLastName) {
    this.authorLastName = authorLastName;
}

/**
 * Get the title of the song.
 *
 * @return The title of the song.
 */
public String getTitle() {
    return title;
}

/**
 * Set the title of the song.
 *
 * @param title The title of the song to set.
 */
public void setTitle(String title) {
    this.title = title;
}

/**
 * Get the continent where the author has their listeners.
 *
 * @return The continent.
 */
public String getContinent() {
    return continent;
}

/**
 * Set the continent where the author has their listeners.
 *
 * @param continent The continent to set.
 */
public void setContinent(String continent) {
    this.continent = continent;
}

/**
 * Get the number of plays for the song.
 *
 * @return The number of plays.
 */
public int getPlaysCount() {
    return playsCount;
}

/**
 * Set the number of plays for the song.
 *
 * @param playsCount The number of plays to set.
 */
public void setPlaysCount(int playsCount) {
    this.playsCount = playsCount;
}

/**
 * Get the duration of the song in minutes.
 *
 * @return The duration of the song.
 */
public double getSongTime() {
    return songTime;
}

/**
 * Set the duration of the song in minutes.
 *
 * @param songTime The duration of the song to set.
 */
public void setSongTime(double songTime) {
    this.songTime = songTime;
}

}
