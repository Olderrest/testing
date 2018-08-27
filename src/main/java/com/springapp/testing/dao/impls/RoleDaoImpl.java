package com.springapp.testing.dao.impls;


import com.springapp.testing.dao.RoleDao;
import com.springapp.testing.model.Role;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao{
    private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Role findRoleById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = (Role) session.get(Role.class, new Integer(id));
        if (role != null){
            LOGGER.info("Role successfully found. Role info: " + role);
        }else {
            LOGGER.info("Role not found");
        }
        return role;
    }
}
