import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

public class ChangeText {

    //    Вспомогательный метод который из строки создает массив слов приведенных к нижнему регистру
    public static ArrayList<String> arrayWorldsBuilder(String text) {
        String tempText = text.toLowerCase(Locale.ROOT).replaceAll("[.:;?,!-]", " ");
        String[] tempArrayWords;
        tempArrayWords = tempText.split("\\s+");// в массив срок разбиваем текст на слова (по пробелам)
        ArrayList<String> arrayWords = new ArrayList<>();
        for (String s :
                tempArrayWords) {
            arrayWords.add(s);
        }
        return arrayWords;
    }

    //    Вспомогательный метод который собирает итоговую строку с учетом регистра и знаков препинания.
    public static String resultStringBuilder(String text, ArrayList<String> arrayWords) {
        char[] arrayChars = text.toCharArray(); // разбиваем изначальный текст на массив элементов
        StringBuilder resultString = new StringBuilder();// создаем стрингбилдер для хранения итогового результата

        for (int i = 0; i < arrayChars.length; i++) { // прооходимся по массиву элементов изночальной строки
            if (!Character.isAlphabetic(arrayChars[i])) { // если элемент - не буква
                resultString.append(arrayChars[i]); // добавляем ее в итоговую строу
            } else if ((arrayChars[i] >= 65) && (arrayChars[i] <= 90) && (arrayWords.size() > 0)) { //ИНАЧЕ (если буква и она БОЛЬШАЯ и в массиве слов есть еще слова)
                char[] tempArrayChar = arrayWords.get(0).toCharArray(); // брем первое слово из массива слов и разбиваем его на символы (во временный масив символов)
                arrayWords.remove(0);  // удаляем это слово из массива
                tempArrayChar[0] = Character.toUpperCase(tempArrayChar[0]); //Поднимаем регистр первой буквы слова
                // По буквам дообавляем измененное слово (с большой буквы) в итоговую строку
                for (int j = 0; j < tempArrayChar.length; j++) {
                    resultString.append(tempArrayChar[j]);
                }
                //пока изначальном тексте идут буквы пропускаем их.
                while ((Character.isAlphabetic(arrayChars[i + 1])) && (i < (arrayChars.length - 1))) {
                    i++;
                }
            } else {// ИНАЧЕ (если буква маленькая)
                resultString.append(arrayWords.get(0));
                arrayWords.remove(0);
                while ((i <= (arrayChars.length - 2)) && (Character.isAlphabetic(arrayChars[i + 1])) && (i < (arrayChars.length - 1))) {
                    i += 1;
                }
            }
        }

        return resultString.toString();
    }

    public static ArrayList<String> deleteС(String text) {
        ArrayList<String> arrayWorlds = arrayWorldsBuilder(text);
        ArrayList<String> resultArrayWorlds = new ArrayList<>();

        for (String word :
                arrayWorlds) {
            char[] tempArrayChar = word.toCharArray();
            String changedWord = "";
            int i = 0;
            do {
                if (tempArrayChar[i] == 'c') {
                    switch (tempArrayChar[i + 1]) {
                        case 'i':
                            tempArrayChar[i] = 's';
                            break;
                        case 'e':
                            tempArrayChar[i] = 's';
                            break;
                        case 'k':
                            tempArrayChar[i] = '0';
                            break;
                        default:
                            tempArrayChar[i] = 'k';
                            break;
                    }
                }
                i++;
            } while (i < (tempArrayChar.length - 2));

            if (tempArrayChar[tempArrayChar.length - 1] == 'c') tempArrayChar[tempArrayChar.length - 1] = 'k';
            for (char ch :
                    tempArrayChar) {
                if (Character.isAlphabetic(ch)) {
                    changedWord = changedWord + ch;
                }
            }
            resultArrayWorlds.add(changedWord);
        }

        return resultArrayWorlds;
    }

    public static ArrayList<String> deleteDoubleLetter(ArrayList<String> arrayWords) {

        ArrayList<String> resultArrayWorlds = new ArrayList<>();
        for (String word :
                arrayWords) {
            char[] tempArrayChar = word.toCharArray();
            int i = 0;
            if (word.length()>1) {
                do {
                    if (tempArrayChar[i] == tempArrayChar[i + 1]) {
                        switch (tempArrayChar[i]) {
                            case 'e':
                                tempArrayChar[i] = '0';
                                tempArrayChar[i + 1] = 'i';
                                break;
                            case 'o':
                                tempArrayChar[i] = '0';
                                tempArrayChar[i + 1] = 'u';
                                break;
                            default:
                                tempArrayChar[i] = '0';
                                break;
                        }

                    }
                    i++;
                } while (i < (tempArrayChar.length - 1));
            }
            String changedWord = "";
            for (char ch :
                    tempArrayChar) {
                if (Character.isAlphabetic(ch)) {
                    changedWord = changedWord + ch;
                }
            }
            resultArrayWorlds.add(changedWord);
        }
        return resultArrayWorlds;
    }

    public static String deleteEAtEndWord(ArrayList<String> arrayWords) throws IOException {

        ArrayList<String> resultArrayWorlds = new ArrayList<>();
        for (String word :
                arrayWords) {
            char[] tempArrayChar = word.toCharArray();
            if ((tempArrayChar[tempArrayChar.length - 1] == 'e')&&(tempArrayChar.length > 1)) {
                tempArrayChar[tempArrayChar.length - 1] = '0';
            }
            String changedWord = "";
            for (char ch :
                    tempArrayChar) {
                if (Character.isAlphabetic(ch)) {
                    changedWord = changedWord + ch;
                }
            }
            resultArrayWorlds.add(changedWord);
        }

        String resultText = resultStringBuilder(InputOutput.getInfoFromFile(), resultArrayWorlds);

        return resultText;
    }

    public static String deleteArticles(String text){
        text = text.replace(" A "," ");
        text = text.replace(" a "," ");
        text = text.replace(" An "," ");
        text = text.replace(" an "," ");
        text = text.replace(" Th "," ");
        text = text.replace(" th "," ");

        return text;
    }
}