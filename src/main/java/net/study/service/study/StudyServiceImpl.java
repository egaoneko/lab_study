package net.study.service.study;

import net.study.domain.Study;
import net.study.domain.form.StudyCreateForm;
import net.study.repository.StudyRepository;
import net.study.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/10/15 | 12:56 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
public class StudyServiceImpl implements StudyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyServiceImpl.class);
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @Autowired
    public StudyServiceImpl(StudyRepository studyRepository, UserRepository userRepository) {
        this.studyRepository = studyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Study create(StudyCreateForm form) {
        Study study = new Study();
        study.setTitle(form.getTitle());
        study.setContent(form.getContent());
        study.setPostingDate(new Date());
        study.setUser(userRepository.findOne(form.getUserId()));
        study.setCategory(form.getCategory());
        study.setArea(form.getArea());
        study.setCharge(form.getCharge());
        study.setOnOffLine(form.getOnOffLine());
        study.setWay(form.getWay());
        study.setPrice(form.getPrice());
        study.setParticipant(form.getParticipant());
        study.setStatus(form.getStatus());

        return studyRepository.save(study);
    }

    @Override
    public Study update(StudyCreateForm form) {

        Study study = studyRepository.findOne(form.getId());
        study.setTitle(form.getTitle());
        study.setContent(form.getContent());
        study.setCategory(form.getCategory());
        study.setArea(form.getArea());
        study.setCharge(form.getCharge());
        study.setOnOffLine(form.getOnOffLine());
        study.setWay(form.getWay());
        study.setPrice(form.getPrice());
        study.setParticipant(form.getParticipant());
        study.setStatus(form.getStatus());

        return studyRepository.save(study);
    }

    @Override
    public StudyCreateForm convertStudyToStudyCreateForm(Study study) {
        StudyCreateForm studyCreateForm = new StudyCreateForm();

        studyCreateForm.setId(study.getId());
        studyCreateForm.setTitle(study.getTitle());
        studyCreateForm.setContent(study.getContent());
        studyCreateForm.setUserId(study.getUser().getId());
        studyCreateForm.setCategory(study.getCategory());
        studyCreateForm.setArea(study.getArea());
        studyCreateForm.setCharge(study.getCharge());
        studyCreateForm.setOnOffLine(study.getOnOffLine());
        studyCreateForm.setWay(study.getWay());
        studyCreateForm.setPrice(study.getPrice());
        studyCreateForm.setParticipant(study.getParticipant());
        studyCreateForm.setStatus(study.getStatus());

        return studyCreateForm;
    }
}
