package xy.alcs.common.entity;

import java.util.List;

/**
 * @Author:ju
 * @Description:
 * @Date:Create in 16:37 2017-12-21
 */
public class PageData<T> {
    private Integer page;
    private Integer total;
    private Integer pageTotal;
    private Integer pageSize; //页大小
    private List<T> rows;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setPageTotal(Integer pageTotal) {
        this.pageTotal = pageTotal;
    }

    public Integer getPageTotal() {
        if (pageSize != null && pageSize != 0) {
            if (total % pageSize == 0) {
                pageTotal = total / pageSize;
            } else {
                pageTotal = total / pageSize + 1;
            }
        }
        return pageTotal;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public PageData() {
    }
}
