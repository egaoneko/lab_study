package net.study.service.user;

import net.study.domain.File;
import net.study.domain.User;
import net.study.domain.form.UserCreateForm;
import net.study.domain.form.UserUpdatePasswordForm;
import net.study.repository.FileRepository;
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
    private final FileRepository fileRepository;

    @Autowired
    public UserServiceImpl(FileRepository fileRepository, IdenticonGeneratorUtil identiconGeneratorUtil, UserRepository userRepository) {
        this.fileRepository = fileRepository;
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

        java.io.File avatar = new java.io.File(filePath + fileMap.get("realPath"));

        File file = new File(fileMap.get("fileName"), fileMap.get("realPath"), avatar.length(), 0);
        file = fileRepository.save(file);

        user.setFile(file);
        user=userRepository.save(user);

        file.setUser(user);
        fileRepository.save(file);

        return user;
    }

    @Override
    public User updatePassword(UserUpdatePasswordForm form) {
        User user = userRepository.getOne(form.getId());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
        return userRepository.save(user);
    }
}
