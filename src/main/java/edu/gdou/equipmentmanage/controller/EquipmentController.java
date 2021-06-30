package edu.gdou.equipmentmanage.controller;

import edu.gdou.equipmentmanage.bean.Equipment;
import edu.gdou.equipmentmanage.bean.Erepair;
import edu.gdou.equipmentmanage.bean.Ereservation;
import edu.gdou.equipmentmanage.service.EquipmentService;
import edu.gdou.usermanage.entity.Gmsuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("equipment")
public class EquipmentController {
    @Autowired
    EquipmentService equipmentService;

    @GetMapping("rentEquipemnt")
    public String rentEquipemntPage(){
        return "equipment_query";
    }

    @GetMapping("queryEquipment")
    public String userqueryEquipmentPage(){
        return "equipment_queryrent_user";
    }

    @GetMapping("addEquipment")
    public String addEquipmentPage(){
        return "equipment_new";
    }

    @GetMapping("manageEquipment")
    public String manageEquipmentPage(){
        return "equipment_queryManager";
    }

    @GetMapping("mrecoveryEquipment")
    public String mrecoveryEquipmentPage(){
        return "equipment_recovery";
    }

    @GetMapping("mrepairedEquipment")
    public String mrepairedEquipmentPage(){
        return "equipment_repair";
    }





    //添加器材
    @RequestMapping("addEquipment")
    @ResponseBody
    public boolean addEquipment(@RequestBody Equipment equipment){
        System.out.println(equipment.getEno());
        String eno = equipment.getEno();
        if (equipmentService.queryEquipmentByEno(eno).size()>0){
            return false;
        }
        else {
            return equipmentService.addEquipment(equipment);
        }
    }

    @RequestMapping("addEreservation")
    @ResponseBody
    public boolean addEreservation(@RequestBody Ereservation ereservation, HttpSession session){
        System.out.println(ereservation);
        Gmsuser user= (Gmsuser) session.getAttribute("user");
        ereservation.setUserid(user.getUserId());
        return equipmentService.addEreservation(ereservation);
    }

    @RequestMapping("addErepair")
    @ResponseBody
    public boolean addErepair(@RequestBody Erepair erepair){
        System.out.println(erepair);
        return equipmentService.addErepair(erepair);
    }

//    //根据器材编号查询器材
//    @RequestMapping("queryEquipmentByEno")
//    @ResponseBody
//    public List<Equipment> queryEquipmentByEno(@RequestBody String data){
//        String[] info = data.split("=");
//        String eno = info[1];
//        System.out.println(eno);
//        return equipmentService.queryEquipmentByEno(eno);
//    }

    //查询所有器材
    @RequestMapping("queryEquipmentAll")
    @ResponseBody
    public List<Equipment> queryEquipmentAll(){
        List<Equipment> equipmentList = equipmentService.queryEquipmentAll();
        System.out.println(equipmentList);
        return equipmentList;
    }

    @RequestMapping("queryEquipmentL")
    @ResponseBody
    public List<Equipment> queryEquipmentL(@RequestBody String data){
        String[]arguments=data.split("&");
        String[] argument1=arguments[0].split("=");
        String[] argument2=arguments[1].split("=");
        if(argument1.length>1&&argument2.length>1) {
            System.out.println(Integer.parseInt(argument1[1])+Integer.parseInt(argument2[1]));
            List<Equipment> list = equipmentService.queryEquipmentLimit(Integer.parseInt(argument1[1]), Integer.parseInt(argument2[1]));
            return list;
        }else
            return null;
    }

    @RequestMapping("queryEquipmentNum")
    @ResponseBody
    public int queryEquipmentNum() {
        return equipmentService.queryEquipmentNum();
    }


    @RequestMapping("queryEquipmentByEnEs")
    @ResponseBody
    public List<Equipment> queryEquipmentByEnEs(int offset,int length,String type,String sitution){
            List<Equipment> list = equipmentService.queryEquipmentByEnEs(offset,length,type,sitution);
            System.out.println(list.size());
            return list;
    }

    @RequestMapping("queryEquipmentNum111")
    @ResponseBody
    public int queryEquipmentNum111(String type,String sitution) {
        System.out.println("queryEquipmentNum111方法结果"+equipmentService.queryEquipmentNum111(type,sitution));
        return equipmentService.queryEquipmentNum111(type,sitution);
    }

    @RequestMapping("queryEquipmentEtype")
    @ResponseBody
    public List<String> queryEquipmentEtype() {
        return equipmentService.queryEquipmentEtype();
    }

    @RequestMapping("updateEquipmentByEno")
    @ResponseBody
    public boolean updateEquipmentByEno(String eno, String sitution) {

        return equipmentService.updateEquipmentByEno(eno,sitution);
    }

    @RequestMapping("deleteEquipmentByEno")
    @ResponseBody
    public boolean deleteEquipmentByEno(String eno) {
        System.out.println("准备删除器材！");
        return equipmentService.deleteEquipmentByEno(eno);
    }

    @RequestMapping("queryEreservationByEnUser")
    @ResponseBody
    public List<Ereservation> queryEreservationByEnUser(int offset,int length,String eno,String userid){
        System.out.println("queryEreservationByEnUser方法接受到的参数："+userid);
        List<Ereservation> list = equipmentService.queryEreservationByEnUser(offset,length,eno,userid);
        System.out.println(list);
        return list;
    }

    @RequestMapping("queryEreservationNum")
    @ResponseBody
    public int queryEreservationNum(String eno,String userid) {
        System.out.println("queryEreservationNum方法结果："+equipmentService.queryEquipmentNum111(eno,userid));
        return equipmentService.queryEreservationNum(eno,userid);
    }

    @RequestMapping("deleteEreservationByEno")
    @ResponseBody
    public boolean deleteEreservationByEno(String eno) {
        return equipmentService.deleteEreservationByEno(eno);
    }


    @RequestMapping("queryErepairByEnEt")
    @ResponseBody
    public List<Erepair> queryErepairByEnEt(int offset,int length,String eno,String type){
        System.out.println("开始执行queryErepairByEnEt");
        System.out.println("queryErepairByEnEt接受到的参数+"+offset+length+eno+type);
        List<Erepair> list = equipmentService.queryErepairByEnEt(offset,length,eno,type);
        System.out.println(list);
        return list;
    }

    @RequestMapping("queryErepairtNum")
    @ResponseBody
    public int queryErepairtNum(String eno,String type) {
        System.out.println("queryEquipmentNum111方法结果"+equipmentService.queryErepairNum(eno,type));
        return equipmentService.queryErepairNum(eno,type);
    }

    @RequestMapping("deleteErepairByEno")
    @ResponseBody
    public boolean deleteErepairByEno(String eno) {
        System.out.println("调用删除维修记录函数");
        return equipmentService.deleteErepairByEno(eno);
    }

}
