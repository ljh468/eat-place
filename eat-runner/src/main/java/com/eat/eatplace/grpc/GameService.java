package com.eat.eatplace.grpc;

import com.eat.eatplace.rpc.GameGrpc;
import com.eat.eatplace.rpc.GameRequest;
import com.eat.eatplace.rpc.GameResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService
public class GameService extends GameGrpc.GameImplBase {

  @Override
  public void requestGameStart(GameRequest request,
                               StreamObserver<GameResponse> response) {
    Long userId = request.getUserId();
    GameResponse reply = GameResponse.newBuilder()
                                     .setGameId(UUID.randomUUID().toString())
                                     .setBoardId(UUID.randomUUID().toString())
                                     .setUserId(userId)
                                     .setUserName("USER_NAME")
                                     .setMessage("MESSAGE")
                                     .build();
    response.onNext(reply);
    response.onCompleted();
  }

  @Override
  public void getGame(GameRequest request,
                      StreamObserver<GameResponse> responseObserver) {
    Long userId = request.getUserId();
    for (int i = 0; i < 10; i++) {
      GameResponse reply = GameResponse.newBuilder()
                                       .setGameId(UUID.randomUUID().toString())
                                       .setBoardId(UUID.randomUUID().toString())
                                       .setUserId(userId)
                                       .setUserName("USER_NAME")
                                       .setMessage("MESSAGE" + i)
                                       .build();
      responseObserver.onNext(reply);
    }
    responseObserver.onCompleted();
  }

  @Override
  public StreamObserver<GameRequest> getGames(StreamObserver<GameResponse> responseObserver) {
    return new StreamObserver<>() {

      @Override
      public void onNext(GameRequest request) {
        GameResponse reply = GameResponse.newBuilder()
                                         .setGameId(UUID.randomUUID().toString())
                                         .setBoardId(UUID.randomUUID().toString())
                                         .setUserId(request.getUserId())
                                         .setUserName("USER_NAME")
                                         .setMessage("MESSAGE")
                                         .build();
        responseObserver.onNext(reply);
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          throw new RuntimeException(e);
        }
      }

      @Override
      public void onCompleted() {
        responseObserver.onCompleted();
      }

      @Override
      public void onError(Throwable t) {

      }
    };
  }

}
