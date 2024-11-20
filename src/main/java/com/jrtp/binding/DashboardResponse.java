package com.jrtp.binding;

public class DashboardResponse {
	
	Integer userid;
	Integer totalenquiry;
	Integer enrolled;
	Integer lost;
	
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getTotalenquiry() {
		return totalenquiry;
	}
	public void setTotalenquiry(Integer totalenquiry) {
		this.totalenquiry = totalenquiry;
	}
	public Integer getEnrolled() {
		return enrolled;
	}
	public void setEnrolled(Integer enrolled) {
		this.enrolled = enrolled;
	}
	public Integer getLost() {
		return lost;
	}
	public void setLost(Integer lost) {
		this.lost = lost;
	}
	@Override
	public String toString() {
		return "DashboardResponse [userid=" + userid + ", totalenquiry=" + totalenquiry + ", enrolled=" + enrolled
				+ ", lost=" + lost + "]";
	}
	

}
