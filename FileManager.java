
import java.io.*;
import java.util.*;

public class FileManager {

    private static final String DB_FOLDER = "database";

    static {
        initDatabase();
    }

    private static void initDatabase() {
        File folder = new File(DB_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
        createFile("users.txt");
        createFile("doctors.txt");
        createFile("patients.txt");
        createFile("appointments.txt");
    }

    private static void createFile(String fileName) {
        try {
            File file = new File(DB_FOLDER + "/" + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            System.out.println("Error creating file: " + fileName);
        }
    }

    private static File getFile(String collection) {
        return new File(DB_FOLDER + "/" + collection + ".txt");
    }

    public static List<String> findAll(String collection) {
        List<String> data = new ArrayList<>();
        try (Scanner scanner = new Scanner(getFile(collection))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    data.add(line);
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading " + collection);
        }

        return data;
    }

    public static String findById(String collection, String id) {
        List<String> data = findAll(collection);

        for (String line : data) {
            if (line.startsWith(id + ",")) {
                return line;
            }
        }
        return null;
    }

    public static void save(String collection, String record) {
        try (FileWriter fw = new FileWriter(getFile(collection), true); BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(record);
            bw.newLine();

        } catch (Exception e) {
            System.out.println("Error saving to " + collection);
        }
    }

    public static void update(String collection, String id, String newRecord) {
        List<String> data = findAll(collection);

        try (PrintWriter writer = new PrintWriter(getFile(collection))) {

            for (String line : data) {
                if (line.startsWith(id + ",")) {
                    writer.println(newRecord);
                } else {
                    writer.println(line);
                }
            }

        } catch (Exception e) {
            System.out.println("Error updating " + collection);
        }
    }

    public static void delete(String collection, String id) {
        List<String> data = findAll(collection);

        try (PrintWriter writer = new PrintWriter(getFile(collection))) {

            for (String line : data) {
                if (!line.startsWith(id + ",")) {
                    writer.println(line);
                }
            }

        } catch (Exception e) {
            System.out.println("Error deleting from " + collection);
        }
    }

    public static String generateId(String collection) {
        List<String> data = findAll(collection);
        String prefix = String.valueOf(collection.charAt(0)).toUpperCase();
        return prefix + String.format("%03d", (data.size() + 1));
    }

}
