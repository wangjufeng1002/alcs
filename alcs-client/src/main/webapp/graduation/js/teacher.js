/**
 * Created by wangyuqing6 on 2018/3/27.
 */
$(function () {

    var contestListData;    //竞赛列表数据
    var raterWorksListData; //作品列表
    var raterInfo;          //评委信息
    var globalCid = null;
    var globalWid = null;
    var teamId = null;
    getRaterInfo();


    init();


    // 初始化页面
    function init() {
        initTable("myCompetitionDoing", null);
        bindEvent();
        $('.menu-list-item').trigger('click');
    }

    //事件绑定
    function bindEvent() {
        //导航栏一级菜单切换
        $('.menu-list-item').on('click', function () {
            if ($(this).children('.menu-list-second').length == 0) {
                $('.menu-list-second-item').removeClass('curr');
                $(this).children('.menu-list-first').addClass('curr').parents('.menu-list-item').siblings('.menu-list-item').children('.menu-list-first').removeClass('curr');
            } else {
                // $(this).siblings('.menu-list-item').children('.menu-list-first').removeClass('curr');
                $(this).children('.menu-list-second').is(':hidden') ? $(this).find('.arrow-down').addClass('arrow-up') : $(this).find('.arrow-down').removeClass('arrow-up');
                $(this).children('.menu-list-second').is(':hidden') ? $(this).children('.menu-list-second').show() : $(this).children('.menu-list-second').hide();
            }
        });
        //导航栏二级菜单切换
        $('.menu-list-second-item').on('click', function (e) {
            $('.menu-list-second-item').removeClass('curr');
            $('.menu-list-item .menu-list-first').removeClass('curr');
            $(this).addClass('curr');
            e.stopPropagation();
        });
        //点击分配给我的竞赛--正在进行
        $('.menu-list-first.my-competition').siblings('.menu-list-second').children('.my-competition-doing').on('click', function () {
            initTable("myCompetitionDoing", null);
        });
        //点击分配给我的竞赛--已完成
        $('.menu-list-first.my-competition').siblings('.menu-list-second').children('.my-competition-done').on('click', function () {
            initTable("myCompetitionDone", null);
        });
        //点击分配给我的报告--已批阅
        $('.menu-list-first.my-report').siblings('.menu-list-second').children('.my-report-done').on('click', function () {
            initTable("myReportDone", null);
        });
        //点击分配给我的报告--待批阅
        $('.menu-list-first.my-report').siblings('.menu-list-second').children('.my-report-will').on('click', function () {
            initTable("myReportWill", null);
        });
        //点击个人中心--个人信息
        $('.menu-list-first.my-personal').siblings('.menu-list-second').children('.my-personal-situation').on('click', function () {
            initTable("myPersonalSituation", raterInfo);
        });
        //点击个人中心--密码修改
        $('.menu-list-first.my-personal').siblings('.menu-list-second').children('.my-personal-password').on('click', function () {
            initTable("myPersonalPassword", null);
        });
        // 点击查看详情弹窗
        $('body').on('click', '.look-details', function () {
            var cid = $(this).children('input').val();
            detailShow(cid);

        });
        // 点击关闭按钮
        $('body').on('click', '.close-icon', function () {
            $(this).parents('.fade').remove();
        });
        // 点击关闭按钮
        $('body').on('click', '.pop-window-footer-btn.cancel-btn', function () {
            $(this).parents('.fade').remove();
        });
        //点击已批阅查看报告弹出弹窗
        $('body').on('click', '.look-report', function () {
            var cid = $(this).children('input:first-child').val();
            globalCid = cid;
            var wId = $(this).children('input:last-child').val();
            globalWid = wId;
            reportShow(2, globalWid);
        });
        //点击待批阅查看报告弹出弹窗
        $('body').on('click', '.submit-report', function () {
            var cid = $(this).children('input:first-child').val();
            globalCid = cid;
            var wId = $(this).children('input:last-child').val();
            globalWid = wId;
            reportShow(1, globalWid);
        });
        //确认修改密码
        $('body').on('click', '.amend-password-button', function () {
            //判断两次密码输入是否一致
            if ($('.amend-password').val() == $('.amend-password-second').val()) {
                //请求
                $.ajax({
                    data: {type: 2, password: $('.amend-password').val()},
                    url: '',
                    success: function (res) {
                        if(res.code == '0000'){
                            toastAdd(1, '修改成功');
                            setTimeout(function () {
                                window.location.href = "/graduation/html/login.html";
                            }, 1);
                        }else{
                            toastAdd(1, '网络失败请重试');
                        }

                    },
                    error: function () {
                        toastAdd(1, '网络失败请重试');
                    }
                })
            } else {
                toastAdd(1, '两次密码输入不一致，请重新输入');
            }
        });
        $('body').on('click', '.look-report-window .submit-btn', function () {
            toastAdd(2, '');
        });
        $('body').on('click', '.sure-window .submit-btn', function () {
            //提交审批
            approval();
        });

        $('body').on('click', '.right-container .right-head-personal-exit', function () {
            $.ajax({
                url:"/client/outLogin",
                type:"post",
                success:function (res) {
                    if(res.code == "0000"){
                        window.location.href="/graduation/html/login.html";
                    }
                    else{
                        toastAdd(1, '网络失败请重试');
                    }
                },
                error: function () {
                    toastAdd(1, '网络失败请重试');
                }
            });
        });
    }

    // 初始化所有菜单项数据
    function initTable(type, resultData) {
        $('.right-content').empty();
        if (type == "myCompetitionDoing") {
            var rows;
            if (resultData == null) {
                getRateContestList(1, 10, 1);
                resultData = contestListData;
                rows = contestListData.rows;
            } else {
                rows = resultData.rows;
            }
            var headData = {
                "cid": "赛事编码",
                "title": '比赛名称',
                "summary": "简介",
                "startEndTime": '竞赛时间',
                "enrollTime": '报名时间',
                "scoreTime": "评分时间",
                "workEndTime": "作品提交截止时间",
                "operation": '操作'
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.cid + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.startEndTime + '</td>' +
                '<td>' + headData.enrollTime + '</td>' +
                '<td>' + headData.workEndTime + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + rows[i].cid + '</td>' +
                    '<td>' + rows[i].title + '</td>' +
                    '<td>' + rows[i].startEndTime + '</td>' +
                    '<td>' + rows[i].enrollTime + '</td>' +
                    '<td>' + rows[i].workEndTime + '</td>' +
                    '<td><span class="look-details">' +
                    '查看竞赛详情' + '<input type="hidden" value="' + rows[i].cid + '"/>' +
                    '</span>' + '</tr>';

            }
            var table = '<table class="table" id="table">' + tableHead + tableBody + '</table>';
            $('.right-content').append(table);
            //初始化分页
            pageAdd();
            new pageInit({
                totalCount: resultData.total,//总页数
                currIndex: resultData.page - 1,//当前页，0为第一页
                pageSize: $('.pageSelect option:selected').val(),//每页数据量
                initCurrIndex: 0,//页面一进来的时候展示的页码，默认展示第一页,0为第一页
                nextCallback: function () {
                    //点击下一页的回调函数
                },
                lastCallback: function () {
                    //点击上一页的回调函数
                },
                selectCallback: function () {
                    //选择下拉框中页数的回调函数
                },
                pageCallback: function () {
                    debugger
                    getRateContestList(currIndex + 1, this.pageSize, 1);
                    initTable("myCompetitionDoing", contestListData);
                    //点击页码的回调函数和跳至几页的回调函数
                },
            })
        }
        else if (type == "myCompetitionDone") {
            var rows;
            if (resultData == null) {
                getRateContestList(1, 10, 2);
                resultData = contestListData;
                rows = contestListData.rows;
            } else {
                rows = resultData.rows;
            }
            var headData = {
                "cid": "赛事编码",
                "title": '比赛名称',
                "summary": "简介",
                "startEndTime": '竞赛时间',
                "enrollTime": '报名时间',
                "scoreTime": "评分时间",
                "workEndTime": "作品提交截止时间",
                "operation": '操作'
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.cid + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.startEndTime + '</td>' +
                '<td>' + headData.enrollTime + '</td>' +
                '<td>' + headData.workEndTime + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + rows[i].cid + '</td>' +
                    '<td>' + rows[i].title + '</td>' +
                    '<td>' + rows[i].startEndTime + '</td>' +
                    '<td>' + rows[i].enrollTime + '</td>' +
                    '<td>' + rows[i].workEndTime + '</td>' +
                    '<td><span class="look-details">' +
                    '查看竞赛详情' + '<input type="hidden" value="' + rows[i].cid + '"/>' +
                    '</span>' + '</tr>';

            }
            var table = '<table class="table" id="table">' + tableHead + tableBody + '</table>';
            $('.right-content').append(table);
            //初始化分页
            pageAdd();
            new pageInit({
                totalCount: resultData.total,//总页数
                currIndex: resultData.page - 1,//当前页，0为第一页
                pageSize: $('.pageSelect option:selected').val(),//每页数据量
                initCurrIndex: 0,//页面一进来的时候展示的页码，默认展示第一页,0为第一页
                nextCallback: function () {
                    //点击下一页的回调函数
                },
                lastCallback: function () {
                    //点击上一页的回调函数
                },
                selectCallback: function () {
                    //选择下拉框中页数的回调函数
                },
                pageCallback: function () {
                    debugger
                    getRateContestList(currIndex + 1, this.pageSize, 2);
                    initTable("myCompetitionDoing", contestListData);
                    //点击页码的回调函数和跳至几页的回调函数
                },
            })
        }
        else if (type == "myReportDone") {
            var rows;
            if (resultData == null) {
                getRaterWorkList(1, 10, 1);
                resultData = raterWorksListData;
                rows = raterWorksListData.rows;
            } else {
                rows = resultData.rows;
            }
            var headData = {
                "wId": "作品编码",
                "title": '比赛题目',
                "scoreTime": '打分时间段',
                "workEndTime": '作品提交时间',
                "image": '图片链接',
                "thesis": "作品链接",
                "operation": '操作',
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.wId + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.scoreTime + '</td>' +
                '<td>' + headData.image + '</td>' +
                '<td>' + headData.thesis + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + rows[i].wId + '</td>' +
                    '<td>' + rows[i].title + '</td>' +
                    '<td>' + rows[i].scoreTime + '</td>' +
                    '<td>' + rows[i].image + '</td>' +
                    '<td>' + rows[i].thesis + '</td>' +
                    '<td>' +
                    '<span class="look-details">查看竞赛详情' +
                    '<input type="hidden" value="' + rows[i].cId + '"/>' +
                    '</span>' +
                    '<span class="look-report">查看报告' +
                    '<input type="hidden" value="' + rows[i].cId + '"/>' +
                    '<input type="hidden" value="' + rows[i].wId + '"/>' +
                    '</span>' +
                    '</td>' +
                    '</tr>';
            }
            var table = '<table class="table" id="table">' + tableHead + tableBody + '</table>';
            $('.right-content').append(table);
            //初始化分页
            pageAdd();
            new pageInit({
                totalCount: resultData.total,//总页数
                currIndex: resultData.page - 1,//当前页，0为第一页
                pageSize: $('.pageSelect option:selected').val(),//每页数据量
                initCurrIndex: 0,//页面一进来的时候展示的页码，默认展示第一页,0为第一页
                nextCallback: function () {
                    //点击下一页的回调函数
                },
                lastCallback: function () {
                    //点击上一页的回调函数
                },
                selectCallback: function () {
                    //选择下拉框中页数的回调函数
                },
                pageCallback: function () {
                    //点击页码的回调函数和跳至几页的回调函数
                    debugger
                    getRaterWorkList(currIndex + 1, this.pageSize, 1);
                    initTable("myReportDone", raterWorksListData);
                },
            })
        }
        else if (type == "myReportWill") {
            var rows;
            if (resultData == null) {
                getRaterWorkList(1, 10, 0);
                resultData = raterWorksListData;
                rows = raterWorksListData.rows;
            } else {
                rows = resultData.rows;
            }
            var headData = {
                "wId": "作品编码",
                "title": '比赛题目',
                "scoreTime": '打分时间段',
                "workEndTime": '作品提交时间',
                "image": '图片链接',
                "thesis": "作品链接",
                "operation": '操作',
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.wId + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.scoreTime + '</td>' +
                '<td>' + headData.image + '</td>' +
                '<td>' + headData.thesis + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + rows[i].wId + '</td>' +
                    '<td>' + rows[i].title + '</td>' +
                    '<td>' + rows[i].scoreTime + '</td>' +
                    '<td>' + rows[i].image + '</td>' +
                    '<td>' + rows[i].thesis + '</td>' +
                    '<td>' +
                    '<span class="look-details">查看竞赛详情' +
                    '<input type="hidden" value="' + rows[i].cId + '"/>' +
                    '</span>' +
                    '<span class="submit-report">审批报告' +
                    '<input type="hidden" value="' + rows[i].cId + '"/>' +
                    '<input type="hidden" value="' + rows[i].wId + '"/>' +
                    '</span>' +
                    '</td>' +
                    '</tr>';
            }
            var table = '<table class="table" id="table">' + tableHead + tableBody + '</table>';
            $('.right-content').append(table);
            //初始化分页
            pageAdd();
            new pageInit({
                totalCount: resultData.total,//总页数
                currIndex: resultData.page - 1,//当前页，0为第一页
                pageSize: $('.pageSelect option:selected').val(),//每页数据量
                initCurrIndex: 0,//页面一进来的时候展示的页码，默认展示第一页,0为第一页
                nextCallback: function () {
                    //点击下一页的回调函数
                },
                lastCallback: function () {
                    //点击上一页的回调函数
                },
                selectCallback: function () {
                    //选择下拉框中页数的回调函数
                },
                pageCallback: function () {
                    //点击页码的回调函数和跳至几页的回调函数
                    debugger
                    getRaterWorkList(currIndex + 1, this.pageSize, 0);
                    initTable("myReportWill", raterWorksListData);
                },
            })
        }
        else if (type == "myPersonalSituation") {
            if(resultData == null){
                getRaterInfo();
                resultData = raterInfo;
            }
            var myPersonalSituation =
                '<ul class="personal-con">' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">学校</div>' +
                '<div class="personal-item-content">西安邮电大学</div>' +
                '</li>' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">账号</div>' +
                '<div class="personal-item-content">' +resultData.ratAccount +
                '</div>' +
                '</li>' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">姓名</div>' +
                '<div class="personal-item-content">' +resultData.ratName +
                '</div>' +
                '</li>' +
                '</ul>';
            $('.right-content').append(myPersonalSituation);
        }
        else if (type == "myPersonalPassword") {
            var myPersonalPassword =
                '<input type="password" class="amend-password" placeholder="请输入新密码">' +
                '<input type="password" class="amend-password-second" placeholder="请再次输入新密码">' +
                '<div class="amend-password-button">确认修改</div>';
            $('.right-content').append(myPersonalPassword);
            $('.amend-password').val('');
        }
    }

    // 拼接分页
    function pageAdd() {
        var pageFooter =
            '<div class="pageFooter">' +
            '<div class="pageFooterCon">' +
            '<span class="totalCount">共<span id="pageCount"></span>条</span>' +
            '<span class="lastPage">&lt;</span>' +
            '<ul class="pageItemCon"></ul>' +
            '<span class="nextPage">&gt;</span>' +
            '<select name="" class="pageSelect">' +
            '<option value="10">10条/页</option>' +
            '<option value="20">20条/页</option>' +
            '<option value="30">30条/页</option>' +
            '</select>' +
            '<span class="skipPage">跳至<input type="text" class="pageInput">页</span>' +
            '</div>' +
            '</div>';
        $('.right-content').append(pageFooter);
    }

    // 详情弹窗
    function detailShow(cid) {
        $('.fade').remove();
        var data;
        $.ajax({
            url: '/client/contest/getByid',
            data: {'cid': cid},
            async: false,
            type: 'post',
            success: function (result) {
                if (result.code == "0000") {
                    data = result.data;
                    globalWid = data.wId;
                    globalCid = data.cId;
                    teamId = data.teamId;
                } else {
                    alert(result.msg);
                }
            },

        });
        var detailWindow =
            '<div class="fade competition-detail-window">' +
            '<div class="pop-window">' +
            '<div class="pop-window-head">' +
            '<div class="pop-window-wrapper">' +
            '<span class="pop-window-head-title">竞赛详情</span>' +
            '<span class="close-icon"></span>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-body">' +
            '<div class="pop-window-wrapper">' +
            '<div class="pop-window-item">' +
            '<div class="pop-window-item-title">' +
            '<i class="blue-circle"></i>' +
            '<span class="pop-window-body-name">题目</span>' +
            '</div>' +
            '<div class="pop-window-item-content">' +
            '<p class="pop-window-body-detail">' + data.title + '</p>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-item">' +
            '<div class="pop-window-item-title">' +
            '<i class="blue-circle"></i>' +
            '<span class="del-window-body-name">竞赛要求</span>' +
            '</div>' +
            '<div class="pop-window-item-content">' +
            '<p class="pop-window-body-detail">' + data.summary + '</p>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-item">' +
            '<div class="pop-window-item-title">' +
            '<i class="blue-circle"></i>' +
            '<span class="pop-window-body-name">竞赛起止时间</span>' +
            '</div>' +
            '<div class="pop-window-item-content">' +
            '<p class="pop-window-body-detail">' + data.startEndTime + '</p>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-item">' +
            '<div class="pop-window-item-title">' +
            '<i class="blue-circle"></i>' +
            '<span class="pop-window-body-name">报名起止时间</span>' +
            '</div>' +
            '<div class="pop-window-item-content">' +
            '<p class="pop-window-body-detail">' + data.enrollTime + '</p>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-item">' +
            '<div class="pop-window-item-title">' +
            '<i class="blue-circle"></i>' +
            '<span class="pop-window-body-name">评分起止时间</span>' +
            '</div>' +
            '<div class="pop-window-item-content">' +
            '<p class="pop-window-body-detail">' + data.scoreTime + '</p>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-item">' +
            '<div class="pop-window-item-title">' +
            '<i class="blue-circle"></i>' +
            '<span class="pop-window-body-name">作品提交结束时间</span>' +
            '</div>' +
            '<div class="pop-window-item-content">' +
            '<p class="pop-window-body-detail">' + data.workEndTime + '</p>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '</div>' +
            '<div class="pop-window-footer">' +
            '<span class="pop-window-footer-btn cancel-btn">关闭</span>' +
            '</div>' +
            '</div>' +
            '</div>';
        $('body').append(detailWindow);
    }

    // toast提示
    function toastAdd(type, text) {

        if (type == 1) {
            $('.sure-window').remove();
            var delay = {
                showIn: 1200, // 显示时间
                fadeOut: 500 // 渐隐动画时间
            };
            var $toast = '<div class="toast-tip">' + text + '</div>';
            $('body').append($toast);
            setTimeout(function () {
                $('.toast-tip').remove();
            }, delay.showIn);
        }
        else if (type == 2) {
            var toast =
                '<div class="fade sure-window">' +
                '<div class="pop-window">' +
                '<div class="pop-window-item">' +
                '<div class="sure-title">提交后不可修改，确定提交吗？</div>' +
                '</div>' +
                '<div class="pop-window-footer">' +
                '<span class="pop-window-footer-btn submit-btn">确定</span>' +
                '<span class="pop-window-footer-btn cancel-btn">取消</span>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        $('body').append(toast);
    }

    // 报告功能【待批阅报告1，已批阅报告2】
    function reportShow(type, wid) {
        var data;
        $.ajax({
            url: "/client/rater/workDetail",
            data: {"wid": wid},
            type: "post",
            async: false,
            success: function (result) {
                data = result.data;
                globalCid = data.cId;
                globalWid = data.wId;
                teamId = data.teamId;
            },
            error: function (result) {

            }
        });
        if (type == 1) {
            var report =
                '<div class="fade look-report-window">' +
                '<div class="pop-window">' +
                '<div class="pop-window-head">' +
                '<div class="pop-window-wrapper">' +
                '<span class="pop-window-head-title">报告详情</span>' +
                '<span class="close-icon"></span>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-body">' +
                '<div class="pop-window-wrapper">' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告内容</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<div id="content">' + data.code + '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告图片</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<div class="picture-upLoad">' +
                '<img class="picture" src="' + data.image + '"/>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告附件</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<a href="' + data.thesis + '" class="file-name">' + '文件链接' + '</a>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">评分</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<input type="number"  class="grade" id="score"/>' +
                '</div>' +
                '</div>' +

                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">评语</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<textarea id="approvalContent" rows="3" cols="50"">' + '</textarea>' +
                '</div>' +
                '</div>' +

                '</div>' +
                '</div>' +
                '<div class="pop-window-footer">' +
                '<span class="pop-window-footer-btn submit-btn">提交</span>' +
                '<span class="pop-window-footer-btn cancel-btn">取消</span>' +
                '</div>' +
                '</div>' +
                '</div>';
            $('body').append(report);
        }
        else if (type == 2) {
            var report =
                '<div class="fade look-report-window">' +
                '<div class="pop-window">' +
                '<div class="pop-window-head">' +
                '<div class="pop-window-wrapper">' +
                '<span class="pop-window-head-title">我的报告</span>' +
                '<span class="close-icon"></span>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-body">' +
                '<div class="pop-window-wrapper">' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告内容</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<div id="content">' + data.code + '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告图片</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<div class="picture-upLoad">' +
                '<img class="picture" src="' + data.image + '"/>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告附件</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<a href="' + data.thesis + '" class="file-name">' + '文件链接' + '</a>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">评分</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<input type="text" disabled class="grade" value=' + data.score + '>' +
                '</div>' +
                '</div>' +

                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">评语</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<textarea rows="3" disabled cols="50"">' + data.content + '</textarea>' +
                '</div>' +
                '</div>' +

                '</div>' +
                '</div>' +
                '<div class="pop-window-footer">' +
                '<span class="pop-window-footer-btn cancel-btn">关闭</span>' +
                '</div>' +
                '</div>' +
                '</div>';


            $('body').append(report);
        }
    }

    //获取评委的基本信息
    function getRaterInfo() {
        debugger
        $.ajax({
            url: "/client/user/getRaterInfo",
            type: "post",
            dataType: "json",
            async: false,
            success: function (result) {
                if (result.code == "0000") {
                    debugger
                    raterInfo = result.data;
                    $(".menu-user-name").html(raterInfo.ratName);
                    $(".menu-user-identity").html(raterInfo.ratAccount);
                    $(".right-head-personal-name").html(raterInfo.ratName + " - " + raterInfo.ratAccount);
                } else {
                    window.location.href = "http://127.0.0.1/graduation/html/login.html";
                }

            },
            error: function () {

            }

        });
    }

    //获取比赛列表
    function getRateContestList(page, rows, status) {
        var queryJson = {"contestStatus": status};
        var queryParam = JSON.stringify(queryJson);
        $.ajax({
            url: "/client/contest/rater/list",
            data: {"page": page, "rows": rows, "queryParam": queryParam},
            type: "post",
            dataType: "json",
            async: false,
            success: function (result) {
                contestListData = result;
            },
            error: function () {

            }

        });
    }

    //获取分配给评委的比赛列表
    function getRaterWorkList(page, row, status) {
        var queryJson = {"status": status};         //是否打分
        var queryParam = JSON.stringify(queryJson);
        $.ajax({
            url: "/client/rater/works",
            data: {"page": page, "rows": row, "queryParam": queryParam},
            type: "post",
            dataType: "json",
            async: false,
            success: function (result) {
                raterWorksListData = result;
            },
            error: function () {

            }

        });
    }

    //评分
    function approval() {
        debugger
        var content = $('#approvalContent').val();
        var score = $('#score').val();
        if (score > 100 || score < 0) {
            toastAdd(1, "分数不符合要求");
        }
        var dataJson = {wid: globalWid, cid: globalCid, teamId: teamId, score: score, content: content};
        var dataString = JSON.stringify(dataJson);
        $.ajax({
            url: "/client/rater/approval",
            data: {approvalPram: dataString},
            type: "post",
            async: false,
            success: function (result) {
                if (result.code == '0000') {
                    $('.sure-window').remove();
                    $('.fade').remove();
                    initTable("myReportWill", null);
                    toastAdd(1, "批阅成功");
                } else {
                    toastAdd(1, "提交失败请重试");
                }
            },
            error: function (result) {
                toastAdd(1, "网络异常");
            }

        });
    }
});
