package com.game.docking.entity;

/**
 * 产品反馈
 */
public class VisitProductBack {

    private Long si; // 产品ID
    private String imsi; // IMSI
    private Integer ordernum; // 执行的序号
    private Integer errcode; // 错误码：0为成功

    public Long getSi() {
        return si;
    }

    public void setSi(Long si) {
        this.si = si;
    }

    public String getImsi() {
        return imsi;
    }

    public void setImsi(String imsi) {
        this.imsi = imsi;
    }

    public Integer getOrdernum() {
        return ordernum;
    }

    public void setOrdernum(Integer ordernum) {
        this.ordernum = ordernum;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }
}
