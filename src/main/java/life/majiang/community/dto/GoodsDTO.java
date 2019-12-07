package life.majiang.community.dto;

import lombok.Data;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Data
public class GoodsDTO {
    private Integer id;
    private String name;
    private Integer price;
    private String unit;

}
