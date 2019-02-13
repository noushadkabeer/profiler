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
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import com.lemon.profiler.constants.ProfilerConstants;
import com.lemon.profiler.service.PropertyReaderService;
import com.lemon.profiler.service.impl.PropertyReaderServiceImpl;
import com.opensymphony.xwork2.ActionContext;

/**
 * Class file to send mail.
 * 
 * @author
 *
 */
public class EmailUtil {
	private static PropertyReaderService propS = new PropertyReaderServiceImpl();
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
	public static void sendMail(String subject, Serializable toMany, String html, String from) {

		try {
			// String host = PropertyUtils.getProperty("mail.host");
			String host = propS.getKeyValue("mail.host");

			// Get system properties
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", host);
			// properties.put("mail.debug", "true");

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties);
			MimeMessage message = new MimeMessage(session);
			message.setContent(html, "text/html");
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) toMany));
			message.setSubject(subject);
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public static void sendMailTestRunFail(String subject, Serializable toMany, String html, String from,
			String fileName, byte[] byteArray) {

		try {
			/*
			 * PropertyUtils.propFileName = "alfresco-global.properties";
			 * PropertyUtils.loadProperties();
			 */

			// String host = PropertyUtils.getProperty("mail.host");
			// String host = propS.getKeyValue("mail.host");

			// Get system properties
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", propS.getKeyValue("mail.host"));
			properties.setProperty("mail.smtp.port", propS.getKeyValue("mail.smtp.port"));
			properties.setProperty("mail.smtp.socketFactory.port", propS.getKeyValue("mail.smtp.socketFactory.port"));
			properties.setProperty("mail.smtp.socketFactory.class", propS.getKeyValue("mail.smtp.socketFactory.class"));
			properties.setProperty("mail.smtp.auth", propS.getKeyValue("mail.smtp.auth"));
			// properties.put("mail.debug", "true");

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

			DataSource dataSource = new ByteArrayDataSource(byteArray, "text/plain");
			// EmailUtil bds = new EmailUtil(_data, "AttName");
			messageBodyPart.setDataHandler(new DataHandler(dataSource));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) toMany));
			message.setSubject(subject);
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	public void pushMail(String subject, String to, String cc, String bcc, String content, String from) {
		Properties properties = new Properties();
		properties.put("mail.smtp.host", propS.getKeyValue("mail.host"));
		properties.put("mail.smtp.socketFactory.port", propS.getKeyValue("mail.smtp.socketFactory.port"));
		properties.put("mail.smtp.socketFactory.class", propS.getKeyValue("mail.smtp.socketFactory.class"));
		properties.put("mail.smtp.auth", propS.getKeyValue("mail.smtp.auth"));
		properties.put("mail.smtp.port", propS.getKeyValue("mail.smtp.port"));
		final String fromme = from;
		String ret = "SUCCESS";
		try {
			Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					String emailPassword = ActionContext.getContext().getSession().get(ProfilerConstants.USER_EMAIL_PASSWORD).toString();
					return new PasswordAuthentication(fromme, emailPassword);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
			message.setSubject(subject);
			message.setText(content);
			Transport.send(message);
		} catch (Exception e) {
			ret = "ERROR";
			e.printStackTrace();
		}
	}

}
