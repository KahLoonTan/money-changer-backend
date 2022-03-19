package com.rwsentosa.moneychanger.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import com.rwsentosa.moneychanger.constant.Constant;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import org.hibernate.annotations.Nationalized;

@Getter
@Setter
@MappedSuperclass
@NoArgsConstructor
@ToString(callSuper = true)
@Log4j2
public class BaseEntity implements Serializable {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  @Id
  protected Long id;

  @Nationalized
  @Column(name = "created_by", nullable = false)
  protected String createdBy;

  @Column(name = "created_time", nullable = false)
  protected OffsetDateTime createdTime;

  @Nationalized
  @Column(name = "modified_by", nullable = false)
  protected String modifiedBy;

  @Column(name = "modified_time", nullable = false)
  protected OffsetDateTime modifiedTime;

  @Column(name = "record_status", nullable = false)
  protected int recordStatus = Constant.RECORD_STATUS_ACTIVE;

  @Column(name = "version", nullable = false)
  @Version
  protected int version;

}
