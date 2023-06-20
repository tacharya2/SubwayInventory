package com.subwayInventory.subway.Controller;

import com.subwayInventory.subway.entity.SubwayItems;
import com.subwayInventory.subway.service.SubwayItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class SubwayItemsController {

    private final SubwayItemsService subwayItemsService;

    @Autowired
    public SubwayItemsController(@Qualifier("subwayItemsServiceIMPL") SubwayItemsService subwayItemsService) {
        this.subwayItemsService = subwayItemsService;
    }
    // http://localhost:8082/allItems
    @GetMapping("/allItems")
    public List<SubwayItems> findAll(){
        return subwayItemsService.findAll();
    }

    // http://localhost:8082/allItems/1
    @GetMapping("/allItems/{id}")
    public SubwayItems findById(@PathVariable int id){
        return subwayItemsService.findById(id);
    }

    // http://localhost:8082/addItems
    @PostMapping("/addItems")
    public SubwayItems addItems(@RequestBody SubwayItems subwayItems){
        subwayItems.setId(0);
        subwayItemsService.saveOrUpdate(subwayItems);
        return subwayItems;
    }
    // http://localhost:8082/updateItems
    @PutMapping("/updateItems")
    public SubwayItems updateItems(@RequestBody SubwayItems subwayItems) {
        subwayItemsService.saveOrUpdate(subwayItems);
        return subwayItems;
    }
    // http://localhost:8082/deleteItems/4
    @DeleteMapping("/deleteItems/{id}")
    public String deleteItems(@PathVariable int id){
        subwayItemsService.deleteById(id);

        return "record of "+ id + " has been removed.";
    }
}