package in.suffix.shefat.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeErrorDetails {
    private LocalDateTime time;
    private String msg;
    private String status;
}
