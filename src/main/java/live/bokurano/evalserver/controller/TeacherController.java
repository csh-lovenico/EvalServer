package live.bokurano.evalserver.controller;

import live.bokurano.evalserver.common.JsonResult;
import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('teacher')")
public class TeacherController {

	@Autowired
	private TeacherService teacherService;

	@GetMapping("/api/teacher/getTeachingCourses")
	public JsonResult getTeachingCourses(@RequestParam("teacherId") String teacherId) {
		JsonResult result = new JsonResult();
		ServerResult serverResult = teacherService.getTeacherCourse(teacherId);
		if (serverResult.isSuccess()) {
			result.setResult(serverResult.getResult());
			result.setStatus(HttpStatus.OK.value());
		} else {
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.setMessage(serverResult.getMessage());
		}
		return result;
	}

	@GetMapping("/api/teacher/getEvaluationOfMyCourse")
	public JsonResult getEvaluationOfMyCourse(@RequestParam("teacherId") String teacherId) {
		JsonResult result = new JsonResult();
		ServerResult serverResult = teacherService.getEvaluationOfMyCourse(teacherId);
		if (serverResult.isSuccess()) {
			result.setResult(serverResult.getResult());
			result.setStatus(HttpStatus.OK.value());
		} else {
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.setMessage(serverResult.getMessage());
		}
		return result;
	}
}
