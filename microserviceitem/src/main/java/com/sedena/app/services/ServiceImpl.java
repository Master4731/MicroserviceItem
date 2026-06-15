package com.sedena.app.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.sedena.app.daos.IMicroserviceDAO;
import com.sedena.app.entities.item;

@Service
public class ServiceImpl implements IService {

    private IMicroserviceDAO dao;

    public ServiceImpl(IMicroserviceDAO dao) {
        this.dao = dao;
    }

    @Override
    public boolean insert(item i) {
        if (i.getId() == 0) {
            item result = dao.save(i);
            return result != null;
        }
        return false;
    }

    @Override
    public item selectByName(String name) {

        return dao.searchByName(name)
                .orElseThrow();
    }

    @Override
    public item selectById(long id) {
        return dao.findById(id)
                .orElseThrow();
    }

    @Override
    public boolean updateById(item i) {
        if (dao.existsById(i.getId())) {
            dao.save(i);
            return true;
        }
        throw new NoSuchElementException("El item " + i.getId() + " no existe");
    }

    @Override
    public List<item> selectALL() {

        return (List<item>) dao.findAll();
    }

    @Override
    public boolean deleteById(long id) {

        if (dao.existsById(id)) {
            dao.deleteById(id);
            return true;
        }
        throw new NoSuchElementException("El item " + id + " no existe");
    }

}
