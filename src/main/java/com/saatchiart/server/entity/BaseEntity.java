package com.saatchiart.server.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name="createby")
  
    private String createdBy;

    @Getter @Setter
    @CreatedDate
    @Column(name="createdate")
    private Date createdDate;

    @Getter @Setter
    @Column(name="modifiedby")
    private String modifiedBy;

    @Getter @Setter
    @LastModifiedDate
    @Column(name="modifieddate")
    private Date modifiedDate;
}
