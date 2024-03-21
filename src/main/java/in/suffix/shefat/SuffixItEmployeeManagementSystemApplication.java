package in.suffix.shefat;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition()
public class SuffixItEmployeeManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuffixItEmployeeManagementSystemApplication.class, args);
	}

}
