package Model;

import javafx.scene.media.Media;

import java.io.*;
import java.util.ArrayList;

public class MediaFile implements Serializable{


    public void writeVector(ArrayList<File> vector, String path) throws IOException {
        FileOutputStream fos = new FileOutputStream(path);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(vector);
        oos.close();

    }

    public ArrayList<File> readVector(String path) throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(path);
        ObjectInputStream ois = new ObjectInputStream(fis);


        ArrayList<File> mailBoxes = new ArrayList<>();


        mailBoxes = ((ArrayList<File>) ois.readObject());

        ois.close();
        return mailBoxes;
    }




}
