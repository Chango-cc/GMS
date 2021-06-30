package edu.gdou.equipmentmanage.service;


import edu.gdou.equipmentmanage.bean.Equipment;
import edu.gdou.equipmentmanage.bean.Erepair;
import edu.gdou.equipmentmanage.bean.Ereservation;
import edu.gdou.equipmentmanage.dao.EquipmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentImpl implements EquipmentService {
    @Autowired
    private EquipmentDao equipmentDao;

    @Override
    public boolean addEquipment(Equipment equipment) {
        return equipmentDao.addEquipment(equipment);
    }

    @Override
    public List<Equipment> queryEquipmentByEno(String eno) {
        return equipmentDao.queryEquipmentByEno(eno);
    }

    @Override
    public boolean deleteEquipmentByEno(String eno) {
        return equipmentDao.deleteEquipmentByEno(eno);
    }

    @Override
    public boolean updateEquipmentByEno(String eno, String sitution) {
        return equipmentDao.updateEquipmentByEno(eno,sitution);
    }

    @Override
    public List<Equipment> queryEquipmentAll() {
        return equipmentDao.queryEquipmentAll();
    }

    @Override
    public List<Equipment> queryEquipmentLimit(int offset, int length) {
        return equipmentDao.queryEquipmentLimit(offset,length);
    }

    @Override
    public int queryEquipmentNum() {
        return equipmentDao.queryEquipmentNum();
    }

    @Override
    public List<Equipment> queryEquipmentByEnEs(int offset, int length, String type, String sitution) {
        return equipmentDao.queryEquipmentByEnEs(offset,length,type,sitution);
    }

    @Override
    public int queryEquipmentNum111(String type, String sitution) {
        return equipmentDao.queryEquipmentNum111(type,sitution);
    }

    @Override
    public List<String> queryEquipmentEtype(){
        return equipmentDao.queryEquipmentEtype();
    }

    @Override
    public boolean addEreservation(Ereservation ereservation) {
        return equipmentDao.addEreservation(ereservation);
    }

    @Override
    public boolean addErepair(Erepair erepair) {
        return equipmentDao.addErepair(erepair);
    }

    @Override
    public List<Ereservation> queryEreservationByEnUser(int offset, int length, String eno, String userid) {
        return equipmentDao.queryEreservationByEnUser(offset,length,eno,userid);
    }

    @Override
    public List<Ereservation> queryEreservationByMatchId(String matchId) {
        return equipmentDao.queryEreservationByMatchId(matchId);
    }

    @Override
    public int queryEreservationNum(String eno, String userid) {
        return equipmentDao.queryEreservationNum(eno,userid);
    }

    @Override
    public boolean deleteEreservationByEno(String eno) {
        return equipmentDao.deleteEreservationByEno(eno);
    }

    @Override
    public List<Erepair> queryErepairByEnEt(int offset, int length, String eno, String type) {
        return equipmentDao.queryErepairByEnEt(offset,length,eno,type);
    }

    @Override
    public int queryErepairNum(String eno, String type) {
        return equipmentDao.queryErepairNum(eno,type);
    }

    @Override
    public boolean deleteErepairByEno(String eno) {
        return equipmentDao.deleteErepairByEno(eno);
    }

}
