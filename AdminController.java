package org.example.blog.controller;

import org.example.blog.mapper.AdminMapper;
import org.example.blog.pojo.Admin;
import org.example.blog.pojo.query.AdminQuery;
import org.example.blog.service.IAdminService;
import org.example.blog.util.PageResult;
import org.example.blog.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    //注入对象使用
    //@Autowired
    //private AdminMapper adminMapper;
    @Autowired
    private IAdminService adminService;

    //  /admin/list?page=1&limit=10&name=...&email=..   搜索条件
    @RequestMapping("/list")
    @ResponseBody    //以json形式返回数据
    public PageResult list(AdminQuery adminQuery){

        System.out.println("AdminController.list");
        PageResult pageResult=adminService.list(adminQuery);
        return pageResult;
    }

    //转发页面，返回String，不需要加@Responsebody
    @RequestMapping("/toAdminList")
    public String toAdminList(){
        return "admin_list";
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public Result deleteById(Integer id){
        adminService.deleteById(id);
        return Result.ok("删除成功");
    }

    @RequestMapping("/deleteAll")
    @ResponseBody
    public Result deleteAll(Integer[] ids){
        adminService.deleteAll(ids);
        return Result.ok("删除成功");
    }

    @RequestMapping("/toAdminAdd")
    public String toAdminAdd()
    {
        return "admin_add";
    }

    @RequestMapping("/add")
    @ResponseBody
    public Result add(Admin admin){
        adminService.add(admin);
        return Result.ok("添加成功");

    }

    @RequestMapping("/toAdminUpdate")
    public String toAdminUpdate()
    {
        return "admin_update";
    }

    @RequestMapping("/selectById")
    @ResponseBody
    public Result selectById(Integer id)
    {
        Admin admin=adminService.selectById(id);
        return Result.ok(admin);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Result update(Admin admin)
    {
        adminService.update(admin);
        return Result.ok("修改成功");
    }

    @RequestMapping("/toLogin")
    public String toLogin()
    {
        return "login";
    }

    @RequestMapping("/login")
    @ResponseBody
    public Result login(String name, String password, String code,HttpSession session){
        //1.先比较验证码是不是正确
        //2.如果验证码错误，直接返回验证码错误
        //3.如果验证码正确，再比较用户名和密码
        String codeInSession= (String) session.getAttribute("codeInSession");
        if(!codeInSession.equalsIgnoreCase(code)){
            return Result.error("验证码错误");
        }
        Admin admin=adminService.login(name,password);
        if(admin==null){
            return Result.error("用户名或密码错误");
        }

        //用户登录成功，把凭证放到session
        session.setAttribute("admin",admin);
        return Result.ok("登陆成功");

    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //移除凭证
        session.removeAttribute("admin");
        //跳转到登录界面
        return "redirect:/admin/toLogin";
    }

}
