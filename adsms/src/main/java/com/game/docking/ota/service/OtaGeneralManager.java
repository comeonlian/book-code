package com.game.docking.ota.service;

import java.nio.ByteBuffer;

import com.game.comm.entity.City;
import com.game.docking.ota.entity.SendOtaHeader;
import com.game.docking.ota.entity.VisitOta;
import com.game.docking.util.sina.JsonIPInfo;
import com.game.ota.entity.OtaCustomer;
import com.game.ota.entity.OtaDevice;

/**
 * 通用服务接口
 */
public interface OtaGeneralManager {

    /**
     * 合作客户数据处理
     */
    public void dealPartnerDatas(VisitOta visit, String currentdate);

    /**
     * 根据sina接口获取省市
     */
    public City getByJsonIPInfo(JsonIPInfo info);

    public OtaDevice getDevice(VisitOta visit);

    /**
     * 数据处理
     */
    public ByteBuffer dealProduct(SendOtaHeader otaHeader, VisitOta visit, OtaDevice device, OtaCustomer customer);

    public void insertLogAccess(VisitOta visit);
}
