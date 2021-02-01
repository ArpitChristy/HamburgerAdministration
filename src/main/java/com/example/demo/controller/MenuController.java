package com.example.demo.controller;

import com.example.demo.model.Location;
import com.example.demo.model.Menu;
import com.example.demo.repository.MenuRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Log4j2
@RestController
@RequestMapping("/api")
public class MenuController {
    @Autowired
    MenuRepository menuRepository;
    @GetMapping("/menu")
    public ResponseEntity<?> getAllMenu(@RequestParam(required=false) String name, @RequestParam(required=false) String category){
        try{
            List<Menu> menus = new ArrayList<>();
            if(name == null || name.length() == 0){
                log.info("String variable name is empty");
                menuRepository.findAll().forEach(menus::add);
            }
            else{
                menuRepository.findByItemContaining(name).forEach(menus::add);
            }
            if(menus.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(menus, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/menu/{id}")
    public ResponseEntity<?> getByMenuById(@PathVariable("id") int menuId){
        Optional<Menu> menuData  = menuRepository.findByMenuId(menuId);
        if(menuData.isPresent()){
            return new ResponseEntity<>(menuData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping("/menu")
    public ResponseEntity<?> saveMenu(@RequestBody Menu menu) {
        try {
            menuRepository.save(menu);
            return new ResponseEntity<>("Created a new Location successfully!", HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("menu/{id}")
    public ResponseEntity<?> updateMenu(@PathVariable("id") int menuId, @RequestBody Menu menu){
        Optional<Menu> menuData = menuRepository.findByMenuId(menuId);
        if(menuData.isPresent()){
            Menu menu_obj = menuData.get();
            menu_obj.setItem(menu.getItem());
            menu_obj.setPrice(menu.getPrice());
            menu_obj.setCategory(menu.getCategory());

            return new ResponseEntity<>(menuRepository.save(menu_obj), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("menu/{id}")
    public ResponseEntity<?> deleteMenu(@PathVariable("id") int menuId){
        try{
            menuRepository.deleteById(menuId);
            return new ResponseEntity<>("Successfully deleted the location",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/menu")
    public ResponseEntity<?> deleteAllMenu(){
        try{
            menuRepository.deleteAll();
            return new ResponseEntity<>("Deleted all locations successfully",HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
