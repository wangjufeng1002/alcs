
function pageInit(options) {
    pageEvent(options);
    if(options.currIndex != null){
        debugger
        page(options.currIndex,options);
    }else{
        page(options.initCurrIndex,options);
    }

}
function pageEvent(options) {
    //点击页码
    $('.pageItemCon').delegate('.pageItem','click', function() {
        currIndex = parseInt($(this).attr('data-id'));
        page(currIndex,options);
        options.pageCallback();
    });
    //上一页
    $('.lastPage').on('click', function() {
        currIndex = parseInt($('.pageItem.current').attr('data-id'));
        if(currIndex < 1){
            page(currIndex,options);
        }else{
            page(currIndex-1,options);
        }
        options.lastCallback();
    });
    //下一页
    $('.nextPage').on('click', function() {
        currIndex = parseInt($('.pageItem.current').attr('data-id'));
        var totalPage =  Math.ceil(parseInt($('#pageCount').text())/parseInt($('.pageSelect option:selected').val()));
        if(currIndex > totalPage - 2){
            page(totalPage-1,options);
        }else{
            page(currIndex+1,options);
        }
        options.nextCallback();
    });
    //每页几条下拉框
    $('.pageSelect').on('change',function() {
        page(0,options);
        options.selectCallback();
    });
    //跳至多少页失去焦点触发
    $('.pageInput').on('blur',function() {
        var currIndex = $(this).val();
        var totalPage =  Math.ceil(parseInt($('#pageCount').text())/parseInt($('.pageSelect option:selected').val()));
        if(currIndex > totalPage){
            currIndex = totalPage;
        }else if(currIndex < 1){
            currIndex = 1;
        }
        page(currIndex-1,options);
        options.pageCallback();
    });
}
function page(currIndexNum,options) {
    var currIndex = currIndexNum || 0;
    var pageSize = $('.pageSelect option:selected').val();//获取每页多少数量
    var totalCount = options.totalCount;//后端传给的总数据条数totalCount
    var totalPage = Math.ceil(totalCount / pageSize);//获取总页数
    getList(currIndex,pageSize,totalPage,totalCount);
}
function getList(currIndex, pageSize, totalPage, totalCount) {
    //初始化页面数据
    currIndex = parseInt(currIndex);
    pageSize = parseInt(pageSize);
    totalPage = parseInt(totalPage);
    totalCount = parseInt(totalCount);
    $('.page-select').val(currIndex);
    $('#pageCount').text(totalCount);
    $('.count-select option').each(function() {
        if($(this).val() == pageSize) {
            $(this).attr('selected', 'selected');
        }
    });
    var page = currIndex + 1;
    var pageCount = totalPage;
    var pageBefore = '';
    var pageAfter = '';
    var pageCur = '<li class="pageItem current" data-id="' + (page - 1) + '">' + page + '</li>';
    var pageHtml = '';
    if(pageCount >= 10) {
        if(page >= 5 && page <= pageCount - 4) {
            for (var i = page - 2; i < page; i++) {
                pageBefore += '<li class="pageItem" data-id="' + (i - 1) + '">' + i + '</li>'
            }
            for (var i = page + 1; i < page + 3; i++) {
                pageAfter += '<li class="pageItem" data-id="' + (i - 1) + '">' + i + '</li>'
            }
            pageHtml = '<li class="pageItem" data-id="0">1</li><li class="ellipsis">...</li>' + pageBefore + pageCur + pageAfter + '<li class="ellipsis">...</li><li class="pageItem" data-id="' + (pageCount - 1) + '">' + pageCount + '</li>';
        } else if(1 <= page && page <= 4) {
            for (var i = 1; i <= 6; i++) {
                if (i == page) {
                    pageBefore += '<li class="pageItem current" data-id="' + (i - 1) + '">' + i + '</li>'
                } else {
                    pageBefore += '<li class="pageItem" data-id="' + (i - 1) + '">' + i + '</li>'
                }
            }
            pageHtml = pageBefore + '<li class="ellipsis">...</li><li class="pageItem" data-id="' + (pageCount - 1) + '">' + pageCount + '</li>';
        } else if(page >= pageCount - 3 && page <= pageCount) {
            for (var i = page - 3; i <= pageCount; i++) {
                if (i == page) {
                    pageAfter += '<li class="pageItem current" data-id="' + (i - 1) + '">' + i + '</li>'
                } else {
                    pageAfter += '<li class="pageItem" data-id="' + (i - 1) + '">' + i + '</li>'
                }
            }
            pageHtml = '<li class="pageItem" data-id="0">1</li><li class="ellipsis">...</li>' + pageAfter;
        }
    } else {
        debugger
        if(pageCount == 0){
            pageHtml += '<li class="pageItem current" data-id="' + 1 + '">' + 1 + '</li>'
        }
        for (var i = 1; i <= pageCount; i++) {
            if (i == page) {
                pageHtml += '<li class="pageItem current" data-id="' + (i - 1) + '">' + i + '</li>'
            } else {
                pageHtml += '<li class="pageItem" data-id="' + (i - 1) + '">' + i + '</li>'
            }
        }
    }
    $('.pageItemCon').html(pageHtml);
}