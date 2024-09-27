package com.example.lunch_2.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.lunch_2.entity.Lunch;

/**
 * Lunch：リポジトリ
 */
@Mapper
public interface LunchMapper {

	/**
	 * 全ての「ランチ」を取得します。
	 */
	List<Lunch> selectAll();

	/**
	 * 指定されたIDに対応する「ランチ」を取得します。
	 */
	Lunch selectById(@Param("id") Integer id);

	/**
	 * 「ランチ」を登録します。
	 */
	void insert(Lunch lunch);

	/**
	 * 「ランチ」を更新します。
	 */
	void update(Lunch lunch);

	/**
	 * 指定されたIDの「ランチ」を削除します。
	 */
	void delete(@Param("id") Integer id);
}