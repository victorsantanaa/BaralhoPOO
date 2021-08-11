package br.com.victor.service;


import br.com.victor.model.CardModel;
import br.com.victor.utils.Utils;
import com.google.gson.Gson;
import jdk.jshell.execution.Util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class CardService {

    /*
    https://deckofcardsapi.com/
     */
    static String webService = "https://deckofcardsapi.com/api/deck/";
    static int succesCode = 200;

    public static CardModel drawACard(String deckId) throws Exception {

        String urlRequest = webService + deckId + "/draw/?count=1";

        try {
            URL url = new URL(urlRequest);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            if(connection.getResponseCode() != succesCode) {
                throw new RuntimeException("HTTP error code: " + connection.getResponseCode());
            }

            BufferedReader response = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String json = Utils.convertJson(response);

            Gson gson = new Gson();
            CardModel card = gson.fromJson(json, CardModel.class);
            CardModel card2 = gson.fromJson(new Gson().toJson(card.getCards().get(0)), CardModel.class);
            card2.setRemaining(card.getRemaining());
            card2.setDeckId(card.getDeckId());

            return card2;
        } catch (Exception e) {
            throw new Exception("Erro: " + e);
        }
    }
}

