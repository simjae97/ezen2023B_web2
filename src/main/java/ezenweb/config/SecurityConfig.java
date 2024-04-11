package ezenweb.config;


import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Autowired
    MemberService memberService;




    @Bean
    //1.http 요청에 따른 부여된 권한/상태 확인후 PATH/자원 제한
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests
                ( authorizeRequest ->
                {
                    authorizeRequest
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/board")).authenticated() //get : /board : 인증 된 회원
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/board/write")).hasRole("USER") //글쓰기는 인증되어있고 ROLE이 USER인 애들만
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/chat")).hasAnyRole("TEAM1","TEAM2") // chat은 인증되어있고 ROLE이 TEAM1 , TEAM2 인 애들만
                            .requestMatchers(AntPathRequestMatcher.antMatcher("/**")).permitAll(); //그외 나머지는 permitALL은 다 허가
        } );
        //2. 로그인 폼 커스텀 : 기존 컼ㄴ트롤러 매핑함수 주석처리
//        http.formLogin(AbstractHttpConfigurer::disable); //시큐리티가 제공하는 로그인폼을 사용하지 않겠다
        http.formLogin( //json으로 값 보내주면 안됨! contenttype:form // post put 막혀있어서 안됨
                로그인관련매개변수->
                        로그인관련매개변수
                                .loginPage("/member/login")                             //로그인을 할 view url 정의
                                .loginProcessingUrl("/member/login/post.do")           //로그인을 처리할 url 정의
                                .usernameParameter("memail")                          //로그인에 사용할 id 변수명
                                .passwordParameter("mpassword")                      //패스워드에 사용할 pw변수명
                                .defaultSuccessUrl("/")                             //로그인 성공시 반환될 url
                                .failureForwardUrl("/member/login")                //로그인 실패시 반환될 url
                );
        //3.로그아웃 커스텀 : 기존 컼ㄴ트롤러 매핑함수 주석처리
        http.logout(httpSecurityLogoutConfigurer -> httpSecurityLogoutConfigurer
                .logoutRequestMatcher(
                        AntPathRequestMatcher.antMatcher("/member/logout/get.do"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true) // 세션 초기화
        );

        //4.csrf(post,put 요청 금지) 공격 방지 : 특정(인증/허가 url만 post,put이 가능 하도록
        http.csrf( AbstractHttpConfigurer :: disable); // csrf 사용안함

        //5.로그인 처리에 필요한 서비스를 등록
        http.userDetailsService(memberService); //
        //실제 배포시
//        http.csrf(매개변수 -> 매개변수.ignoringRequestMatchers(AntPathRequestMatcher(PATH))) //이런식으로 직접 주소 열어줘야
        return http.build();
    }

    //2. 시큐리티가 패스워드 검증할때 사용할 암호화 객체
    @Bean // 해당 메소드를 스프링 컨테이너에 등록
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

}
