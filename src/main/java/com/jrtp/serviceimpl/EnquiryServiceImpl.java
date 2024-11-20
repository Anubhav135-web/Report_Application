package com.jrtp.serviceimpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jrtp.binding.DashboardResponse;
import com.jrtp.binding.EnquiryForm;
import com.jrtp.binding.EnquirySearchCriteria;
import com.jrtp.entity.CourseEntity;
import com.jrtp.entity.EnqStatusEntity;
import com.jrtp.entity.StudentEnqEntity;
import com.jrtp.entity.UserDtlsEntity;
import com.jrtp.repository.CourseRepo;
import com.jrtp.repository.EnqStatusRepo;
import com.jrtp.repository.StudentEnqRepo;
import com.jrtp.repository.UserDtlsRepo;
import com.jrtp.service.EnquiryService;

import jakarta.servlet.http.HttpSession;

@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	StudentEnqRepo stuenqrepo;
	@Autowired
	UserDtlsRepo userdtlsrepo;
	@Autowired
	EnqStatusRepo enqstatusrepo;
	@Autowired
	CourseRepo courserepo;
	@Autowired
	HttpSession session;
//   @Autowired
//   UserDtlsEntity userdtlsentity;
	DashboardResponse response = new DashboardResponse();

	@Override
	public List<String> getCourseName() {
		List<CourseEntity> courseentity = courserepo.findAll();
		EnquiryForm form = new EnquiryForm();
		List<String> names = new ArrayList<>();
		for (CourseEntity coursename : courseentity) {
			names.add(coursename.getCourseName());
		}

		return names;
	}

	@Override
	public List<String> getEnqStatus() {
		List<EnqStatusEntity> enqstatusentity = enqstatusrepo.findAll();
		List<String> status = new ArrayList<>();
		for (EnqStatusEntity enqstatus : enqstatusentity) {
			status.add(enqstatus.getStatusname());
		}

		return status;
	}

	@Override
	public String addEnquiry(EnquiryForm form) {
		// Create a new enquiry entity

		StudentEnqEntity enquiry = new StudentEnqEntity();
		enquiry.setStuname(form.getStudentName());
		enquiry.setPhone(form.getPhone());
		enquiry.setMode(form.getMode());
		enquiry.setCourse(form.getCourse());
		enquiry.setStatus(form.getStatus());
		enquiry.setCreatedDate(LocalDate.now());
		enquiry.setUpdatedate(LocalDate.now());

		// Retrieve the userId from session
		Integer userId = (Integer) session.getAttribute("userId");
		if (userId == null) {
			throw new RuntimeException("User ID is not available in the session");
		}

		// Fetch the user entity
		Optional<UserDtlsEntity> userOptional = userdtlsrepo.findById(userId);
		if (userOptional.isEmpty()) {
			throw new RuntimeException("User not found for ID: " + userId);
		}

		// Set the user entity in the enquiry
		UserDtlsEntity userDtlsEntity = userOptional.get();
		enquiry.setUserdtlsentity(userDtlsEntity);

		// Save the enquiry
		stuenqrepo.save(enquiry);

		return "Enquiry Saved Successfully";
	}

	@Override
	public EnquiryForm editEnquiry(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DashboardResponse getDashboard(Integer userid) {
		DashboardResponse response = new DashboardResponse();
		Optional<UserDtlsEntity> findById = userdtlsrepo.findById(userid);
		if (findById.isPresent()) {
			UserDtlsEntity userentity = findById.get();
			List<StudentEnqEntity> enquiries = userentity.getEnquiries();
			Integer total = enquiries.size();
			Integer enrolled = enquiries.stream().filter(enq -> enq.getStatus().equalsIgnoreCase("enrolled"))
					.collect(Collectors.toList()).size();
			Integer lost = enquiries.stream().filter(enq -> enq.getStatus().equalsIgnoreCase("lost"))
					.collect(Collectors.toList()).size();
			response.setTotalenquiry(total);
			response.setEnrolled(enrolled);
			response.setLost(lost);

		}
		return response;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries() {
		Integer useId = (Integer) session.getAttribute("userId");
		Optional<UserDtlsEntity> user = userdtlsrepo.findById(useId);
		UserDtlsEntity userEntity=user.get();
		 List<StudentEnqEntity>enquiry=	userEntity.getEnquiries();
		return enquiry;
	}

	@Override
	public List<StudentEnqEntity> getfilteredenquiries(EnquirySearchCriteria criteria) {
	    Integer useId = (Integer) session.getAttribute("userId");
	    Optional<UserDtlsEntity> user = userdtlsrepo.findById(useId);
	    UserDtlsEntity userEntity = user.get();
	    List<StudentEnqEntity> enquiries = userEntity.getEnquiries();

	    return enquiries.stream()
	        .filter(enquiry -> 
	            (criteria.getCourse() == null || criteria.getCourse().isEmpty() || enquiry.getCourse().equalsIgnoreCase(criteria.getCourse())) &&
	            (criteria.getStatus() == null || criteria.getStatus().isEmpty() || enquiry.getStatus().equalsIgnoreCase(criteria.getStatus())) &&
	            (criteria.getMode() == null || criteria.getMode().isEmpty() || enquiry.getMode().equalsIgnoreCase(criteria.getMode()))
	        )
	        .collect(Collectors.toList());
	}
	public EnquiryForm getEnquiryById(Integer enquiryId) {
	    // Fetch the enquiry entity by ID
	    StudentEnqEntity enquiryEntity = stuenqrepo.findById(enquiryId)
	        .orElseThrow(() -> new IllegalArgumentException("Invalid Enquiry ID"));

	    // Convert the entity to the EnquiryForm
	    EnquiryForm enquiryForm = new EnquiryForm();
	    enquiryForm.setUserid(enquiryEntity.getUserdtlsentity().getUserid());
	    enquiryForm.setStudentName(enquiryEntity.getStudentName());
	    enquiryForm.setPhone(enquiryEntity.getPhone());
	    enquiryForm.setMode(enquiryEntity.getMode());
	    enquiryForm.setCourse(enquiryEntity.getCourse());
	    enquiryForm.setStatus(enquiryEntity.getStatus());

	    return enquiryForm;
	}


}
