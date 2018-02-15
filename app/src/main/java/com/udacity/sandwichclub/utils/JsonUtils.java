package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {
    // get List<String> from a JSONArray
    public static List<String> fromJSONArray(JSONArray jsonArray) throws JSONException {
        ArrayList<String> resulStringList = new ArrayList<>();
        for (int idx=0;idx<jsonArray.length();idx++){
            resulStringList.add(jsonArray.get(idx).toString());
        }
        return  resulStringList;
    }

    public static Sandwich parseSandwichJson(String json) {
        /* A sample sandwich JSON we have to parse*/
        /*
        {
            "name": {
                    "mainName": "Ham and cheese sandwich",
                    "alsoKnownAs": []
                    },
            "placeOfOrigin": "",
            "description": "A ham and cheese sandwich is a common type of sandwich. It is made by putting cheese and sliced ham
            between two slices of bread. The bread is sometimes buttered and/or toasted. Vegetables like lettuce, tomato, onion
            or pickle slices can also be included. Various kinds of mustard and mayonnaise are also common.",
            "image": "https://upload.wikimedia.org/wikipedia/commons/thumb/5/50/Grilled_ham_and_cheese_014.JPG/800px-Grilled_ham_and_cheese_014.JPG",
            "ingredients": ["Sliced bread", "Cheese", "Ham"]
        }
        */
        try {
            JSONObject sandwich = new JSONObject(json);
            JSONObject name = sandwich.getJSONObject("name");
            // the JSON is valid so we can create the result object
            return new Sandwich(name.getString("mainName"),
                    fromJSONArray(name.getJSONArray("alsoKnownAs")),
                    sandwich.getString("placeOfOrigin"),
                    sandwich.getString("description"),
                    sandwich.getString("image"),
                    fromJSONArray(sandwich.getJSONArray("ingredients")));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
