package life.majiang.community.dto;

import lombok.Data;

@Data
public class ProductionplanDTO {
    private Integer id;
    private String timecreate;
    private Integer neednum;
    private String timelatest;
    private Integer state;
}
