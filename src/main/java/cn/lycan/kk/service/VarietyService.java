package cn.lycan.kk.service;

import cn.lycan.kk.dao.VarietyDAO;
import cn.lycan.kk.entity.Variety;
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
public class VarietyService {
    @Autowired
    VarietyDAO varietyDAO;
    
    /**
     * 查询所有Variety并根据id排序
     *
     * @return
     */
    public List<Variety> varietyList() {
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        log.info("执行方法：varietyList()，根据:" + sort + "," + "查找所有Variety:" + varietyDAO.findAll(sort));
        return varietyDAO.findAll(sort);
    }
    
    /**
     * 根据id查找Variety
     *
     * @param id
     * @return
     */
    public Variety getById(int id) {
        Variety v = varietyDAO.findById(id).orElse(null);
        log.info("执行方法:getById(int id),根据id:" + id + " " + "查找到Variety：" + v);
        return v;
    }
}
