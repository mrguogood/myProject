package guo.util;



import com.alibaba.fastjson.JSON;
import com.google.gson.JsonObject;
import guo.entity.Header;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/test")
public class NoteUtil {

    /*
    @PathVariable 将请求地址最后的字段(value)和value= 的字符(key)组成map; 这样可以区分不同的请求实现不同的功能
    @ResponseBody 将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
                                       写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
     @RequestBody
                     @RequestBody主要用来接收前端传递给后端的json字符串中的数据的(请求体中的数据的)；
                     而最常用的使用请求体传参的无疑是POST请求了，所以使用@RequestBody接收数据时，一般都用POST方式进行提交。
                     在后端的同一个接收方法里，@RequestBody与@RequestParam()可以同时使用，
                     @RequestBody最多只能有一个，而@RequestParam()可以有多个。

                        注：一个请求，只有一个RequestBody；一个请求，可以有多个RequestParam。
     */
    @RequestMapping(value = "{mehtodName}", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String hbtdSeamlessLockTempAbutment(@RequestBody String params,
                                               @PathVariable Map<String, String> map, HttpServletRequest request, HttpServletResponse response) {
        JsonObject responseJson = new JsonObject();
        String mehtodName = map.get("mehtodName");// 接口名称
        JsonObject requestParam = (JsonObject) JSON.parse(params);
        JsonObject headerJson = (requestParam.has("header") ? requestParam.get("header").getAsJsonObject() : null);
        JsonObject deviceJson = (requestParam.has("device") ? requestParam.get("device").getAsJsonObject() : null);
        Header header = (Header)JSON.parseObject(headerJson.getAsString(),Header.class);
        return "a";
    }


    /*
    @RequestParam("data")  eg:http://localhost:8080/getuserid?data = 12344   将请求地址中的12344赋值给dataString

      @RequestParam(value = "file", required = false)
                   required：是否必需，默认为 true，即 请求中必须包含该参数，如果没有包含，将会抛出异常
     */
    @RequestMapping(value = "/appUploadRailDisease", method = {RequestMethod.POST})
    @ResponseBody
    public void uploadRailDisease(@RequestParam("data") String dataString,
                                                       @RequestParam(value = "file", required = false) MultipartFile[] files) {

    }


}
