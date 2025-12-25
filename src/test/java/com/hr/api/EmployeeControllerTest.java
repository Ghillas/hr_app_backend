package com.hr.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;  
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.hr.api.application.restapiadapter.employee.EmployeeController;
import com.hr.api.domain.employee.EmployeeService;

@WebMvcTest(controllers = EmployeeController.class)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmployeeService employeeService;

    @Test
    public void testGetEmployees() throws Exception {
        mockMvc.perform(get("/employees"))
            .andExpect(status().isOk());
    }

}
