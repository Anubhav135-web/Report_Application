package com.jrtp.serviceimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.binding.LoginForm;
import com.jrtp.binding.SignUpForm;
import com.jrtp.binding.UnlockForm;
import com.jrtp.entity.UserDtlsEntity;
import com.jrtp.repository.CourseRepo;
import com.jrtp.repository.EnqStatusRepo;
import com.jrtp.repository.StudentEnqRepo;
import com.jrtp.repository.UserDtlsRepo;
import com.jrtp.service.UserService;
import com.jrtp.utils.EmailUtils;
import com.jrtp.utils.PwdUtils;

import jakarta.mail.MessagingException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDtlsRepo userdtlsrepo;
	@Autowired
	StudentEnqRepo studentenqrepo;
	@Autowired
	EnqStatusRepo enqstatusrepo;
	@Autowired
	CourseRepo courserepo;
	@Autowired
	EmailUtils emailutils;

	@Override
	public boolean signUpUser(SignUpForm signupform) throws MessagingException {
		UserDtlsEntity userdtlsentity1 = userdtlsrepo.findByEmail(signupform.getemail());
		if (userdtlsentity1 != null) {
			return false;
		}
		UserDtlsEntity userdtlsentity = new UserDtlsEntity();
		String tempas = PwdUtils.generatePwd();

		userdtlsentity.setAccstatus("Locked");
		userdtlsentity.setPassword(tempas);
		BeanUtils.copyProperties(signupform, userdtlsentity);
		String to = signupform.getemail();
		String subject = "Test mail";
		StringBuffer text = new StringBuffer("");
		text.append("Unlock Testing");
		text.append("<br/>");
		text.append(tempas);
		text.append("<br/>");
		text.append("<a href=\"http://localhost:8080/unlock?email=" + to + "\">Click here to unlock your account</a>");

		boolean sent = emailutils.sendHtmlEmail(to, subject, text.toString());
		userdtlsrepo.save(userdtlsentity);
		if (sent) {
			return true;
		}
		return false;

	}

	@Override
	public boolean unlockUser(UnlockForm unlockform) {
		UserDtlsEntity entity = userdtlsrepo.findByEmail(unlockform.getEmail());
		if(unlockform.getTempasword().equals(entity.getPassword())) {
			entity.setPassword(unlockform.getNewpasword());
			entity.setAccstatus("unlocked");
			userdtlsrepo.save(entity);
			return true;
		}
		else {
			return false;
		}
		
		}

	@Override
	public String forgotPassword(String email) {
	UserDtlsEntity entity=	userdtlsrepo.findByEmail(email);
	if(entity==null) {
		return "Email does not exist";
	}
	else {
		String pwd=entity.getPassword();
		String body="Please find below password";
		String text="Your password ::"+pwd;
		emailutils.sendHtmlEmail(email,body,text);
		return "success";
	}
	
	
		
	}
    
	@Override
	public String loginUser(LoginForm loginform) {
		UserDtlsEntity entity=userdtlsrepo.findByEmailAndPassword(loginform.getEmail(),loginform.getPassword());
		
		    if(entity==null){
		    	return "Invalid credentials";
		    }
		    if(entity.getAccstatus().equalsIgnoreCase("locked")) {
		    	return "your account locked";
		    }
			return "success";
		
	}

}
