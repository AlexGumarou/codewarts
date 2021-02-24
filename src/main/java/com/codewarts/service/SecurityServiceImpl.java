package com.codewarts.service;

import com.codewarts.dao.MainDaoImpl;
import com.codewarts.entity.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("securityService")
@Transactional
public class SecurityServiceImpl implements UserDetailsService {
    private MainDaoImpl mainDaoImpl;

    @Autowired
    public void setSecurityDao(MainDaoImpl mainDaoImpl) {
        this.mainDaoImpl = mainDaoImpl;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Staff staff = mainDaoImpl.getAllStaff(name);
        if (staff == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return Staff.fromUser(staff);
    }
}
