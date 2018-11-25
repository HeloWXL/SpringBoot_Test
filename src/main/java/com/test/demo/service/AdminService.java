package com.test.demo.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.test.demo.mapper.AdminMapper;
import com.test.demo.model.Admin;
import com.test.demo.model.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangxl
 * @date 2018/11/24
 */
@Service
public class AdminService {
    @Resource
    private AdminMapper adminMapper;

    /**
     * 管理员登录检查
     *
     * @param admin
     * @return
     */
    public Admin checkAdmin(Admin admin) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("admin_name", admin.getAdminName());
        entityWrapper.eq("admin_password", admin.getAdminPassword());
        List<Admin> studentList = adminMapper.selectList(entityWrapper);
        if (studentList.size() > 0) {
            return studentList.get(0);
        } else {
            return null;
        }
    }

    /**
     * 检查管理员是否已经能存在
     *
     * @param name
     * @return
     */
    public Admin getAdminByName(String name) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("admin_name", name);
        List<Admin> admin = adminMapper.selectList(entityWrapper);
        return admin.get(0);
    }

    /**
     * 管理员注册
     *
     * @param name
     * @param password
     * @return int
     */
    public int insertAdmin(String name, String password) {
        Admin admin = new Admin();
        admin.setAdminName(name);
        admin.setAdminPassword(password);
        return adminMapper.insert(admin);
    }


    /**
     * 获取管理员列表
     *
     * @return
     */
    public Map<String, Object> getAdminList() {
        EntityWrapper entityWrapper = new EntityWrapper();
        List<Admin> admin = adminMapper.selectList(entityWrapper);
        int count = adminMapper.selectCount(entityWrapper);
        Map<String, Object> map = new HashMap<>();
        map.put("list", admin);
        map.put("count", count);
        return map;
    }

    /**
     * //删除管理员
     *
     * @param id
     * @return int
     */
    public int deleteAdmin(Integer id) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("admin_id", id);
        return adminMapper.delete(entityWrapper);
    }


    /**
     * 更新管理员信息
     *
     * @param admin
     * @return
     */
    public int updateAdmin(Admin admin) {
        EntityWrapper entityWrapper = new EntityWrapper();
        entityWrapper.eq("admint_id", admin.getAdminId());
        return adminMapper.update(admin, entityWrapper);
    }


}
