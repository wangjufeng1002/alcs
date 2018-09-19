$(function () {
        debugger
        $.ajaxSetup({
             //设置ajax请求结束后的执行动作
            complete:
                function (XMLHttpRequest, textStatus) {
                    // 通过XMLHttpRequest取得响应头，sessionstatus
                    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                    if (sessionstatus == "TIMEOUT") {
                        var win = window;
                        while (win != win.top) {
                            win = win.top;
                        }
                        var host = window.location.host;
                        alert(host);
                        win.location.href = "http://" + host + "/graduation/html/login.html";
                    }
                }
        });
    }
);
