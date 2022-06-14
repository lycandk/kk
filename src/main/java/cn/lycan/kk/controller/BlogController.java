package cn.lycan.kk.controller;

import cn.lycan.kk.entity.BlogArticle;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Makkapakka
 * @date 2022-6-13
 * @package_name cn.lycan.kk.controller
 * @description
 */
@RestController
public class BlogController {
    
    @Autowired
    cn.lycan.kk.service.BlogArticleService blogArticleService;
    
    
    @PostMapping("api/admin/content/article")
    public Result saveArticle(@RequestBody BlogArticle article) {
        blogArticleService.addOrUpdate(article);
        return ResultFactory.buildSuccessResult("保存成功");
    }
    
    @GetMapping("/api/article/{id}")
    public Result getOneArticle(@PathVariable("id") int id) {
        return ResultFactory.buildSuccessResult(blogArticleService.findById(id));
    }
    
    /**
     * 分页查询
     *
     * @param size
     * @param page
     * @return 该api前台也许访问，不属于管理列表
     */
    @GetMapping("/api/article/{size}/{page}")
    public Result listArticles(@PathVariable("size") int size, @PathVariable("page") int page) {
        return ResultFactory.buildSuccessResult(blogArticleService.list(page - 1, size));
    }
    
    @DeleteMapping("/api/admin/content/article/{id}")
    public Result deleteArticle(@PathVariable("id") int id) {
        blogArticleService.deleteById(id);
        return ResultFactory.buildSuccessResult("删除成功");
    }
    
}
