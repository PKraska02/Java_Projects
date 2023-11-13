/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package pl.polsl.Model_package;

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
import java.util.HashSet;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

/**
 *
 * @author Piotr
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

    /**
     * Test of readFile method, of class ModelStatisticsSpotify.
     */
    //Change the URL
    @ParameterizedTest
    @ValueSource(strings = {"C://Users//Piotr//source//repos//Java_Projects//Kraska_Piotr_Statystyka_Spotify//testReadFile_filepath.txt", " "})
    public void testReadFile(String filePath) throws FileNotFoundException {
        System.out.println("testReadFile is running...");
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        ModelStatisticsSpotify suggestInstance = new ModelStatisticsSpotify();
        List<ModelStatisticsSpotify> expResult = new ArrayList<>();
        //Example values for testing...
        suggestInstance.setAuthorName("A");
        suggestInstance.setAuthorLastName("b");
        suggestInstance.setTitle("c");
        suggestInstance.setContinent("EU");
        suggestInstance.setPlaysCount(1);
        suggestInstance.setSongTime(1);
        expResult.add(suggestInstance);
        
        if (filePath == null || filePath.trim().isEmpty()) {
            assertThrows(FileNotFoundException.class, () -> {
                instance.readFile(filePath);
                fail("FileNotFoundException!");
            });
        } else {
            List<ModelStatisticsSpotify> result = instance.readFile(filePath);
            assertEquals(expResult.size(), result.size());
            for (int i = 0; i < expResult.size(); i++) {
                System.out.println(expResult.get(i));
                System.out.println(result.get(i));
                assertEquals(expResult.get(i), result.get(i));
            }
        }
        System.out.println("testReadFile ending process...");

    }

    /**
     * Test of saveDataToFile method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSaveDataToFile() {
        System.out.println("saveDataToFile");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        String filePath = "";
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        instance.saveDataToFile(spotifystatslist, filePath);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mostPopularArtist method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testMostPopularArtist() {
        System.out.println("mostPopularArtist");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<Map.Entry<String, Integer>> expResult = null;
        List<Map.Entry<String, Integer>> result = instance.mostPopularArtist(spotifystatslist);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leastPopularArtist method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testLeastPopularArtist() {
        System.out.println("leastPopularArtist");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<Map.Entry<String, Integer>> expResult = null;
        List<Map.Entry<String, Integer>> result = instance.leastPopularArtist(spotifystatslist);
        assertEquals(expResult, result);
    }

    /**
     * Test of mostPopularSong method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testMostPopularSong() {
        System.out.println("mostPopularSong");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<ModelStatisticsSpotify> expResult = null;
        List<ModelStatisticsSpotify> result = instance.mostPopularSong(spotifystatslist);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leastPopularSong method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testLeastPopularSong() {
        System.out.println("leastPopularSong");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<ModelStatisticsSpotify> expResult = null;
        List<ModelStatisticsSpotify> result = instance.leastPopularSong(spotifystatslist);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of artistSorter method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testArtistSorter() {
        System.out.println("artistSorter");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        List<ModelStatisticsSpotify> expResult = null;
        List<ModelStatisticsSpotify> result = instance.artistSorter(spotifystatslist);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of arithmeticMeanOfListens method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testArithmeticMeanOfListens() {
        System.out.println("arithmeticMeanOfListens");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        int expResult = 0;
        int result = instance.arithmeticMeanOfListens(spotifystatslist);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of spearmanKorelation method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testSpearmanKorelation() {
        System.out.println("spearmanKorelation");
        List<ModelStatisticsSpotify> spotifystatslist = null;
        ModelStatisticsSpotify instance = new ModelStatisticsSpotify();
        double expResult = 0.0;
        double result = instance.spearmanKorelation(spotifystatslist);
        assertEquals(expResult, result, 0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateReport method, of class ModelStatisticsSpotify.
     */
    @Test
    public void testGenerateReport() throws Exception {
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

