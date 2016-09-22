package com.game.shorts.service;

import java.util.List;
import java.util.Map;

import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortPadv;

public interface ShortPadvManager extends GenericManager<ShortPadv, Long> {

    public Page<ShortPadv> searchShortPadv(final Page<ShortPadv> page, final List<PropertyFilter> filters);

    public ShortPadv getShortPadv(Long id);

    public ShortPadv saveShortPadv(ShortPadv object);

    public void deleteShortPadv(Long id);

    public boolean delAll(List<Long> ids);

    /**
     * 根据客户ID查找第三方信息
     * 
     * @param customid
     * @return
     */
    public List<ShortPadv> findByCustomid(String customid);

    /**
     * 根据客户、省市查找记录
     * 
     * @param customid
     * @param cityid
     * @param provinceCode
     * @return
     */
    public List<Map<String, Object>> searchObjList(String customid, String cityid, String provinceCode);

    public List<ShortPadv> searchShortPadvList(String city, String imsi, String customid);

    /**
     * 校验名称是否存在
     * 
     * @param padvName
     * @param oldName
     * @return
     */
    public boolean isNameUnique(String padvName, String oldName);
    
    public ShortPadv getPadv(String customerid, String cityid, int providerCode);
}
