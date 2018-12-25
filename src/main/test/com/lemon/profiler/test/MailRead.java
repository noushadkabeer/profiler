package com.lemon.profiler.test;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;


public class MailRead {

   public static void check(String host, String storeType, String user,
      String password) 
   {
      try {
    	  
    	  MailSSLSocketFactory sf = new MailSSLSocketFactory();
    	  sf.setTrustAllHosts(true); 
    	  
      //create properties field
      Properties properties = new Properties();
      properties.put("mail.imap.ssl.trust", "*");
	  properties.put("mail.imap.ssl.socketFactory", sf);
      properties.put("mail.pop3.host", host);
      properties.put("mail.pop3.port", "995");
      properties.put("mail.pop3.starttls.enable", "true");
      Session emailSession = Session.getDefaultInstance(properties);
  
      //create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");

      store.connect(host, user, password);

      //create the folder object and open it
      Folder emailFolder = store.getFolder("INBOX");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array and print it
      Message[] messages = emailFolder.getMessages();
      System.out.println("messages.length---" + messages.length);

      for (int i = 0, n = messages.length; i < n; i++) {
         Message message = messages[i];
         System.out.println("---------------------------------");
         System.out.println("Email Number " + (i + 1));
         System.out.println("Subject: " + message.getSubject());
         System.out.println("From: " + message.getFrom()[0]);
         System.out.println("Text: " + message.getContent().toString());

      }

      //close the store and folder objects
      emailFolder.close(false);
      store.close();

      } catch (NoSuchProviderException e) {
         e.printStackTrace();
      } catch (MessagingException e) {
         e.printStackTrace();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   public static void main(String[] args) {

      String host = "imap.gmail.com";// change accordingly
      String mailStoreType = "imap";
      String username = "NishadK@deltaoilgasjobs.com";// change accordingly
      String password = "DelTa@2018";// change accordingly

      //check(host, mailStoreType, username, password);
      new MailRead().sendEmail();

   }
   
   
	   private String from ="NishadK@deltaoilgasjobs.com";
	   private String password = "DelTa@2018";
	   private String to = "emperor3330@gmail.com";
	   private String subject = "Test Email";
	   private String body = "This is a test mail";

	   static Properties properties = new Properties();
	   static {
	      properties.put("mail.smtp.host", "smtp.gmail.com");
	      properties.put("mail.smtp.socketFactory.port", "465");
	      properties.put("mail.smtp.socketFactory.class",
	         "javax.net.ssl.SSLSocketFactory");
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.port", "465");
	   }

	   public void sendEmail() {
	      String ret = "SUCCESS";
	      try {
	         Session session = Session.getDefaultInstance(properties,  
	            new javax.mail.Authenticator() {
	               protected PasswordAuthentication 
	               getPasswordAuthentication() {
	                  return new 
	                  PasswordAuthentication(from, password);
	               }
	            }
	         );

	         Message message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.setRecipients(Message.RecipientType.TO, 
	            InternetAddress.parse(to));
	         message.setSubject(subject);
	         message.setText(body);
	         Transport.send(message);
	      } catch(Exception e) {
	         ret = "ERROR";
	         e.printStackTrace();
	      }
   }

}
