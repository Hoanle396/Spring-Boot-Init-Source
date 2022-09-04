package com.courses.edu.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners({ AuditingEntityListener.class })
@Data
public abstract class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createAt = new Date();

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updateAt = new Date();

}