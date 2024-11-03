import parkingComponents.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String[]> lst = new ArrayList<>();
        List<int[]> lst2 = new ArrayList<>();
        String filePath = "src/input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lst.add(line.split(", "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        for (String[] stringsArray : lst) {
            int[] values;
            int gateNumber, carNumber, arrive, parks;
            gateNumber = Integer.parseInt(stringsArray[0].substring(stringsArray[0].length() - 1));
            carNumber = Integer.parseInt(stringsArray[1].substring(stringsArray[1].length() - 1));
            arrive = Integer.parseInt(stringsArray[2].substring(stringsArray[2].length() - 1));
            parks = Integer.parseInt(stringsArray[3].substring(stringsArray[3].length() - 1));
            values = new int[]{gateNumber, carNumber, arrive, parks};
            lst2.add(values);
        }
    }
}
