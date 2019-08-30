import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class LevelGenerator {
    final static String LEVEL_PATH = "Resources/levels.txt";
    final static String NUMBER_PATH = "Resources/numbers.txt";
    final static String HEALTH_PATH = "Resources/health.txt";
    private ArrayList<String> possibleLevels;
    private HashMap<Integer,String> numbers;
    private String health;

    public LevelGenerator() {
        this.possibleLevels = getPossibleLevels();
        try{
        parseNumbersAndHealth();}
        catch(IOException e){
            e.printStackTrace();
        }
    }
    //Reads level.txt and write it in the list
    private ArrayList<String> parseLevelFile() throws IOException {   //read strings from Resources/levels.txt with \n as delimiter (newline)
        ArrayList<String> parsedLevels = new ArrayList<>();
        try (FileInputStream fs = new FileInputStream(new File(LEVEL_PATH));
             InputStreamReader ir = new InputStreamReader(fs);
             BufferedReader reader = new BufferedReader(ir)) {
            while (reader.ready()) {
                parsedLevels.add(reader.readLine());
            }
        }
        return parsedLevels;
    }

    private void parseNumbersAndHealth() throws IOException{
        numbers = new HashMap();
        int cnt=0;
        try (FileInputStream fs = new FileInputStream(new File(NUMBER_PATH));
             InputStreamReader ir = new InputStreamReader(fs);
             BufferedReader reader = new BufferedReader(ir)) {
            while (reader.ready()) {
               numbers.put(cnt++,reader.readLine());
            }
        }
        health="";
        try (FileInputStream fs = new FileInputStream(new File(HEALTH_PATH));
             InputStreamReader ir = new InputStreamReader(fs);
             BufferedReader reader = new BufferedReader(ir)) {
            while (reader.ready()) {
                health+=reader.readLine();
            }
        }

    }


    //Reads level.txt and write it in the list
    private ArrayList<String> getPossibleLevels() {
        ArrayList<String> parsedLevels = new ArrayList<>();
        try {
            parsedLevels = parseLevelFile();
        } catch (Exception e) {
            System.err.println("levels.txt does not contain levels or does not exist \n"+e.getMessage());
        }
        finally{
            return parsedLevels;
        }
    }

    //Choosing random Level from the list of possible levels
    public Level createLevel() {
        Level currentLevel = null;
        if (possibleLevels.isEmpty()) {
            System.err.println("LevelFile empty");
        } else {
            Random rand = new Random(System.currentTimeMillis());
            int val = Math.abs(rand.nextInt())% (possibleLevels.size());
            System.out.println("Anzahl möglicher Level: "+possibleLevels.size());
            String levelString = possibleLevels.get(val);
            currentLevel = new Level(levelString);
        }
        return currentLevel;
    }

}




