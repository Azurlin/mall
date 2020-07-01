package com.az.pic.service;

import com.jt.common.utils.UploadUtil;
import com.jt.common.vo.PicUploadResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class PicService {
    public PicUploadResult picUpload(MultipartFile pic){
                /*
            1.验证图片合法,通过后缀名 .png .jpg .gif等的后缀是合法
            2.生成一个多级路径
                /upload/a/c/3/d/3/f/4/h/
                磁盘路径使用
                url地址
            3.重命名图片名称,存储到静态文件夹下的路径中
                d:/img//upload/a/c/3/d/3/f/4/h/asdljflasj-asdlkfjsad-sdakf.jpg
            4.对应存储的地址,生成一个可访问的url
                http://image.jt.com/upload/a/c/3/d/3/f/4/h/asdljflasj-asdlkfjsad-sdakf.jpg
         */

        PicUploadResult result = new PicUploadResult();
        //获取图片原文件名称
        String oName = pic.getOriginalFilename();
        //moon.jpg 从字符串截取最后一个.到结尾
        String extName = oName.substring(oName.lastIndexOf("."));
        //使用正则表达式判断是否合法
        boolean matches = extName.matches(".(png|jpg|gif|bmp|jpeg)$");
        //关心不合法 生成对象 设置error=1 返回对象 后续逻辑不再执行
        if(!matches){
            result.setError(1);
            return result;
        }

        //若是合法 生成一个多级路径
        String dir = "/"+ UploadUtil.getUploadPath(oName,"easymall")+"/";
        //创建磁盘的文件夹
        String path = "d:/img"+dir;
        File _dir = new File(path);
        //判断文件是否文在不存在则创建
        if(!_dir.exists()){
            _dir.mkdirs();
        }
        try {
            String nName = UUID.randomUUID().toString()+extName;
            String file=path+nName;
            pic.transferTo(new File(file));
            //拼接url
            String url = "http://image.jt.com/"+dir+nName;
            result.setUrl(url);
            return result;
        }catch (Exception e){
            e.printStackTrace();
            result.setError(1);
            return result;
        }

    }
}
