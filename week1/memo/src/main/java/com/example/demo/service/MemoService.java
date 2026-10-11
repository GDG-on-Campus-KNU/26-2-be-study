package com.example.demo.service;

import com.example.demo.dto.MemoDto;
import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoService {
    private final MemoRepository memoRepository;

    public MemoService(MemoRepository memoRepository){
        this.memoRepository = memoRepository;
    }

    public Memo create(MemoDto memoDto){
        return memoRepository.save(memoDto.getContent());
    }

    public List<Memo> findAll(){
        return memoRepository.findAll();
    }
}
