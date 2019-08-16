import java.io.*;
import java.util.ArrayList;
import java.util.Random;

//quick levelgen mockup
public class LevelGenerator {
    final static String LEVEL_PATH = "Resources/levels.txt";
    private ArrayList<String> possibleLevels;

    public LevelGenerator() {
        this.possibleLevels = getPossibleLevels();
    }

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

    public Level createLevel() {
        Level currentLevel = null;
        if (possibleLevels.isEmpty()) {
            System.err.println("LevelFile empty");
        } else {
            Random rand = new Random(System.currentTimeMillis());
            int val = rand.nextInt() % possibleLevels.size();
            System.out.println(possibleLevels.size()+"|"+val);
            String levelString = possibleLevels.get(0);
            currentLevel = new Level(levelString);
        }
        System.out.println("LG");
        return currentLevel;
    }
}




