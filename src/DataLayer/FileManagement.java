/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DataLayer;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author asus
 * @param <T>
 */
public class FileManagement<T> implements IFileManagement<T> {

    private static final String PROJECT_ROOT = System.getProperty("user.dir");
    public static String carInputFile = PROJECT_ROOT + "/src/DataLayer/Data/CarData.txt";

    private String fileName;

    public FileManagement() {
    }

    public FileManagement(String fileName) {
        this.fileName = fileName;
    }

//-------------------------------------------------------------------------
    @Override
    public void writeDataToFile(List<String> data) throws IOException {
        Files.write(new File(fileName).toPath(), data, Charset.forName("utf-8"));
    }

    @Override
    public List<String> readDataFromFile() throws IOException {
        List<String> result;
        result = Files.readAllLines(new File(fileName).toPath(), Charset.forName("utf-8"));
        return result;
    }

    public static void loadFile(String filePathStr) {
        Path filePath = Paths.get(filePathStr);
        try {
            // Ensure parent directories exist
            Path parentDir = filePath.getParent();
            if (parentDir != null && Files.notExists(parentDir)) {
                Files.createDirectories(parentDir);
                System.out.println("Created directories: " + parentDir);
            }

            // create file if not exist
            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
                System.out.println("File was created: " + filePath);
            } else {
                System.out.println("File already exists: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Error when creating file or directories: " + e.getMessage());
        }
    }
}
