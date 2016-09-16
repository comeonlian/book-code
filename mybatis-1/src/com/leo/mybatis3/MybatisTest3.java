package com.leo.mybatis3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.leo.entity.Classes;
import com.leo.entity.User;
import com.leo.util.MyBatisTest;

public class MybatisTest3 extends MyBatisTest {

	public MybatisTest3() {
		super("mybatis.xml");
	}
	/* ****************************	一对一	*************************** */
	@Test
	public void testJoin1(){
		String statement = "com.leo.mybatis3.classMapper.getClassesById";
		Classes cl = getSqlSession().selectOne(statement, 1);
		System.out.println(cl);
		getSqlSession().close();
	}
	
	@Test
	public void testJoin2(){
		String statement = "com.leo.mybatis3.classMapper.getClassById";
		Classes cl = getSqlSession().selectOne(statement, 1);
		System.out.println(cl);
		getSqlSession().close();
	}
	
	/* ****************************	一对多	*************************** */
	@Test
	public void testJoin3(){
		String statement = "com.leo.mybatis3.classesMapper.getClasses1";
		Classes cl = getSqlSession().selectOne(statement, 1);
		System.out.println(cl);
		getSqlSession().close();
	}
	
	@Test
	public void testJoin4(){
		String statement = "com.leo.mybatis3.classesMapper.getClass";
		Classes cl = getSqlSession().selectOne(statement, 1);
		System.out.println(cl);
		getSqlSession().close();
	}
	
	/* ****************************	     动态SQL	*************************** */
	@Test
	public void testSql(){
		String statement = "com.leo.mybatis3.duserMapper.getUser";
		Map<String,Object> map = new HashMap<String, Object>();
		//map.put("name", "o");
		map.put("minAge", 13);
		map.put("maxAge", 18);
		List<User> users = getSqlSession().selectList(statement, map);
		System.out.println(users);
		getSqlSession().close();
	}
	
	/* ****************************		调用存储过程 	*************************** */
	@Test
	public void testProcedure(){
		String statement = "com.leo.mybatis3.duserMapper.getProcedure";
		Map<String,Integer> map = new HashMap<String, Integer>();
		map.put("sexId", 0);
		map.put("userCount", -1);
		getSqlSession().selectOne(statement, map);
		System.out.println(map.get("userCount"));
		getSqlSession().close();
	}
	
	
	/* ****************************		测试一级缓存 		*************************** */
	@Test
	public void testCache1(){
		String statement = "com.leo.mybatis3.userMapper.getUserById";
		SqlSession session = getSqlSession();
		//第一次查询
		User user = session.selectOne(statement, 2);
		System.out.println(user);
		
		//清空缓存
		//session.clearCache();
		
		session.update("com.leo.mybatis3.userMapper.updateUser", new User(3,"xxoo",28));
		
		
		//第二次查询  参数不同  重新执行查询
		user = session.selectOne(statement, 2);
		System.out.println(user);
		
		
		session.close();
	}
	
	
	/* ****************************		测试二级缓存		*************************** */
	@Test
	public void testCache2(){
		SqlSessionFactory factory = getSqlSessionFactory();
		SqlSession session1 = factory.openSession();
		
		String statement = "com.leo.mybatis3.userMapper.getUserById";
		User user1 = session1.selectOne(statement , 2);
		System.out.println(user1);
		session1.commit();
		
		SqlSession session2 = factory.openSession();
		User user2 = session2.selectOne(statement, 2);
		System.out.println(user2);
		session2.commit();
		
		session1.close();
		session2.close();
	}
	
}
