package live.bokurano.evalserver.controller;

import live.bokurano.evalserver.common.JsonResult;
import live.bokurano.evalserver.service.EvaluationService;
import live.bokurano.evalserver.transfer.InputConfig;
import live.bokurano.evalserver.util.GlobalProps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasAuthority('admin')")
public class AdminController {

	@Autowired
	private EvaluationService evaluationService;

	@PostMapping("/api/admin/enableSystem")
	public JsonResult enableEvaluation(@RequestBody InputConfig config) {
		GlobalProps.put("enabled", true);
		GlobalProps.put("currentYear", config.getCurrentYear());
		GlobalProps.put("currentSemester", config.getCurrentSemester());
		JsonResult result = new JsonResult();
		result.setStatus(200);
		result.setMessage("开启成功");
		return result;
	}

	@PostMapping("/api/admin/disableSystem")
	public JsonResult disableEvaluation() {
		GlobalProps.put("enabled", false);
		JsonResult result = new JsonResult();
		result.setStatus(200);
		result.setMessage("关闭成功");
		return result;
	}

	@PostMapping("/api/admin/generateStat")
	public JsonResult disableAndGenerateStat() {
		if (GlobalProps.get("enabled") != null) {
			if (!(boolean) GlobalProps.get("enabled") && GlobalProps.get("currentYear") != null) {
				evaluationService.generateStats();
			} else {
				throw new IllegalStateException("配置错误,未关闭系统或学年未配置");
			}
		} else {
			throw new IllegalStateException("未进行配置");
		}
		JsonResult result = new JsonResult();
		result.setStatus(200);
		result.setMessage("生成统计成功");
		return result;
	}
}
