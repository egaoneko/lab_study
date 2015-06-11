package net.study.service.study;

import net.study.domain.Study;
import net.study.domain.form.StudyCreateForm;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 6/10/15 | 12:49 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface StudyService {

    Study create(StudyCreateForm form);

    Study update(StudyCreateForm form);

    StudyCreateForm convertStudyToStudyCreateForm(Study study);
}
