package net.study.service.user;

import net.study.domain.Assets;
import net.study.domain.User;
import net.study.domain.form.UserCreateForm;
import net.study.domain.form.UserUpdatePasswordForm;
import net.study.repository.AssetsRepository;
import net.study.repository.UserRepository;
import net.study.util.identicon.IdenticonGeneratorUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * First Editor : Donghyun Seo (egaoneko@naver.com)
 * Last Editor  :
 * Date         : 5/31/15 | 1:42 PM
 * Description  :
 * Copyright ⓒ 2013-2015 Donghyun Seo All rights reserved.
 * version      :
 */

@Service
@PropertySource("classpath:file.properties")
public class UserServiceImpl implements UserService {

    @Value("${file.identicon.filePath}")
    private String filePath;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final IdenticonGeneratorUtil identiconGeneratorUtil;
    private final AssetsRepository assetsRepository;

    @Autowired
    public UserServiceImpl(AssetsRepository assetsRepository, IdenticonGeneratorUtil identiconGeneratorUtil, UserRepository userRepository) {
        this.assetsRepository = assetsRepository;
        this.identiconGeneratorUtil = identiconGeneratorUtil;
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> getUserById(long id) {
        LOGGER.debug("Getting user={}", id);
        return Optional.ofNullable(userRepository.findOne(id));
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        LOGGER.debug("Getting user by email={}", email.replaceFirst("@.*", "@***"));
        return userRepository.findOneByEmail(email);
    }

    @Override
    public Collection<User> getAllUsers() {
        LOGGER.debug("Getting all users");
        return userRepository.findAll(new Sort("email"));
    }

    @Override
    public User create(UserCreateForm form){
        User user = new User();
        user.setEmail(form.getEmail());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        user.setRole(form.getRole());
        user.setName(form.getName());
        user.setCreatedDate(new Date());
        user.setLastDate(new Date());

        Map<String, String> fileMap;
        try{
            fileMap = identiconGeneratorUtil.generator(form.getEmail());
        } catch (IOException e){
            return userRepository.save(user);
        }

        File avatar = new File(filePath + fileMap.get("realPath"));

        Assets assets = new Assets(fileMap.get("fileName"), fileMap.get("realPath"), avatar.length(), 0);
        assets = assetsRepository.save(assets);

        user.setAssets(assets);
        user=userRepository.save(user);

        assets.setUser(user);
        assetsRepository.save(assets);

        return user;
    }

    @Override
    public User updatePassword(UserUpdatePasswordForm form) {
        User user = userRepository.getOne(form.getId());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        return userRepository.save(user);
    }
}
