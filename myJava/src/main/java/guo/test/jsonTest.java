package guo.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

    public class jsonTest {


        public static void main(String[] args) {

            //1.比如从前端接收的是这个样子的json字符串,但是我们是不能直接获取到name sex phone所对应的值的，所以必须要对这个字符串进行解析，
            String stu = "{\"name\":\"ss\",\"sex\":\"1\",\"phone\":\"123456789\"}";
            //先转换成JSONObject类型
            JSONObject stuObj = JSON.parseObject(stu);
            //通过JSONObject中的getString("key")方法，得到对应的值
            System.out.println("name：" + stuObj.getString("name") + " sex:" + stuObj.getString("sex"));


            //2.字符串中含有数组的，比如像下面这个字符串
            String stuInfo = "{\"classId\":1,\"stuInfo\":[{\"name\":\"aa\",\"sex\":\"1\",\"phone\":\"123456789\"},{\"name\":\"bb\",\"sex\":\"0\",\"phone\":\"987654321\"}]}";
            JSONObject stuInfoObj = JSONObject.parseObject(stuInfo);//先转换成JSONObject类型
            System.out.println("班级id:" + stuInfoObj.getString("classId"));//拿到班级id
            String stuInfoStr = stuInfoObj.getString("stuInfo");//拿到stuInfo这个json字符串
            System.out.println("学生信息集合:" + stuInfoStr);
            JSONArray jsonstuInfo = JSONObject.parseArray(stuInfoStr);//将stuInfo解析成json数组
            for (int i = 0; i < jsonstuInfo.size(); i++) {//遍历学生信息
                JSONObject jsonStuInfo = jsonstuInfo.getJSONObject(i);//根据下标以此拿数据，每一个数据又是一个JSONObject对象，所以用JSONObject接收
                String name = jsonStuInfo.getString("name");
                String sex = jsonStuInfo.getString("sex");
                String phone = jsonStuInfo.getString("phone");
                System.out.println("这个学生的姓名是：" + name + "性别是：" + sex + "电话号码是：" + phone);
            }

            Scanner sc = new Scanner(System.in );
            int n = sc.nextInt();
            int[]  x= new int[n];
            int[] y = new int[n];
            for (int i  = 0 ; i<n; i++){
                x[i] = sc.nextInt();
            }
            for (int i = 0; i<n; i++){
                y[i] = sc.nextInt();
            }

            int dis[] = new int[n];
            int result[] = new int[n];
            for (int i = 0;i<n ; i++){
                result[i] = Integer.MAX_VALUE;
            }

            for (int i = 0;i<n;i++){
                for (int j= 0;j<n;j++){
                    for (int k = 0;k<n; k++){
                        dis[0] = Math.abs(x[i]-x[k]) +  Math.abs(x[j]-x[k]);
                    }
                    Arrays.sort(dis);
                    int temp = 0;
                    for (int t= 0;t<n;t++){
                        temp = temp +dis[t];
                        result[t] = Math.min(result[t],temp);
                    }
                }
            }

            System.out.println(result[0]);
            for (int i = 1;i<n;i++){
                System.out.println(" "+result[i]);

            }        }

    }

