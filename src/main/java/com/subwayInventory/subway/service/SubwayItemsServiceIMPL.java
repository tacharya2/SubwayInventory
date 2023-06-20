package com.subwayInventory.subway.service;

import com.subwayInventory.subway.dao.SubwayItemsDAO;
import com.subwayInventory.subway.entity.SubwayItems;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubwayItemsServiceIMPL implements SubwayItemsService{

    private final SubwayItemsDAO subwayItemsDAO;

    @Autowired
    public SubwayItemsServiceIMPL(SubwayItemsDAO subwayItemsDAO) {
        this.subwayItemsDAO = subwayItemsDAO;
    }

    @Override
    public List<SubwayItems> findAll() {
        return subwayItemsDAO.findAll();
    }

    @Override
    public SubwayItems findById(int id) {
        return subwayItemsDAO.findById(id);
    }

    @Override
    public String saveOrUpdate(SubwayItems thisItem) {
        return subwayItemsDAO.saveOrUpdate(thisItem);
    }

    @Override
    public String deleteById(int id) {
        return subwayItemsDAO.deleteById(id);
    }
}
