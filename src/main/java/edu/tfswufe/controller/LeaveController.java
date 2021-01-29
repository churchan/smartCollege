package edu.tfswufe.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import edu.tfswufe.entity.Personnel;
import edu.tfswufe.entity.Leave;
import edu.tfswufe.service.PersonnelService;
import edu.tfswufe.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baomidou.mybatisplus.plugins.Page;

import edu.tfswufe.util.MTimeUtil;

@Controller
@RequestMapping("/leave")
public class LeaveController {

	@Autowired
	private LeaveService leaveService;
	@Autowired
	private PersonnelService personnelService;

	@RequestMapping("/list.do")
	public String selectList(Model model){
		List<Leave> list = leaveService.selectList();
		model.addAttribute("list", list);
		return "admin/leave_list";
	}

	@RequestMapping("/{id}/detail.do")
	public String selectLeave(@PathVariable Integer id, Model model){
		Leave leave = leaveService.selectLeave(id);
		model.addAttribute("leave", leave);
		return "admin/leave_detail";
	}

	@RequestMapping("/{id}/update.do")
	public String updateStatus(@PathVariable Integer id){
		leaveService.updateStatus(id);
		return "forward:/leave/notlist.do";
	}

	@RequestMapping("/toAdd.do")
	public String toAdd(){
		return "admin/leave_add";
	}

	@RequestMapping("/add.do")
	public String add(Integer personnelNumber, Leave leave, String start, String end){
		Personnel personnel =personnelService.selectByNumber(personnelNumber);
		leave.setDepartmentNumber(personnel.getDepartmentNumber());
		leave.setPersonnelNumber(personnelNumber);
		leave.setStartTime(MTimeUtil.stringParse(start));
		leave.setEndTime(MTimeUtil.stringParse(end));
		leaveService.insert(leave);
		return "forward:/personnel/welcome.do";
	}

	@RequestMapping("/oneself.do")
	public String seletBypersonnel(HttpSession session, int pageNo, Model model){
		Personnel personnel = (Personnel)session.getAttribute("loged");
		Page<Leave> page = leaveService.seletBypersonnel(personnel.getPersonnelNumber(), pageNo);
		model.addAttribute("page", page);
		return "admin/oneself_leave";
	}

	@RequestMapping("/notlist.do")
	public String selectNotList(Model model, HttpSession session){
		//获取登录用户的信息
		Personnel personnel = (Personnel) session.getAttribute("loged");
		List<Leave> list = leaveService.selectListByStatus(personnel.getDepartmentNumber(), "未批准");
		model.addAttribute("list", list);
		return "admin/leave_notlist";
	}

	@RequestMapping("/yeslist.do")
	public String selectYesList(Model model, HttpSession session){
		//获取登录用户的信息
		Personnel personnel = (Personnel) session.getAttribute("loged");
		List<Leave> list = leaveService.selectListByStatus(personnel.getDepartmentNumber(), "已批准");
		model.addAttribute("list", list);
		return "admin/leave_yeslist";
	}
}
