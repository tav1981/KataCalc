import java.util.Scanner;

public class Main
{
    static String rangeError = "Каждое число может быть от 1 до 10 включительно.";
    static String formatError = "Неправильный формат строки.";
    static String operationError = "Ошибка с операциями.";
    static String notationError = "Различные системы чисел.";
    static String theEnd = "Завершение работы программы!";
    static String romanError = "Результат работы с римскими числами может быть только положительным!";
    static String enterValue = "Введите выражение:";
    static String outputValue = "Ответ:";

    public static void main(String[] args) {

        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.");
        System.out.println("Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.");
        System.out.println("Калькулятор умеет принимать на вход числа от 1 до 10 включительно, не более.");
        System.out.println("Калькулятор умеет работать только с целыми числами.");
        System.out.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно.");

        do {
            System.out.println("\u001B[34m" + enterValue + "\u001B[0m");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            System.out.println("\u001B[34m" + outputValue + "\u001B[0m");
            System.out.println("\u001B[32m" + calc(userInput) + "\u001B[0m");

            if (!isPresent(calc(userInput))) {
                try {
                    Integer i = Integer.valueOf(calc(userInput));
                } catch (NumberFormatException e) {
                    System.err.println(theEnd);
                    break;
                }
            }
        } while (true);

    }

    public static String calc(String input) {

        String [] operations = {"\\+","-","\\*","/"};
        Integer answer = 0;
        boolean romanOk = false;
        RomanNumeric romanAnswer;
        String string1 = input.replaceAll("\\s", ""); //удаляем пробелы и табы

        for (int i=0; i<operations.length; i++) {
            String [] stringDev = string1.split(operations[i]);
            if (stringDev.length==2) {
                for (int i1=0; i1<stringDev.length; i1++) {
                    if ((isPresent(stringDev[0])^isPresent(stringDev[1]))&!romanOk){
                        return notationError;
                    }
                    if (isPresent(stringDev[i1])){ //проверяем римские числа и преобразуем в обычные
                        romanOk = true;
                        RomanNumeric roman = RomanNumeric.valueOf(stringDev[i1]);
                        stringDev[i1] = Integer.toString(roman.getEqual());
                    }
                     try {
                        Integer i2 = Integer.valueOf(stringDev[i1]);
                         if (i2<11 & i2>0) {
                             switch (operations[i]) {
                                 case "\\+" : answer = answer + i2; break;
                                 case "-" :  answer = answer + i2*(1-i1) - i2*i1; break;
                                 case "\\*" : answer = answer*i2 + i2*(1-i1); break;
                                 case "/" : answer = answer/i2 + i2*(1-i1); break;
                             }
                         } else {return rangeError;}
                     }
                     catch (NumberFormatException e)
                     { return formatError; }
                }
                if (romanOk){
                    if (answer<1) {return romanError;}
                    RomanNumeric numOut = RomanNumeric.values()[answer-1];
                    return numOut.toString();

                }
                return Integer.toString(answer);
            }
        }

        return operationError;

    }
    private static boolean isPresent(String data) {

        try {
            Enum.valueOf(RomanNumeric.class, data);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}



