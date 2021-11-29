package com.vit.mapper;

import java.sql.ResultSet;

// java generic type T
public interface RowMapper<T> {
//	get từng row trong table, nó sẽ lấy giá trị từng field trong cái row đó
	T mapRow(ResultSet rs);
	
}
