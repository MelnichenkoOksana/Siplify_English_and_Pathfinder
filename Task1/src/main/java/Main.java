public class Main {
    public static void main(String[] args) {
String text = "Ssuccess, cc, ciceckc, jjjj, the";
ChangeText ChangeMyText = new ChangeText(text);
text = ChangeText.deleteС(text);
        System.out.println(text);
        text = ChangeText.deleteDoubleLetter(text);
        System.out.println(text);
        text = ChangeText.deleteEAtEndWord(text);
        System.out.println(text);


    }
}
