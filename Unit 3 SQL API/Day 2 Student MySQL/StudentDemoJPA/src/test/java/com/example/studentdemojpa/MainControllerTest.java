//package com.example.studentdemojpa;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//import java.util.Optional;
//
//@WebMvcTest(MainController.class)
//public class MainControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private StudentRepository sRepository;
//
//    @BeforeEach
//    void setUp() {
//        Mockito.reset(sRepository); // Reset mocks to their initial state
//    }
//
//    @Test
//    void testAddNewStudentValidData() throws Exception {
//        com.example.studentdemojpa.model.Student student = new com.example.studentdemojpa.model.Student();
//        student.setFirstName("John");
//        student.setLastName("Doe");
//        student.setEmail("john.doe@example.com");
//        student.setEContact("1234567890");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonContent = objectMapper.writeValueAsString(student);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add")
//                        .contentType("application/json")
//                        .content(jsonContent))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Saved"));
//    }
//
//    @Test
//    void testGetAllStudents() throws Exception {
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/all"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andReturn();
//        System.out.println(mvcResult.getResponse().getContentAsString());
//    }
//
//    @Test
//    void testUpdateStudentValidData() throws Exception {
//
//        com.example.studentdemojpa.model.Student savedStudent = new com.example.studentdemojpa.model.Student();
//        savedStudent.setEmail("existing.email@example.com");
//        savedStudent.setEContact("1234567890");
//
//        Mockito.when(sRepository.findById(1L)).thenReturn(Optional.of(savedStudent));
//
//        // Create a new student
//        com.example.studentdemojpa.model.Student newStudent = new com.example.studentdemojpa.model.Student();
//        newStudent.setEmail("new.email@example.com");
//        newStudent.setEContact("0987654321");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add")
//                        .contentType("application/json")
//                        .content(new ObjectMapper().writeValueAsString(newStudent)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Saved"));
//
//
//        com.example.studentdemojpa.model.Student updatedStudent = new com.example.studentdemojpa.model.Student();
//        updatedStudent.setEmail("updated.email@example.com");
//        updatedStudent.setEContact("1122334455");
//
//        mockMvc.perform(MockMvcRequestBuilders.put("/update/1")
//                        .contentType("application/json")
//                        .content(new ObjectMapper().writeValueAsString(updatedStudent)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Updated"));
//
//    }
//
//    @Test
//    void testAddAndDeleteStudent() throws Exception {
//        // First, add a student
//        com.example.studentdemojpa.model.Student studentToAdd = new com.example.studentdemojpa.model.Student();
//        studentToAdd.setFirstName("John");
//        studentToAdd.setLastName("Doe");
//        studentToAdd.setEmail("john.doe@example.com");
//        studentToAdd.setEContact("1234567890");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String jsonContent = objectMapper.writeValueAsString(studentToAdd);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/add")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonContent))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("Saved"));
//
//        // Finally, delete the student
//        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/1") //hard code 1
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.status().isOk());
//    }
//
//
//
//    @Test
//    void testDeleteNonExistentStudent() throws Exception {
//        mockMvc.perform(MockMvcRequestBuilders.delete("/delete/999")) // Assuming no student with ID 999 exists
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    }
//
//}
