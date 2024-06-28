package com.example.jpa;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;


import java.util.Optional;

//인터페이스라는 껍데기만 필요하고 타입만 봐도 어느정도 쿼리를 만들어낼 수 있다.
//save라는 이름만 보면 자동으로 쿼리를 주기 때문에 구현할 필요가 없다.
@NoRepositoryBean
public interface CrudRepository<T, ID> extends Repository<T, ID> {
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

    Optional<T> findById(ID id);
    boolean existsById(ID id);

    Iterable<T> findAll();
    Iterable<T> findAllById(Iterable<ID> ids);

    long count();

    void deleteById(ID id);
    void delete(T entity);
    void deleteAll(Iterable<? extends T> entities);
    void deleteAll();
}
