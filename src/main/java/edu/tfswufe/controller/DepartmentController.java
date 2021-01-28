package edu.tfswufe.controller;

import edu.tfswufe.entity.Department;
import edu.tfswufe.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

@Controller
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	@RequestMapping("/listPage.do")
	public String selectListByPgae(Model model, int pageNo){
		Page<Department> page = departmentService.selectListByPage(pageNo);
		model.addAttribute("page",page);
		return "admin/department_list";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(Model model){
		int num = departmentService.selectMaxNum();
		model.addAttribute("departmentNumber",num+1);
		return "admin/department_add";
	}

	@RequestMapping("/add.do")
	public String add(Department department){
		departmentService.insert(department);
		return "forward:/department/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/toUpdate.do")
	public String toUpdate(@PathVariable Integer id, Model model){
		Department department = departmentService.selectById(id);
		model.addAttribute("department", department);
		return "admin/department_update";
	}

	@RequestMapping("/{id}/update.do")
	public String updateById(@PathVariable Integer id, Department department){
		department.setId(id);
		departmentService.updateById(department);
		return "forward:/department/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/delete.do")
	public String deleteById(@PathVariable Integer id){
		departmentService.deleteById(id);
		return "forward:/department/listPage.do?pageNo=1";
	}

}
