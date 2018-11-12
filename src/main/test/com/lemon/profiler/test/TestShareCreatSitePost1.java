package com.lemon.profiler.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class TestShareCreatSitePost1 {

   public static void main(String[] args) throws Exception {
      System.out.println(createSite());
   }
//https://community.alfresco.com/thread/182253-automatic-site-creation-surf-config-not-generated
   private static String createSite() {
      try {
         DefaultHttpClient client = new DefaultHttpClient();
         HttpPost post = new HttpPost(
               "http://localhost:8080/share/page/dologin");
         List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
         nameValuePairs.add(new BasicNameValuePair("admin", "admin"));
         nameValuePairs.add(new BasicNameValuePair("admin", "admin"));
         post.setEntity(new UrlEncodedFormEntity(nameValuePairs));
         client.execute(post);
         CookieStore cookieStore = client.getCookieStore();
         client = new DefaultHttpClient();
         client.setCookieStore(cookieStore);
         post = new HttpPost(
               "http://localhost:8080/share/service/modules/create-site");
         post.setHeader("Content-Type", "application/json");
         post.setEntity(new StringEntity(createSiteParameters(
               "JavaTest-002", "Sito di test by JAVA", "JavaTest-002"),
               "UTF-8"));
         ResponseHandler<String> responseHandler = new BasicResponseHandler();
         String response = client.execute(post, responseHandler);
         return response;
      } catch (Exception e) {
         e.printStackTrace();
      }
      return "Error!";
   }

   private static String createSiteParameters(String title,
         String description, String shortName) {
      String site = "{\"visibility\":\"PRIVATE\", "
            + "\"sitePreset\":\"site-dashboard\", " + "\"title\":\""
            + title + "\", " + "\"description\":\"" + description + "\", "
            + "\"shortName\":\"" + shortName + "\"}";
      System.out.println(site);
      return site;
   }
}