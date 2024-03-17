package guo.controller;

import com.alibaba.fastjson.JSONObject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("export")
public class FileUploadController {
    // 设置固定的日期格式
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    // 将 yml 中的自定义配置注入到这里
    @Value("${file.root.path}")
    private String filePath;
    // 日志打印
    private Logger log = LoggerFactory.getLogger("FileUploadController");


    @PostMapping("upload")
    @ResponseBody
    public JSONObject upload(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        // 得到格式化后的日期
        String format = dateFormat.format(new Date());
        // 获取上传的文件名称
        String fileName = file.getOriginalFilename();
        // 时间 和 日期拼接
        String newFileName = format + "_" + fileName;
        // 得到文件保存的位置以及新文件名
        File dest = new File(filePath + newFileName);
        try {
            // 上传的文件被保存了
            file.transferTo(dest);
            // 打印日志
            log.info("上传成功，当前上传的文件保存在 {}",filePath + newFileName);
            // 自定义返回的统一的 JSON 格式的数据，可以直接返回这个字符串也是可以的。
            result.put("success",true);
            result.put("message","文件上传成功");
            return result;
        } catch (IOException e) {
            log.error(e.toString());
            // 待完成 —— 文件类型校验工作
            result.put("success",false);
            result.put("message","上传错误");
            return result;
        }

    }

}
