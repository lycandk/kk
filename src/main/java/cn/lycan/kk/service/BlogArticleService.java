package cn.lycan.kk.service;

import cn.lycan.kk.dao.BlogArticleDAO;
import cn.lycan.kk.entity.BlogArticle;
import cn.lycan.kk.entity.MyPage;
import cn.lycan.kk.redis.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Makkapakka
 * @date 2022-6-13
 * @package_name cn.lycan.kk.service
 * @description
 */
@Service
@Slf4j
public class BlogArticleService {
    Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
    MyPage<BlogArticle> articles;
    @Autowired
    BlogArticleDAO blogArticleDAO;
    
    @Autowired
    RedisService redisService;
    
    /**
     * 编写一个传入页码与页面尺寸参数的方法
     *
     * @param page
     * @param size
     * @return
     */
    public MyPage list(int page, int size) {
        
        MyPage<BlogArticle> blogArticles;
        String key = "articlepage:" + page;
        Object articlePageCache = redisService.get(key);
        
        if (articlePageCache == null) {
            Page<BlogArticle> articlesInDb = blogArticleDAO.findAll(PageRequest.of(page, size, sort));
            articles = new MyPage<>(articlesInDb);
            log.info("获取到文章列表:" + articles);
            redisService.set(key, articles);
        } else {
            articles = (MyPage<BlogArticle>) articlePageCache;
        }
        return articles;
    }
    
    public BlogArticle findById(int id) {
        BlogArticle blogArticle;
        String key = "article:" + id;
        Object articleCache = redisService.get(key);
    
        if (articleCache == null) {
            blogArticle = blogArticleDAO.findById(id);
            log.info("根据id:" + id + "查询文章:" + blogArticle);
            redisService.set(key, blogArticle);
        } else {
            blogArticle = (BlogArticle) articleCache;
        }
        return blogArticle;
    }
    
    public void addOrUpdate(BlogArticle blogArticle) {
        log.info("添加文章：" + blogArticle);
        blogArticleDAO.save(blogArticle);
        redisService.delete("article" + blogArticle.getId());
        Set<String> keys = redisService.getKeysByPattern("articlepage*");
        redisService.delete(keys);
    }
    
    public void deleteById(int id) {
        log.info("根据id：" + id + "删除文章");
        blogArticleDAO.deleteById(id);
        redisService.delete("article:" + id);
        Set<String> keys = redisService.getKeysByPattern("articlepage*");
        redisService.delete(keys);
    }
    
}
