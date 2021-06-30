package edu.gdou.equipmentmanage.dao;

import edu.gdou.equipmentmanage.bean.Equipment;
import edu.gdou.equipmentmanage.bean.Erepair;
import edu.gdou.equipmentmanage.bean.Ereservation;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface EquipmentDao {

    //查询指定次序的器材
    List<Equipment> queryEquipmentLimit(int offset, int length);

    //查询所有器材总数
    public int queryEquipmentNum();

    //新增器材
    public boolean addEquipment(Equipment equipment);

    //查询所有器材
    public List<Equipment> queryEquipmentAll();

    //根据类型和租用时间查询器材
    public List<Equipment> queryEquipmentByEno(String eno);

    //根据器材编号（eno）删除器材
    public  boolean deleteEquipmentByEno(String eno);

    //根据器材编号（eno）更新器材
    public  boolean updateEquipmentByEno(String eno, String sitution);

    //根据器材类型和当前状态查询器材
    public List<Equipment> queryEquipmentByEnEs(int offset, int length,String type,String sitution);

    //根据器材类型和当前状态查询器材总数
    public int queryEquipmentNum111(String type,String sitution);

    //获取器材种类
    public List<String> queryEquipmentEtype();

    //新增租用订单
    public boolean addEreservation(Ereservation ereservation);

    //添加维修订单
    public boolean addErepair(Erepair erepair);

    //根据学号和器材编号查询租用表
    public List<Ereservation> queryEreservationByMatchId(String userid);

    //根据学号和器材编号查询租用表
    public List<Ereservation> queryEreservationByEnUser(int offset, int length,String eno,String userid);

    //根据器材编号和学号查询租用订单总数
    public int queryEreservationNum(String eno,String userid);


    //根据器材编号删除租用表记录
    public boolean deleteEreservationByEno(String eno);

    //根据器材编号和器材类型查询维修记录
    public List<Erepair> queryErepairByEnEt(int offset, int length,String eno,String type);

    //根据器材编号和器材类型查询维修订单总数
    public int queryErepairNum(String eno,String type);

    //根据器材编号删除维修记录
    public boolean deleteErepairByEno(String eno);

}
