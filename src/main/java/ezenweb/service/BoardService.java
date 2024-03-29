package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;

    @Autowired
    MemberService memberService;

    //1.C
    @Transactional
    public boolean postBoard(BoardDto boardDto){
        MemberDto logindto = memberService.doLogininfo();
        if (logindto == null){
            return false;
        }
        Optional<MemberEntity> memberEntity = memberEntityRepository.findById(logindto.getMno());
        if (!memberEntity.isPresent()){
            return false;
        }
        MemberEntity memberEntity1 =  memberEntity.get();

        //글쓰기
        BoardEntity boardEntity = boardEntityRepository.save(boardDto.toEntity());
        if(boardEntity.getBno() >= 1){//글 쓰기를 성공했으면
            boardEntity.setMemberEntity(memberEntity1);
            return true;
        }

        return false;
    }

    //2.R
    @Transactional
    public List<Object> getBoard(){
        //1.리포지토리를 이용한 모든 엔티티를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        for (BoardEntity boardEntity : result) {
            System.out.println(boardEntity.getBno());
        }
        return null;
    }

    //3.U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("안녕안녕");
        return false;
    }

    //4.D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }
}
