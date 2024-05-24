package dj.com.mybatisplus.generate.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dj.com.mybatisplus.generate.entity.Dj1;
import dj.com.mybatisplus.generate.service.Dj1Service;
import dj.com.tool.BeanTool;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiandong 2024-05-21 create
 */
@RestController
public class Dj1Controller {

    @Resource
    Dj1Service djService;

    /**
     * 保存
     */
    @PostMapping("/saveDj1")
    @ResponseBody
    public String save(@RequestBody Dj1 dj) {
        boolean save = djService.save(dj);
        return String.valueOf(save);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteDj1")
    @ResponseBody
    public String delete(@RequestBody Long id) {
        boolean remove = djService.removeById(id);
        return String.valueOf(remove);
    }

    /**
     * 更新
     */
    @PostMapping("/updateDj1")
    @ResponseBody
    public String update(@RequestBody Dj1 dj) {
        boolean update = djService.updateById(dj);
        return String.valueOf(update);
    }

    /**
     * 查询
     */
    @PostMapping("/getDj1")
    @ResponseBody
    public Dj1 get(@RequestBody Long id) {
        return djService.getById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/listDj1")
    @ResponseBody
    public List<Dj1> list(@RequestBody DjQuery djQuery) {
        Dj1 dj = new Dj1();
        BeanTool.copyProperties(djQuery, dj);

        LambdaQueryWrapper<Dj1> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.setEntity(dj);
        IPage<Dj1> pageQuery = new Page<>();
        pageQuery.setPages(djQuery.getPage());
        pageQuery.setSize(djQuery.getSize());
        return djService.list(pageQuery, queryWrapper);
    }


}
