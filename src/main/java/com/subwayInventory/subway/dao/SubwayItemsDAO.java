package com.subwayInventory.subway.dao;

import java.util.List;

import com.subwayInventory.subway.entity.SubwayItems;

public interface SubwayItemsDAO {
	List<SubwayItems> findAll();

	SubwayItems findById(int id);

	String saveOrUpdate(SubwayItems thisItem);

	String deleteById(int id);
}