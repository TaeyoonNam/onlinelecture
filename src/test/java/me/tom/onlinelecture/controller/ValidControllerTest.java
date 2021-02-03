package me.tom.onlinelecture.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.tom.onlinelecture.dto.MemberDto;
import me.tom.onlinelecture.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(MemberController.class)
public class ValidControllerTest {

  @Autowired private MockMvc mockMvc;

  @Autowired private ObjectMapper objectMapper;

  @MockBean
  MemberService mockMemberService;

  @Test
  public void validation_실패() throws Exception {

    // given
    MemberDto memberDto = new MemberDto("test_email", "password", "이름", "01012341234");
    String content = objectMapper.writeValueAsString(memberDto);

    // when & then
    mockMvc
        .perform(
            post("/api/member")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andExpect(jsonPath("$.message").value("이메일 정보를 입력해주시기 바랍니다."))
        .andDo(print());
  }
}
