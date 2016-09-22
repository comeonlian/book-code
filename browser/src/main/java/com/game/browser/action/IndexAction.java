package com.game.browser.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import com.game.bmanager.entity.HotWords;
import com.game.bmanager.entity.Recommend;
import com.game.bmanager.service.HotWordsManager;
import com.game.bmanager.service.RecommendManager;
import com.game.bwlog.entity.LogIndex;
import com.game.bwlog.service.LogIndexManager;
import com.game.modules.utils.RequestUtil;
import com.game.modules.web.struts2.Struts2Utils;
import com.game.util.SessionUtils;
import com.google.common.base.CaseFormat;
import com.opensymphony.xwork2.ActionSupport;

@Namespace("/lmuch")
public class IndexAction extends ActionSupport {
    private static final long serialVersionUID = -6566546272973459809L;

    @Autowired
    private LogIndexManager logIndexManager;
    @Autowired
    private RecommendManager recommendManager;
    @Autowired
    private HotWordsManager hotWordsManager;

    private List<Recommend> recommends = new ArrayList<Recommend>();
    private List<HotWords> hotWords = new ArrayList<HotWords>();

    @Override
    public String execute() {
        HttpServletRequest request = Struts2Utils.getRequest();
        String dockingSessionid = request.getParameter("sessionid");
        System.out.println(new Date() + " index: " + RequestUtil.getAllParams(request));
        String cookieSessionid = RequestUtil.getJsessionid(request);

        String sessionid = RequestUtil.getSessionId(request);
        recommends = recommendManager.getByCustomid("");
        hotWords = hotWordsManager.getByCustomid("");
        LogIndex logIndex = new LogIndex(new Date(), SessionUtils.findRealIp(request), 0L, "", dockingSessionid, cookieSessionid, sessionid);
        logIndexManager.save(logIndex);
        return "index";
    }

    // ************** getting and setting *************
    public List<Recommend> getRecommends() {
        return recommends;
    }

    public void setRecommends(List<Recommend> recommends) {
        this.recommends = recommends;
    }

    public List<HotWords> getHotWords() {
        return hotWords;
    }

    public void setHotWords(List<HotWords> hotWords) {
        this.hotWords = hotWords;
    }

    public static void main(String[] args) {
        String str = "domainId";
        System.out.println(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, str));
    }
}
