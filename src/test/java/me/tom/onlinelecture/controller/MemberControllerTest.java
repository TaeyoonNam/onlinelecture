package me.tom.onlinelecture.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.service.MemberService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

/**
 * SpringJUnit4ClassRunner / MockitoJUnitRunner / SpringRunner ??
 * - MockitoJUnitRunner는 Mockito를 사용하여 mock 객체를 주입받아 테스트하기 위해 @Mock, @Spy, @InjectMock 등을 제공하는 Runner
 * - SpringRunner는 테스트 환경에서 @Autowired를 통해 Bean을 주입받을 수 있는 Spring Application Context를 제공한다.
 *    (주의!!!) SpringRunner에서도 Mockito를 사용할 수 있지만 제약사항이 존재한다.
 *            어노테이션 @Rule 또는 initMocks를 등록시켜줘야 한다.
 * - SpringJUnit4ClassRunner는 SpringRunner의 부모 클래스이다.
 * 참고 : https://hwannny.tistory.com/87
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(MemberController.class)
public class MemberControllerTest {

  @MockBean
  private MemberService memberService;

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @Test
  public void validation_실패() throws Exception {

    // given
    MemberDto memberDto =
        new MemberDto().builder()
            .email("tom@test.com")
            .password("password")
            .name("테스터1")
            .hp("01012341234")
            .memNickNm("테스")
            .build();

    String content = objectMapper.writeValueAsString(memberDto);

    // when & then
    mockMvc
        .perform(
            post("/api/member")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.message").value("패스워드를 입력해주시기 바랍니다."))
        .andDo(print());
  }

  @Test
  public void validation_성공() throws Exception {

    // given
    MemberDto memberDto =
        new MemberDto().builder()
            .email("test@email.com")
            .password("test1!23")
            .name("테스터1")
            .memNickNm("nickName")
            .hp("01012341234")
            .build();
    String content = objectMapper.writeValueAsString(memberDto);

    // when & then
    mockMvc
        .perform(
            post("/api/member")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is2xxSuccessful())
        .andDo(print());
  }
}
