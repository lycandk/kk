package cn.lycan.kk.service;

import cn.lycan.kk.dao.CatDAO;
import cn.lycan.kk.entity.Cat;
import cn.lycan.kk.entity.Variety;
import cn.lycan.kk.redis.RedisService;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import cn.lycan.kk.utils.CastUtils;
import com.alibaba.fastjson2.JSON;
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
    Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
    @Autowired
    RedisService redisService;
    @Autowired
    private CatDAO catDAO;
    @Autowired
    private VarietyService varietyService;
    
    /**
     * 按照id排序查找所有Cat
     *
     * @return
     */
    public List<Cat> catList() {
        List<Cat> cats;
        String key = "catlist";
        Object catCache = redisService.get(key);
        
        if (catCache == null) {
            cats = catDAO.findAll(sort);
            log.info("按照id排序：" + sort + " " + "查找所有Cat:" + JSON.parse(String.valueOf(cats)));
            redisService.set(key, cats);
        } else {
            // 从缓存拿回来的是 Object ，我们需要编写一个方法把它转换为 List
            cats = CastUtils.objectConvertToList(catCache, Cat.class);
        }
        return cats;


//        return catDAO.findAll(sort);
    }
    
    /**
     * 根据id查找Cat
     *
     * @param id
     * @return
     */
    public Result getById(int id) {
        log.info("根据id查询cat:" + catDAO.findById(id));
        return ResultFactory.buildSuccessResult(catDAO.findById(id));
    }
    
    /**
     * 根据id获取到的Cat是否为空判断新增或者更新
     *
     * @param cat
     */
    public void addOrUpdate(Cat cat) {
        redisService.delete("catlist");
        log.info("存入id为：" + cat.getId() + "的cat：" + cat);
        catDAO.save(cat);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("catlist");
//        if (null == getById(cat.getId())) {
//            log.info("插入新cat：" + cat);
//            catMapper.add(cat);
//        } else {
//            log.info("更新id为：" + cat.getId() + "的cat：" + cat);
//            catMapper.update(cat);
//        }
    }
    
    public void deleteById(int id) {
        redisService.delete("catlist");
        log.info("删除id为：" + id + "的cat");
        catDAO.deleteById(id);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        redisService.delete("catlist");
//        if (null == getById(id)) {
//            log.info("无法删除，找不到id为：" + id + "的cat");
//        } else {
//            log.info("删除id为：" + id + "的cat");
//            catMapper.deleteById(id);
//        }
    }
    
    public List<Cat> listByVariety(int vid) {
        Variety variety = varietyService.getById(vid);
        log.info("根据vid：" + vid + "查询相关品种");
        log.info("根据：" + variety + "查询cat:" + catDAO.findAllByVarieties(variety));
        return catDAO.findAllByVarieties(variety);
//        if (null == variety) {
//            log.error("没有该id对应的品种：" + vid + "--" + variety + "，查询所有cat");
//            return catMapper.findAll(sort);
//        } else {
//            log.info("根据：" + variety + "查询cat");
//            return catMapper.findAllByVariety(variety.getId());
//        }
    }
    
    public List<Cat> search(String keywords) {
        log.info("根据关键词：" + keywords + "查找相关猫咪");
        return catDAO.findAllByNicknameLikeOrVarietyLike('%' + keywords + '%', '%' + keywords + '%');
    }
    
}
