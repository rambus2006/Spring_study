package com.example.di._002;

// CrudRepository.java
//인터페이스에서는 객체를 만들지 못하니까
public interface CrudRepository {
    public Object[] getAll(Object o);
    public void save(Object o);
    public void delete(int id);
    public void update(Object o);
}

