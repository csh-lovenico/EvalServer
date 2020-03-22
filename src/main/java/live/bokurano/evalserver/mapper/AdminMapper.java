package live.bokurano.evalserver.mapper;

import live.bokurano.evalserver.model.mysql.Admin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

public interface AdminMapper {

	@Select("SELECT * FROM admin WHERE admin_username=#{adminUsername}")
	@Results({
			@Result(property = "adminUsername", column = "admin_username"),
			@Result(property = "adminPassword", column = "adminPassword")
	})
	Admin loadAdmin(@Param("adminUsername") String adminUsername);
}
