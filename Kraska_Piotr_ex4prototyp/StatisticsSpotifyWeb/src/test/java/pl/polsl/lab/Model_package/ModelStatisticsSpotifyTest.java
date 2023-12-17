/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.lab.Model_package;

import pl.polsl.lab.Modelpackage.ModelStatisticsSpotify;
import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Comparator;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;



/**
 *
 * @author Piotr
 * @version 2.0
 */
public class ModelStatisticsSpotifyTest {
    
    enum Region{
        EU, NA, AS, AF, WorldWide
    }
    public ModelStatisticsSpotifyTest() {
    }
    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
    
        /**
        * Test the behavior of the readFile method in the ModelStatisticsSpotify class.
        * This method reads Spotify statistics data from a specified file and compares it with expected results.
        *
        * @param filePath The file path from which Spotify statistics data should be read.
        * @throws FileNotFoundException If the specified file is not found.
        */
        @ParameterizedTest
        @ValueSource(strings = {
                "C://Users//Piotr//source//repos//Java_Projects//Kraska_Piotr_Statystyka_Spotify//testReadFile_filepath.txt",
                "E://test.txt",
                " "
        })
        public void testReadFile(String filePath) throws FileNotFoundException {
            //System.out.println("testReadFile is running...");
            ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
            ModelStatisticsSpotify suggestInstance = new ModelStatisticsSpotify();
            List<ModelStatisticsSpotify> expResult = new ArrayList<>();
            // Example values for testing...
            suggestInstance.setAuthorName("A");
            suggestInstance.setAuthorLastName("b");
            suggestInstance.setTitle("c");
            suggestInstance.setContinent("EU");
            suggestInstance.setPlaysCount(1);
            suggestInstance.setSongTime(1);
            expResult.add(suggestInstance);

            if (filePath == null || filePath.trim().isEmpty()) {
            assertThrows(FileNotFoundException.class, () -> instance.readFile(filePath), "For null or empty filePath, expect FileNotFoundException");
            } else if (!Files.exists(Paths.get(filePath.trim()))) {
            assertThrows(FileNotFoundException.class, () -> instance.readFile(filePath), "For non-existing file, expect FileNotFoundException");
            } else {
            List<ModelStatisticsSpotify> result = instance.readFile(filePath);
            assertEquals(expResult.size(), result.size(), "Expected and actual result size should be equal");
            for (int i = 0; i < expResult.size(); i++) {
                ModelStatisticsSpotify expectedModel = expResult.get(i);
                ModelStatisticsSpotify actualModel = result.get(i);

                // Compare individual attributes
                assertEquals(expectedModel.getAuthorName(), actualModel.getAuthorName(), "AuthorName should match");
                assertEquals(expectedModel.getAuthorLastName(), actualModel.getAuthorLastName(), "AuthorLastName should match");
                assertEquals(expectedModel.getTitle(), actualModel.getTitle(), "Title should match");
                assertEquals(expectedModel.getContinent(), actualModel.getContinent(), "Continent should match");
                assertEquals(expectedModel.getPlaysCount(), actualModel.getPlaysCount(), "PlaysCount should match");
                assertEquals(expectedModel.getSongTime(), actualModel.getSongTime(), "SongTime should match");
            }
        }
            //System.out.println("testReadFile ending process...");
    }


    /**
    * Test the behavior of the saveDataToFile method in the ModelStatisticsSpotify class.
    * This method saves Spotify statistics data to a specified file.
    *
    * @param filePath The file path where the Spotify statistics data should be saved.
    */
    @ParameterizedTest
    @ValueSource(strings = {
        "C://Users//Piotr//source//repos//Java_Projects//Kraska_Piotr_Statystyka_Spotify//testReadFile_filepath.txt", 
    })
    public void testSaveDataToFile(String filePath) {
        //System.out.println("testSaveDataToFile is running...");

        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        ModelStatisticsSpotify stats1 = new ModelStatisticsSpotify();
        stats1.setAuthorName("A");
        stats1.setAuthorLastName("b");
        stats1.setTitle("c");
        stats1.setContinent("EU");
        stats1.setPlaysCount(1);
        stats1.setSongTime(1.0);
        spotifystatslist.add(stats1);
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.saveDataToFile(spotifystatslist, filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            assertEquals("A b c EU 1 1.0", line, "Saved data in the file should match the expected line");
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while reading the file.");
        }

        //System.out.println("testSaveDataToFile ending process...");
    }

