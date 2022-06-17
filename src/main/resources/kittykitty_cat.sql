create table cat
(
    id             int auto_increment
        primary key,
    cover          varchar(255) null,
    nickname       varchar(255) not null,
    variety        varchar(255) not null,
    scientificname varchar(255) null,
    latinname      varchar(255) null,
    placeoforigin  varchar(255) null,
    color          varchar(255) null,
    birthdate      varchar(255) null,
    abs            varchar(999) null,
    vid            int null,
    constraint fk_cat_variety_on_vid
        foreign key (vid) references variety (id)
            on update cascade on delete set null
);

INSERT INTO kittykitty.cat (id, cover, nickname, variety, scientificname, latinname, placeoforigin, color, birthdate,
                            abs, vid)
VALUES (1, 'http://localhost:8443/api/file/3dojtk.png', '虎虎', '美短', '美国短毛猫', 'American Shorthair', '美国', '银色条纹',
        '2022-1-1', '美国最早的家猫据说是随17世纪的移民来到新大陆的猫咪，接下来的几个世纪里，壮实而具有工作效率的猫咪扩散到整个美国，'' +
            ''它们大多是熟练的捕鼠能手而非家庭宠物。但到了20世纪初，一种庭院捕鼠猫的改良品种开始出现，被称为短毛家猫。'' +
            ''人们精心的繁育进一步改良了家猫品种，到20世纪60年代，该品种被更名为美国短毛猫，逐渐在纯种猫咪展览上吸引人们的注意力。'' +
            ''美国短毛猫健康而且性格坚忍，是适合几乎任何类型家庭的完美宠物。'' +
            ''美国短毛猫素以体格魁伟，骨骼粗壮，肌肉发达，生性聪明，性格温顺著称，是短毛猫类中大型品种。被毛厚密，毛色多达30种，'' +
            ''其中以银色条纹品种最为名贵', 3);
INSERT INTO kittykitty.cat (id, cover, nickname, variety, scientificname, latinname, placeoforigin, color, birthdate,
                            abs, vid)
VALUES (2, 'http://localhost:8443/api/file/ybedjw.jpg', '草莓', '梨花', '中华田园猫', 'Felis catus', '中国', '灰黑间隔条纹',
        '2021-12-30', '"中华田园猫"是对中国本土家猫类的统称。其中又有狸花猫、橘猫(黄狸)、四川简州猫(四耳猫)、三花猫、白猫、黑猫(玄猫)，黑白花猫、黄猫、山东狮子猫等多个品种，其中以狸花猫和黄狸最常见(灰色狸花猫则较为鲜见)，其中的橘猫体型最易发胖，而纯白长毛异色眼的山东狮子猫尤为稀有和珍贵。
由于我国历史上并不像重视金鱼那样人工选择定向培养猫品种，因此猫文化不及西方国家那样发达。过去咱们的中国普通猫一直没个学名，近些年来网友根据同样尴尬的中国普通家犬给中国普通家猫命名为“中华田园猫”。经过CFA中国长城猫俱乐部历时六年的不懈努力，"中华田园猫"类中的"中国狸花猫"成为了我国目前唯一被世界认可的中国本土自然品种。',
        1);
INSERT INTO kittykitty.cat (id, cover, nickname, variety, scientificname, latinname, placeoforigin, color, birthdate,
                            abs, vid)
VALUES (7, 'http://localhost:8443/api/file/xna7nn.jpg', '花贝', '梨花', '中华田园猫', 'Felis catus', '中国', '经典梨花', '2019-8-1',
        '女生，已绝育，三岁龄，性格粘人', 1);
