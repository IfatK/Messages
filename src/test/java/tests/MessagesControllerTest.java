package tests;

import App.Main;
import App.MessagesController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MessagesController.class)
@ContextConfiguration(classes={Main.class})
public class MessagesControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void addMessage() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                .post("/messages/add")
                .content("{\n" +
                        "\t\"recipient\" : \"yoni@gmail.com\",\n" +
                        "\t\"content\" : \"Hi yoni, how are you?\",\n" +
                        "\t\"sender\" : \"ella@gmail.com\"\n" +
                        "}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
