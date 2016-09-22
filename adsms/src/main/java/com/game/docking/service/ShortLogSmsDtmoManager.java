package com.game.docking.service;

import java.util.List;

import com.game.comm.entity.ShortLogSmsDtmo;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.service.GenericManager;

public interface ShortLogSmsDtmoManager extends GenericManager<ShortLogSmsDtmo, Integer> {
	Page<ShortLogSmsDtmo> searchPageSmsDtmo(Page<ShortLogSmsDtmo> page, List<PropertyFilter> filters);
}
