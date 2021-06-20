package edu.gdou.utilities;

import java.util.ArrayList;
import java.util.List;

public class Access {
    private static List<ChildMenu> getChildList(){
        List<ChildMenu>list=new ArrayList<ChildMenu>();
        ChildMenu childMenu;
        childMenu=new ChildMenu("0","用户查询",0,"match/queryMatch",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","用户新建",0,"match/queryMatch",0b00100);list.add(childMenu);
        childMenu=new ChildMenu("0","赛事查询",3,"match/queryMatch",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","赛事创建",3,"match/newMatch",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","赛事取消",3,"match/cancelMatch",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","赛事审核",3,"match/censorMatch",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","裁判公告",3,"match/queryReferee",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","裁判创建",3,"match/newReferee",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","裁判删除",3,"match/deleteReferee",0b10001);list.add(childMenu);
        childMenu=new ChildMenu("0","裁判删除",3,"match/deleteReferee",0b11111);list.add(childMenu);
        return list;
    }
    public static List<ParentMenu> getList(){
        ParentMenu parentMenu;
        int role=0b00001;
        List<ParentMenu>parentMenus=new ArrayList<ParentMenu>();
        parentMenu=new ParentMenu("1","用户管理",new ArrayList<>());parentMenus.add(parentMenu);
        parentMenu=new ParentMenu("2","场地管理",new ArrayList<>());parentMenus.add(parentMenu);
        parentMenu=new ParentMenu("3","器材管理",new ArrayList<>());parentMenus.add(parentMenu);
        parentMenu=new ParentMenu("4","赛事管理",new ArrayList<>());parentMenus.add(parentMenu);
        List<ChildMenu>childMenus =getChildList();
        for (ChildMenu childMenu:childMenus) {
            int code=childMenu.getCode();
            if((role&code)>0){
                parentMenus.get(childMenu.getPid()).getList().add(childMenu);
            }
        }
        for (int i=0;i<parentMenus.size();i++){
            if (parentMenus.get(i).getList().size()<1) {
                parentMenus.remove(parentMenus.get(i--));
            }
        }
        return parentMenus;
    }
}
