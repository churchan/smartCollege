package edu.tfswufe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.tfswufe.entity.Department;
import edu.tfswufe.entity.Personnel;
import edu.tfswufe.entity.History;
import edu.tfswufe.entity.Position;
import edu.tfswufe.service.DepartmentService;
import edu.tfswufe.service.PersonnelService;
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
@RequestMapping("/personnel")
public class PersonnelController {

	@Autowired
	private PersonnelService personnelService;
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
	public String checkLogin(HttpSession session, Personnel personnel){
		Personnel personnel2 = personnelService.checkLogin(personnel.getPersonnelNumber(),
				personnel.getPassword());
		if (personnel2 != null) {
			session.setAttribute("loged", personnel2);
			String level = personnel2.getPosition().getLevel();
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
		Page<Personnel> page = personnelService.selectListByPage(pageNo);
		model.addAttribute("page", page);
		return "admin/personnel_list";
	}

	@RequestMapping("/{id}/detial.do")
	public String selectPersonnel(@PathVariable Integer id, Model model){
		Personnel personnel = personnelService.selectPersonnel(id);
		model.addAttribute("personnel", personnel);
		return "admin/personnel_detail";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(Model model){
		List<History> eList = historyService.selectList();
		String personnelNumber=historyService.selectMaxEmpnum();
		int i = Integer.parseInt( personnelNumber );
		System.out.println(personnelNumber);
		model.addAttribute("personnelNumber",i+1);
		List<Department> dList = departmentService.selectList();
		model.addAttribute("dList", dList);
		List<Position> pList = positionService.selectList();
		model.addAttribute("pList", pList);
		return "admin/personnel_add";
	}

	@RequestMapping("/add.do")
	public String add(Personnel personnel, String date) {
		personnel.setBirthday(MTimeUtil.stringParse(date));
		personnelService.addPersonnel(personnel);
		return "forward:/personnel/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/toUpdate.do")
	public String toUpdate(Model model, @PathVariable Integer id){
		Personnel personnel = personnelService.selectPersonnel(id);
		model.addAttribute("personnel", personnel);
		List<Department> dList = departmentService.selectList();
		model.addAttribute("dList", dList);
		List<Position> pList = positionService.selectList();
		model.addAttribute("pList", pList);
		return "admin/personnel_update";
	}

	@RequestMapping("/{id}/update.do")
	public String updateById( @PathVariable Integer id, Personnel personnel, String date, String status,
			HttpSession session){
		personnel.setId(id);
		personnel.setBirthday(MTimeUtil.stringParse(date));
		//得到操作人员的名字
		Personnel personnel2 = (Personnel) session.getAttribute("loged");
		if("admin".equals(personnel2.getName())) {
			status="在职";
		}
		personnelService.updatepersonnel(personnel, status, personnel2.getName());
		return "forward:/personnel/listPage.do?pageNo=1";
	}

	@RequestMapping("/{id}/delete.do")
	public String deleteById(@PathVariable Integer id){
		personnelService.deletepersonnel(id);
		return "forward:/personnel/listPage.do?pageNo=1";
	}

	@RequestMapping("/oneself/{id}/detial.do")
	public String selectPersonnel2(@PathVariable Integer id, Model model){
		Personnel personnel = personnelService.selectPersonnel(id);
		model.addAttribute("personnel", personnel);
		return "admin/oneself_detail";
	}

	@RequestMapping("/oneself/{id}/toUpdate.do")
	public String toUpdate2(Model model, @PathVariable Integer id){
		Personnel personnel = personnelService.selectPersonnel(id);
		model.addAttribute("personnel", personnel);
		return "admin/oneself_update";
	}

	@RequestMapping("/search")
	public String search(Model model, String input, int pageNo){
		Page<Personnel> page = personnelService.search(input, pageNo);
		model.addAttribute("page", page);
		return "admin/search_result";
	}

	@RequestMapping("/logout.do")
	public String logout(HttpSession session){
		session.removeAttribute("loged");
		return "login";
	}

}
