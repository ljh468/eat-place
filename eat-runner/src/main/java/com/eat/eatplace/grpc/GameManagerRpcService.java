package com.eat.eatplace.grpc;

import com.eat.eatplace.rpc.Game;
import com.eat.eatplace.rpc.GameManagerGrpc;
import com.eat.eatplace.rpc.GameRequest;
import com.eat.eatplace.rpc.GameResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@Slf4j
@GrpcService
public class GameManagerRpcService extends GameManagerGrpc.GameManagerImplBase {

  // 단일요청, 단일 응답
  @Override
  public void notifyGameStart(GameRequest request,
                               StreamObserver<GameResponse> response) {
    Long userId = request.getUserId();
    GameResponse reply = GameResponse.newBuilder()
                                     .setIsGameStart(true)
                                     .setGame(Game.newBuilder().build())
                                     .build();

    response.onNext(reply);
    response.onCompleted();
  }

  // 단일요청, 스트림 응답
  @Override
  public void getGame(GameRequest request,
                      StreamObserver<GameResponse> responseObserver) {
    Long userId = request.getUserId();
    for (int i = 0; i < 10; i++) {
      GameResponse reply = GameResponse.newBuilder()
                                       .setIsGameStart(true)
                                       .setGame(Game.newBuilder().build())
                                       .build();
      responseObserver.onNext(reply);
    }
    responseObserver.onCompleted();
  }

  // 스트림 요청, 스트림 응답
  @Override
  public StreamObserver<GameRequest> getGames(StreamObserver<GameResponse> responseObserver) {
    return new StreamObserver<>() {

      @Override
      public void onNext(GameRequest request) {
        GameResponse reply = GameResponse.newBuilder()
                                         .setIsGameStart(true)
                                         .setGame(Game.newBuilder().build())
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
