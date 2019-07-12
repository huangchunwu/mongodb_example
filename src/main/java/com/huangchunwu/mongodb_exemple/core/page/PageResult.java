package com.huangchunwu.mongodb_exemple.core.page;

import lombok.Data;

import java.util.List;

@Data
public class PageResult<T> {

    private Integer pageNo;

    private Integer pageTotalSize;

    private List<T> data;

    private PageResult(Builder builder) {
        setPageNo(builder.pageNo);
        setPageTotalSize(builder.pageTotalSize);
        setData(builder.data);
    }

    public static Builder newBuilder() {
        return new Builder();
    }


    public static final class Builder<T> {
        private Integer pageNo;
        private Integer pageTotalSize;
        private List<T> data;

        private Builder() {
        }

        public Builder pageNo(Integer pageNo) {
            this.pageNo = pageNo;
            return this;
        }

        public Builder pageTotalSize(Integer pageTotalSize) {
            this.pageTotalSize = pageTotalSize;
            return this;
        }

        public Builder data(List<T> data) {
            this.data = data;
            return this;
        }

        public PageResult build() {
            return new PageResult(this);
        }
    }
}
