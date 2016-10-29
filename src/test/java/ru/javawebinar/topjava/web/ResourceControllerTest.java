package ru.javawebinar.topjava.web;

import org.junit.Test;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by Vitalii on 10/28/2016.
 */
public class ResourceControllerTest extends AbstractControllerTest {

    @Test
    public void testStyle() throws Exception{
        mockMvc.perform(get("/resources/css/style.css"))
                .andDo(print())
                .andExpect(content().contentType("text/css"))
                .andExpect(status().isOk());
    }
}
