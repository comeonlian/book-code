package com.game.shorts.service;

import java.util.List;
import java.util.Map;

import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortKeyInfo;
import com.game.shorts.entity.ShortKeyTip;

public interface ShortKeyInfoManager extends GenericManager<ShortKeyInfo, Long> {

    // ************ 关键字 主表 begin ****************
    public Page<ShortKeyInfo> searchShortKeyInfo(final Page<ShortKeyInfo> page, final List<PropertyFilter> filters);

    public ShortKeyInfo getShortKeyInfo(Long id);

    public ShortKeyInfo saveShortKeyInfo(ShortKeyInfo object);

    public boolean delAll(List<Long> ids);

    /**
     * 返回客户端数据
     * 
     * @return 只返回最后一条记录
     */
    public ShortKeyInfo clientShortKeyInfo(String customid);

    /**
     * 当下发的是第三方信息时， 关键字信息直接发送 当下发的是流量查询信息时，关键字上层信息要过滤掉（比对内容字段 保留）
     * 
     * @param keyFlow
     *            是否余量查询
     * @return
     */
    public Map<String, Object> clientShortKeyInfo(boolean keyFlow, String apkver);

    // ************* 关键字主表 end *****************

    // ****************** 关键字 子表 begin **************
    public Page<ShortKeyTip> searchShortKeyTip(final Page<ShortKeyTip> page, final List<PropertyFilter> filters);

    /**
     * 根据关键字主键查找子信息
     * 
     * @param pid
     * @return
     */
    public List<ShortKeyTip> searchByPid(Long pid);

    public ShortKeyTip saveShortKeyTip(ShortKeyTip object);

    public ShortKeyTip getShortKeyTip(Long id);

    public boolean delShortKeyTipAll(List<Long> ids);
    // ****************** 关键字 子表 end ****************
}
