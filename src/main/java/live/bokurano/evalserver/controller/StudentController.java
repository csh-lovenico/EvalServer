package live.bokurano.evalserver.controller;

import live.bokurano.evalserver.common.JsonResult;
import live.bokurano.evalserver.common.ServerResult;
import live.bokurano.evalserver.model.mongo.SingleEvaluation;
import live.bokurano.evalserver.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
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
	public JsonResult postEvaluations(@RequestBody List<SingleEvaluation> singleEvaluations) {
		JsonResult result = new JsonResult();
		ServerResult serverResult = studentService.saveEvaluation(singleEvaluations);
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
