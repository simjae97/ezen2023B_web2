package ezenweb.controller;


import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/member")
//@CrossOrigin("http://localhost:3000") // ********* 요청 근원지를 교차 허용(3000번지에서 8080에 요청 가능)
public class MemberController {

    @Autowired
    MemberService memberService;
    @PostMapping("/signup/post.do")
    public boolean doSignupPost(@RequestBody MemberDto memberDto){ //회원가입
        System.out.println(memberDto);
        System.out.println("안녕");
        return memberService.doSignupPost(memberDto);
    }

    @PostMapping("/login/post.do")
    public boolean doLoginPost(@RequestBody MemberDto memberDto){ //로그인
        System.out.println(memberDto);
        System.out.println("안녕");
        return memberService.doLoginPost(memberDto);
    }

    @GetMapping("/logout/get.do")   //로그아웃
    public boolean doLogoutGet(){
        return memberService.doLogoutGet();
    }

    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLogininfo();
    }

    @PostMapping("/idcheck/post.do") //내가만든거
    public boolean getfindMemail(@RequestBody MemberDto memberDto){
        return memberService.doIdCheck(memberDto);
    }

    @GetMapping("/find/email/get.do") //수업시간에한거
    public boolean getfindMemail(String memail){
        return memberService.getfindMemail(memail);
    }

    @GetMapping("/find/list/get.do")
    public List<Map<Object,Object>> findboardlist(){
        return memberService.findByMyBoardList();
    }

}
