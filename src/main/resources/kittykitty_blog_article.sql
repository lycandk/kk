create table blog_article
(
    id                   int auto_increment
        primary key,
    article_title        varchar(255) null,
    article_content_html longtext null,
    article_content_md   longtext null,
    article_abstract     varchar(255) null,
    article_cover        varchar(255) null,
    article_date         datetime null
);

INSERT INTO kittykitty.blog_article (id, article_title, article_content_html, article_content_md, article_abstract,
                                     article_cover, article_date)
VALUES (45, '花贝', '<div class="hljs-center">
<p>花贝</p>
</div>
<p>性格温柔，平顺。</p>
', '::: hljs-center
花贝
:::
  性格温柔，平顺。
	', '花贝的故事', 'http://localhost:8443/api/file/15awt6.jpg', '2022-06-14 00:00:00');
