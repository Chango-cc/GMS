package edu.gdou.utilities;

public class ChildMenu {
    String id;
    String name;
    int pid;
    String url;
    int code;

    public ChildMenu(String id, String name, int pid, String url, int code) {
        this.id = id;
        this.name = name;
        this.pid = pid;
        this.url = url;
        this.code = code;
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

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ChildMenu{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid=" + pid +
                ", url='" + url + '\'' +
                ", code=" + code +
                '}';
    }
}
