package myPLS.utils;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;
import myPLS.main.mainApp;

public class MailUtil {

	/**
	 * This sendEmail method for sending email to registered users
	 * @param session session number
	 * @param toEmail email address of users
	 * @param subject subject of email
	 * @param paramMap mapping parameter
	 * @throws TemplateNotFoundException handle  TemplateNotFound exception
	 * @throws MalformedTemplateNameException handle exception
	 * @throws ParseException handle parse exception
	 * @throws IOException handle IO Exception 
	 * @throws TemplateException handle Template exception
	 */
public void sendEmail(Session session, String toEmail, String subject, Map<String,String> paramMap) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
	try
    {
      MimeMessage msg = new MimeMessage(session);
      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
      msg.addHeader("format", "flowed");
      msg.addHeader("Content-Transfer-Encoding", "8bit");
      msg.setFrom(new InternetAddress("no_reply", "NoReply-"));
      msg.setReplyTo(InternetAddress.parse("no_reply", false));
      msg.setSubject(subject, "UTF-8");
      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
		// Free marker Template
		Configuration cfg = new Configuration();
        cfg.setClassForTemplateLoading(mainApp.class, "/templates/");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        Template template = cfg.getTemplate("mailTemplate.ftl");
        Writer out = new StringWriter();
        template.process(paramMap, out);
        BodyPart body = new MimeBodyPart();
        body.setContent(out.toString(), "text/html");
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(body);
        msg.setContent(multipart);
        Transport.send(msg);
        System.out.println("EMail Sent Successfully!!");
    }
    catch (Exception e) {
      e.printStackTrace();
    }
}


}
