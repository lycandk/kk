package cn.lycan.kk.dao;

import cn.lycan.kk.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Makkapakka
 * @date 2022-6-9
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface VarietyDAO extends JpaRepository<Variety, Integer> {
}
