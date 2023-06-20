package com.subwayInventory.subway.service;

import com.subwayInventory.subway.entity.SubwayItems;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface SubwayItemsService {
    List<SubwayItems> findAll();

    SubwayItems findById(int id);

    String saveOrUpdate(SubwayItems thisItem);

    String deleteById(int id);
}
