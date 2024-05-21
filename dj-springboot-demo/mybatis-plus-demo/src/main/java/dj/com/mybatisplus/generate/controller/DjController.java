package dj.com.mybatisplus.generate.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import dj.com.mybatisplus.generate.entity.Dj;
import dj.com.mybatisplus.generate.service.DjService;
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
public class DjController {

    @Resource
    DjService djService;

    /**
     * 保存
     */
    @PostMapping("/saveDj")
    @ResponseBody
    public String save(@RequestBody Dj dj) {
        boolean save = djService.save(dj);
        return String.valueOf(save);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteDj")
    @ResponseBody
    public String delete(@RequestBody Long id) {
        boolean remove = djService.removeById(id);
        return String.valueOf(remove);
    }

    /**
     * 更新
     */
    @PostMapping("/updateDj")
    @ResponseBody
    public String update(@RequestBody Dj dj) {
        boolean update = djService.updateById(dj);
        return String.valueOf(update);
    }

    /**
     * 查询
     */
    @PostMapping("/getDj")
    @ResponseBody
    public Dj get(@RequestBody Long id) {
        return djService.getById(id);
    }

    /**
     * 分页查询
     */
    @PostMapping("/listDj")
    @ResponseBody
    public List<Dj> list(@RequestBody DjQuery djQuery) {
        Dj dj = new Dj();
        BeanTool.copyProperties(djQuery, dj);

        LambdaQueryWrapper<Dj> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.setEntity(dj);
        IPage<Dj> pageQuery = new Page<>();
        pageQuery.setPages(djQuery.getPage());
        pageQuery.setSize(djQuery.getSize());
        return djService.list(pageQuery, queryWrapper);
    }


}
