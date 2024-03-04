package guo.util;

import com.google.gson.JsonObject;

public class JsonUtil {
    public static void main(String[] args) {
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
        for (long i : result){
            System.out.println(i);
        }
        System.out.println(result.toString());
    }
}
