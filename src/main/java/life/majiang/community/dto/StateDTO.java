package life.majiang.community.dto;

import lombok.Data;

@Data
public class StateDTO {
    /*
    原材料表中：
    待处理1
    已同意2
    未同意3
    生产计划表中：
    进行中1
    未开始2
    已完成3
     */
    private int NumOne = 0;
    private int NumTwo = 0;
    private int NumThree = 0;
}
