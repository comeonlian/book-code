package com.game.shorts.service;

import java.util.List;

import com.game.modules.service.GenericManager;
import com.game.shorts.entity.ShortProductActive;
import com.game.shorts.entity.ShortProductActiveWap;

public interface ShortProductActiveManager extends GenericManager<ShortProductActive, Long> {

    public boolean delAll(List<Long> ids);

    public List<ShortProductActive> findByPid(Long pid);

    /**
     * 根据父类ID查找模拟划屏列表
     * 
     * @param pid
     * @return
     */
    public List<ShortProductActiveWap> findWapsByPid(Long pid);

    /**
     * 保存主子表
     */
    public ShortProductActive saveEntityAndItem(ShortProductActive entity, List<ShortProductActiveWap> waps);

    /**
     * 删除wap
     * 
     * @param id
     */
    public void deleteWap(Long id);
}
