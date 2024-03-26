package ezenweb.service;


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

@Service
public class BoardService {

    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;

    //1.C
    @Transactional
    public boolean postBoard(){
        //-----------------xptmxm----------------------
        //1.회원가입
            //1.엔티티 객체 생성
        MemberEntity memberEntity = MemberEntity.builder().memail("qwe@qwe.com").mpassword("1234").mname("유재석").build();
            //2. 해당 엔티티를 DB에 저장할수 있도록 조작
        MemberEntity savememberEntity =  memberEntityRepository.save(memberEntity);
        //2.회원가입된 회원으로 글쓰기
        BoardEntity boardEntity = BoardEntity.builder().bcontent("게시물글입니다").memberEntity(savememberEntity).build();
        BoardEntity saveboardEntity = boardEntityRepository.save(boardEntity);
        //3.해당글에 댓글 작성

        ReplyEntity replyEntity = ReplyEntity.builder().rcontent("댓글입니다").boardEntity(saveboardEntity).memberEntity(savememberEntity).build();
        ReplyEntity savereplyEntity = replyEntityRepository.save(replyEntity);
        return true;
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
