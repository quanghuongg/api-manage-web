package com.mange.api.controller;

import com.mange.api.define.Constant;
import com.mange.api.entity.User;
import com.mange.api.entity.model.Response;
import com.mange.api.exception.ApiServiceException;
import com.mange.api.service.ManagerService;
import com.mange.api.uitls.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = {"/manager"})
public class ManagerController {
    private final ManagerService managerService;

    @Autowired

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(value = {"/register"}, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody User user) throws ApiServiceException {
        if (user.getUsername().isEmpty() || user.getPassword().isEmpty()) {
            throw new ApiServiceException(Constant.OBJECT_EMPTY_FIELD);
        }

        User existedUser = managerService.findByUsername(user.getUsername());
        if (ServiceUtils.isNotEmpty(existedUser)) {
            throw new ApiServiceException(Constant.USER_CREATE_EXISTING);
        }
        managerService.save(user);
        Response response = Response.builder()
                .code(Constant.SUCCESS_CODE)
                .message(Constant.SUCCESSFUL_MESSAGE)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping(value = {"/list-user"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<?> getAllStudent(@RequestParam int type) {
        Response responseObject = Response.builder()
                .code(0)
                .data(managerService.getAllUser(type))
                .message("success")
                .build();
        return new ResponseEntity<>(responseObject, HttpStatus.OK);

    }
}

