package com.vit.dao;

import java.util.List;

import com.vit.mapper.RowMapper;

//	java generic type T
public interface GenericDAO<T> {

	/*
	 * 3 tham số cần truyền vào:
	 *  - sql
	 *  - đối tượng cần trả về
	 *  - giá trị tham số (multi parameter)  sau WHERE có thể có nhìu giá trị cho nên t có sử dụng (Object... parameters) để truyền nhiều giá trị
	 *  trên 3 giá trị nó sẽ tạo thanh 1 mảng chứa các giá trị đó
	 * */
//	select data
	List<T>  query(String sql , RowMapper<T> rowMapper, Object... parameters );
//	update or delete data
	void update(String sql, Object... parameters);
//	insert data
	Long insert(String sql, Object... parameters);
//	count
	Long count(String sql, Object... parameters);
	
}
