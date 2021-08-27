package report;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import config.PropertiesFile;
import common.CommonOperations;

public class SendReportViaEmail {
	Session mailSession;
	MimeMessage message;
	PropertiesFile config_file = new PropertiesFile();
	CommonOperations common_operation = new CommonOperations();
	public static void main(String [] args) throws AddressException, MessagingException{  
		//setSessionObject();
		//createEmail(mailSession);
		//Transport.send(message);
		//System.out.println("Onek henostha kortasos");
	}
	
	public Session setSessionObject() {
		//String host="mail.javatpoint.com";  
		final String user = config_file.getProperties("smtp_email_user");
		final String password = config_file.getProperties("smtp_email_pass"); 

		final Properties prop = new Properties();
		prop.put("mail.smtp.username", user);
		prop.put("mail.smtp.password", password);
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true"); // TLS
		mailSession = Session.getInstance(prop, new javax.mail.Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(prop.getProperty("mail.smtp.username"), 
						prop.getProperty("mail.smtp.password"));
			}
		});
		return mailSession;
	}

	public MimeMessage createEmail(Session mailSession) throws AddressException, MessagingException {
		message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress("no-reply@gmail.com"));
		String current_date_time = common_operation.dateTimeFormatter("E, MMM dd yyyy HH:mm:ss");
		message.setSubject("Testing Report of " + current_date_time);

		/* Step 1: Create MimeBodyPart and set content and its Mime Type */
		BodyPart mimeBody = new MimeBodyPart();
		String body_text = "<h1> Full Report </h1> <br> Hello " + config_file.getProperties("report_email_to")+ ", <br> You can find the total report of last test execution as attachment below <br><br>";
		mimeBody.setContent(body_text, "text/html");
		/* Step 2: Create MimeMultipart and  wrap the mimebody to it */
		Multipart multiPart = new MimeMultipart();
		multiPart.addBodyPart(mimeBody);
		/* Step 3: set the multipart content to Message in caller method*/
		message.setContent(multiPart);

		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		File full_report = new File(common_operation.project_path + "/" + config_file.getProperties("report_name"));
		try {
			attachmentBodyPart.attachFile(full_report);
		} catch (IOException e) {
			e.printStackTrace();
		}

		multiPart.addBodyPart(attachmentBodyPart);


		InternetAddress[] toEmailAddresses = 
				InternetAddress.parse(config_file.getProperties("report_email_to"));
		//		InternetAddress[] ccEmailAddresses = 
		//		        InternetAddress.parse("user21@gmail.com, user22@gmail.com");
		//		InternetAddress[] bccEmailAddresses = 
		//		        InternetAddress.parse("user31@gmail.com");

		message.setRecipients(Message.RecipientType.TO,toEmailAddresses);
		//		message.setRecipients(Message.RecipientType.CC,ccEmailAddresses);
		//		message.setRecipients(Message.RecipientType.BCC,bccEmailAddresses);

		return message;
	}
	
	public void sendEmail() {
		Session mailSession = setSessionObject();
		try {
			MimeMessage message = createEmail(mailSession);
			Transport.send(message);
			System.out.println("Onek henostha kortasos");
		} catch (Exception e) {
			System.out.println("Message is " + e.getMessage());
			System.out.println("Cause is " + e.getCause());
			e.printStackTrace();
		}
	}
}
