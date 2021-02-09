package com.codewarts.dao;

import com.codewarts.entity.Accounting;
import com.codewarts.entity.Department;
import com.codewarts.entity.Staff;
import com.codewarts.entity.StaffRole;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DirectorDao {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Staff> getAllStaff() {
        return sessionFactory.getCurrentSession().createQuery("from Staff", Staff.class).list();
    }

    public void addStaff(Staff staff, int idRole, int idDepartment) {
        StaffRole staffRole = sessionFactory.getCurrentSession().get(StaffRole.class, idRole);
        Department department = sessionFactory.getCurrentSession().get(Department.class, idDepartment);
        staff.setStaffRole(staffRole);
        staff.setDepartment(department);
        sessionFactory.getCurrentSession().saveOrUpdate(staff);
    }

    public List<Department> getAllDepartments() {
        return sessionFactory.getCurrentSession().createQuery("from Department ", Department.class).list();
    }

    public List<StaffRole> getAllStaffRoles() {
        return sessionFactory.getCurrentSession().createQuery("from StaffRole", StaffRole.class).list();
    }

    public List<Staff> getAllTeachers(Department department) {
        return sessionFactory.getCurrentSession().createQuery("from Staff s where s.department = :department and s.staffRole.role = :teacher")
                .setParameter("department", department)
                .setParameter("teacher", "TEACHER")
                .list();
    }

    public List<Accounting> getAllHoursByTeacher(int idTeacher, LocalDate dateFrom, LocalDate dateTo) {
        return sessionFactory.getCurrentSession().createQuery("from Accounting a where a.staff.id = :id and a.date" +
                " between :dateFrom and :dateTo")
                .setParameter("id", idTeacher)
                .setParameter("dateFrom", dateFrom)
                .setParameter("dateTo", dateTo).list();
    }

    public int getPricePerHourByTeacher(int idTeacher) {
        Staff staff = sessionFactory.getCurrentSession().get(Staff.class, idTeacher);
        return staff.getPricePerHour();
    }
}
