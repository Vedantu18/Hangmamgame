package com.dang.man;

import java.io.*;
import java.util.*;

public class Words {
    private HashMap<String, String[]> wordList;
    private ArrayList<String> categories;

    public Words() {
        try {
            wordList = new HashMap<>();
            categories = new ArrayList<>();

            String filePath = this.getClass().getResource(Constants.DATA_PATH).getPath();
            if(filePath.contains("%20")) filePath = filePath.replaceAll("%20", " ");
            System.out.println(filePath);
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String category = parts[0];
                categories.add(category);
                String[] values = Arrays.copyOfRange(parts, 1, parts.length);
                wordList.put(category, values);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public String[] loadChallenge() {
        Random rand = new Random();
        String category = categories.get(rand.nextInt(categories.size()));
        String[] categoryWords = wordList.get(category);
        String word = categoryWords[rand.nextInt(categoryWords.length)];
        return new String[]{category.toUpperCase(), word.toUpperCase()};
    }
}
