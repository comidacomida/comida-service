package com.comida.service;

import static com.comida.helper.ComidaServiceHelper.mapItemsClientToVo;

import com.comida.client.IfoodClient;
import com.comida.client.response.ifood.IfoodItemResponse;
import com.comida.exceptions.GlobalException;
import com.comida.vo.ItemVo;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComidaService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ComidaService.class);

  private final IfoodClient ifoodClient;

  @Autowired
  public ComidaService(IfoodClient ifoodClient) {this.ifoodClient = ifoodClient;}


  public List<ItemVo> getItems(final String authorization) {


    final CompletableFuture<IfoodItemResponse> futureIfoodItems =
        ifoodClient.getItems(authorization);

    IfoodItemResponse ifoodItemResponse =
        Optional.ofNullable(getFutureResult(futureIfoodItems))
            .orElse(new IfoodItemResponse());

    List<ItemVo> itemVoList = mapItemsClientToVo(ifoodItemResponse);


    return itemVoList;
  }

  private <T> T getFutureResult(final CompletableFuture<T> futureResult) {

    try {
      return futureResult.get();
    } catch (final InterruptedException e) {
      LOGGER.error(e.getMessage(), e);
      Thread.currentThread().interrupt();
    } catch (Exception e) {
      LOGGER.error(e.getMessage(), e);
      throw (GlobalException) e.getCause();
    }
    return null;
  }

}