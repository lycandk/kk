package cn.lycan.kk.controller;

import cn.lycan.kk.entity.Cat;
import cn.lycan.kk.service.CatService;
import cn.lycan.kk.service.VarietyService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Makkapakka
 * @date 2022-5-28
 * @package_name cn.lycan.kk.controller
 * @description
 */
@RestController
@CrossOrigin
@Log4j2
public class CatCafeController {

    @Autowired
    CatService catService;

    @Autowired
    VarietyService varietyService;

    @GetMapping("/api/cats")
    public List<Cat> catList() {
        return catService.catList();
    }

    @PostMapping("/api/cats")
    public void addOrUpdate(@RequestBody Cat cat) throws Exception {
        catService.addOrUpdate(cat);
    }

    @PostMapping("/api/delete")
    public void deleteCatById(@RequestBody Cat cat) throws Exception {
        catService.deleteById(cat.getId());
    }

    @GetMapping("/api/varieties/{vid}/cats")
    public List<Cat> listByVariety(@PathVariable("vid") int vid) {
        if (0 == vid) {
            return catList();
        } else {
            return catService.listByVariety(vid);
        }
    }
}
