package com.example.memo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository) {
        this.memoRepository = memoRepository;
    }

    public Memo create(String content) {
        return memoRepository.save(content);
    }

    public List<Memo> findAll() {
        return memoRepository.findAll();
    }

    public Memo findById(int id) {
        return memoRepository.findById(id)
                .orElseThrow(() -> new MemoNotFoundException(id));
    }

    public Memo update(int id, String content) {
        return memoRepository.update(id, content)
                .orElseThrow(() -> new MemoNotFoundException(id));
    }

    public void delete(int id) {
        boolean deleted = memoRepository.delete(id);
        if (!deleted) {
            throw new MemoNotFoundException(id);
        }
    }
}
