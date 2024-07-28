package com.example.textprocessing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DataCollectionTest {
    private DataCollection arrayListCollection;
    private DataCollection setCollection;
    private DataCollection mapCollection;

    @BeforeEach
    void setUp() {
        arrayListCollection = new DataCollection(DataCollection.CollectionType.ARRAYLIST);
        setCollection = new DataCollection(DataCollection.CollectionType.SET);
        mapCollection = new DataCollection(DataCollection.CollectionType.MAP);
    }

    @Test
    void testAddItemToArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        assertEquals(1, arrayListCollection.size());
        assertTrue(arrayListCollection.toString().contains("item1"));
    }

    @Test
    void testAddItemToSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.addItem("item1");
        assertEquals(1, setCollection.size());
        assertTrue(setCollection.toString().contains("item1"));
    }

    @Test
    void testAddItemToMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        assertEquals(1, mapCollection.size());
        assertTrue(mapCollection.toString().contains("key1=value1"));
    }

    @Test
    void testUpdateItemInArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        arrayListCollection.updateItem(0, "updatedItem");
        assertTrue(arrayListCollection.toString().contains("updatedItem"));
    }

    @Test
    void testUpdateItemInSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.updateItem(0, "updatedItem");
        assertTrue(setCollection.toString().contains("updatedItem"));
    }

    @Test
    void testUpdateItemInMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        mapCollection.updateItem("key1", "updatedValue");
        assertTrue(mapCollection.toString().contains("key1=updatedValue"));
    }

    @Test
    void testRemoveItemFromArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        arrayListCollection.removeItem(0);
        assertEquals(0, arrayListCollection.size());
    }

    @Test
    void testRemoveItemFromSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.removeItem(0);
        assertEquals(0, setCollection.size());
    }

    @Test
    void testRemoveItemFromMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        mapCollection.removeItem("key1");
        assertEquals(0, mapCollection.size());
    }

    @Test
    void testClearArrayList() throws IOException {
        arrayListCollection.addItem("item1");
        arrayListCollection.clear();
        assertEquals(0, arrayListCollection.size());
    }

    @Test
    void testClearSet() throws IOException {
        setCollection.addItem("item1");
        setCollection.clear();
        assertEquals(0, setCollection.size());
    }

    @Test
    void testClearMap() throws IOException {
        mapCollection.addItem("key1", "value1");
        mapCollection.clear();
        assertEquals(0, mapCollection.size());
    }
}
