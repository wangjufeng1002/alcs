$(function () {
    debugger
        $.ajaxSetup({
//设置ajax请求结束后的执行动作
            complete:
                function (XMLHttpRequest, textStatus) {
                debugger
                    // 通过XMLHttpRequest取得响应头，sessionstatus
                    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                    if (sessionstatus == "TIMEOUT") {
                        var win = window;
                        while (win != win.top) {
                            win = win.top;
                        }
                        win.location.href = "http://127.0.0.1/graduation/html/login.html";
                    }
                }
        });
    }
);
