package com.lemon.profiler.core.pagination;

public abstract class GenericSearchCriteria {
	
	private boolean pagination = false;
    private int pageSize = 25;
    private String sortOrder = "ASC";
    private int pageNum = 0;
    
    public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public boolean isPagination()
    {
        return pagination;
    }
    public void setPagination(boolean pagination)
    {
        this.pagination = pagination;
    }
    public String getSortOrder()
    {
        return sortOrder;
    }
    public void setSortOrder(String sortOrder)
    {
        this.sortOrder = sortOrder;
    }
    public int getPageSize()
    {
        return pageSize;
    }
    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }
}
