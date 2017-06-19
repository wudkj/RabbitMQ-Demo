package com.lin.wxPay;

/**
 * Created by Administrator on 2017/6/19 0019.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 核心请求处理类
 *
 * @author 简爱微萌
 * @Email zyw205@gmail.com
 */
@Controller
public class ValidateServlet {

    private static final long serialVersionUID = -5021188348833856475L;

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public void send(HttpServletRequest request, HttpServletResponse response) {
        check(request, response);
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    @ResponseBody
    public void send2(HttpServletRequest request, HttpServletResponse response) {
        check(request, response);
    }

    private void check(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
        String signature = request.getParameter("signature");
        // 时间戳
        String timestamp = request.getParameter("timestamp");
        // 随机数
        String nonce = request.getParameter("nonce");
        // 随机字符串
        String echostr = request.getParameter("echostr");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                out.print(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            out.close();
            out = null;
        }
    }

}