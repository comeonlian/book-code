package com.game.docking.entity;

public class VisitErrorCmd {

    private Long id; // 命令ID
    private String rst; // 错误码

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRst() {
        return rst;
    }

    public void setRst(String rst) {
        this.rst = rst;
    }
}
