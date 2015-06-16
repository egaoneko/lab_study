package net.study.domain;

import javax.persistence.*;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/17/15 | 2:25 AM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Entity
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private String realPath;

    @Column(nullable = false)
    private Long fileSize;

    @Column(nullable = false)
    private Integer downloadCount;

    @OneToOne
    User user;

    public File() {
    }

    public File(String fileName, String realPath, Long fileSize, Integer downloadCount) {
        this.fileName = fileName;
        this.realPath = realPath;
        this.fileSize = fileSize;
        this.downloadCount = downloadCount;
    }

    public File(String fileName, String realPath, Long fileSize, Integer downloadCount, User user) {
        this.fileName = fileName;
        this.realPath = realPath;
        this.fileSize = fileSize;
        this.downloadCount = downloadCount;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Integer getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(Integer downloadCount) {
        this.downloadCount = downloadCount;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
