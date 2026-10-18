package com.example.demo.service;

import com.example.demo.dto.MemoDeleteResponse;
import com.example.demo.dto.MemoDto;
import com.example.demo.entity.Memo;
import com.example.demo.repository.MemoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    // 없는 id면 404. update, delete도 이 메서드를 거쳐서 같은 규칙을 따른다
    public Memo findById(Long id){
        return memoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "메모를 찾을 수 없습니다. id=" + id));
    }

    public Memo update(Long id, MemoDto memoDto){
        Memo memo = findById(id);
        memo.setContent(memoDto.getContent());
        return memo;
    }

    public MemoDeleteResponse delete(Long id){
        findById(id);
        memoRepository.delete(id);
        return new MemoDeleteResponse("삭제 완료", id);
    }
}
