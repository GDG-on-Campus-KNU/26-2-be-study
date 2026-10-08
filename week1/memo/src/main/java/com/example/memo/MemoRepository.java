package com.example.memo;

import org.springframework.stereotype.Repository;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MemoRepository {
    private int counter = 1;
    private final Map<Integer, Memo> memos = new LinkedHashMap<>();

    public synchronized Memo save(String content) {
        Memo memo = new Memo(counter++, content);
        memos.put(memo.id(), memo);
        return memo;
    }

    public synchronized List<Memo> findAll() {
        return List.copyOf(memos.values());
    }

    public synchronized Optional<Memo> findById(int id) {
        return Optional.ofNullable(memos.get(id));
    }

    public synchronized Optional<Memo> update(int id, String content) {
        if (!memos.containsKey(id)) {
            return Optional.empty();
        }

        Memo updated = new Memo(id, content);
        memos.put(id, updated);
        return Optional.of(updated);
    }

    public synchronized boolean delete(int id) {
        return memos.remove(id) != null;
    }
}
