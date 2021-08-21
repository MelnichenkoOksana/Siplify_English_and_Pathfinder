# Выполнено в рамках тестового задания.
> ## Задача 1
> ### Условие:  Implement an algorithm that simplifies the English text.
> #### 1) Remove "c" from the text
> If the text contains “ci” and “ce”, change it to “si” and “se”.
> If “ck” then change it to “k”.
> In the other case replace “c” with “k”.
> All the changes should be made in a strong order left-to-right.
> ##### For example:
> The word “success” first of all will be the word “sukcess”. Then “suksess”.
> 
> #### 2) Remove a double letter
> If the text contains “ee” then replace it by simple “i”.
> If “oo” then change it by “u”.
> In the other case any double letter should be changed by one letter.
> #### For example:
> “ooo” will be “uo”
> “oou” will be “u”
> “iee” will be “i”
> 
> #### 3) Remove the letter “e” at the end of each word
> Remove the letter “e” at the end of each word if the word length > 1.
> #### For example:
> “The” will be “Th”.
> 
> #### 4) Remove articles
> Remove the articles “a”, “an” or “the” from the text. They should be removed only if they were the words a, an, the in the original text.
> #### For example:
> Input text: “the table”. After the first three steps it will be changed to “th tabl”. After the fourth step: “tabl”
> #### Examples:
> Input:
>   cacao and coffee
>   success 
> Output:
>   kakao and kofi
>   sukses
> 
> #### Ввод и вывод 
> Исходные данные считываются из файла INPUT.txt
> Результат сохраняется в файле Output.txt

 
## Решение:
### Допущения: 
При реализации принято допущение, что входящий текст будет представлен с учетом правил пунктуации и синтаксиса английского языка. 

### Недостатки: 
1.	На данный момент при выводе "теряются" переходы на следующую строку и прочие нечитаемые символы
2.	Не будет  корректно работать с аббревиатурами, словами в верхнем регистре, словами написанными в разных регистрах (заглавная буква не первая и(или) более одной заглавной буквы в слове)

### Особенности реализации: 
- При разработке были использованы следующие внутренние библиотеки java:
> java.io.IOException;
> java.util.ArrayList;
> java.util.Locale;
- Все действия выполняются последовательно в одном потоке.
- Каждый пункт условия реализован в отдельном методе.
