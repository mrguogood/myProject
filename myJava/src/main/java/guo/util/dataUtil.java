package guo.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class dataUtil {
    public static void main(String[] args) {
        //将Date类型的日期转换为字符串格式
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(date);
        System.out.println(strDate);

        //将字符串格式的日期转换为Date类型的日期
        try {
             date = sdf.parse(strDate);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        JsonObject params = new JsonObject();
        params.addProperty("ids", "[1,3]");
        String[] ids = params.get("ids").getAsString().replaceAll("\\[|\\]|\\s", "").split(",");
        long[] result = new long[ids.length];
        for (int i = 0; i < ids.length; i++) {
            result[i] = Long.parseLong(ids[i]);
        }
        /*
        System.out.print是用于输出到控制台的标准输出流的方法。
        对于long数组，System.out.print不能直接输出整个数组，但你可以使用循环遍历数组的每个元素并输出它们
         */
        System.out.println(result.toString());
    }
}
