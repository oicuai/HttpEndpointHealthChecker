package utils;

import java.io.File;
import java.util.Scanner;

public class ConsoleUtils {

    /**
     * Returns the yaml file path input into the console
     * @return String file path of yaml input file
     */
    public static String getYamlFromCLI() {
        return readInputFromCLI();
    }

    private static void printFileRequest() {
        System.out.print("Enter the absolute path of the the yaml file here > ");
    }

    private static String readInputFromCLI() {
        final Scanner SCANNER = new Scanner(System.in);
        while (true) {
            printFileRequest();
            String path = SCANNER.nextLine();
            File file = new File(path);
            if (file.exists()) return path;
            System.out.println("File does not exist, please try again, or use ctrl + C to exit");
        }


    }
}
