package ezenweb.service;


import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.BoardImgEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.BoardImgRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    @Autowired
    FileService fileService;

    @Autowired
    BoardImgRepository boardImgRepository;
    //1.C 이미지도 저장하기
    @Transactional
    public boolean postBoard(BoardDto boardDto){
        final boolean[] result = {false};

        MemberDto logindto = memberService.doLogininfo();
        if (logindto == null){
            result[0]= false;
        }




        Optional<MemberEntity> memberEntity = memberEntityRepository.findById(logindto.getMno());
        if (!memberEntity.isPresent()){
            result[0]= false;
        }
        MemberEntity memberEntity1 =  memberEntity.get();

        //글쓰기
        BoardEntity boardEntity = boardEntityRepository.save(boardDto.toEntity());
        if(boardEntity.getBno() >= 1){//글 쓰기를 성공했으면
            boardEntity.setMemberEntity(memberEntity1);
            result[0]= true;
        }

        List<BoardImgEntity> filenameList = boardDto.getUploadList().stream().map((e)->{
            return BoardImgEntity.builder().bimg(fileService.fileUpload(e)).build();} ).collect(Collectors.toList());
        System.out.println("저장시 발생한 오류");

        filenameList.forEach( (e) -> {
            System.out.println("이미지명"+e.getBimg());
            BoardImgEntity boardImgEntity = boardImgRepository.save(e);
            if(boardImgEntity.getBimg() == null){
                result[0] = false;
            }
            boardImgEntity.setBoardEntity(boardEntity);

        } );

        return result[0];
    }
//    List<String > uploadlist = new ArrayList<>();
//        boardDto.getUploadList().forEach((e)-> {
//        uploadlist.add(fileService.fileUpload(e));
//    });
//    List<BoardImgEntity> uploadlist2 = new ArrayList<>();
//        uploadlist.forEach( e-> {
//        System.out.println(e);
//        uploadlist2.add(BoardImgEntity.builder().bimg(e).build());
//    });
//
//        uploadlist2.forEach( e->{
//        BoardImgEntity boardImgEntity = boardImgRepository.save(e);
//        if(boardImgEntity.getBimg() == null){
//            result[0] = false;
//        }
//        boardImgEntity.setBoardEntity(boardEntity);
//    });
    //2.R
    @Transactional
    public List<BoardDto> getBoard(){
//        //1.리포지토리를 이용한 모든 엔티티를 호출
//        List<BoardEntity> result = boardEntityRepository.findAll();
//        //2.꺼내온 엔티티를 DTO로 변환
//        //1.꺼내온 엔티티를 순회한다
//        List<BoardDto> result2 = result.stream().map(BoardEntity::toDto).collect(Collectors.toList());

        return boardEntityRepository.findAll().stream().map(BoardEntity::toDto).collect(Collectors.toList());
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
