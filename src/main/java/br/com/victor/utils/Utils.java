package br.com.victor.utils;

import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.URL;

public class Utils {

    public static String convertJson(BufferedReader bufferedReader) throws IOException {
        String response;
        String json = "";
        while ((response = bufferedReader.readLine()) != null) {
            json += response;
        }
        return  json;
    }

    private static final EntityManagerFactory FACTORY = Persistence
            .createEntityManagerFactory("baralho");

    public static EntityManager getEntityManager() {
        return FACTORY.createEntityManager();
    }

    public static void mostraAImagemDaCarta(String urlOfImage, String suit, String value) {

        try {
            URL url = new URL(urlOfImage);
            BufferedImage c = ImageIO.read(url);
            ImageIcon image = new ImageIcon(c);

            JFrame frame = new JFrame(value + " " + suit);
            JLabel label = new JLabel(image);

            frame.add(label);
            frame.setDefaultCloseOperation
                    (JFrame.HIDE_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
