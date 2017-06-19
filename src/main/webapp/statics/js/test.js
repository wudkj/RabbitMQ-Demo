function addcookie(name, value, expireHours) {
    var cookieString = name + "=" + escape(value) + "; path=/";
    //判断是否设置过期时间
    if (expireHours > 0) {
        var date = new Date();
        date.setTime(date.getTime + expireHours * 3600 * 1000);
        cookieString = cookieString + "; expire=" + date.toGMTString();
    }
    document.cookie = cookieString;
}

function getcookie(name) {
    var strcookie = document.cookie;
    var arrcookie = strcookie.split("; ");
    for (var i = 0; i < arrcookie.length; i++) {
        var arr = arrcookie[i].split("=");
        if (arr[0] == name)return decodeURIComponent(arr[1]); //增加对特殊字符的解析
    }
    return "";
}

function delCookie(name) {//删除cookie
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval = getcookie(name);
    if (cval != null) document.cookie = name + "=" + cval + "; path=/;expires=" + exp.toGMTString();
}

function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}

function getOpenId() {
    $(function () {
        var fromurl = location.href;
        var access_code = GetQueryString('code');
        if (access_code == "" || access_code == null) {
            var url = 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxbf46ad8a4193878a&redirect_uri=' + encodeURIComponent(fromurl) + '&response_type=code&scope=snsapi_base&state=STATE%23wechat_redirect&connect_redirect=1#wechat_redirect';
            location.href = url;
        } else {
            $.get("/getOpenID?access_code=" + access_code, function (data, status) {
                var obj = eval('(' + data + ')');
                alert("openId = " + obj.openid);
            });
        }
    })
}
function is_weixin() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == "micromessenger") {
        return true;
    } else {
        return false;
    }
}
var isWeixin = is_weixin();
function is_alipay() {
    var ua = navigator.userAgent.toLowerCase();
    if (ua.match(/Alipay/i) == "alipay") {
        return true;
    } else {
        return false;
    }
}
var isAlipay = is_alipay();
var result = "普通浏览器";
if (isAlipay) {
    result = "支付宝浏览器";
} else if (isWeixin) {
    result = "微信浏览器2";
    getOpenId();
}