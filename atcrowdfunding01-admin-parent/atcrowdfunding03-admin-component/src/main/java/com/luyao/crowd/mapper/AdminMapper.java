package com.luyao.crowd.mapper;

import com.luyao.crowd.pojo.Admin;
import com.luyao.crowd.pojo.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    long countByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int deleteByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int insert(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int insertSelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    List<Admin> selectByExample(AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    Admin selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_admin
     *
     * @mbg.generated Wed Mar 16 17:22:17 CST 2022
     */
    int updateByPrimaryKey(Admin record);

    /**
     * 删除原有的admin的角色
     * @param adminId
     */
    void deleteOldRelationship(@Param("adminId") Integer adminId);

    /**
     * 保存新的关联关系
     * @param adminId
     * @param roleIdList
     */
    void insertNewRelationship(@Param("adminId") Integer adminId,@Param("roleIdList") List<Integer> roleIdList);
}