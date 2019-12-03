package life.majiang.community.dto;

import lombok.Data;
import org.springframework.scheduling.support.SimpleTriggerContext;
@Data
public class LoginDTO {
    private String username;
    private String password;
}
