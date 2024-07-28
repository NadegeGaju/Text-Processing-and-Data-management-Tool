package com.example.textprocessing;

import java.io.IOException;
import java.util.*;

public class DataCollection {
    private ArrayList<String> arrayList;
    private Map<String, String> map;
    private Set<String  > set;
    public final CollectionType type;

    public enum CollectionType {
        ARRAYLIST,
        SET,
        MAP
    }

    public DataCollection(CollectionType type) {
        this.type = type;
        if(type == CollectionType.ARRAYLIST) {
            this.arrayList = new ArrayList<>();
            return;
        }else if(type == CollectionType.SET){
            this.set = new HashSet<>();
            return;
        }else if(type == CollectionType.MAP){
            this.map = new HashMap<>();
            return;
        }
    }

    public DataCollection(CollectionType type, String data) {
        this.type = type;
        if(type == CollectionType.ARRAYLIST) {
            String[] listData = data.substring(1, data.length() - 1).split(", ");
            this.arrayList = new ArrayList<>(Arrays.asList(listData));
        }else if(type == CollectionType.SET){
            String[] setData = data.substring(1, data.length() - 1).split(", ");
            this.set = new HashSet<>(Arrays.asList(setData));
        }else if(type == CollectionType.MAP){
            this.map = new HashMap<>();
            String mapData = data.substring(1, data.length() - 1);
            String[] pairs = mapData.split(", ");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                if (keyValue.length == 2) {
                    this.map.put(keyValue[0].trim(), keyValue[1].trim());
                }
            }

        }
    }

    public void addItem(String item) throws IOException {
        if (type == CollectionType.ARRAYLIST) {
            this.arrayList.add(item);
            FilesHelper.writeToFile("arrayList.txt", this.arrayList.toString());
        } else if (type == CollectionType.SET) {
            this.set.add(item);
            FilesHelper.writeToFile("set.txt", this.set.toString());
        }
    }

    public void addItem(String key, String value) throws IOException {
        if (type == CollectionType.MAP) {
            this.map.put(key, value);
            FilesHelper.writeToFile("map.txt", this.map.toString());
        } else {
            throw new UnsupportedOperationException("This method is not supported for ARRAYLIST or SET type");
        }
    }

    public Map<String, String> getMap() {
        return this.map;
    }

    public void updateItem(int index, String newItem) throws IOException{
        if (type == CollectionType.ARRAYLIST) {
            if (index < 0 || index >= this.arrayList.size()) {
                throw new IndexOutOfBoundsException("Index out of bounds for ArrayList");
            }
            this.arrayList.set(index, newItem);
            FilesHelper.writeToFile("arrayList.txt", this.arrayList.toString());
        } else if (type == CollectionType.SET) {
            List<String> list = new ArrayList<>(this.set);
            list.set(index, newItem);
            this.set = new HashSet<>(list);
            FilesHelper.writeToFile("set.txt", this.set.toString());
        } else {
            throw new UnsupportedOperationException("This method is not supported for MAP type");
        }
    }

    public void updateItem(String key, String newValue) throws IOException {
        if (type == CollectionType.MAP) {
            this.map.put(key, newValue);
            FilesHelper.writeToFile("map.txt", this.map.toString());
        } else {
            throw new UnsupportedOperationException("This method is not supported for ARRAYLIST or SET type");
        }
    }

    public void removeItem(int index) throws IOException {
        if (type == CollectionType.ARRAYLIST) {
            if (index < 0 || index >= this.arrayList.size()) {
                throw new IndexOutOfBoundsException("Index out of bounds for ArrayList");
            }
            this.arrayList.remove(index);
            FilesHelper.writeToFile("arrayList.txt", this.arrayList.toString());
        } else if (type == CollectionType.SET) {
            List<String> list = new ArrayList<>(this.set);
            list.remove(index);
            this.set = new HashSet<>(list);
            FilesHelper.writeToFile("set.txt", this.set.toString());
        } else {
            throw new UnsupportedOperationException("This method is not supported for MAP type");
        }
    }

    public void removeItem(String key) throws IOException {
        if (type == CollectionType.MAP) {
            this.map.remove(key);
            FilesHelper.writeToFile("map.txt", this.map.toString());
        } else {
            throw new UnsupportedOperationException("This method is not supported for ARRAYLIST or SET type");
        }
    }

    @Override
    public String toString() {
        if (type == CollectionType.ARRAYLIST) {
            return "ArrayList: " + arrayList.toString();
        } else if (type == CollectionType.SET) {
            return "Set: " + set.toString();
        } else if (type == CollectionType.MAP) {
            return "Map: " + map.toString();
        } else {
            return "Unknown collection type";
        }
    }

    public void clear() {
        if (type == CollectionType.ARRAYLIST) {
            this.arrayList.clear();
        } else if (type == CollectionType.SET) {
            this.set.clear();
        } else if (type == CollectionType.MAP) {
            this.map.clear();
        } else {
            throw new UnsupportedOperationException("Unknown collection type");
        }
    }

    public int size() {
        if (type == CollectionType.ARRAYLIST) {
            return this.arrayList.size();
        } else if (type == CollectionType.SET) {
            return this.set.size();
        } else if (type == CollectionType.MAP) {
            return this.map.size();
        } else {
            throw new UnsupportedOperationException("Unknown collection type");
        }
    }
}