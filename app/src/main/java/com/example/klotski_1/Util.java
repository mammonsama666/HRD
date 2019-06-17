package com.example.klotski_1;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.HashMap;

class Util {
    static String getJson(Context mContext, String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = mContext.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String next = "";
            while (null != (next = bufferedReader.readLine())) {
                stringBuilder.append(next);
            }
        } catch (IOException e) {
            e.printStackTrace();
            stringBuilder.delete(0, stringBuilder.length());
        }
        return stringBuilder.toString().trim();
    }
}

class SerializableHashMap implements Serializable {

    private HashMap<String, String> map;

    HashMap<String, String> getMap() {
        return map;
    }

    void setMap(HashMap<String, String> map) {
        this.map = map;
    }
}
