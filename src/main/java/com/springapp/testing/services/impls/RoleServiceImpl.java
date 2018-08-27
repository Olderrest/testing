package com.springapp.testing.services.impls;

import com.springapp.testing.dao.RoleDao;
import com.springapp.testing.model.Role;
import com.springapp.testing.services.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RoleServiceImpl implements RoleService {

    private RoleDao roleDao;

    public void setRoleDao(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    @Transactional
    public Role findRoleById(int id) {
        return this.roleDao.findRoleById(id);
    }
}
