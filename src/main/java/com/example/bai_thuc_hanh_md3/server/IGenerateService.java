package com.example.bai_thuc_hanh_md3.server;

import java.util.List;

public interface IGenerateService<E>{
    List<E> findAll();
    E findOne(int id);
    void create(E e);
    void update(E e);

}
