package com.game.shorts.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.modules.orm.GenericDao;
import com.game.modules.orm.Page;
import com.game.modules.orm.PropertyFilter;
import com.game.modules.orm.hibernate.GenericDaoHibernate;
import com.game.modules.security.springsecurity.CustomizeInterceptor;
import com.game.modules.service.impl.GenericManagerImpl;
import com.game.shorts.entity.ShortKeyInfo;
import com.game.shorts.entity.ShortKeyTip;
import com.game.shorts.service.ShortKeyInfoManager;

@Service("shortShortKeyInfoManager")
public class ShortKeyInfoManagerImpl extends GenericManagerImpl<ShortKeyInfo, Long> implements ShortKeyInfoManager {

    private GenericDao<ShortKeyInfo, Long> shortShortKeyInfoDao;
    private GenericDao<ShortKeyTip, Long> shortShortKeyTipDao;
    private static Logger logger = LoggerFactory.getLogger(ShortKeyInfoManagerImpl.class);

    @Autowired
    public ShortKeyInfoManagerImpl(SessionFactory sessionFactory) {
        this.shortShortKeyTipDao = new GenericDaoHibernate<ShortKeyTip, Long>(ShortKeyTip.class, sessionFactory);
        this.shortShortKeyInfoDao = new GenericDaoHibernate<ShortKeyInfo, Long>(ShortKeyInfo.class, sessionFactory);
        this.dao = this.shortShortKeyInfoDao;
    }

    @Override
    public boolean delAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                // 删除子信息
                List<ShortKeyTip> tips = shortShortKeyTipDao.findBy("nfid", id);
                for (ShortKeyTip item : tips) {
                    shortShortKeyTipDao.remove(item.getId());
                }
                this.dao.remove(id);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Page<ShortKeyInfo> searchShortKeyInfo(Page<ShortKeyInfo> page, List<PropertyFilter> filters) {
        return dao.findPage(page, filters);
    }

    @Override
    public ShortKeyInfo getShortKeyInfo(Long id) {
        return dao.get(id);
    }

    @Override
    public ShortKeyInfo saveShortKeyInfo(ShortKeyInfo object) {
        object = dao.save(object);
        // Rebuilt SpringSecurity
        CustomizeInterceptor.refresh();
        return object;
    }

    @Override
    public List<ShortKeyTip> searchByPid(Long pid) {
        return null;
    }

    @Override
    public ShortKeyTip saveShortKeyTip(ShortKeyTip object) {
        object = shortShortKeyTipDao.save(object);
        return object;
    }

    @Override
    public ShortKeyTip getShortKeyTip(Long id) {
        return shortShortKeyTipDao.get(id);
    }

    @Override
    public boolean delShortKeyTipAll(List<Long> ids) {
        try {
            for (Long id : ids) {
                this.shortShortKeyTipDao.remove(id);
            }
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return false;
        }
    }

    @Override
    public Page<ShortKeyTip> searchShortKeyTip(Page<ShortKeyTip> page, List<PropertyFilter> filters) {
        return shortShortKeyTipDao.findPage(page, filters);
    }

    @Override
    public ShortKeyInfo clientShortKeyInfo(String customid) {
        StringBuffer sb = new StringBuffer();
        sb.append("from ShortKeyInfo where 1=1 and customids=? order by id desc ");
        ShortKeyInfo shortShortKeyInfo = dao.findOne(sb.toString(), customid);
        return shortShortKeyInfo;
    }

    @Override
    public Map<String, Object> clientShortKeyInfo(boolean keyFlow, String apkver) {

        int av = 1;
        Map<String, Object> resultMap = new HashMap<String, Object>();
        StringBuffer sb = new StringBuffer();
        sb.append("from ShortKeyInfo where 1=1 ");
        // 判断第三方信息或者余量查询
        if (keyFlow) {
            sb.append(" and customids = '2' ");
        } else {
            sb.append(" and customids = '" + av + "' ");
        }
        // sb.append(" order by id desc ");
        ShortKeyInfo shortShortKeyInfo = dao.findOne(sb.toString());
        if (shortShortKeyInfo != null) {
            String comtent = shortShortKeyInfo.getComtent() == null ? "" : shortShortKeyInfo.getComtent(); // 比对内容
            String keytent = shortShortKeyInfo.getKeytent() == null ? "" : shortShortKeyInfo.getKeytent(); // 关键字
            String advkey = shortShortKeyInfo.getAdvkey() == null ? "" : shortShortKeyInfo.getAdvkey(); // 广告键
            String advtent = shortShortKeyInfo.getAdvtent() == null ? "" : shortShortKeyInfo.getAdvtent(); // 广告值
            String advtip = shortShortKeyInfo.getAdvtip() == null ? "" : shortShortKeyInfo.getAdvtip(); // 广告开始
            String advend = shortShortKeyInfo.getAdvend() == null ? "" : shortShortKeyInfo.getAdvend(); // 广告结束
            String delkey = shortShortKeyInfo.getDelkey() == null ? "" : shortShortKeyInfo.getDelkey(); // 禁止删除键
            resultMap.put("comtent", comtent);
            resultMap.put("keytent", keytent);
            resultMap.put("advkey", advkey);
            resultMap.put("advtent", advtent);
            resultMap.put("advtip", advtip);
            resultMap.put("advend", advend);
            resultMap.put("delkey", delkey);
            resultMap.put("id", shortShortKeyInfo.getId());
            List<ShortKeyTip> shortShortKeyTips = shortShortKeyTipDao.findBy("nfid", shortShortKeyInfo.getId());
            List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
            for (ShortKeyTip shortShortKeyTip : shortShortKeyTips) {
                Map<String, Object> kt = new HashMap<String, Object>();
                // kt.put("id", shortShortKeyTip.getId());
                kt.put("kbegin", shortShortKeyTip.getKbegin() == null ? "" : shortShortKeyTip.getKbegin());
                kt.put("keyend", shortShortKeyTip.getKeyend() == null ? "" : shortShortKeyTip.getKeyend());
                // kt.put("nfid", shortShortKeyTip.getNfid());
                // 2013-9-16 添加的节点
                kt.put("del", shortShortKeyTip.getDel() == null ? 0 : shortShortKeyTip.getDel());
                kt.put("ret", shortShortKeyTip.getRet() == null ? 0 : shortShortKeyTip.getRet());
                kt.put("smes", shortShortKeyTip.getSmes() == null ? "" : shortShortKeyTip.getSmes());
                kt.put("cons", shortShortKeyTip.getCons() == null ? "" : shortShortKeyTip.getCons());

                results.add(kt);
            }
            resultMap.put("tipend", results);
        }
        return resultMap;
    }

}
