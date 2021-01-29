package edu.tfswufe.controller;

import java.util.List;

import edu.tfswufe.entity.Attendance;
import edu.tfswufe.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@RequestMapping("/addStart.do")
	public String addStart(Integer personnelNumber){
		attendanceService.addStart(personnelNumber);
		return "welcome";
	}

	@RequestMapping("/addEnd.do")
	public String addEnd(Integer personnelNumber){
		attendanceService.addEnd(personnelNumber);
		return "welcome";
	}

	@RequestMapping("/list.do")
	public String selectList(Model model){
		List<Attendance> list = attendanceService.selectList();
		model.addAttribute("aList",list);
		return "admin/attendance_list";
	}

	@RequestMapping("/{personnelNumber}/oneself.do")
	public String select(Model model, @PathVariable Integer personnelNumber){
		List<Attendance> list = attendanceService.selectBypersonnel(personnelNumber);
		model.addAttribute("aList",list);
		return "admin/oneself_attendance";
	}
}
