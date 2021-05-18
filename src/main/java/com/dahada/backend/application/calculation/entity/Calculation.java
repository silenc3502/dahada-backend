package com.dahada.backend.application.calculation.entity;

import com.dahada.backend.domain.calculation.entity.CalculationState;
import com.dahada.backend.domain.common.vo.Address;
import com.dahada.backend.domain.user.enitity.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode(of="calculationNo")
@ToString
@Entity
public class Calculation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long calculationNo;

    @CreationTimestamp
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;

    /*
    @Embedded
    private Address address = null;
     */

    private String city;
    private String street;
    private String addressDetail;
    private String zipcode;

    private String workList;

    private Long payment;

    // 상태값엔 결제대기/결제완료 두 가지를 놓도록 한다.
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CalculationState state = CalculationState.WAIT;

    private String etcs;
}