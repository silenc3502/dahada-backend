package com.dahada.backend.application.partners.entity;

import com.dahada.backend.domain.calculation.entity.CalculationState;
import com.dahada.backend.domain.user.enitity.UserRole;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Slf4j
@Entity
@Table(name = "dahada_partner")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long partnerId;

    @Getter
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String addressDetail;

    @Column(nullable = false)
    private String zipcode;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.DAHADA_PARTNER;

    @Column(nullable = false)
    private String companyRegistrationNumber;

    @Column(nullable = false)
    private String businessName;

    @Column(nullable = false)
    private String companyName;

    @Column(nullable = false)
    private String representativeName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String businessCondition;

    @Column(nullable = false)
    private String businessCategory;

    @Column(nullable = false)
    private String bankName;

    @Column(nullable = false)
    private String depositorName;

    @Column(nullable = false)
    private String accountNumber;

    @CreationTimestamp
    private Date regDate;
    @UpdateTimestamp
    private Date updDate;

    public Partner(String email, String city, String street, String addressDetail, String zipcode,
                   String companyRegistrationNumber, String businessName, String companyName,
                   String representativeName, String phoneNumber, String businessCondition,
                   String businessCategory, String bankName, String depositorName, String accountNumber) {

        this.email = email;
        this.city = city;
        this.street = street;
        this.addressDetail = addressDetail;
        this.zipcode = zipcode;
        this.companyRegistrationNumber = companyRegistrationNumber;
        this.businessName = businessName;
        this.companyName = companyName;
        this.representativeName = representativeName;
        this.phoneNumber = phoneNumber;
        this.businessCondition = businessCondition;
        this.businessCategory = businessCategory;
        this.bankName = bankName;
        this.depositorName = depositorName;
        this.accountNumber = accountNumber;
    }
}