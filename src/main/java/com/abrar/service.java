package com.abrar;

import java.sql.SQLException;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class service {
@RequestMapping("/send")
public ModelAndView service(HttpServletRequest request, HttpServletRequest response) throws ClassNotFoundException, SQLException {
	String msg = request.getParameter("msg");
	HttpSession session = response.getSession();
	String UID = (String)session.getAttribute("UID");
	methods mthd = new methods();
	if(!msg.equals(null)) {
		
		
			 new InsertToDB().insert(msg,UID);
			
	}

	ModelAndView mv = new ModelAndView();
	mv.addObject("Name", mthd.getName(UID));
	mv.addObject("UID", UID);
	mv.setViewName("front.jsp");
	return mv;
}
@RequestMapping("/fetchUID")
public ModelAndView fetchUID(HttpServletRequest request, HttpServletRequest response) throws ClassNotFoundException, SQLException {
	ModelAndView mv = new ModelAndView();
	String UID = new methods().getUID(request.getParameter("Phone Number"));
	mv.addObject("UID", "Your Unique Identification ID is " + UID);
	if(UID.isBlank()) {
		mv.setViewName("Wrong Phone Number.jsp");
	}else {
	mv.setViewName("show.jsp");
	}
	return mv;
}
@RequestMapping("/login")
public ModelAndView login(HttpServletRequest request, HttpServletRequest response) throws ClassNotFoundException, SQLException {
	ModelAndView mv = new ModelAndView();
	String UID = request.getParameter("UID");
methods mthd = new methods();

boolean flag = mthd.isValid(UID);
if(flag) {
	mv.addObject("Name", mthd.getName(UID));
	mv.addObject("UID", UID);
	
	mv.setViewName("front.jsp");	
}else {


 mv.setViewName("index2.jsp");
}

	return mv;
}
@RequestMapping("/regsiterUser")
public ModelAndView registerUser(HttpServletRequest request, HttpServletRequest response) throws ClassNotFoundException, SQLException {
	ModelAndView mv = new ModelAndView();
	String FName = request.getParameter("Fname");
	String LName = request.getParameter("Lname");
	String mail = request.getParameter("mail");
	String number = request.getParameter("number");
	String condtn = new registerUser().register(FName, LName, mail, number);
	if(condtn.equals("null")) {
		mv.setViewName("alreadyRegistered.jsp");

	}else {
		condtn = "Your Unique Identification ID is "  + condtn; 
		mv.addObject("UID", condtn);
		mv.setViewName("show.jsp");

	}
	
	return mv;
}

}
