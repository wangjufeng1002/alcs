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

    public PageData() {
    }
}
