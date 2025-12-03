package com.example.demo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;

@Entity
public class DemoEntity {

	public void DemoEntity() {
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

// 		@JsonAlias({"end_date"})
	private LocalDateTime endDate;
	
	@Override
	public String toString() {
		return "Tenant [id=" + id + ", endDate=" + endDate + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}
}