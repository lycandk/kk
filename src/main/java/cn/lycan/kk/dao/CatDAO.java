package cn.lycan.kk.dao;

import cn.lycan.kk.entity.Cat;
import cn.lycan.kk.entity.Variety;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-6-9
 * @package_name cn.lycan.kk.dao
 * @description
 */
public interface CatDAO extends JpaRepository<Cat, Integer> {
    
    /**
     * 根据品种查询猫咪
     *
     * @param variety
     * @return
     */
    List<Cat> findAllByVarieties(Variety variety);
    
    /**
     * 根据昵称或品种模糊查询猫咪
     *
     * @param keyword1
     * @param keyword2
     * @return
     */
    List<Cat> findAllByNicknameLikeOrVarietyLike(String keyword1, String keyword2);
}
