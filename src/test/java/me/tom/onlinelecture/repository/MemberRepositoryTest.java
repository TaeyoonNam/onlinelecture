package me.tom.onlinelecture.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import me.tom.onlinelecture.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

  @Autowired private MemberRepository memberRepository;

  @Test
  public void 회원정보_저장() {

    // given
    Member member =
        Member.builder()
            .email("test_tom@localhost.com")
            .password("tom_password")
            .name("남태윤")
            .build();

    // when
    memberRepository.save(member);

    // then
    Member savedMember = memberRepository.findByMemberNo(member.getMemberNo());
    assertEquals(savedMember.getEmail(), member.getEmail());
    assertEquals(savedMember.getPassword(), member.getPassword());
    assertEquals(savedMember.getName(), member.getName());
  }
}
