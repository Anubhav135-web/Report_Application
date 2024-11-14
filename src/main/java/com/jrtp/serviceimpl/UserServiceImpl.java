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
		UserDtlsEntity userdtlsentity1=userdtlsrepo.findByEmail(signupform.getemail());
		if(userdtlsentity1!=null) {
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

		boolean sent= emailutils.sendHtmlEmail(to, subject, text.toString());
		userdtlsrepo.save(userdtlsentity);
		if(sent) {
			return true;
		}
		return false;

	}

	@Override
	public String unlockUser(UnlockForm unlockform) {

		return null;
	}

	@Override
	public String forgotPassword(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String loginUser(LoginForm loginform) {
		// TODO Auto-generated method stub
		return null;
	}

}
