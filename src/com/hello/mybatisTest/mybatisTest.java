package com.hello.mybatisTest;

import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.hello.dao.UserMapper;
import com.hello.pojo.User;

public class mybatisTest {
	private SqlSessionFactory sqlSessionFactory = null;

	@Before
	public void init() throws Exception {
		// 1. 创建SqlSessionFactoryBuilder对象
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

		// 2. 加载SqlMapConfig.xml配置文件
		InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
		System.out.println("AAAAAAAS");
		// 3. 创建SqlSessionFactory对象
		this.sqlSessionFactory = sqlSessionFactoryBuilder.build(inputStream);
	}

	@Test
	public void queryUserById(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		User user = sqlSession.selectOne("queryUserById" , 1);
		System.out.println(user);
		sqlSession.close();
	}
	@Test
	public void queryUserByName(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
//		User user = sqlSession.selectOne("queryUserByName" , 1);
		List<User> userList = sqlSession.selectList("queryUserByName", "%明%");
		for (User user : userList) {
			System.out.println(user);
		}
		sqlSession.close();
	}
	@Test
	public void saveUser(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
//		User user = sqlSession.selectOne("queryUserByName" , 1);
		User u = new User();
		u.setUsername("kankan");
		u.setBirthday(new Date());
		u.setSex("1");
		int i = sqlSession.insert("saveUser", u);
		System.out.println(i);
		sqlSession.commit();
		sqlSession.close();
	}
	@Test
	public void query(){
		SqlSession sqlSession = sqlSessionFactory.openSession();
		UserMapper mapper = sqlSession.getMapper(UserMapper.class);
		User user = mapper.queryUserById(1);
		System.out.println(user);
		sqlSession.close();
	}
}
