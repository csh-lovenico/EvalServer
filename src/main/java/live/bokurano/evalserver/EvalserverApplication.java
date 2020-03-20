package live.bokurano.evalserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("live.bokurano.evalserver.mapper")
public class EvalserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(EvalserverApplication.class, args);
	}

}
