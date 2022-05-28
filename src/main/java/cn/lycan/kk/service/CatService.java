package cn.lycan.kk.service;

import cn.lycan.kk.entity.Cat;
import cn.lycan.kk.entity.Variety;
import cn.lycan.kk.mapper.CatMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-5-28
 * @package_name cn.lycan.kk.service
 * @description
 */
@Service
@Log4j2
@Transactional(rollbackFor = Exception.class)
public class CatService {
    @Autowired
    CatMapper catMapper;

    @Autowired
    VarietyService varietyService;
    Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");

    /**
     * 按照id排序查找所有Cat
     *
     * @return
     */
    public List<Cat> catList() {
        log.info("按照id排序：" + sort + " " + "查找所有Cat");
        return catMapper.findAll(sort);
    }

    /**
     * 根据id查找Cat
     *
     * @param id
     * @return
     */
    public Cat getById(int id) {
        log.info("根据id：" + id + " " + "查询cat");
        return catMapper.findById(id);
    }

    /**
     * 根据id获取到的Cat是否为空判断新增或者更新
     *
     * @param cat
     */
    public void addOrUpdate(Cat cat) {
        if (null == getById(cat.getId())) {
            log.info("插入新cat：" + cat);
            catMapper.add(cat);
        } else {
            log.info("更新id为：" + cat.getId() + "的cat：" + cat);
            catMapper.update(cat);
        }
    }

    public void deleteById(int id) {
        if (null == getById(id)) {
            log.info("无法删除，找不到id为：" + id + "的cat");
        } else {
            log.info("删除id为：" + id + "的cat");
            catMapper.deleteById(id);
        }
    }

    public List<Cat> listByVariety(int vid) {
        Variety variety = varietyService.getById(vid);
        if (null == variety) {
            log.info("没有该品种：" + variety + "，查询所有cat");
            return catMapper.findAll(sort);
        } else {
            log.info("根据：" + variety + "查询cat");
            return catMapper.findAllByVariety(variety.getId());
        }
    }

}
