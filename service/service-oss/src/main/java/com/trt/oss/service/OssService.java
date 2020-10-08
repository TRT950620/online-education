package com.trt.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Authror 谭仁涛
 * @Description
 * @Date 2020/9/20 18:54
 */
public interface OssService {

    /**
     * 上传头像到oss
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
