package net.study.service.currentuser;

import net.study.domain.CurrentUser;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:40 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

public interface CurrentUserService {

    boolean canAccessUser(CurrentUser currentUser, Long userId);

}