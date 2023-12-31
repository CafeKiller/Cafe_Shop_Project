package com.coffee.mall_tiny01.service;

import com.coffee.mall_tiny01.common.api.CommonResult;

/*
* 会员管理Service
* */
public interface UmsMemberService {

    /* 生成验证码 */
    CommonResult generateAuthCode(String telephone);

    /* 判断验证码和手机号码是否匹配 */
    CommonResult verifyAuthCode(String telephone, String authCode);

}
