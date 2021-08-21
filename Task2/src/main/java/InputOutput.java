import java.io.*;
import java.util.ArrayList;
import java.util.Locale;

public class InputOutput {

    public static String getInfoFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("INPUT.txt"));
        StringBuffer sb = new StringBuffer();
        sb.append(bufferedReader.readLine());
        while(bufferedReader.ready()){
            sb.append("\n");
            sb.append(bufferedReader.readLine());
        }
        return sb.toString();
    }

    public static ArrayList<char[][]> conversionStringAnArray(String incomingString){
        incomingString = incomingString.toLowerCase(Locale.ROOT).replaceAll("\\s+",""); // удаляем из строки все пробелы и невидимые строку
        ArrayList<char[][]> labyrinthMap =new ArrayList<>();
        char[] tempCharArray = incomingString.toCharArray(); //разбиваем строку на массив символов
        int numberFloors = Character.getNumericValue(tempCharArray[0]);//кол-во этажей
        int length = Character.getNumericValue(tempCharArray[1]); //длина этажа
        int width = Character.getNumericValue(tempCharArray[2]); //ширина этажа

        int counterSquare = 0;
        int counterLength = 0;
        int counterWidth = 0;
        for (int i = 3; i < tempCharArray.length; i++) {
            char [][] floor = new char[length][width];
            while (counterSquare<((length*width))){
                while (counterLength<length){
                    while (counterWidth<width){
                        floor[counterLength][counterWidth]=tempCharArray[i];
                        counterWidth++;
                        counterSquare++;
                        i++;
                    }
                    counterLength++;
                    counterWidth =0;
                }
                labyrinthMap.add(floor);
                counterSquare = 0;
            }
        }

        for (char[][] f:
             labyrinthMap) {
            System.out.println(f);
        }
        return labyrinthMap;
    }

    public static boolean writeFile(String filename, String resultText){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename)))
        {
            bw.write(resultText);
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return true;
    }
}