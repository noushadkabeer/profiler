package com.lemon.profiler.test;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonSimpleReadExample {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("C:\\NSD\\work\\profiler\\Resumes\\Sampledocs1\\temp\\\\94177246CV_Hanan Ijam\\94177246CV_Hanan Ijam.pdf.json"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            String age = (String) jsonObject.get("mobile");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("skills");
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}