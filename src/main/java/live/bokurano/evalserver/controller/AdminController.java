package live.bokurano.evalserver.controller;

import live.bokurano.evalserver.common.JsonResult;
import live.bokurano.evalserver.service.EvaluationService;
import live.bokurano.evalserver.util.GlobalProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminController {

	@Autowired
	private EvaluationService evaluationService;

	@PostMapping("/api/admin/enableEvaluation")
	public JsonResult enableEvaluation() {
		GlobalProps.put("enabled",true);
		return null;
	}

	@PostMapping("/api/admin/disableEvaluation")
	public JsonResult disableEvaluation() {
		GlobalProps.put("enabled",false);
		return null;
	}

	@PostMapping("/api/admin/disableAndGenerateStat")
	public JsonResult disableAndGenerateStat() {
		evaluationService.generateStats();
		//		GlobalProps.put("enabled",false);
		return null;
	}
}
