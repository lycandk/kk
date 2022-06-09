package cn.lycan.kk.controller;

import cn.lycan.kk.entity.Cat;
import cn.lycan.kk.result.Result;
import cn.lycan.kk.result.ResultFactory;
import cn.lycan.kk.service.CatService;
import cn.lycan.kk.service.VarietyService;
import cn.lycan.kk.utils.StringUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public Result catList() {
        return ResultFactory.buildSuccessResult(catService.catList());
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
    public Result listByVariety(@PathVariable("vid") int vid) {
        if (0 == vid) {
            return ResultFactory.buildSuccessResult(catList());
        } else {
            return ResultFactory.buildSuccessResult(catService.listByVariety(vid));
        }
    }
    
    @GetMapping("/api/search")
    public List<Cat> searchCats(@RequestParam("keyword") String keyword) {
        if ("".equals(keyword)) {
            return catService.catList();
        } else {
            return catService.findAllByNameLikeOrVarietyLike(keyword);
        }
    }
    
    @PostMapping("api/covers")
    /**
     * 涉及到对文件的操作，对接收到的文件重命名，但保留原始的格式。可以进一步做一下压缩，或者校验前端传来的数据是否为指定格式，这里不再赘述。
     */
    public String coversUpload(MultipartFile file) throws Exception {
        String folder = "d:/Software/webpfojects/kittykitty/kk/src/main/resources/workspace/image";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists()) {
            f.getParentFile().mkdirs();
        }
        try {
            file.transferTo(f);
            String imageUrl = "http://localhost:8443/api/file/" + f.getName();
            return imageUrl;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
