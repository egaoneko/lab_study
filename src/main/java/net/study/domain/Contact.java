package net.study.domain;

import net.study.domain.enums.ContactType;
import net.study.domain.enums.ValidEntity;

import javax.persistence.*;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/18/15 | 4:14 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ContactType contactType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ValidEntity validEntity;

    @OneToOne
    private User user;

    public Contact() {
    }

    public Contact(String content, ContactType contactType, ValidEntity validEntity, User user) {
        this.content = content;
        this.contactType = contactType;
        this.validEntity = validEntity;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ContactType getContactType() {
        return contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
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
