package com.lin.wxPay;

import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sound.sampled.AudioFormat;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Administrator on 2017/6/19 0019.
 */
@Controller
public class BizController {
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView send(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("test.html");
    }

    @RequestMapping(value = "/getOpenID", method = RequestMethod.GET)
    @ResponseBody
    public String getOpenID(@RequestParam(value = "access_code", defaultValue = "") String access_code) {
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxbf46ad8a4193878a&secret=99311d2820ea40b67bf25bce1286581b&code=" + access_code + "&grant_type=authorization_code";
        String result = GetJson(url);
        return result;
    }

    protected String GetJson(String url) {
        try {
            URI uri = new URI(url);
            SimpleClientHttpRequestFactory schr = new SimpleClientHttpRequestFactory();
            ClientHttpRequest chr = schr.createRequest(uri, HttpMethod.GET);
            //chr.getBody().write(param.getBytes());//body中设置请求参数
            ClientHttpResponse res = chr.execute();
            InputStream is = res.getBody(); //获得返回数据,注意这里是个流
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            while ((str = br.readLine()) != null) {
                return str;
            }

        } catch (URISyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
