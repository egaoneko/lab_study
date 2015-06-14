package net.study.domain;

import net.study.domain.enums.ValidEntity;

import javax.persistence.*;
import java.util.Date;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/14/15 | 9:53 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class ForgotPassword {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String keyHash;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date expiredDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ValidEntity validEntity;

    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKeyHash() {
        return keyHash;
    }

    public void setKeyHash(String keyHash) {
        this.keyHash = keyHash;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public ValidEntity getValidEntity() {
        return validEntity;
    }

    public void setValidEntity(ValidEntity validEntity) {
        this.validEntity = validEntity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
