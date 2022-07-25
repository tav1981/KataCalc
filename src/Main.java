import java.util.Scanner;

public class Main
{
    static String rangeError = "Каждое число может быть от 1 до 10 включительно.";
    static String formatError = "Неправильный формат строки.";
    static String operationError = "Ошибка с операциями.";

    public static void main(String[] args)
    {

        System.out.println("Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления с двумя числами: a + b, a - b, a * b, a / b.");
        System.out.println("Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.");
        System.out.println("Калькулятор умеет принимать на вход числа от 1 до 10 включительно, не более.");
        System.out.println("Калькулятор умеет работать только с целыми числами.");
        System.out.println("Калькулятор умеет работать только с арабскими или римскими цифрами одновременно.");

        do
        {
            System.out.println("\u001B[34m" + "Введите выражение:" + "\u001B[0m");

            Scanner scanner = new Scanner(System.in);
            String userInput = scanner.nextLine();

            System.out.println("\u001B[34m" + "Ответ:" + "\u001B[0m");
            System.out.println("\u001B[32m" + calc(userInput) + "\u001B[0m");


            try
            {Integer i = Integer.valueOf(calc(userInput));}
            catch (NumberFormatException e)
            {
                System.err.println("Завершение работы программы!");
                break;
            }


        }while (true);

    }

    public static String calc(String input)
    {

        String [] operations = {"\\+","-","\\*","/"};
        Integer answer = 0;
        String string1 = input.replaceAll("\\s", ""); //удаляем пробелы и табы

        for (int i=0; i<operations.length; i++)
        {
            String [] stringDev = string1.split(operations[i]);
            if (stringDev.length==2)
            {
                for (int i1=0; i1<stringDev.length; i1++)
                {
                     try
                     {
                        Integer i2 = Integer.valueOf(stringDev[i1]);
                         if (i2<11 & i2>0)
                         {
                             switch (operations[i])
                             {
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
                return Integer.toString(answer);
            }
        }

        return operationError;

    }

}

