package com.game.docking.service;

import java.util.List;

import com.game.comm.entity.ShortLogSmsDtmr;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.service.GenericManager;

public interface ShortLogSmsDtmrManager extends GenericManager<ShortLogSmsDtmr, Integer> {
	Page<ShortLogSmsDtmr> searchPageSmsDtmr(Page<ShortLogSmsDtmr> page, List<PropertyFilter> filters);
}
