package ezenweb.service;

import ezenweb.model.dto.BoardDto;
import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MemberService implements UserDetailsService {
    
    //0.구현체이므로 userDetailService의 추상클래스 구현
    // -시큐리티 로그인 서비스 커스텀
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //로그인창에서 입력받은 아이디
        System.out.println("username = " + username);
        //2.입력받은 아이디로 실제 아이디와 실제 (암호화된) 패스워드
            //2-1 memail 이용한 회원 엔티티 찾기
        MemberEntity memberEntity = memberEntityRepository.findByMemail(username);

        // - ROLE 부여
        List<GrantedAuthority> 등급목록 = new ArrayList<>();
        등급목록.add(new SimpleGrantedAuthority("ROLE_USER")); // ROLE_등급명
        //3.UserDetails 반환 [1.실제아이디 2.패스워드]
            //UserDetails 목적 : Token에 입력받은 아이디/패스워드 검증하기 위한 실제 정보
        UserDetails userDetails = User.builder()
                .username(memberEntity.getMemail()) //실제 아이디
                .password(memberEntity.getMpassword()) //실제 비밀번호
                .authorities(등급목록) //권한들
                .build();
        return userDetails;
    }

    @Autowired
    MemberEntityRepository memberEntityRepository;

    //1.회원가입 (시큐리티 사용시 패스워드 암호화 필수)
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


    @Autowired private HttpServletRequest request;

//    // 2.로그인 secure 쓰니까 이제 안씀
//    public boolean doLoginPost(MemberDto memberDto){ //로그인
//        //1.
////        MemberEntity memberEntity =memberEntityRepository.findByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
////        System.out.println(memberEntity);
////        //2.
////        boolean result = memberEntityRepository.existsByMemailAndMpassword(memberDto.getMemail(), memberDto.getMpassword());
////        System.out.println(result);
//        MemberEntity memberEntity1 = memberEntityRepository.findByLoginSql(memberDto.getMemail(), memberDto.getMpassword());
//        if(memberEntity1 == null){
//            return false;
//        }
//        System.out.println(memberEntity1);
//        request.getSession().setAttribute("loginInfo",memberEntity1.toDto());
////        findByMyBoardList(); // 내가쓴글 리스트
//        return true;
//    }

//    //3로그아웃
//    public boolean doLogoutGet(){
//        request.getSession().setAttribute("loginInfo",null);
//        return true;
//    }
    //4.현재 로그인 회원정보 호출 (세션 값 반환/호출)

    public MemberDto doLogininfo(){
        //사용전
//        Object object = request.getSession().getAttribute("loginInfo");
//        if(object != null){
//            return (MemberDto) object;
//        }
//
//        return null;
        //security 사용 getPrincipal: 본인/주역/주체자 : 세션은 브라우저당 1개
        Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //2.로그인 중이 아니면
        if(object.equals("anonymousUser")){
            return null;
        }
        //3.로그인중이면 UserDetail 타입변환
        UserDetails userDetails = (UserDetails) object;
        //4.
        MemberEntity memberEntity = memberEntityRepository.findByMemail(userDetails.getUsername());
        //5.비밀번호 제외한 회원정보
        return MemberDto.builder().memail(memberEntity.getMemail())
                .mname(memberEntity.getMname())
                .mno(memberEntity.getMno())
                .build();
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