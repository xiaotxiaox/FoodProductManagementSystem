package life.majiang.community.dto;

import life.majiang.community.model.Permissions;
import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
    private int id;
    private String name;
    private String description;
    private List<Permissions> permissions;
}
