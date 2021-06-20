package edu.gdou.utilities;

import java.util.List;

public class ParentMenu {
    String id;
    String name;
    List<ChildMenu>list;

    public ParentMenu(String id, String name, List<ChildMenu> list) {
        this.id = id;
        this.name = name;
        this.list = list;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChildMenu> getList() {
        return list;
    }

    public void setList(List<ChildMenu> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ParentMenu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", list=" + list +
                '}';
    }
}
