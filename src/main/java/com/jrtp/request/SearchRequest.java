package com.jrtp.request;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;
@Data
public class SearchRequest {
	private String planname;
	private String planstatus;
	private String gender;
	private LocalDate planstartdate;
	private LocalDate planenddate;

}
