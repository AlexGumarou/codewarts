package com.codewarts.service;

import com.codewarts.dao.MainDao;
import com.codewarts.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("securityService")
@Transactional
public class SecurityService implements UserDetailsService {
    private MainDao mainDao;

    @Autowired
    public void setMainDao(MainDao mainDao) {
        this.mainDao = mainDao;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Staff staff = mainDao.getStaffByLogin(name);
        if (staff == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return Staff.fromUser(staff);
    }
}
