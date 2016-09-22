package com.game.docking.service;

import java.util.List;

import com.game.comm.entity.ShortLogSmsKy;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.service.GenericManager;

public interface ShortLogSmsKyManager extends GenericManager<ShortLogSmsKy, Integer> {
	Page<ShortLogSmsKy> searchPageSmsKy(Page<ShortLogSmsKy> page, List<PropertyFilter> filters);
}
