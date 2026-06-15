package com.sedena.app.services;

import java.util.List;

import com.sedena.app.entities.item;

public interface IService {
    boolean insert(item i);

    item selectByName(String name);

    item selectById(long id);

    boolean updateById(item i);

    List<item> selectALL();

    boolean deleteById(long id);

}
