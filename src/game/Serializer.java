package game;

import player.Player;

import java.io.*;
import java.util.ArrayList;

public class Serializer {

    static public void serialize(String filePath, Object data) {
        try {
            FileOutputStream file = new FileOutputStream(filePath);
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(data);
            out.close();
            file.close();
            System.out.println("Saved file");
        } catch (Exception error) {
            System.out.println("Error IN SAVING DATA");
            System.out.println(error);

        }
    }

    static public Object deserialize(String filePath) {
        try {
            FileInputStream file = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(file);
            var data = in.readObject();
            in.close();
            file.close();
            return data;
        } catch (Exception error) {
            System.out.println("ERROR IN LOADING DATA");
            System.out.println(error);
            return false; // we couldn't complete deserialization
        }
    }

}