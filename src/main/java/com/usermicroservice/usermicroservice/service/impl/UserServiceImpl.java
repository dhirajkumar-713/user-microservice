package com.usermicroservice.usermicroservice.service.impl;

import com.usermicroservice.usermicroservice.dto.DepartmentDTO;
import com.usermicroservice.usermicroservice.dto.ResponseDTO;
import com.usermicroservice.usermicroservice.dto.UserDTO;
import com.usermicroservice.usermicroservice.entity.User;
import com.usermicroservice.usermicroservice.exception.UserNotFoundException;
import com.usermicroservice.usermicroservice.repository.UserRepository;
import com.usermicroservice.usermicroservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private WebClient webClient;
    private UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public ResponseDTO getUser(Long userId) {
        ResponseDTO responseDTO = new ResponseDTO();
        if(userRepository.findById(userId).isEmpty()){
            throw new UserNotFoundException("The User does not exist");
        }
        User user = userRepository.findById(userId).get();

        UserDTO userDTO = mapToUserDTO(user);

        /*ResponseEntity<DepartmentDTO> departmentDTOEntity = restTemplate.getForEntity("http://localhost:8081/api/departments/" + user.getDepartmentId(), DepartmentDTO.class);*/
        DepartmentDTO departmentDTOEntity = webClient.get().uri("http://localhost:8081/api/departments/" + user.getDepartmentId(),
                DepartmentDTO.class).retrieve().bodyToMono(DepartmentDTO.class).block();
        DepartmentDTO departmentDto = departmentDTOEntity;

        responseDTO.setUserDTO(userDTO);
        responseDTO.setDepartmentDTO(departmentDto);
        return responseDTO;
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getUserId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }
}
