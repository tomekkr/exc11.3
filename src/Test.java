import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

class Test {
    public static void main(String[] args) {
        String fileName = "dzialania_matematyczne.txt";
        String fileNameResults = "dzialania_matematyczne_z_wynikami.txt";
        File file = new File(fileName);

        try (
                FileWriter fileWriter = new FileWriter(fileNameResults);
                Scanner scanner = new Scanner(file)
        ) {
            while (scanner.hasNextLine()) {
                int firstNumber = scanner.nextInt();
                String operator = scanner.next();
                int secondNumber = scanner.nextInt();
                int result = performOperation(operator, firstNumber, secondNumber);
                String mathOperationInOneLine = firstNumber + " " + operator + " " + secondNumber + " = " + result;
                System.out.println(mathOperationInOneLine);
                fileWriter.write(mathOperationInOneLine + "\n");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Nie odnaleziono pliku: " + fileName);
        } catch (InputMismatchException e) {
            System.err.println("Nieprawidłowe dane");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ArithmeticException e) {
            System.err.println("Nieprawidłowe działanie matematyczne: " + e.getLocalizedMessage());
        }
    }

    private static int performOperation(String operator, int a, int b) {
        return switch (operator) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> 0;
        };
    }
}