package com.warriors.backend.users.userside.adapter.controller.users

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.warriors.backend.users.fixture.UserFixture
import com.warriors.backend.users.serverside.adapter.mysql.repository.UsersMySqlRepository
import com.warriors.backend.users.serverside.dto.UserEntity
import com.warriors.backend.users.serverside.mapper.UserDocumentMapper.toEntity
import com.warriors.backend.users.userside.mapper.UsersMapper.toUser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.TestPropertySource
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import java.util.UUID
import javax.print.attribute.standard.Media


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension::class)
@TestPropertySource(locations = ["classpath:application-test.properties"])
class UsersControllerTest {
    @Autowired
    private lateinit var usersMySqlRepository: UsersMySqlRepository

    @Autowired
    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setUp(){
        usersMySqlRepository.deleteAll()
    }


    @Test
    fun `When i try to create a user who don't exist, the user is created`() {
        // GIVEN
        val userDTO = UserFixture.createUserRestRessource

        // WHEN
        mockMvc.post("/v1/user") {
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(userDTO)
        }
            // THEN
            .andExpect {
                status { isCreated() }
                content {
                    contentType(MediaType.APPLICATION_JSON)
                }
            }
    }


    @Test
    fun `When i try to create a user with bad mail, the user is not created and i have a 400`(){
        // GIVEN

        val badUserDTO = UserFixture.createUserRestRessourceInvalid
        // WHEN

        mockMvc.post("/v1/user"){
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(badUserDTO)
        }
        // THEN
            .andExpect {
                status { isBadRequest() }
                content { MediaType.APPLICATION_PROBLEM_JSON_VALUE }
            }
        assertThat(usersMySqlRepository.existsByEmail(badUserDTO.email)).isFalse();
    }

    @Test
    fun `When i try to add a already existent user, the user is not modified and i have a 409`(){
        // GIVEN
        val goodUser = UserFixture.createUserRestRessource
        val secondUser = UserFixture.createUserRestRessource.copy(username = "utilisateur de test")

        usersMySqlRepository.saveAndFlush<UserEntity>(goodUser.toUser().toEntity())
        // WHEN
        mockMvc.post("/v1/user"){
            contentType = MediaType.APPLICATION_JSON
            accept = MediaType.APPLICATION_JSON
            content = jacksonObjectMapper().writeValueAsString(secondUser)
        }
        // THEN
            .andExpect {
                status { isConflict() }
                content { MediaType.APPLICATION_PROBLEM_JSON_VALUE }
            }
        assertThat(usersMySqlRepository.existsByEmail(goodUser.email)).isTrue();
    }


    @Test
    fun `When i try to get a user and the user Exist i get the user`(){
        // GIVEN
        val userEntity = UserFixture.userEntity
        val savedUser = usersMySqlRepository.saveAndFlush(userEntity)

        // WHEN
        mockMvc.get("/v1/user/${savedUser.id}"){
            contentType = MediaType.APPLICATION_JSON
            accept= MediaType.APPLICATION_JSON
        }
        // THEN
            .andExpect {
                status { isOk() }
                content { MediaType.APPLICATION_JSON }
            }
    }

    @Test
    fun `When i try to get a user who don't exist, a 404 is returned`() {
        // GIVEN
        val id = UUID.randomUUID()
        // WHEN
        mockMvc.get("/v1/user/${id}"){
            contentType = MediaType.APPLICATION_JSON
            accept= MediaType.APPLICATION_JSON
        }
            // THEN
            .andExpect {
                status { isNotFound() }
                content { MediaType.APPLICATION_PROBLEM_JSON_VALUE }
            }
    }

    @Test
    fun ` when i try to get a user with malformed id i have a error`(){
        // GIVEN
        val id ="1234"
        // WHEN
        mockMvc.get("/v1/user/${id}"){
            contentType = MediaType.APPLICATION_JSON
            accept= MediaType.APPLICATION_JSON
        }
            // THEN
            .andExpect {
                status { isBadRequest() }
                content { MediaType.APPLICATION_PROBLEM_JSON_VALUE }
            }
    }

    @Test
    fun `When i try to get all users and they have user in database i get all the users`(){
        // GIVEN
        val userEntity = UserFixture.userEntity
        val savedUser = usersMySqlRepository.saveAndFlush(userEntity)


        // WHEN
        mockMvc.get("/v1/user"){
            contentType = MediaType.APPLICATION_JSON
            accept= MediaType.APPLICATION_JSON
        }
            // THEN
            .andExpect {
                status { isOk() }
                content { MediaType.APPLICATION_JSON }
            }
    }

    @Test
    fun `When i try to get all users and they have no user in database i get all the users`(){
        // GIVEN
        val userEntity = UserFixture.userEntity
        val savedUser = usersMySqlRepository.saveAndFlush(userEntity)


        // WHEN
        mockMvc.get("/v1/user"){
            contentType = MediaType.APPLICATION_JSON
            accept= MediaType.APPLICATION_JSON
        }
            // THEN
            .andExpect {
                status { isOk() }
                content { MediaType.APPLICATION_JSON }
            }
    }
}