package com.example.textprocessing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

class DataCollectionIntegrationTest {

    private DataCollection arrayListCollection;
    private DataCollection setCollection;
    private DataCollection mapCollection;

    private static final String TEST_ARRAYLIST_FILE_NAME = "arrayList.txt";
    private static final String TEST_SET_FILE_NAME = "set.txt";
    private static final String TEST_MAP_FILE_NAME = "map.txt";

    @BeforeEach
    void setUp() {
        arrayListCollection = new DataCollection(DataCollection.CollectionType.ARRAYLIST);
        setCollection = new DataCollection(DataCollection.CollectionType.SET);
        mapCollection = new DataCollection(DataCollection.CollectionType.MAP);
    }

    @AfterEach
    void tearDown() {
        deleteTestFile(TEST_ARRAYLIST_FILE_NAME);
        deleteTestFile(TEST_SET_FILE_NAME);
        deleteTestFile(TEST_MAP_FILE_NAME);
    }

    private void deleteTestFile(String fileName) {
        File file = new File(fileName);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testAddItemToArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        assertEquals(1, arrayListCollection.size());
        assertTrue(arrayListCollection.toString().contains("item1"));
        assertTrue(fileContainsContent(TEST_ARRAYLIST_FILE_NAME, "[item1]"));
        assertNotEquals(0, arrayListCollection.size());
        assertFalse(arrayListCollection.toString().contains("item2"));
        assertFalse(fileContainsContent(TEST_ARRAYLIST_FILE_NAME, "[item2]"));
    }

    @Test
    void testAddItemToSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.addItem("item1");
        assertEquals(1, setCollection.size());
        assertTrue(setCollection.toString().contains("item1"));
        assertTrue(fileContainsContent(TEST_SET_FILE_NAME, "[item1]"));
        assertNotEquals(0, setCollection.size());
        assertFalse(setCollection.toString().contains("item2"));
        assertFalse(fileContainsContent(TEST_SET_FILE_NAME, "[item2]"));
    }

    @Test
    void testAddItemToMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        assertEquals(1, mapCollection.size());
        assertTrue(mapCollection.toString().contains("key1=value1"));
        assertTrue(fileContainsContent(TEST_MAP_FILE_NAME, "{key1=value1}"));
        assertNotEquals(0, mapCollection.size());
        assertFalse(mapCollection.toString().contains("key2=value2"));
        assertFalse(fileContainsContent(TEST_MAP_FILE_NAME, "{key2=value2}"));
    }

    @Test
    void testUpdateItemInArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        arrayListCollection.updateItem(0, "updatedItem");
        assertTrue(arrayListCollection.toString().contains("updatedItem"));
        assertTrue(fileContainsContent(TEST_ARRAYLIST_FILE_NAME, "[updatedItem]"));
        assertNotEquals("item1", arrayListCollection.toString());
        assertFalse(arrayListCollection.toString().contains("item1"));
        assertFalse(fileContainsContent(TEST_ARRAYLIST_FILE_NAME, "[item1]"));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayListCollection.updateItem(1, "newItem"));
    }

    @Test
    void testUpdateItemInSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.updateItem(0, "updatedItem");
        assertTrue(setCollection.toString().contains("updatedItem"));
        assertTrue(fileContainsContent(TEST_SET_FILE_NAME, "[updatedItem]"));
        assertThrows(IndexOutOfBoundsException.class, () -> setCollection.updateItem(1, "newItem"));
    }

    @Test
    void testUpdateItemInMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        mapCollection.updateItem("key1", "updatedValue");
        assertTrue(mapCollection.toString().contains("key1=updatedValue"));
        assertTrue(fileContainsContent(TEST_MAP_FILE_NAME, "{key1=updatedValue}"));
        assertNotEquals("value1", mapCollection.getMap().get("key1"));
        assertFalse(mapCollection.toString().contains("key1=value1"));
        assertFalse(fileContainsContent(TEST_MAP_FILE_NAME, "{key1=value1}"));
    }

    @Test
    void testRemoveItemFromArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        arrayListCollection.removeItem(0);
        assertEquals(0, arrayListCollection.size());
        assertTrue(fileContainsContent(TEST_ARRAYLIST_FILE_NAME, "[]"));
        assertNotEquals(1, arrayListCollection.size());
        assertFalse(arrayListCollection.toString().contains("item1"));
        assertFalse(fileContainsContent(TEST_ARRAYLIST_FILE_NAME, "[item1]"));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayListCollection.removeItem(1));
    }

    @Test
    void testRemoveItemFromSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.removeItem(0);
        assertEquals(0, setCollection.size());
        assertTrue(fileContainsContent(TEST_SET_FILE_NAME, "[]"));
        assertNotEquals(1, setCollection.size());
        assertFalse(setCollection.toString().contains("item1"));
        assertFalse(fileContainsContent(TEST_SET_FILE_NAME, "[item1]"));
        assertThrows(IndexOutOfBoundsException.class, () -> setCollection.removeItem(1));
    }

    @Test
    void testRemoveItemFromMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        mapCollection.removeItem("key1");
        assertEquals(0, mapCollection.size());
        assertTrue(fileContainsContent(TEST_MAP_FILE_NAME, "{}"));
        assertNotEquals(1, mapCollection.size());
        assertFalse(mapCollection.toString().contains("key1=value1"));
        assertFalse(fileContainsContent(TEST_MAP_FILE_NAME, "{key1=value1}"));
    }

    private boolean fileContainsContent(String fileName, String expectedContent) throws IOException {
        Path path = Paths.get(fileName);
        String content = Files.readString(path);
        return content.contains(expectedContent);
    }
}
