package live.bokurano.evalserver.controller;

import live.bokurano.evalserver.common.JsonResult;
import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasAuthority('student')")
public class StudentController {
	@Autowired
	private StudentService studentService;

	@GetMapping("/api/student/getUnevaluatedCourses")
	public JsonResult getUnevaluatedCourses(@RequestParam("studentId") String studentId) {
		JsonResult result = new JsonResult();
		ServerResult serverResult = studentService.getUnevaluatedCourse(studentId);
		if (serverResult.isSuccess()) {
			result.setStatus(HttpStatus.OK.value());
			result.setResult(serverResult.getResult());
		} else {
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.setMessage(serverResult.getMessage());
		}
		return result;
	}

	@PostMapping("/api/student/postEvaluations")
	public JsonResult postEvaluations(@RequestBody SingleEvaluation singleEvaluation) {
		JsonResult result = new JsonResult();
		ServerResult serverResult = studentService.saveEvaluation(singleEvaluation);
		if (serverResult.isSuccess()) {
			result.setStatus(HttpStatus.OK.value());
		} else {
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.setMessage(serverResult.getMessage());
		}
		return result;
	}

	@GetMapping("/api/student/getEvaluationHistory")
	public JsonResult getEvaluationHistory(@RequestParam("studentId") String studentId) {
		JsonResult result = new JsonResult();
		ServerResult serverResult = studentService.getEvaluationHistory(studentId);
		if (serverResult.isSuccess()) {
			result.setStatus(HttpStatus.OK.value());
			result.setResult(serverResult.getResult());
		} else {
			result.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			result.setMessage(serverResult.getMessage());
		}
		return result;
	}
}
