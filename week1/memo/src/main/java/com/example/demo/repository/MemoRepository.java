package com.example.demo.repository;


import com.example.demo.entity.Memo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoRepository {
    private final Map<Long, Memo> store = new LinkedHashMap<>();
    private long sequence = 0;

    public Memo save(String content){
        Memo memo = new Memo(++sequence,content);
        store.put(memo.getId(),memo);
        return memo;
    }

    public List<Memo> findAll(){
        return new ArrayList<>(store.values());
    }

    public Optional<Memo> findById(Long id){
        return Optional.ofNullable(store.get(id));
    }

    public void delete(Long id){
        store.remove(id);
    }
}
