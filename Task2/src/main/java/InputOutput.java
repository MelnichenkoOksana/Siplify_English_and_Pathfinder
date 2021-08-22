import java.io.*;
import java.util.Locale;

public class InputOutput {

    public static String getInfoFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("INPUT.txt"));
        StringBuffer sb = new StringBuffer();
        sb.append(bufferedReader.readLine());
        while (bufferedReader.ready()) {
            sb.append("\n");
            sb.append(bufferedReader.readLine());
        }
        return sb.toString();
    }

    public static char[][][] conversionStringToArray(String incomingString) {
        incomingString = incomingString.toLowerCase(Locale.ROOT).replaceAll("\\s+", ""); // удаляем из строки все пробелы и невидимые строку

        char[] tempCharArray = incomingString.toCharArray(); //разбиваем строку на массив символов
        int numberFloors = Character.getNumericValue(tempCharArray[0]);//кол-во этажей
        int length = Character.getNumericValue(tempCharArray[1]); //длина этажа
        int width = Character.getNumericValue(tempCharArray[2]); //ширина этажа
        char[][][] labyrinthMap = new char[numberFloors][length][width];

        int counterFloors = 0;
        for (int i = 3; i < tempCharArray.length; i++) {

            while (counterFloors < numberFloors) {
                int counterLength = 0;
                int counterWidth = 0;
                while (counterLength < length) {
                    while (counterWidth < width) {
                        labyrinthMap[counterFloors][counterLength][counterWidth] = tempCharArray[i];
                        counterWidth++;
                        i++;
                    }
                    counterLength++;
                    counterWidth = 0;
                }
                counterFloors++;
//                i--;
            }
        }

        return labyrinthMap;
    }

    public static Point searchDesiredPoint(char[][][] labyrinthMap, char a) {
        Point desiredPoint = null;
        for (int i = 0; i < labyrinthMap.length; i++) {
            for (int j = 0; j < labyrinthMap[0].length; j++) {
                for (int k = 0; k < labyrinthMap[0][0].length; k++) {
                    if (labyrinthMap[i][j][k] == a) {
                        desiredPoint = new Point(i, j, k);
                    }
                }
            }
        }

        return desiredPoint;
    }

    public static boolean writeFile(String result) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("Output.txt"))) {
            Point.newSolution newSolution = null;
            bw.write(result);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }
}