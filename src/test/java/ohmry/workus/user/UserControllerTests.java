package ohmry.workus.user;

import ohmry.workus.core.ApiStatus;
import org.json.JSONObject;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class UserControllerTests {
    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;
    protected MockHttpSession mockHttpSession;

    @BeforeAll
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        this.mockHttpSession = new MockHttpSession();
    }

    @Test
    @Order(1)
    public void 사용자_생성_시_이메일없음() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "이병훈");
        request.put("password", "oohmry");

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiStatus.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.message").value(ApiStatus.INVALID_PARAMETER.getMessage()))
                .andDo(print());
    }

    @Test
    @Order(2)
    public void 사용자_생성_시_이름없음() throws Exception {
        JSONObject request = new JSONObject();
        request.put("email", "o.ohmry@gmail.com");
        request.put("password", "oohmry");

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiStatus.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.message").value(ApiStatus.INVALID_PARAMETER.getMessage()))
                .andDo(print());
    }

    @Test
    @Order(3)
    public void 사용자_생성_시_비밀번호없음() throws Exception {
        JSONObject request = new JSONObject();
        request.put("email", "o.ohmry@gmail.com");
        request.put("name", "이병훈");

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiStatus.INVALID_PARAMETER.getCode()))
                .andExpect(jsonPath("$.message").value(ApiStatus.INVALID_PARAMETER.getMessage()))
                .andDo(print());
    }

    @Test
    @Order(4)
    public void 사용자_생성() throws Exception {
        JSONObject request = new JSONObject();
        request.put("email", "o.ohmry@gmail.com");
        request.put("name", "이병훈");
        request.put("password", "oohmry");

        mockMvc.perform(post("/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.email").value(request.get("email")))
                .andExpect(jsonPath("$.data.name").value(request.get("name")))
                .andDo(print());
    }

    @Test
    @Order(5)
    public void 사용자_로그인_시_없는_사용자() throws Exception {
        JSONObject request = new JSONObject();
        request.put("email", "o.ohmry2@gmail.com");
        request.put("password", "oohmry");

        mockMvc.perform(post("/signin")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.code").value(ApiStatus.USER_NOT_FOUND.getCode()))
                .andExpect(jsonPath("$.message").value(ApiStatus.USER_NOT_FOUND.getMessage()))
                .andDo(print());
    }

    @Test
    @Order(6)
    public void 사용자_로그인_시_비밀번호_틀림() throws Exception {
        JSONObject request = new JSONObject();
        request.put("email", "o.ohmry@gmail.com");
        request.put("password", "oohmry2");

        mockMvc.perform(post("/signin")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value(ApiStatus.INVALID_USER_CREDENTIAL.getCode()))
                .andExpect(jsonPath("$.message").value(ApiStatus.INVALID_USER_CREDENTIAL.getMessage()))
                .andDo(print());
    }

    @Test
    @Order(7)
    public void 사용자_로그인() throws Exception {
        JSONObject request = new JSONObject();
        request.put("email", "o.ohmry@gmail.com");
        request.put("password", "oohmry");

        mockMvc.perform(post("/signin")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data.id").value(1))
                .andExpect(jsonPath("$.data.email").value(request.get("email")))
                .andDo(print());
    }

    @Test
    @Order(8)
    public void 사용자_정보조회() throws Exception {
        mockMvc.perform(get("/user")
                        .session(mockHttpSession))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @Order(9)
    public void 사용자_정보수정() throws Exception {
        JSONObject request = new JSONObject();
        request.put("name", "이병훈_수정");

        mockMvc.perform(put("/user")
                        .session(mockHttpSession)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(request.toString()))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.name").value(request.get("name")))
                .andDo(print());
    }
}
