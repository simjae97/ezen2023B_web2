package ezenweb.service;

import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;
    public boolean doSignupPost( MemberDto memberDto){
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
        //1.리포지토리를 통한 모든 히원 엔티티 호출
        List<MemberEntity> memberEntityList =memberEntityRepository.findAll();
        //2. DTO와 동일한 아이디/패스워드 찾는다
//        memberEntityList.forEach( (m) ->{
//            //3.만약에 아이디가 동일하면 (엔티티와 dto
//            if(m.getMemail().equals(memberDto.getMemail())){
//                //4.만약에 패스워드까지 동일하면
//                if(m.getMpassword().equals(memberDto.getMpassword())){
//
//                    find = true;
//
//                }
//            }
//
//        });
        for (MemberEntity memberEntity : memberEntityList) {
            if(memberEntity.getMemail().equals(memberDto.getMemail())){
                if (memberEntity.getMpassword().equals(memberDto.getMpassword())){
                    request.getSession().setAttribute("loginInfo",memberDto);
                    return true;
                }
            }
        }
//        return memberEntityList.stream()
//                // 멤버 엔티티와 DTO의 이메일이 일치하는지 확인합니다.
//                .anyMatch(m -> m.getMemail().equals(memberDto.getMemail()) &&
//                        m.getMpassword().equals(memberDto.getMpassword()));
        return false;
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
}
