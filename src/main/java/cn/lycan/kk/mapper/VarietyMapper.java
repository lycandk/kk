package cn.lycan.kk.mapper;

import cn.lycan.kk.entity.Variety;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-5-28
 * @package_name cn.lycan.kk.mapper
 * @description
 */
@Mapper
public interface VarietyMapper {

    /**
     * 查询所有variety
     *
     * @param sort
     * @return
     */
    @Select("SELECT * FROM variety")
    List<Variety> findall(Sort sort);

    /**
     * 根据id查询variety
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM variety WHERE id = #{id}")
    Variety findById(int id);
}
