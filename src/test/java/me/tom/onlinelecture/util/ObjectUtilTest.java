package me.tom.onlinelecture.util;

import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.entity.Member;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

public class ObjectUtilTest {

  @Test
  public void MemberToMemberDÎto() {
    ModelMapper modelMapper = new ModelMapper();
    Member member = new Member();
    member.setEmail("test@email.com");
    member.setHp("01012341234");
    member.setName("test");

    MemberDto memberDto = modelMapper.map(member, MemberDto.class);
    assertEquals(member.getEmail(), memberDto.getEmail());
    assertEquals(member.getHp(), memberDto.getHp());
    assertEquals(member.getName(), memberDto.getName());
  }

}
