package cn.lycan.kk.mapper;

import cn.lycan.kk.entity.Cat;
import cn.lycan.kk.entity.Variety;
import org.apache.ibatis.annotations.*;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-5-28
 * @package_name cn.lycan.kk.mapper
 * @description
 */
@Mapper
public interface CatMapper {

    /**
     * 根据Variety找到相关Cat
     *
     * @param vid
     * @return
     */
    @Select("SELECT * FROM cat WHERE vid = #{vid}")
    @Result(column = "vid", property = "varieties", javaType = Variety.class, one = @One(select = "cn.lycan.kk.mapper.VarietyMapper.findById"))
    List<Cat> findAllByVariety(int vid);

    /**
     * 根据关键词模糊查询相关nickname或variety的Cat
     *
     * @param keyword
     * @return
     */
    @Select("SELECT * FROM cat WHERE nickname LIKE #{keyword} OR variety LIKE #{keyword}")
    List<Cat> findAllByNameLikeOrVarietyLike(String keyword);

    /**
     * 按照sort排序规则查询Cat
     *
     * @param sort
     * @return
     */
    @Select("SELECT * FROM cat")
    @Result(column = "vid", property = "varieties", javaType = Variety.class, one = @One(select = "cn.lycan.kk.mapper.VarietyMapper.findById"))
    List<Cat> findAll(Sort sort);

    /**
     * 通过id查询Cat
     *
     * @param id
     * @return
     */
    @Select("SELECT * FROM cat WHERE id = #{id}")
    Cat findById(int id);

    /**
     * 插入Cat
     *
     * @param cat
     */
    @Insert("INSERT INTO cat (cover,nickname,variety,scientificname,latinname,placeoforigin,color,birthdate,abs,vid)" +
            "VALUES (#{cover},#{nickname},#{variety},#{scientificname},#{latinname},#{placeoforigin},#{color},#{birthdate}," +
            "#{abs},#{varieties.id})")
    void add(Cat cat);


    /**
     * 根据id更新Cat
     *
     * @param cat
     */
    @Update("UPDATE cat SET cover=#{cover},nickname=#{nickname},variety=#{variety},scientificname=#{scientificname},latinname=#{latinname}," +
            "placeoforigin=#{placeoforigin},color=#{color},birthdate=#{birthdate},abs=#{abs},vid=#{varieties.id} WHERE id = #{id}")
    void update(Cat cat);

    /**
     * 根据id删除Cat
     *
     * @param id
     */
    @Delete("DELETE FROM cat WHERE id =#{id}")
    void deleteById(int id);


}
