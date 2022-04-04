package com.luyao.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.luyao.crowd.exception.LoginAcctAlreadyInUseException;
import com.luyao.crowd.exception.LoginFailedException;
import com.luyao.crowd.mapper.AdminMapper;
import com.luyao.crowd.pojo.Admin;
import com.luyao.crowd.pojo.AdminExample;
import com.luyao.crowd.service.api.AdminService;
import com.luyao.crowd.util.CrowdCourtConst;
import com.luyao.crowd.util.CrowdUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @Author yao
 * @create 2022-03-20
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    private Logger logger = LoggerFactory.getLogger(AdminServiceImpl.class);


    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)//事务注解增删改
    @Override
    public void saveAdmin(Admin admin) {
        //加密密码
        String encode = CrowdUtil.encode(admin.getUserPswd());
        admin.setUserPswd(encode);
        //生成创建时间
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = simpleDateFormat.format(date);
        admin.setCreateTime(time);
        //保存,异常处理
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("异常的全类名"+e.getClass().getName());
            if(e instanceof DuplicateKeyException){
                throw new LoginAcctAlreadyInUseException(CrowdCourtConst.MESSAGE_LOGIN_ACCT_ALREADY_IN_USE);
            }
        }
    }
    @Transactional(readOnly = true)//事务注解查询
    @Override
    public Admin queryById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Transactional(readOnly = true)
    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {
        //1.验证账号是否存在，以及是否出现错误
        AdminExample example = new AdminExample();
        example.createCriteria().andLoginEqualTo(loginAcct);
        List<Admin> list  = adminMapper.selectByExample(example);
        if (list==null||list.size()==0){
            throw new LoginFailedException(CrowdCourtConst.MESSAGE_LOGIN_FAILED);
        }
        if (list.size()>1){
            throw new RuntimeException(CrowdCourtConst.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }
        //2.验证账号是否为null
        Admin admin = list.get(0);
        if (admin==null){
            throw new LoginFailedException(CrowdCourtConst.MESSAGE_LOGIN_FAILED);
        }
        //3.取出数据库的密码值
        String userPswdDB = admin.getUserPswd();
        //4.对表单值进行加密
        String encode = CrowdUtil.encode(userPswd);
        //5.判断加密值是否相等
        if (!(Objects.equals(userPswdDB, encode))){
            throw new LoginFailedException(CrowdCourtConst.MESSAGE_LOGIN_FAILED);
        }
        return admin;
    }
    @Transactional(readOnly = true)
    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        //开启分页并设置参数
        PageHelper.startPage(pageNum, pageSize);
        //查询数据
        AdminExample example = new AdminExample();
        example.createCriteria().andLoginLike("%"+keyword+"%");
        example.or().andUserNameLike("%"+keyword+"%");
        example.or().andEmailLike("%"+keyword+"%");
        List<Admin> list = adminMapper.selectByExample(example);
        //进行分页
        PageInfo<Admin> pageInfo = new PageInfo<>(list,5);
        return pageInfo;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void remove(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void edit(Admin admin) {
        //有选择的更新，对于Null值不更新
        adminMapper.updateByPrimaryKeySelective(admin);
    }
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    @Override
    public void saveAdminRoleRelationship(Integer adminId, List<Integer> roleIdList) {

        // 1. 根据adminId删除旧的关联关系数据
        adminMapper.deleteOldRelationship(adminId);

        // 2.根据roleIdList和adminId保存新的关联关系
        if (roleIdList != null && roleIdList.size()>0) {
            adminMapper.insertNewRelationship(adminId, roleIdList);
        }
    }
}
