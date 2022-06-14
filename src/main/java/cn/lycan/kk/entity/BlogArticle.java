package cn.lycan.kk.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @author Makkapakka
 * @date 2022-6-13
 * @package_name cn.lycan.kk.entity
 * @description
 */
@Data
@Entity
@Table(name = "blog_article")
@ToString
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class BlogArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NotNull(message = "id 不能为 null")
    private int id;
    
    /**
     * Article title.
     */
    @NotEmpty(message = "文章标题不能为空")
    private String articleTitle;
    
    /**
     * Article content after render to html.
     */
    private String articleContentHtml;
    
    /**
     * Article content in markdown syntax.
     */
    private String articleContentMd;
    
    /**
     * Article abstract.
     */
    private String articleAbstract;
    
    /**
     * Article cover's url.
     */
    private String articleCover;
    
    /**
     * Article release date.
     */
    private Date articleDate;
}
