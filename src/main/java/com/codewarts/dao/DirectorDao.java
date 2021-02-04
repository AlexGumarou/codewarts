package com.codewarts.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DirectorDao {
    @Autowired
    private SessionFactory sessionFactory;
}
