/* 1. 회원 샘플 데이터 */
insert
    into member( memail , mpassword , mname  )
    values( 'qwe1' , '1234' , '유재석' ),
    ( 'qwe2' , '1234' , '강호동' ),
    ( 'qwe3' , '1234' , '신동엽' ),
    ( 'qwe4' , '1234' , '하하' ),
    ( 'qwe5' , '1234' , '서장훈' );

/* 2. 게시물 샘플 데이터 */
insert
    into board( bcontent , member_entity_mno )
    values( '게시물내용1' , 1 ),
    ( '게시물내용1' , 2 ),
    ( '게시물내용1' , 3 ),
    ( '게시물내용1' , 4 ),
    ( '게시물내용1' , 5 ),
    ( '게시물내용1' , 1 );

/* 3. 댓글 샘플 데이터 */
insert
    into reply( rcontent , board_entity_bno , member_entity_mno )
    values( '댓글1' , 1 , 1 ),
    ( '댓글2' , 1 , 1 ),
    ( '댓글3' , 2 , 2 ),
    ( '댓글4' , 2 , 1 ),
    ( '댓글5' , 3 , 4 ),
    ( '댓글5' , 1 , 5 );