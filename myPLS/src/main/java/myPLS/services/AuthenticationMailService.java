package myPLS.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import freemarker.template.TemplateException;
import myPLS.utils.MailUtil;

public class AuthenticationMailService {
	private static MailUtil mailService = new MailUtil();

	public void sendAuthMail(String name, String toEmail,String role){
		final String fromEmail = "swen610.chasers@gmail.com"; 
		final String password = "swen610@";
		Properties props = new Properties();
		try {
			// props.load(new FileInputStream(new File("mail.properties")));
			props.load(new FileInputStream(new File("mail.properties")));
			Authenticator auth = new Authenticator() {
				//override the getPasswordAuthentication method
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(fromEmail, password);
				}
			};
			Session session = Session.getDefaultInstance(props, auth);
			HashMap<String, String> user = new HashMap<String, String>();
			user.put("name", name);
			user.put("email",toEmail);
			user.put("role", role);
		    mailService.sendEmail(session, toEmail,"MyPLS email Verification", user);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		
	}
}