    /**
    * Test the behavior of the mostPopularArtist method in the ModelStatisticsSpotify class.
    * This method identifies the most popular artist based on the total number of plays for all songs.
    *
    * @param inputData The input data for the test, representing statistics for various songs by different artists.
    */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testMostPopularArtist(String inputData) {
        //System.out.println("testMostPopularArtist is running...");

        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            //System.out.println("Processing input: " + artistData);
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();

            String[] data = artistData.split(" ");
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
            
            }
            ModelStatisticsSpotify instance = new ModelStatisticsSpotify();

            // Manually create the expected result based on the input data
            List<Map.Entry<String, Integer>> expResult = new ArrayList<>();
            expResult.add(new AbstractMap.SimpleEntry<>("P" + " " + "Q",10));

            List<Map.Entry<String, Integer>> result = instance.mostPopularArtist(spotifystatslist);

            // Adjust the assertions based on the expected result
            assertEquals(expResult, result, "Most popular artist should match the expected result");


        //System.out.println("testMostPopularArtist ending process...");
    }

    /**
    * Test the behavior of the leastPopularArtist method in the ModelStatisticsSpotify class.
    * This method identifies the least popular artist based on the total number of plays for all songs.
    *
    * @param inputData The input data for the test, representing statistics for various songs by different artists.
    */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testLeastPopularArtist(String inputData) {
        //System.out.println("testLeastPopularArtist is running...");

        // Split the input data into individual artists
        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            // Process each artist data as a separate test case
            //System.out.println("Processing input: " + artistData);
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();

            String[] data = artistData.split(" ");
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
            
            }
            ModelStatisticsSpotify instance = new ModelStatisticsSpotify();

            // Manually create the expected result based on the input data
            List<Map.Entry<String, Integer>> expResult = new ArrayList<>();
            expResult.add(new AbstractMap.SimpleEntry<>("A" + " " + "b",1));

            List<Map.Entry<String, Integer>> result = instance.leastPopularArtist(spotifystatslist);

            // Adjust the assertions based on the expected result
            assertEquals(expResult, result,"Least popular artist should match the expected result");


        //System.out.println("testLeastPopularArtist ending process...");
    }

    /**
    * Test the behavior of the mostPopularSong method in the ModelStatisticsSpotify class.
    * This method identifies the most popular song based on the number of plays.
    *
    * @param inputData The input data for the test, representing statistics for various songs in different regions.
    */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testMostPopularSong(String inputData) {
        //System.out.println("testMostPopularSong is running...");

        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            //System.out.println("Processing input: " + artistData);
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();

            String[] data = artistData.split(" ");
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
            
            }
            ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
            List<Map.Entry<String, Integer>> expResult = new ArrayList<>();
            expResult.add(new AbstractMap.SimpleEntry<>("R" ,10));

            List<ModelStatisticsSpotify> temp = instance.mostPopularSong(spotifystatslist);
            List<Map.Entry<String, Integer>> result = new ArrayList<>();
            for (ModelStatisticsSpotify stats : temp) {
            String songName = stats.getTitle();
            int playsCount = stats.getPlaysCount();
            result.add(new AbstractMap.SimpleEntry<>(songName, playsCount));
            }

            assertEquals(expResult, result,"Most popular song should match the expected result");


        //System.out.println("testMostPopularSong ending process...");
    }

    /**
    * Test the behavior of the leastPopularSong method in the ModelStatisticsSpotify class.
    * This method identifies the least popular song based on the number of plays.
    *
    * @param inputData The input data for the test, representing statistics for various songs in different regions.
    */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testLeastPopularSong(String inputData) {
        //System.out.println("testLeastPopularSong is running...");

        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            //System.out.println("Processing input: " + artistData);
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();

            String[] data = artistData.split(" ");
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
            
            }
            ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
            List<Map.Entry<String, Integer>> expResult = new ArrayList<>();
            expResult.add(new AbstractMap.SimpleEntry<>("c" ,1));

            List<ModelStatisticsSpotify> temp = instance.leastPopularSong(spotifystatslist);
            List<Map.Entry<String, Integer>> result = new ArrayList<>();
            for (ModelStatisticsSpotify stats : temp) {
            String songName = stats.getTitle();
            int playsCount = stats.getPlaysCount();
            result.add(new AbstractMap.SimpleEntry<>(songName, playsCount));
            }

            assertEquals(expResult, result,"Least popular song should match the expected result");


        //System.out.println("testLeastPopularSong ending process...");
    }

    /**
    * Test the behavior of the artistSorter method in the ModelStatisticsSpotify class.
    * This method sorts a list of Spotify statistics based on the number of plays in descending order.
    *
    * @param inputData The input data for the test, representing statistics for various songs in different regions.
    */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testArtistSorter(String inputData) {
        //System.out.println("testArtistSorter is running...");

            // Split the input data into individual artists
            String[] artistDataList = inputData.split(", ");
            List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
            ModelStatisticsSpotify stats;
            for (String artistData : artistDataList) {
                // Process each artist data as a separate test case
                //System.out.println("Processing input: " + artistData);

                String[] data = artistData.split(" ");
                stats = new ModelStatisticsSpotify(); // Create a new instance for each artist
                stats.setAuthorName(data[0]);
                stats.setAuthorLastName(data[1]);
                stats.setTitle(data[2]);
                stats.setContinent(data[3]);
                stats.setPlaysCount(Integer.parseInt(data[4]));
                stats.setSongTime(Double.parseDouble(data[5]));

                spotifystatslist.add(stats);
            }

            ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
            List<ModelStatisticsSpotify> expResult = new ArrayList<>(spotifystatslist);
            expResult.sort(Comparator.comparingInt(ModelStatisticsSpotify::getPlaysCount).reversed());
            List<ModelStatisticsSpotify> result = instance.artistSorter(spotifystatslist);
            assertEquals(expResult, result,"Sorted exprected list should be the same as result sorted list");

            //System.out.println("testArtistSorter ending process...");
    }

   /**
    * Test the behavior of the arithmeticMeanOfListens method in the ModelStatisticsSpotify class.
    * This method calculates the arithmetic mean of the number of listens for a list of Spotify statistics.
    *
    * @param inputData The input data for the test, representing statistics for various songs in different regions.
    */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 3 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testArithmeticMeanOfListens(String inputData) {
    //System.out.println("testArithmeticMeanOfListens is running...");
        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();

        for (String artistData : artistDataList) {

            String[] data = artistData.split(" ");
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
        }

        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        int expResult = (3 + 5 + 10) / 3; 

        int result = instance.arithmeticMeanOfListens(spotifystatslist);
        assertEquals(expResult, result,"The arithmeticMeanOfListens expected value should be the same as result value ");

        //System.out.println("testArithmeticMeanOfListens ending process...");
    }

   /**
    * Test the behavior of the spearmanKorelation method in the ModelStatisticsSpotify class.
    * This method calculates the Spearman correlation coefficient based on input statistics for various songs and regions.
    *
    * @param inputData The input data for the test, representing statistics for various songs in different regions.
    */
    @ParameterizedTest
    @ValueSource(strings = {
    """
    A b c EU 3 1.0
    X Y Z NA 5 3.5
    P Q R AS 10 2.5
    Ed Sheeran ShapeOfYou NA 10000000 3.53
    Ed Sheeran ShapeOfYou EU 20000000 3.53
    Ed Sheeran ShapeOfYou AS 5000000 3.53
    Ed Sheeran ShapeOfYou AF 100000 3.53
    Don Omar DanzaKuduro NA 95000000 3.27
    Don Omar DanzaKuduro EU 35000000 3.27
    Don Omar DanzaKuduro AS 600000 3.27
    Don Omar DanzaKuduro AF 900000 3.27
    X Ambassadors Renegades EU 1500000 3.13
    X Ambassadors Renegades NA 2500000 3.13
    X Ambassadors Renegades AS 50000 3.13
    X Ambassadors Renegades AF 100000 3.13
    """
    })
    public void testSpearmanKorelation(String inputData) {
        //System.out.println("testSpearmanKorelation is running...");
        // Split the input data into individual artists
        String[] artistDataList = inputData.split("\n");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();

        for (String artistData : artistDataList) {
            //System.out.println("Processing input: " + artistData);
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();
            String[] data = artistData.split(" ");
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
        }

        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        double expResult = -0.6232142857142857; 
        double result = instance.spearmanKorelation(spotifystatslist);

        assertEquals(expResult, result, 0,"Expected Spearman Korelation value should be the same as the result value");

        //System.out.println("testSpearmanKorelation ending process...");
    }

    /**
    * Test the behavior of the generateReport method in the ModelStatisticsSpotify class.
    * This method tests the generation of a report based on input statistics for different songs in various regions.
    *
    * @param inputData The input data for the test, representing statistics for various songs in different regions.
    * @throws IOException If an I/O error occurs while generating the report.
    */
    @ParameterizedTest
    @ValueSource(strings = {
    """
    A b c EU 3 1.0
    X Y Z NA 5 3.5
    P Q R AS 10 2.5
    J D K AF 7 1.75
    Ed Sheeran ShapeOfYou NA 10000000 3.53
    Ed Sheeran ShapeOfYou EU 20000000 3.53
    Ed Sheeran ShapeOfYou AS 5000000 3.53
    Ed Sheeran ShapeOfYou AF 100000 3.53
    """
    })
    public void testGenerateReport(String inputData) throws IOException {
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        String[] artistDataList = inputData.split("\n");

        for (String artistData : artistDataList) {
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();
            String[] data = artistData.split(" ");
            stats.setAuthorName(data[0]);
            stats.setAuthorLastName(data[1]);
            stats.setTitle(data[2]);
            stats.setContinent(data[3]);
            stats.setPlaysCount(Integer.parseInt(data[4]));
            stats.setSongTime(Double.parseDouble(data[5]));

            spotifystatslist.add(stats);
        }
        // Define the executable block that contains the method call
        
        for (Region region : Region.values()) {
            String fl = "/invalid/path/test_report.txt";
            Executable executable = () -> {
                instance.generateReport(spotifystatslist,fl,region.name());
            };
            assertThrows(IOException.class, executable,"assert should throw IOExpection");
        }

    }

    /**
    * Test the behavior of the setStatSpotify method in the ModelStatisticsSpotify class.
    * This method is parameterized to test different regions (reg) and their corresponding input and expected results.
    *
    * @param reg      The region for which the setStatSpotify method is tested.
    * @param input    The input data for the test, representing statistics for various songs in different regions.
    * @param expected The expected result after applying the setStatSpotify method to the input data for the specified region.
    */
   @ParameterizedTest
    @CsvSource({
        // Test when reg is "EU"
        "'EU', 'A b c EU 3 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5', 'A b c EU 3 1.0'",
        
        // Test when reg is "NA"
        "'NA', 'A b c EU 3 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5', 'X Y Z NA 5 3.5'",
        
        // Test when reg is "WorldWide"
        "'WorldWide', 'A b c EU 3 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5', 'A b c EU 3 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5'"
    })
    public void testSetStatSpotify(String reg, String input, String expected) {
        List<ModelStatisticsSpotify> list = createStatsListFromString(input);
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<ModelStatisticsSpotify> expResult = createStatsListFromString(expected);
        List<ModelStatisticsSpotify> result = instance.setStatSpotify(reg, list);
        ModelStatisticsSpotify expectedStats = expResult.get(0);
        ModelStatisticsSpotify actualStats = result.get(0);

        // Compare individual properties
        assertEquals(expectedStats.getAuthorName(), actualStats.getAuthorName(),"Authors name are the same");
        assertEquals(expectedStats.getAuthorLastName(), actualStats.getAuthorLastName(),"Authors last name are the same");
        assertEquals(expectedStats.getTitle(), actualStats.getTitle(),"Titles are the same");
        assertEquals(expectedStats.getContinent(), actualStats.getContinent(),"Continents are the same");
        assertEquals(expectedStats.getPlaysCount(), actualStats.getPlaysCount(),"PlaysCount are the same");
        assertEquals(expectedStats.getSongTime(), actualStats.getSongTime(), 0.001,"songTimes are the same"); 
    }

    /*
        Helper method to create a list of ModelStatisticsSpotify from a string
    */
    private List<ModelStatisticsSpotify> createStatsListFromString(String input) {
        List<ModelStatisticsSpotify> statsList = new ArrayList<>();
        String[] statsData = input.split(", ");
        for (String data : statsData) {
            String[] values = data.split(" ");
            ModelStatisticsSpotify stats = new ModelStatisticsSpotify();
            stats.setAuthorName(values[0]);
            stats.setAuthorLastName(values[1]);
            stats.setTitle(values[2]);
            stats.setContinent(values[3]);
            stats.setPlaysCount(Integer.parseInt(values[4]));
            stats.setSongTime(Double.parseDouble(values[5]));
            statsList.add(stats);
        }
        return statsList;
    }

    
}
