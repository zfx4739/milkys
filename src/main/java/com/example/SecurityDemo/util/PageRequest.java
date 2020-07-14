package com.example.SecurityDemo.util;

import lombok.Getter;
import lombok.Setter;

/**
 * 分页
 */
@Setter
@Getter
public class PageRequest {

	/**
	 * 当前页码
	 */
	private int pageNum = 0;
	/**
	 * 每页数量
	 */
	private int pageSize = 10;
	/**
	 * 排序规则
	 */
	private String orderByColumn;
	/**
	 * 排序方式 Asc或Desc
	 */
	private String isAsc;
}
