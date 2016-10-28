package com.t35.service;

import com.t35.pojo.Assets;
import com.t35.pojo.Check;
import com.t35.pojo.Userdetia;
import com.t35.pojo.Work;

public interface CheckService {

	public void saveWork(Work work);

	public void saveAssets(Assets assets);

	public void saveUserdetia(Userdetia userdetia);

	public void getIdBy1(Work work);

	public void getIdBy2(Assets assets);

	public void getIdBy3(Userdetia userdetia);

	public void updateCheck(Check Check);

}
