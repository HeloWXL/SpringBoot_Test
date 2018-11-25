package com.test.demo.controller;


import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author wangxl
 * @date 2018/11/22 10:19
 */

/**
 * swagger通过注解表明该接口会生成文档，包括接口名、请求方法、参数、返回信息的等等。
 *
 * @Api：修饰整个类，描述Controller的作用
 * @ApiOperation：描述一个类的一个方法，或者说一个接口
 * @ApiParam：单个参数描述
 * @ApiModel：用对象来接收参数
 * @ApiProperty：用对象接收参数时，描述对象的一个字段
 * @ApiResponse：HTTP响应其中1个描述
 * @ApiResponses：HTTP响应整体描述
 * @ApiIgnore：使用该注解忽略这个API
 * @ApiError ：发生错误返回的信息
 * @ApiImplicitParam：一个请求参数
 * @ApiImplicitParams：多个请求参数！
 */
@Api(tags ="测试测试...")
@RestController
@RequestMapping("test")
public class TestController {

    protected  Logger logger = LoggerFactory.getLogger(TestController.class);
//    @Resource
//    private UserService userService;

//
//    @RequestMapping("/gets")
//    public String test(){
//        return "test";
//    }


//    @ApiOperation(value="获得所有用户")
//    @GetMapping("/getAllUser")
//    public ResultData<List<User>> getAllUser(){
//        ResultData<List<User>> resultData = new ResultData<>();
//        logger.info("开始查询....");
//        resultData.setResult(userService.getAllUser());
//        resultData.setMsg("查询成功");
//        resultData.setCode(200);
//        return resultData;
//    }

//    @ApiOperation(value="测试1")
//    @GetMapping("/get")
//    public ResultData<String> getTest(@RequestParam("name") String name , @RequestParam("age") String age){
//        ResultData<String> resultData = new ResultData<>();
//        logger.info("开始查询....");
//        if(StringUtils.isEmpty(name)){
//            logger.warn("name为空");
//            resultData.setMsg("name为空");
//        }
//        if(StringUtils.isEmpty(age)){
//            logger.warn("age为空");
//            resultData.setMsg("age为空");
//        }
//        resultData.setCode(Code.SUCCESSED);
//        resultData.setResult(name+age);
//        return resultData;
//    }
//
//    @ApiOperation(value="测试2")
//    @GetMapping("/get2")
//    public String getTest2(HttpServletRequest request){
//        String name = request.getParameter("name");
//        String age = request.getParameter("age");
//        return "this is "+name+"and "+age;
//    }
//
//
//    //通过创建一个Javabean来封装表单参数
//    @ApiOperation(value="测试3")
//    @GetMapping("/test3")
//    public String test3(@RequestBody User user)
//    {
//        String name =user.getUsername();
//        String password = user.getPassword();
//        return name+password;
//    }

//    //通过PathVariable注解来绑定请求路径的参数
//    @RequestMapping( value = "/test4/{a}/{bbb}")
//
//    public String test4(@PathVariable("a") String aaa, @PathVariable String bbb )
//    {
//     return aaa+bbb;
//    }
//
//
//    @RequestMapping( value = "/test5")
//    public String test5(@RequestParam("aaa") String aac, @RequestParam("bbb") String bbb)
//    {
//        return aac+bbb;
//    }
//
////    通过ModelAttribute方式来注入参数的
//    @RequestMapping( value = "/kkk")
//    public String kkk()
//    {
//        return "test2";
//    }
//
//    @RequestMapping( value = "/test6")
//    public String test6( @ModelAttribute("kkk") User user )
//    {
//        return "test3";
//    }


}
