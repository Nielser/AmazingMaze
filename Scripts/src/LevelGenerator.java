import java.io.*;
import java.util.ArrayList;
import java.util.Random;

//quick levelgen mockup
public class LevelGenerator {
    final static String LEVEL_PATH = "Resources/levels.txt";

    private ArrayList<String> possibleLevels;
    public Level currentLevel;

    public LevelGenerator() {
        this.possibleLevels = getPossibleLevels();
    }

    private ArrayList<String> parseLevelFile() throws FileNotFoundException, IOException {   //read strings from levels.txt with \n as delimiter (newline)
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

    private ArrayList<String> getPossibleLevels() { //#Todo fix returns
        ArrayList<String> parsedLevels = null;
        try {
            parsedLevels = parseLevelFile();
            return parsedLevels;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return new ArrayList();
        }
    }

    public void createLevel() {
        if (possibleLevels.isEmpty()) {
            System.err.println("LevelFile empty");
        } else {
            Random rand = new Random(System.currentTimeMillis());
            String levelString = possibleLevels.get(rand.nextInt() % possibleLevels.size());
            currentLevel = new Level(levelString);
        }
    }
}

/*
 * 0    ->[]
 * 1    ->[]
 * 2    ->[]
 * 3    ->[]
 * 4    ->[]
 * */



