/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.Model_package;

import java.io.BufferedReader;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Comparator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


/**
 *
 * @author Piotr
 * @version 1.0
 */
public class ModelStatisticsSpotifyTest {
    
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
    

        @ParameterizedTest
        @ValueSource(strings = {
                "C://Users//Piotr//source//repos//Java_Projects//Kraska_Piotr_Statystyka_Spotify//testReadFile_filepath.txt",
                "E://test.txt",
                " "
        })
        public void testReadFile(String filePath) throws FileNotFoundException {
            System.out.println("testReadFile is running...");
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
                assertThrows(FileNotFoundException.class, () -> instance.readFile(filePath));
            } else if (!Files.exists(Paths.get(filePath.trim()))) {
                assertThrows(FileNotFoundException.class, () -> {
                instance.readFile(filePath);
                });       
            } else {
                List<ModelStatisticsSpotify> result = instance.readFile(filePath);
                assertEquals(expResult.size(), result.size());
                for (int i = 0; i < expResult.size(); i++) {
                    ModelStatisticsSpotify expectedModel = expResult.get(i);
                    ModelStatisticsSpotify actualModel = result.get(i);

                    // Compare individual attributes
                    assertEquals(expectedModel.getAuthorName(), actualModel.getAuthorName());
                    assertEquals(expectedModel.getAuthorLastName(), actualModel.getAuthorLastName());
                    assertEquals(expectedModel.getTitle(), actualModel.getTitle());
                    assertEquals(expectedModel.getContinent(), actualModel.getContinent());
                    assertEquals(expectedModel.getPlaysCount(), actualModel.getPlaysCount());
                    assertEquals(expectedModel.getSongTime(), actualModel.getSongTime());
                }
            }
            System.out.println("testReadFile ending process...");
        }


    /**
     * Test of saveDataToFile method, of class ModelStatisticsSpotify.
     * @param filePath
     */
    @ParameterizedTest
    @ValueSource(strings = {
        "C://Users//Piotr//source//repos//Java_Projects//Kraska_Piotr_Statystyka_Spotify//testReadFile_filepath.txt", 
    })
    public void testSaveDataToFile(String filePath) {
        System.out.println("testSaveDataToFile is running...");

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
            assertEquals("A b c EU 1 1.0", line); // Adjust this based on your actual file format
        } catch (IOException e) {
            e.printStackTrace();
            fail("IOException occurred while reading the file.");
        }

        System.out.println("testSaveDataToFile ending process...");
    }

    /**
     * Test of mostPopularArtist method, of class ModelStatisticsSpotify.
     * @param inputData
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testMostPopularArtist(String inputData) {
        System.out.println("testMostPopularArtist is running...");

        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            System.out.println("Processing input: " + artistData);
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
            assertEquals(expResult, result);


        System.out.println("testMostPopularArtist ending process...");
    }

    /**
     * Test of leastPopularArtist method, of class ModelStatisticsSpotify.
     * @param inputData
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testLeastPopularArtist(String inputData) {
        System.out.println("testLeastPopularArtist is running...");

        // Split the input data into individual artists
        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            // Process each artist data as a separate test case
            System.out.println("Processing input: " + artistData);
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
            assertEquals(expResult, result);


        System.out.println("testLeastPopularArtist ending process...");
    }

    /**
     * Test of mostPopularSong method, of class ModelStatisticsSpotify.
     * @param inputData
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testMostPopularSong(String inputData) {
        System.out.println("testMostPopularSong is running...");

        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            System.out.println("Processing input: " + artistData);
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

            assertEquals(expResult, result);


        System.out.println("testMostPopularSong ending process...");
    }

    /**
     * Test of leastPopularSong method, of class ModelStatisticsSpotify.
     * @param inputData
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testLeastPopularSong(String inputData) {
        System.out.println("testLeastPopularSong is running...");

        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
        for (String artistData : artistDataList) {
            System.out.println("Processing input: " + artistData);
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

            assertEquals(expResult, result);


        System.out.println("testLeastPopularSong ending process...");
    }

    /**
     * Test of artistSorter method, of class ModelStatisticsSpotify.
     * @param inputData
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 1 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testArtistSorter(String inputData) {
        System.out.println("testArtistSorter is running...");

            // Split the input data into individual artists
            String[] artistDataList = inputData.split(", ");
            List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();
            ModelStatisticsSpotify stats;
            for (String artistData : artistDataList) {
                // Process each artist data as a separate test case
                System.out.println("Processing input: " + artistData);

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
            assertEquals(expResult, result);

            System.out.println("testArtistSorter ending process...");
    }

    /**
     * Test of arithmeticMeanOfListens method, of class ModelStatisticsSpotify.
     * @param inputData
     */
    @ParameterizedTest
    @ValueSource(strings = {
            "A b c EU 3 1.0, X Y Z NA 5 3.5, P Q R AS 10 2.5"
    })
    public void testArithmeticMeanOfListens(String inputData) {
    System.out.println("testArithmeticMeanOfListens is running...");

        // Split the input data into individual artists
        String[] artistDataList = inputData.split(", ");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();

        for (String artistData : artistDataList) {
            // Process each artist data as a separate test case
            System.out.println("Processing input: " + artistData);

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

        // Manually calculate the expected result based on the input data
        int expResult = (3 + 5 + 10) / 3; // Expected mean of listens

        int result = instance.arithmeticMeanOfListens(spotifystatslist);

        // Adjust the assertions based on the expected result
        assertEquals(expResult, result);

        System.out.println("testArithmeticMeanOfListens ending process...");
    }

    /**
     * Test of spearmanKorelation method, of class ModelStatisticsSpotify.
     * @param inputData
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
        System.out.println("testSpearmanKorelation is running...");
        // Split the input data into individual artists
        String[] artistDataList = inputData.split("\n");
        List<ModelStatisticsSpotify> spotifystatslist = new ArrayList<>();

        for (String artistData : artistDataList) {
            System.out.println("Processing input: " + artistData);
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

        assertEquals(expResult, result, 0);

        System.out.println("testSpearmanKorelation ending process...");
    }
    //Ended part 1 of testmethod

    /**
     * Test of generateReport method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGenerateReport() throws IOException {
        System.out.println("generateReport");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        String fl = "";
        String reg = "";
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.generateReport(spotifystatslist, fl, reg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatSpotify method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetStatSpotify() {
        System.out.println("setStatSpotify");
        String reg = "";
        List<ModelStatisticsSpotify> list = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<ModelStatisticsSpotify> expResult = null;
        List<ModelStatisticsSpotify> result = instance.setStatSpotify(reg, list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthorName method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGetAuthorName() {
        System.out.println("getAuthorName");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        String expResult = "";
        String result = instance.getAuthorName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthorName method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetAuthorName() {
        System.out.println("setAuthorName");
        String authorName = "";
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.setAuthorName(authorName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAuthorLastName method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGetAuthorLastName() {
        System.out.println("getAuthorLastName");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        String expResult = "";
        String result = instance.getAuthorLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAuthorLastName method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetAuthorLastName() {
        System.out.println("setAuthorLastName");
        String authorLastName = "";
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.setAuthorLastName(authorLastName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTitle method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGetTitle() {
        System.out.println("getTitle");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        String expResult = "";
        String result = instance.getTitle();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTitle method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetTitle() {
        System.out.println("setTitle");
        String title = "";
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.setTitle(title);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContinent method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGetContinent() {
        System.out.println("getContinent");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        String expResult = "";
        String result = instance.getContinent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContinent method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetContinent() {
        System.out.println("setContinent");
        String continent = "";
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.setContinent(continent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPlaysCount method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGetPlaysCount() {
        System.out.println("getPlaysCount");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        int expResult = 0;
        int result = instance.getPlaysCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPlaysCount method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetPlaysCount() {
        System.out.println("setPlaysCount");
        int playsCount = 0;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.setPlaysCount(playsCount);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSongTime method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGetSongTime() {
        System.out.println("getSongTime");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        double expResult = 0.0;
        double result = instance.getSongTime();
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSongTime method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSetSongTime() {
        System.out.println("setSongTime");
        double songTime = 0.0;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.setSongTime(songTime);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
        
    }   
}

