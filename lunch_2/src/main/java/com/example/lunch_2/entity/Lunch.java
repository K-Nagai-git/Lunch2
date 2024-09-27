package com.example.lunch_2.entity;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ランチ：エンティティ
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lunch {
	/** 管理ID */
	private Integer id;
	/** メニュー */
	private String menu;
	/** お店 */
	private String shop;
	/** 値段 */
	private Integer price;
	/** 前回利用日 */
	private LocalDate recentDate;   
	/** 回数 */
	private Integer times;   
	/** 評価 */
	private String rate;
	/** メモ・コメント */
	private String memo;  
}