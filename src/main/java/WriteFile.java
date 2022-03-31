import java.io.*;

public class WriteFile {
    File file3 = new File("fisier3.txt");

    public WriteFile() {

    }

    public void create() throws IOException {
        this.file3.createNewFile();
    }

    public void writeInFile(String name) throws IOException {
        try {
            FileWriter writer = new FileWriter("C:\\Users\\andre\\OneDrive\\Desktop\\PT\\PT2022_30228_Bode_Andreea_Nicoleta_Assignment_2\\fisier4.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.append(name);
            bufferedWriter.newLine();
            bufferedWriter.close();
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }


}
