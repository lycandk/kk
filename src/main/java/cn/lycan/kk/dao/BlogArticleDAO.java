package cn.lycan.kk.dao;

import cn.lycan.kk.entity.BlogArticle;
import cn.lycan.kk.service.BlogArticleService;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Makkapakka
 * @date 2022-6-13
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface BlogArticleDAO extends JpaRepository<BlogArticle, Integer> {
    BlogArticle findById(int id);
}
