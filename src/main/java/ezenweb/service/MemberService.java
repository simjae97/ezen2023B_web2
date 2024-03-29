package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;

    //5.아이디로(이메일) 중복검사
    public boolean getfindMemail(String memail){
        boolean result;
//        //1.모든 엔티티에서 해당 필드의 값을 찾는다
//        memberEntityRepository.findAll().forEach( (m) -> {
//            if (m.getMemail().equals(memail)){
//                //찾았다
//                System.out.println(m);
//            }
//        });

        //2.리포지토리 추상메소드 이용하는 방법
        //2.특정 필드의 조건으로 레코드/엔티티 검색
//        MemberEntity result2 = memberEntityRepository.findByMemail(memail);
//        System.out.println("result2"+result2);
        //3.특정 필드의 조건으로 전재여부 검색
        result = memberEntityRepository.existsByMemail(memail);
        System.out.println("3"+result);
        //4.직접 native sql 작성
//        result2 = memberEntityRepository.findByMemailSql(memail);
//        System.out.println("4"+result2);
//        return false;
        return result;
    };


    //나혼자만든거
    public boolean doIdCheck(MemberDto memberDto){
        List<MemberEntity> memberEntityList =memberEntityRepository.findAll();

        for (MemberEntity memberEntity : memberEntityList) {
            if(memberEntity.getMemail().equals(memberDto.getMemail())){
                System.out.println("ㅇㅇ");
                return false; //중복아이디가 있다
            }
        }
        return true; //중복아이디가 없다
    }

    public boolean doSignupPost( MemberDto memberDto){

        boolean result = doIdCheck(memberDto);
        if(!result){
            return false;
        }

        MemberEntity savedEntity = memberEntityRepository.save(memberDto.toEntity());

        //3.엔티티 생성이 되었는지 아닌지 확인 (PK)
        if(savedEntity.getMno()>0) {
            return true;
        }
        return false;
    }

    //* 로그인 했다는 증거/기록

    @Autowired private HttpServletRequest request;

    // 2.로그인
    public boolean doLoginPost(MemberDto memberDto){ //로그인
        //1.
//        MemberEntity memberEntity =memberEntityRepository.findByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
//        System.out.println(memberEntity);
//        //2.
//        boolean result = memberEntityRepository.existsByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
//        System.out.println(result);
        MemberEntity memberEntity1 = memberEntityRepository.findByLoginSql(memberDto.getMemail(), memberDto.getMpassword());
        if(memberEntity1 == null){
            return false;
        }
        System.out.println(memberEntity1);
        request.getSession().setAttribute("loginInfo",memberEntity1.toDto());
//        findByMyBoardList(); // 내가쓴글 리스트
        return true;
    }

    //3로그아웃
    public boolean doLogoutGet(){
        request.getSession().setAttribute("loginInfo",null);
        return true;
    }
    //4.현재 로그인 회원정보 호출 (세션 값 반환/호출)
    public MemberDto doLogininfo(){
        Object object = request.getSession().getAttribute("loginInfo");
        if(object != null){
            return (MemberDto) object;
        }

        return null;
    }

    //6. 로그인) 내가 쓴글
    public List<Map<Object,Object>> findByMyBoardList(){

        MemberDto loginDto = doLogininfo();
        if(loginDto == null) return null;

//        //=============1.양방향일때==================//
//            //1.로그인된 회원번호를 이용한 엔티티 찾기
//        Optional<MemberEntity> memberEntity = memberEntityRepository.findById(loginDto.getMno());
//        if(memberEntity.isPresent()){ //2.만약 엔티티가 존재하면 존재하면
//            //3. 옵셔널에서 엔티티 추출
//            MemberEntity memberEntity1 = memberEntity.get();
//            List<BoardEntity> result1 = memberEntity1.getBoardEntityList();
//            //내가 쓴글 리스트 엔티티들 ---> 내가 쓴글 리스트 dto로 반호나
////            List<BoardDto> boardDtos = result1.stream().map((e) -> {return e.toDto();}).collect(Collectors.toList());
//            List<BoardDto> boardDtos = result1.stream().map( BoardEntity :: toDto ).collect(Collectors.toList());
//            return boardDtos;
//
//        }
//        else {
//            return null;
//        }

        // ==============2.단방향일떄-------------//
        List<Map<Object,Object>> result2 = memberEntityRepository.findByMyBoardsql(loginDto.getMno());
        System.out.println(result2);
        return result2;
    }
}
/*
    Optional 클래스
        -해당 객체가 null일수도있고 아닐수도있다

 */