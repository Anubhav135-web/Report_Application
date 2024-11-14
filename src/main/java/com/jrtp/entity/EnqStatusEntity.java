package com.jrtp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ENQ_STATUS")
public class EnqStatusEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Integer statusId;
	String statusname;
	public Integer getStatusId() {
		return statusId;
	}
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
	}
	public String getStatusname() {
		return statusname;
	}
	public void setStatusname(String statusname) {
		this.statusname = statusname;
	}
	@Override
	public String toString() {
		return "EnqStatusEntity [statusId=" + statusId + ", statusname=" + statusname + "]";
	}

}
