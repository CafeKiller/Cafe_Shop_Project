package com.coffee.mall_tiny01.controller;


import com.coffee.mall_tiny01.common.api.CommonPage;
import com.coffee.mall_tiny01.common.api.CommonResult;
import com.coffee.mall_tiny01.mbg.model.PmsBrand;
import com.coffee.mall_tiny01.service.PmsBrandService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/brand")
public class PmsBrandController {

    @Autowired
    private PmsBrandService brandService;

    /* 日志对象 */
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);


    /**
     * 请求所有品牌信息
     * */
    @GetMapping("/listAll")
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList(){
        return CommonResult.success(brandService.listAllBrand());
    }

    /**
     * 创建一个品牌信息
     * */
    @PostMapping("/create")
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand){
       CommonResult commonResult;
        int count = brandService.createBrand(pmsBrand);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("create success:{}", pmsBrand);
        }else{
            commonResult = CommonResult.failed("操作失败");
            LOGGER.debug("create failed:{}", pmsBrand);
        }
        return commonResult;
    }

    /**
     * 更新一个品牌信息
     * @param id 品牌id
     * @param pmsBrandDto 品牌更新信息
     * */
    @PostMapping("/update/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrandDto, BindingResult result){
        CommonResult commonResult;
        int count = brandService.updateBrand(id, pmsBrandDto);
        if (count == 1){
            commonResult = CommonResult.success(pmsBrandDto);
            LOGGER.debug("updateBrand success:{}", pmsBrandDto);
        }else {
            commonResult = CommonResult.failed("更新Brand失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrandDto);
        }
        return commonResult;
    }

    /**
     * 删除一个品牌信息
     * @param id 品牌id
     * */
    @PostMapping("/delete/{id}")
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") long id){
        int result = brandService.deleteBrand(id);
        if (result == 1){
            LOGGER.debug("deleteBrand success : id={}",id);
            return CommonResult.success(null);
        }else {
            LOGGER.debug("deleteBrand failed : id={}",id);
            return CommonResult.failed("删除失败");
        }
    }

    /**
     * 分页获取品牌信息
     * @param pageNum 请求元素的起始位置 默认从1开始
     * @param pageSize 请求的单页元素数量 默认单页取3位元素
     * */
    @GetMapping("/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                                        @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize){
        List<PmsBrand> brandList = brandService.listBrand(pageNum, pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

}
