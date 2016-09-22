package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortPadvItem;

public interface ShortPadvItemManager extends GenericManager<ShortPadvItem, Long> {

    /**
     * 根据主表Id查找列表
     * 
     * @param parentId
     * @return
     */
    public List<ShortPadvItem> findByPid(Long parentId);

    /**
     * 根据父类ID、省编码查找子表
     * 
     * @param provincecode
     * @param pid
     * @return
     */
    public ShortPadvItem findByProvinceAndPid(String provincecode, Long pid);

    public List<ShortPadvItem> findByProvince(String provinceCode); // 当前省未推满的第三方信息
}
