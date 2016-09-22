package com.game.comm.service;

import java.util.List;

import com.game.comm.entity.ShortLogIvrLi;
import com.game.comm.entity.ShortLogIvrTyxx;
import com.game.comm.entity.ShortLogSdkback;
import com.game.comm.entity.ShortLogSmsTyxx;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.service.GenericManager;

public interface ShortLogSdkbackManager extends GenericManager<ShortLogSdkback, Long> {

    public ShortLogIvrLi saveIvrLi(ShortLogIvrLi ivrLi);

    public Page<ShortLogIvrLi> searchPageIvrLi(Page<ShortLogIvrLi> page, List<PropertyFilter> filters);

    public ShortLogSmsTyxx saveSmsTyxx(ShortLogSmsTyxx smsTyxx);

    public Page<ShortLogSmsTyxx> searchPageSmsTyxx(Page<ShortLogSmsTyxx> page, List<PropertyFilter> filters);

    public ShortLogIvrTyxx saveIvrTyxx(ShortLogIvrTyxx ivrTyxx);

    public Page<ShortLogIvrTyxx> searchPageIvrTyxx(Page<ShortLogIvrTyxx> page, List<PropertyFilter> filters);

}
