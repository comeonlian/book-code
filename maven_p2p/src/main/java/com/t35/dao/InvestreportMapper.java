package com.t35.dao;

import java.util.List;

import com.t35.pojo.Borrow;
import com.t35.pojo.Investreport;
import com.t35.pojo.User;
import com.t35.pojo.query.BasePage;
import com.t35.pojo.query.Condition;
public interface InvestreportMapper {

	public List<Borrow> queryAllBorrow(BasePage basePage);

	public List<Borrow> queryPage();
	
	public List<Borrow> query(Condition condition);
	
	public Borrow queryById(Borrow borrow);

	public User getAllUserInFo(Borrow borrow);
	
	public List<Investreport> getInvestreport(Borrow borrow);
	
	public User getUser(User user);
	
	public void saveInvestreport(Investreport investreport);
	
	public List<Investreport> findAllExcle();
}
