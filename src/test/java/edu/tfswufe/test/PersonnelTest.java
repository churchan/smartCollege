package edu.tfswufe.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.baomidou.mybatisplus.plugins.Page;

import edu.tfswufe.entity.Personnel;
import edu.tfswufe.service.PersonnelService;

public class PersonnelTest {

	@SuppressWarnings("resource")
	@Test
	public void lognTest(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		Personnel personnel = eService.checkLogin(1001, "1001");
		System.out.println(personnel.toString());
	}

	@SuppressWarnings("resource")
	@Test
	public void selectListTest(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		List<Personnel> eList = eService.selectList();
		for(Personnel e : eList){
			System.out.println(e.toString());
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void selectById(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		Personnel personnel = eService.selectPersonnel(1);
		System.out.println(personnel.getDepartment());
	}

	@SuppressWarnings("resource")
	@Test
	public void selectListPageTest(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		Page<Personnel> page = eService.selectListByPage(1);
		List<Personnel> eList = page.getRecords();
		for(Personnel e : eList){
			System.out.println(e.toString());
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void update(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		Personnel personnel = new Personnel();
		personnel.setId(5);
		personnel.setName("杨姐姐");
		if (eService.updateById(personnel)) {
			System.out.println("成功");
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void select(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		List<Personnel> list = eService.select(1002, "1002");
		for(Personnel personnel : list){
			System.out.println(personnel.toString());
		}
	}

/*	@SuppressWarnings("resource")
	@Test
	public void add(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		personnelService eService = (personnelService) context.getBean("personnelService");
		Personnel personnel = new personnel();
		personnel.setPersonnelNumber(1005);
		personnel.setName("杨杰");
		personnel.setGender("男");
		personnel.setPassword("1005");
		if (eService.addpersonnel(personnel)) {
			System.out.println("成功");
		}
	}*/

	@SuppressWarnings("resource")
	@Test
	public void delete(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		if (eService.deleteById(5)) {
			System.out.println("成功");
		}
	}

	@SuppressWarnings("resource")
	@Test
	public void test(){
		String xmlPath="spring/spring.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(xmlPath);
		PersonnelService eService = (PersonnelService) context.getBean("personnelService");
		eService.deletePersonnel(19);
	}
}
