package com.example.lunch_2.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.lunch_2.entity.Lunch;
import com.example.lunch_2.repository.LunchMapper;
import com.example.lunch_2.service.LunchService;

import lombok.RequiredArgsConstructor;

/**
 * Lunch：サービス実装クラス
 */
@Service
@Transactional
@RequiredArgsConstructor
public class LunchServiceImpl implements LunchService {

	/** DI */
	private final LunchMapper lunchMapper;

	@Override
	public List<Lunch> findAllLunch() {
		return lunchMapper.selectAll();
	}

	@Override
	public Lunch findByIdLunch(Integer id) {
		return lunchMapper.selectById(id);
	}

	@Override
	public void insertLunch(Lunch lunch) {
		lunchMapper.insert(lunch);
	}

	@Override
	public void updateLunch(Lunch lunch) {
		lunchMapper.update(lunch);
	}

	@Override
	public void deleteLunch(Integer id) {
		lunchMapper.delete(id);
	}
}
