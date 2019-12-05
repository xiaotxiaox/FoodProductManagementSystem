package life.majiang.community.controller;

import life.majiang.api.CommonResult;
import life.majiang.community.dto.DepartmentDTO;
import life.majiang.community.dto.TeamDTO;
import life.majiang.community.mapper.DepartmentMapper;
import life.majiang.community.mapper.FactoryMapper;
import life.majiang.community.mapper.TeamMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Department;
import life.majiang.community.model.DepartmentExample;
import life.majiang.community.model.Team;
import life.majiang.community.model.TeamExample;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xiaot
 * 2019/12/5 11:53
 */

@RestController
public class TeamController{
        @Autowired
        private DepartmentMapper departmentMapper;

        @Autowired
        private TeamMapper teamMapper;

        @Autowired
        private UserMapper userMapper;

        @Autowired
        private FactoryMapper factoryMapper;

        @RequestMapping(value = "/api/workshop/team",method = RequestMethod.GET)
        public List<TeamDTO> get(){
            TeamExample example = new TeamExample();
            example.createCriteria();
            List<Team> teams = teamMapper.selectByExample(example);
            List<TeamDTO> teamDTOS = new ArrayList<TeamDTO>();
            for (Team team : teams) {
                TeamDTO temp = new TeamDTO();
                BeanUtils.copyProperties(team,temp);
                temp.setFactory(factoryMapper.selectByPrimaryKey(team.getFactory()));
                temp.setDepartment(departmentMapper.selectByPrimaryKey(team.getDepartment()));
                teamDTOS.add(temp);
            }
            return teamDTOS;
        }

        @RequestMapping(value = "/api/workshop/team",method = RequestMethod.POST)
        public Object post(@RequestBody Team team,
                           HttpServletRequest request){
            teamMapper.insert(team);
            return CommonResult.success("创建成功！");
        }

        @RequestMapping(value = "/api/workshop/team/{id}",method = RequestMethod.PUT)
        public Object put(@RequestBody Team team,
                          @PathVariable(name = "id") int id,
                          HttpServletRequest request){
            teamMapper.updateByPrimaryKey(team);
            return team;
        }

        @RequestMapping(value = "/api/workshop/team/{id}",method = RequestMethod.DELETE)
        public Object delete(@PathVariable(name = "id") int id){
            teamMapper.deleteByPrimaryKey(id);
            return CommonResult.success("删除成功！");
        }
}
