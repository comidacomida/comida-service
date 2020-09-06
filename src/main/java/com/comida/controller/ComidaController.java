package com.comida.controller;

import com.comida.helper.ApiConstants;
import com.comida.service.ComidaService;
import com.comida.vo.ItemVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@Api(tags = {"Comida API"})
public class ComidaController {

  private final ComidaService comidaService;
  public ComidaController(ComidaService comidaService) {this.comidaService = comidaService;}

  @ApiOperation(
      value = "Retrieves all customers",
      notes = "Retrieves customers registered in the application")
  @GetMapping("/items")
  public List<ItemVo> getAllCustomers(@RequestHeader(ApiConstants.AUTHORIZATION_HEADER) final String authorization) {
    return comidaService.getItems(authorization);
  }
}
