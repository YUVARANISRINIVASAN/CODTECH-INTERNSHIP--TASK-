import java.io.*;
import java.util.Scanner;


public class FileHandlingUtility {

    static String fileName = "sample.txt";

    // Method to write data to file
    public static void writeToFile(String content) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write(content);
            System.out.println("File written successfully.");
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Method to read data from file
    public static void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            System.out.println("\n--- File Content ---");
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // Method to modify file (append content)
    public static void modifyFile(String newContent) {
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("\n" + newContent);
            System.out.println("File modified successfully.");
        } catch (IOException e) {
            System.out.println("Error modifying file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("File Handling Utility");
        System.out.println("1. Write File");
        System.out.println("2. Read File");
        System.out.println("3. Modify File");
        System.out.print("Choose an option: ");

        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline

        switch (choice) {
            case 1:
                System.out.print("Enter content to write: ");
                String content = scanner.nextLine();
                writeToFile(content);
                break;

            case 2:
                readFromFile();
                break;

            case 3:
                System.out.print("Enter content to append: ");
                String newContent = scanner.nextLine();
                modifyFile(newContent);
                break;

            default:
                System.out.println("Invalid choice!");
        }

        scanner.close();
    }
}
