package cn.lycan.kk.service;

import cn.lycan.kk.entity.Variety;
import cn.lycan.kk.mapper.VarietyMapper;
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
    VarietyMapper varietyMapper;

    /**
     * 查询所有Variety并根据id排序
     *
     * @return
     */
    public List<Variety> varietyList() {
        Sort sort = Sort.by(Sort.DEFAULT_DIRECTION, "id");
        log.info("根据:" + sort + " " + "sortbyid查找所有Variety:");
        return varietyMapper.findall(sort);
    }

    /**
     * 根据id查找Variety
     *
     * @param id
     * @return
     */
    public Variety getById(int id) {
        log.info("根据id:" + id + " " + "查找Variety");
        Variety v = varietyMapper.findById(id);
        return v;
    }
}
