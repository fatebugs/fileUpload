package com.klns.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * User: genesis
 * Date: 2021/1/13
 * Time: 4:52 下午
 * Writer: 张志洋
 * Prepared by Cronos
 */
@Controller
public class UploadController {
    @RequestMapping("/upload")
    public String uploadFile(HttpServletRequest request,MultipartFile upload) throws IOException {
        //设置文件保存目录
        String path = request.getSession().getServletContext().getRealPath("/uploads/");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();//目录如果不存在就创建
        }
        //获取前端传入的文件名以及后缀名，如果只想获取文件名可以使用getName()
        String fileName = upload.getOriginalFilename();
        //使用UUID防止文件名重复
        String uuid = UUID.randomUUID().toString().replace("-", "");
        fileName=uuid+fileName;
        //文件写入
        upload.transferTo(new File(path,fileName));

        return "success";
    }
}
