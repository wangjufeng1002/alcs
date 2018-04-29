$(function () {
    //定义变量
    var contestListData; //江赛列表数据
    var myContestListData;//我的竞赛数据
    var myContestWorkData;//我的竞赛作品列表
    var imageName = "";
    var fileName ="";
    var submitType = ""; //2 确定报名，3，提交报告，4，保存报告
    var globalCid ="";
    var editor;

    getStudentInfo();

    init();

    // 初始化页面
    function init() {
        initTable("allCompetition", null);
        bindEvent();
    }

    //初始化编辑器
    function initEditor() {
        var E = window.wangEditor;
        editor = new E('#editor');
        // 或者 var editor = new E( document.getElementById('editor') )
        editor.create();
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
        //点击所有竞赛
        $('.menu-list-first.all-competition').on('click', function () {
            initTable("allCompetition", null);
        });
        //点击我的竞赛--正在进行
        $('.menu-list-first.my-competition').siblings('.menu-list-second').children('.my-competition-doing').on('click', function () {
            initTable("myCompetitionDoing", null);
        });
        //点击我的竞赛--正在进行
        $('.menu-list-first.my-competition').siblings('.menu-list-second').children('.my-competition-done').on('click', function () {
            initTable("myCompetitionDone", null);
        });
        //点击我的报告--已提交
        $('.menu-list-first.my-report').siblings('.menu-list-second').children('.my-report-done').on('click', function () {
            initTable("myReportDone", null);
        });
        //点击我的报告--待提交
        $('.menu-list-first.my-report').siblings('.menu-list-second').children('.my-report-will').on('click', function () {
            initTable("myReportWill", null);
        });
        //点击我的获奖
        $('.menu-list-first.my-award').on('click', function () {
            initTable("myAward", null);
        });
        //点击个人中心--个人信息
        $('.menu-list-first.my-personal').siblings('.menu-list-second').children('.my-personal-situation').on('click', function () {
            initTable("myPersonalSituation", null);
        });
        //点击个人中心--密码修改
        $('.menu-list-first.my-personal').siblings('.menu-list-second').children('.my-personal-password').on('click', function () {
            initTable("myPersonalPassword", null);
        });
        // 点击查看详情弹窗
        $('body').on('click', '.look-details', function () {
            debugger
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
        //点击【我要报名】弹出二次确认窗
        $('body').on('click', '.apply-competition', function () {
            var cid = $(this).children('input').val();
            globalCid = cid;
            toastAdd(2, '');
        });
        //二次确认弹窗点击确认
        $('body').on('click', '.sure-window .submit-btn', function () {
            debugger
            makeSure(submitType);
        });
        //点击上传图片触发input=file事件
        $('body').on('click', '.pop-window .upload-name', function (e) {
            e.preventDefault();
            $(this).siblings('.img-file-input').click();
        });
        //点击上传后的图片触发input=file事件
        $('body').on('click', '.pop-window .picture', function (e) {
            e.preventDefault();
            $(this).siblings('.img-file-input').click();
        });
        //图片按钮点击上传【原生】
        $('body').on('change', '.img-file-input', function () {
            //判断浏览器是否支持FileReader接口
            if (typeof FileReader == 'undefined') {
                alert('当前浏览器不支持FileReader接口,请更换浏览器');
                return;
            }
            uploadFile(this, 'img');
            $('.img-file-input').attr("type", "txt");
        });
        //点击上传文件触发file-input时事件
        $('body').on('click', '.pop-window .file-upload', function (e) {
            e.preventDefault();
            $(this).siblings('.file-input').click();
        });
        //文件按钮点击上传【原生】
        $('body').on('change', '.file-input', function () {
            //判断浏览器是否支持FileReader接口
            if (typeof FileReader == 'undefined') {
                alert('当前浏览器不支持FileReader接口,请更换浏览器');
                return;
            }
            uploadFile(this, 'file');
        });
        //点击提交报告弹出弹窗
        $('body').on('click', '.submit-report', function () {
            debugger
            var cid = $(this).children('input').val();
            globalCid = cid;
            reportShow(1);
            initEditor();
        });
        //点击查看报告弹出弹窗
        $('body').on('click', '.look-report', function () {
            debugger
            var cid = $(this).children('input').val();
            globalCid = cid;
            reportShow(2);
            initEditor();
        });
        //确认修改密码
        $('body').on('click', '.amend-password-button', function () {
            //判断两次密码输入是否一致
            if ($('.amend-password').val() == $('.amend-password-second').val()) {
                //请求
                $.ajax({
                    data: {},
                    url: '',
                    success: function () {
                        toastAdd(1, '修改成功');
                    },
                    error: function () {
                        toastAdd(1, '网络失败请重试');
                    }

                })
            } else {
                toastAdd(1, '两次密码输入不一致，请重新输入');
            }
        })

        $('body').on('click','.look-report-window .submit-btn',function () {
            toastAdd(3, '');
        })

    }

    // 初始化所有菜单项数据
    function initTable(type, resultData) {
        $('.right-content').empty();
        if (type == "allCompetition") {
            debugger
            var rows;
            if (resultData == null) {
                getContestList(1, 10);
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
                    '</span>';
                debugger
                if (rows[i].nEnroll == 1) {
                    tableBody +=
                        '<span class="apply-competition">点我报名' +
                        '<input type="hidden" value="' + rows[i].cid + '">' +
                        '</span></td>' +
                        '</tr>';
                }
                else if (rows[i].nEnroll == 2) {
                    tableBody +=
                        '<span>比赛已结束</span></td>' +
                        '</tr>';
                }
                else if (rows[i].nEnroll == 3) {
                    tableBody +=
                        '<span>比赛未开始</span></td>' +
                        '</tr>';
                }
                else if (rows[i].nEnroll == 4) {
                    tableBody +=
                        '<span>报名未开始</span></td>' +
                        '</tr>';
                }
                else if (rows[i].nEnroll == 5) {
                    tableBody +=
                        '<span>报名已结束</span></td>' +
                        '</tr>';
                }
                else if (rows[i].nEnroll == 6) {
                    tableBody +=
                        '<span>比赛进行中</span></td>' +
                        '</tr>';
                } else {
                    tableBody +=
                        '<span>比赛进行中</span></td>' +
                        '</tr>';
                }

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
                    getContestList(currIndex + 1, this.pageSize);
                    initTable("allCompetition", contestListData);

                    //点击页码的回调函数和跳至几页的回调函数
                },
            })
        }
        else if (type == "myCompetitionDoing") {
            var rows;
            if (resultData == null) {
                getMyContestList(1, 10, 1);
                resultData = myContestListData;
                rows = myContestListData.rows;
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
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.cid + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.startEndTime + '</td>' +
                '<td>' + headData.enrollTime + '</td>' +
                '<td>' + headData.workEndTime + '</td>' +
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
                    '</span>';

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
                    getMyContestList(currIndex + 1, this.pageSize, 1);
                    initTable("myCompetitionDoing", myContestListData);
                },
            })
        }
        else if (type == "myCompetitionDone") {
            debugger
            var rows;
            if (resultData == null) {
                getMyContestList(1, 10, 2);
                resultData = myContestListData;
                rows = myContestListData.rows;
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
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.cid + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.startEndTime + '</td>' +
                '<td>' + headData.enrollTime + '</td>' +
                '<td>' + headData.workEndTime + '</td>' +
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
                    '</span>';

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
                    getMyContestList(currIndex + 1, this.pageSize, 2);
                    initTable("myCompetitionDoing", myContestListData);
                },
            })
        }
        else if (type == "myReportDone") {
            debugger
            var rows;
            if (resultData == null) {
                getMyWorkList(1, 10, 2);
                resultData = myContestWorkData;
                rows = myContestWorkData.rows;
            } else {
                rows = resultData.rows;
            }
            var headData = {
                "cid": "赛事编码",
                "title": '比赛名称',
                "startEndTime": '竞赛时间',
                "scoreTime": "评分时间",
                "workEndTime": "作品提交截止时间",
                "workSatus": "作品状态",
                "operation": "操作"
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.cid + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.startEndTime + '</td>' +
                '<td>' + headData.scoreTime + '</td>' +
                '<td>' + headData.workEndTime + '</td>' +
                '<td>' + headData.workSatus + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + rows[i].cid + '</td>' +
                    '<td>' + rows[i].title + '</td>' +
                    '<td>' + rows[i].startEndTime + '</td>' +
                    '<td>' + rows[i].scoreTime + '</td>' +
                    '<td>' + rows[i].workEndTime + '</td>' +
                    '<td>' + rows[i].workSatus + '</td>' +
                    '<td>' +
                    '<span class="look-details">' +
                    '查看竞赛详情' +
                    '<input type="hidden" value="' + rows[i].cid + '"/>' +
                    '</span>' +
                    '<span class="look-report">' +
                    '<input type="hidden" value="' + rows[i].cid + '"/>' +
                    '查看我的报告</span></td>' +
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
                    getMyWorkList(currIndex + 1, this.pageSize, 2);
                    initTable("myReportDone", myContestListData);
                },
            })
        }
        else if (type == "myReportWill") {
            debugger
            var rows;
            if (resultData == null) {
                getMyWorkList(1, 10, 0);
                resultData = myContestWorkData;
                rows = myContestWorkData.rows;
            } else {
                rows = resultData.rows;
            }
            var headData = {
                "cid": "赛事编码",
                "title": '比赛名称',
                "startEndTime": '竞赛时间',
                "scoreTime": "评分时间",
                "workEndTime": "作品提交截止时间",
                "workSatus": "作品状态",
                "operation": "操作"
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.cid + '</td>' +
                '<td>' + headData.title + '</td>' +
                '<td>' + headData.startEndTime + '</td>' +
                '<td>' + headData.scoreTime + '</td>' +
                '<td>' + headData.workEndTime + '</td>' +
                '<td>' + headData.workSatus + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + rows[i].cid + '</td>' +
                    '<td>' + rows[i].title + '</td>' +
                    '<td>' + rows[i].startEndTime + '</td>' +
                    '<td>' + rows[i].scoreTime + '</td>' +
                    '<td>' + rows[i].workEndTime + '</td>' +
                    '<td>' + rows[i].workSatus + '</td>' +
                    '<td>' +
                    '<span class="look-details">' +
                    '查看竞赛详情' +
                    '<input type="hidden" value="' + rows[i].cid + '"/>' +
                    '</span>' +
                    '<span class="submit-report">' +
                    '<input type="hidden" value="' + rows[i].cid + '"/>' +
                    '提交报告</span></td>' +
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
                    getMyWorkList(currIndex + 1, this.pageSize, 0);
                    initTable("myReportDone", myContestListData);
                },
            })
        }
        else if (type == "myAward") {
            var data = {
                "rows": [
                    {
                        "itemid": "1",
                        "startTime": '2017-05-01',
                        "endTime": '2017-05-10',
                        "name": '我的获奖',
                    },
                    {
                        "itemid": "2",
                        "startTime": '2017-05-01',
                        "endTime": '2017-05-10',
                        "name": '我的获奖',
                    },
                    {
                        "itemid": "3",
                        "startTime": '2017-05-01',
                        "endTime": '2017-05-10',
                        "name": '我的获奖',
                    },
                    {
                        "itemid": "4",
                        "startTime": '2017-05-01',
                        "endTime": '2017-05-10',
                        "name": '我的获奖',
                    },
                    {
                        "itemid": "5",
                        "startTime": '2017-05-01',
                        "endTime": '2017-05-10',
                        "name": '我的获奖',
                    },
                ]
            };
            var headData = {
                "itemid": "赛事编码",
                "startTime": '开始时间',
                "endTime": '结束时间',
                "name": '比赛名称',
                "operation": '操作'
            };
            var tableHead =
                '<tr class="table-body">' +
                '<td>' + headData.itemid + '</td>' +
                '<td>' + headData.startTime + '</td>' +
                '<td>' + headData.endTime + '</td>' +
                '<td>' + headData.name + '</td>' +
                '<td>' + headData.operation + '</td>' +
                '</tr>';
            var tableBody = '';
            for (var i = 0; i < data.rows.length; i++) {
                tableBody +=
                    '<tr class="table-body">' +
                    '<td>' + data.rows[i].itemid + '</td>' +
                    '<td>' + data.rows[i].startTime + '</td>' +
                    '<td>' + data.rows[i].endTime + '</td>' +
                    '<td>' + data.rows[i].name + '</td>' +
                    '<td><span class="look-details">查看竞赛详情</span></td>' +
                    '</tr>';
            }
            var table = '<table class="table" id="table">' + tableHead + tableBody + '</table>';
            $('.right-content').append(table);
            //初始化分页
            pageAdd();
            new pageInit({
                totalCount: 200,//总页数
                currIndex: 0,//当前页，0为第一页
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
                },
            })
        }
        else if (type == "myPersonalSituation") {
            var myPersonalSituation =
                '<ul class="personal-con">' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">学校</div>' +
                '<div class="personal-item-content">西安邮电大学</div>' +
                '</li>' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">院系</div>' +
                '<div class="personal-item-content">计算机学院-计算机科学与技术</div>' +
                '</li>' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">班级</div>' +
                '<div class="personal-item-content">14-02班</div>' +
                '</li>' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">学号</div>' +
                '<div class="personal-item-content">04141063</div>' +
                '</li>' +
                '<li class="personal-item">' +
                '<div class="personal-item-title">姓名</div>' +
                '<div class="personal-item-content">王巨峰</div>' +
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
        }
    }

    // 拼接分页
    function pageAdd(page) {
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
      /*  $('.fade').remove();*/
        if (type == 1) {
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
            submitType =2;
            var toast =
                '<div class="fade sure-window">' +
                '<div class="pop-window">' +
                '<div class="pop-window-item">' +
                '<div class="sure-title">确定报名吗？</div>' +
                '</div>' +
                '<div class="pop-window-footer">' +
                '<span class="pop-window-footer-btn submit-btn">确定' +
                '</span>' +
                '<span class="pop-window-footer-btn cancel-btn">取消</span>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        //提交报告
        else if (type == 3) {
            submitType =3;
            var toast =
                '<div class="fade sure-window">' +
                '<div class="pop-window">' +
                '<div class="pop-window-item">' +
                '<div class="sure-title">确定提交？提交后不可修改！</div>' +
                '</div>' +
                '<div class="pop-window-footer">' +
                '<span class="pop-window-footer-btn submit-btn">确定' +
                '</span>' +
                '<span class="pop-window-footer-btn cancel-btn">取消</span>' +
                '</div>' +
                '</div>' +
                '</div>';
        }
        $('body').append(toast);
    }

    //选择图片,上传
    function uploadFile(obj, type) {
        debugger
        var file = obj.files[0];
        var reader = new FileReader();
        //读取文件过程方法
        reader.onloadstart = function (e) {
            console.log("开始读取....");
        };
        reader.onprogress = function (e) {
            console.log("正在读取中....");
        };
        reader.onabort = function (e) {
            console.log("中断读取....");
        };
        reader.onerror = function (e) {
            console.log("读取异常....");
        };
        reader.onload = function (e) {
            console.log("成功读取....");
            successLoad(e, type);
            //上传文件或者照片方法；
            //TODO
            ajaxUploadFile(e.target.result, type);

        };
        reader.readAsDataURL(file);
    }

    //上传成功后的操作
    function successLoad(e, type) {
        if (type == 'img') {
            $('.picture').attr("src", e.target.result).show();
            $('.upload-name').hide();
            $('.img-file-input').attr("type", "file");
        } else if (type == 'file') {
            var file = document.getElementsByClassName('file-input')[0];
            $('.file-name').attr("href", e.target.result).text(file.files[0].name);
        }
    }

    // 报告功能【未提交报告1，已提交报告2】
    function reportShow(type) {
        if (type == 1) {
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
                '<div id="editor"></div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告图片</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<div class="picture-upLoad">' +
                '<input type="file" class="img-file-input">' +
                '<img class="picture"/>' +
                '<div class="upload-name">上传图片</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告附件</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<input type="file" class="file-input">' +
                '<span class="file-upload">上传文件</span>' +
                '<a href="" class="file-name">未选择文件</a>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-footer">' +
                '<span class="pop-window-footer-btn submit-btn" id="submit">提交</span>' +
                '<span class="pop-window-footer-btn save-btn" id="save">保存</span>' +
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
                '<div id="editor"></div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告图片</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<div class="picture-upLoad">' +
                '<input type="file" class="img-file-input">' +
                '<img class="picture"/>' +
                '<div class="upload-name">上传图片</div>' +
                '</div>' +
                '</div>' +
                '</div>' +
                '<div class="pop-window-item">' +
                '<div class="pop-window-item-title">' +
                '<i class="blue-circle"></i>' +
                '<span class="pop-window-body-name">报告附件</span>' +
                '</div>' +
                '<div class="pop-window-item-content">' +
                '<input type="file" class="file-input">' +
                '<span class="file-upload">上传文件</span>' +
                '<a href="" class="file-name">未选择文件</a>' +
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


    //请求获取列表数据
    function getContestList(page, rows) {
        $.ajax({
            url: "/client/contest/list",
            data: {"page": page, "rows": rows},
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

    function getMyContestList(page, rows, status) {
        $.ajax({
            url: "/client/contest/listMyContest",
            data: {"page": page, "rows": rows, "contestStatus": status},
            type: "post",
            dataType: "json",
            async: false,
            success: function (result) {
                myContestListData = result;
            },
            error: function () {

            }

        });
    }

    //获取作品列表
    function getMyWorkList(page, rows, workCommit) {
        $.ajax({
            url: "/client/contest/works",
            data: {"page": page, "rows": rows, "workCommit": workCommit},
            type: "post",
            dataType: "json",
            async: false,
            success: function (result) {
                myContestWorkData = result;
            },
            error: function () {

            }

        });
    }

    //获取学生的基本信息
    function getStudentInfo() {
        $.ajax({
            url: "/client/user/getStuInfo",
            type: "post",
            dataType: "json",
            async: false,
            success: function (result) {
                if (result.code == "0000") {
                    debugger
                    var stuInfo = result.data;
                    var showStr = stuInfo.colName + "-" + stuInfo.majName + "-" + stuInfo.stuId;
                    $(".menu-user-identity").html(showStr);
                    $(".menu-user-name").html(stuInfo.stuName);

                    showStr = stuInfo.stuName + " - " + stuInfo.colName;
                    $(".right-head-personal-name").html(stuInfo.colName);
                } else {
                    window.location.href = "http://127.0.0.1/graduation/html/login.html";
                }

            },
            error: function () {

            }

        });
    }

    //上传文件
    function ajaxUploadFile(content, type) {
        var url = "";
        var data;
        if (type == 'img') {
            url = "/client/upload/image";
            data = {'base64Data': content};

        } else if (type == 'file') {
            url = "/client/upload/file";
            data = {"base64Data": content}

        }
        $.ajax({
            url: url,
            type: "post",
            data: data,
            success: function (result) {
                if(result.code == "0000"){
                    if (type == 'img') {
                        imageName = result.data;

                    } else if (type == 'file') {
                        fileName = result.data;
                    }
                }

            },
            error: function (result) {

            }
        });
    }

    //弹窗2次确认按钮

    function  makeSure(type) {
        if(type == 2){
            $.ajax({
                data: {cId: globalCid},
                url: '/client/contest/enroll',
                success: function (result) {
                    if (result.code == "0000") {
                        toastAdd(1, '成功报名', '')
                    } else {
                        toastAdd(1, '报名失败', '')
                    }
                },
                fail: function () {
                    toastAdd(1, '报名失败请重试', '')
                }
            })
        }
        if(type == 3){
            var text = editor.txt.html();
            alert(text);
            editor.txt.html("<p>dadsadsadwoangdecdnincndinine</p>")
        }
    }
});


