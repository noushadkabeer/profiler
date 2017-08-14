package com.lemon.profiler.util;

import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;




/**
 * Class file to send mail.
 * 
 * @author 
 *
 */
public class EmailUtil
{
	private static Properties globalProps;
	private static byte[] _data; 
	private static java.lang.String _name; 
	
	public EmailUtil() {
		
	}
	
	public EmailUtil(byte[] data, String name) { 
		_data = data; 
		_name = name;
		} 
	
	
	/**
	 * Send Mail
	 * 
	 * @param subject
	 * @param toMany
	 * @param html
	 * @param from
	 * @throws IOException
	 */
	public static void sendMail(String subject, Serializable toMany, String html, String from)
	{

		try
		{
			/*PropertyUtils.propFileName = "alfresco-global.properties";
			PropertyUtils.loadProperties();
			*/
			
			//String host = PropertyUtils.getProperty("mail.host");
			String host = globalProps.getProperty("mail.host");
			
			// Get system properties
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
//			properties.put("mail.debug", "true");
			
			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setContent(html, "text/html");
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)toMany));
			message.setSubject(subject);
			Transport.send(message);
		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
		}
	}
	public static Properties getGlobalProps() {
		return globalProps;
	}
	public static void setGlobalProps(Properties globalProps) {
		EmailUtil.globalProps = globalProps;
	}
	
	public static void sendMailTestRunFail(String subject, Serializable toMany, String html, String from,String fileName,byte[] byteArray)
	{

		try
		{
			/*PropertyUtils.propFileName = "alfresco-global.properties";
			PropertyUtils.loadProperties();
			*/
			
			//String host = PropertyUtils.getProperty("mail.host");
			String host = globalProps.getProperty("mail.host");
			
			// Get system properties
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			//properties.put("mail.debug", "true");
			
			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			// Create the message part
		    BodyPart messageBodyPart = new MimeBodyPart();
		    messageBodyPart.setContent(html, "text/html; charset=utf-8");
		    
		    // Create a multipar message
		    Multipart multipart = new MimeMultipart();
		    multipart.addBodyPart(messageBodyPart);
		    
		    // Part two is attachment
		    messageBodyPart = new MimeBodyPart();

		    //String fileSource = "C:\\Users\\235338\\Documents\\TCS Internal\\MVP3\\"+fileName;
		    //DataSource source = new FileDataSource(fileSource);
		    //messageBodyPart.setDataHandler(new DataHandler(source));
		    DataSource dataSource = new ByteArrayDataSource(byteArray, "text/plain");
		    //EmailUtil bds = new EmailUtil(_data, "AttName"); 
		    messageBodyPart.setDataHandler(new DataHandler(dataSource));
		    messageBodyPart.setFileName(fileName);
		    multipart.addBodyPart(messageBodyPart);
		    

		    // Send the complete message parts
		    message.setContent(multipart);
			
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress((String)toMany));
			message.setSubject(subject);
			Transport.send(message);
		}
		catch (MessagingException mex)
		{
			mex.printStackTrace();
		}
	}
	
	
}
