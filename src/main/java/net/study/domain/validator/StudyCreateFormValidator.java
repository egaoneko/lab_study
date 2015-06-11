package net.study.domain.validator;

import net.study.domain.form.StudyCreateForm;
import net.study.service.study.StudyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/10/15 | 12:46 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Component
public class StudyCreateFormValidator implements Validator {

    private static final Logger LOGGER = LoggerFactory.getLogger(StudyCreateFormValidator.class);
    private  final StudyService studyService;

    @Autowired
    public StudyCreateFormValidator(StudyService studyService){this.studyService = studyService;}

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(StudyCreateForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LOGGER.debug("Validating {}", target);
        StudyCreateForm form = (StudyCreateForm) target;
        validatePrice(errors, form);
        validateParticipant(errors, form);
    }

    private void validatePrice(Errors errors, StudyCreateForm form) {
        if (form.getPrice() < 0) {
            errors.reject("price.wrong", "Can not input the price under 0");
        }
    }

    private void validateParticipant(Errors errors, StudyCreateForm form) {
        if (form.getParticipant() < 1) {
            errors.reject("participant.wrong", "Can not input the participant under 0");
        }
    }
}
