package ezenweb.controller;


import ezenweb.model.dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000")
public class MemberController {

    @Autowired
    MemberService memberService;
    @PostMapping("/signup/post.do")
    public boolean doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println(memberDto);
        System.out.println("안녕");
        return memberService.doSignupPost(memberDto);
    }

}
