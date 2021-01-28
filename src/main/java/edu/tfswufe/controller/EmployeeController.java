package edu.tfswufe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Employee;
import edu.tfswufe.entity.History;
import edu.tfswufe.entity.Position;
import edu.tfswufe.service.DepartmentService;
import edu.tfswufe.service.EmployeeService;
import edu.tfswufe.service.HistoryService;
import edu.tfswufe.service.PositionService;
import edu.tfswufe.util.MTimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private DepartmentService departmentService;
	@Autowired
	private PositionService positionService;
	@Autowired
	private HistoryService historyService;

	@RequestMapping("/login.do")
	public String toLogin(){
		return "login";
	}

	@RequestMapping("/checkLogin.do")
	public String checkLogin(HttpSession session, Employee employee){
		Employee employee2 = employeeService.checkLogin(employee.getEmployeeNumber(),
				employee.getPassword());
		if (employee2 != null) {
			session.setAttribute("loged", employee2);
			String level = employee2.getPosition().getLevel();
			if (level.equals("人事部主任")) {
				return "admin/index1";
			}else if (level.equals("人事部员工")) {
				return "admin/index2";
			}else if (level.equals("部门主任")) {
				return "admin/index3";
			}else {
				return "admin/index4";
			}
		}else{
			return "login";
		}
	}

	@RequestMapping("/welcome.do")
	public String toWelcome(){
		return "welcome";
	}

	@RequestMapping("/listPage.do")
	public String selectList(Model model, int pageNo){
		Page<Employee> page = employeeService.selectListByPage(pageNo);
		model.addAttribute("page", page);
		return "admin/employee_list";
	}

	@RequestMapping("/{id}/detial.do")
	public String selectEmployee(@PathVariable Integer id, Model model){
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		return "admin/employee_detail";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(Model model){
		List<History> eList = historyService.selectList();
		String employeeNumber=historyService.selectMaxEmpnum();
		int i = Integer.parseInt( employeeNumber );
		System.out.println(employeeNumber);
		model.addAttribute("employeeNumber",i+1);
		List<Department> dList = departmentService.selectList();
		model.addAttribute("dList", dList);
		List<Position> pList = positionService.selectList();
		model.addAttribute("pList", pList);
		return "admin/employee_add";
	}

	@RequestMapping("/add.do")
	public String add(Employee employee, String date) {
		employee.setBirthday(MTimeUtil.stringParse(date));
		employeeService.addEmployee(employee);
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/toUpdate.do")
	public String toUpdate(Model model, @PathVariable Integer id){
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		List<Department> dList = departmentService.selectList();
		model.addAttribute("dList", dList);
		List<Position> pList = positionService.selectList();
		model.addAttribute("pList", pList);
		return "admin/employee_update";
	}

	@RequestMapping("/{id}/update.do")
	public String updateById( @PathVariable Integer id, Employee employee, String date, String status,
			HttpSession session){
		employee.setId(id);
		employee.setBirthday(MTimeUtil.stringParse(date));
		//得到操作人员的名字
		Employee employee2 = (Employee) session.getAttribute("loged");
		if("admin".equals(employee2.getName())) {
			status="在职";
		}
		employeeService.updateEmployee(employee, status, employee2.getName());
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/delete.do")
	public String deleteById(@PathVariable Integer id){
		employeeService.deleteEmployee(id);
		return "forward:/employee/listPage.do?pageNo=1";
	}

	@RequestMapping("/oneself/{id}/detial.do")
	public String selectEmployee2(@PathVariable Integer id, Model model){
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		return "admin/oneself_detail";
	}

	@RequestMapping("/oneself/{id}/toUpdate.do")
	public String toUpdate2(Model model, @PathVariable Integer id){
		Employee employee = employeeService.selectEmployee(id);
		model.addAttribute("employee", employee);
		return "admin/oneself_update";
	}

	@RequestMapping("/search")
	public String search(Model model, String input, int pageNo){
		Page<Employee> page = employeeService.search(input, pageNo);
		model.addAttribute("page", page);
		return "admin/search_result";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.removeAttribute("loged");
		return "login";
	}

}
