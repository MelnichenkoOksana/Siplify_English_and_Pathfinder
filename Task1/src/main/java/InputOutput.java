import java.io.*;

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
