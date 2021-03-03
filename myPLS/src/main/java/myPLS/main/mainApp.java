package myPLS.main;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.io.IOException;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.TemplateException;
import freemarker.template.TemplateNotFoundException;
import myPLS.controllers.RegistrationController;
import static spark.Spark.*;

import spark.utils.IOUtils;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import spark.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;
// import static spark.Spark.*;
public class mainApp extends HttpServlet{
	
	private final static RegistrationController registraionController = new RegistrationController();
	public static int fileCount=0;
	public static void main(String[] args) throws TemplateNotFoundException, MalformedTemplateNameException, ParseException, IOException, TemplateException {
		get("/registerUser", (request, response) -> {
            return registraionController.getRegistrationPage();
        });
		
		post("/registerUser", (request, response) -> {
           return registraionController.registerUser(request);
        });
		
		post("/mailVerified",(request,response) -> {
			return registraionController.authoriseUser(request);
		});
		
		post("/resetPassword",(request,response) -> {
			return registraionController.resetPassword(request, response);
		});
		get("/dashboard", (request, response)->{
			return registraionController.getDashboard(request);
		});
		get("/loginPage", (request, response) -> {
            return registraionController.getLoginPage();
        });
        get("/index", (request, response) -> {
			response.redirect("/loginPage");
			return 0;
        });
        get("/", (request, response) -> {
			response.redirect("/loginPage");
			return 0;
        });
        post("/logIn",(request,response) -> {
            return registraionController.logIn(request, response);
        });
		
	}
}
