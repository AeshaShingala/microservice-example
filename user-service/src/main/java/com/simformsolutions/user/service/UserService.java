package com.simformsolutions.user.service;

import com.simformsolutions.user.entity.User;
import com.simformsolutions.user.repository.UserRepository;
import com.simformsolutions.user.valueobject.Department;
import com.simformsolutions.user.valueobject.ResponseTemplateVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestTemplate restTemplate;

    public User saveUser(User user) {
        log.info("Inside saveUser of UserService");
        return userRepository.save(user);
    }

    public ResponseTemplateVO getUserWithDepartment(Long userId) {
        log.info("Inside getUserWithDepartment of UserService");
        ResponseTemplateVO vo= new ResponseTemplateVO();
        User user=userRepository.findByUserId(userId);

        Department department= restTemplate.getForObject("http://DEPARTMENT-SERVICE/departments/"+user.getDepartmentId(),Department.class);
        vo.setUser(user);
        vo.setDepartment(department);
        return vo;
    }
}
