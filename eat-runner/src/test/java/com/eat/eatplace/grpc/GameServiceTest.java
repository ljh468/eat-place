package com.eat.eatplace.grpc;

import com.eat.eatplace.rpc.GameGrpc;
import com.eat.eatplace.rpc.GameRequest;
import com.eat.eatplace.rpc.GameResponse;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Slf4j
class GameServiceTest {
//  public static void main(String[] args) throws InterruptedException {
//    // 서버 주소와 포트에 맞게 ManagedChannel을 설정
//    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8191)
//                                                  .usePlaintext()
//                                                  .build();
//
//    GameClient client = new GameClient(channel);
//
//    // 예시 사용자 ID
//    Long userId = 1234L;
//
//    // 서버 메소드 호출
//    client.requestGameStart(userId);
//    client.getGame(userId);
//    client.getGames();
//
//    // ManagedChannel을 닫기 전에 비동기 호출이 완료될 시간을 기다림
//    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//  }

  @Test
  void requestGameStart() throws InterruptedException {
    // 서버 주소와 포트에 맞게 ManagedChannel을 설정
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8191)
                                                  .usePlaintext()
                                                  .build();

    GameClient client = new GameClient(channel);

    // 예시 사용자 ID
    Long userId = 1234L;

    // 서버 메소드 호출
    client.requestGameStart(userId);

    // ManagedChannel을 닫기 전에 비동기 호출이 완료될 시간을 기다림
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  @Test
  void getGame() throws InterruptedException { // 서버 주소와 포트에 맞게 ManagedChannel을 설정
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8191)
                                                  .usePlaintext()
                                                  .build();

    GameClient client = new GameClient(channel);

    // 예시 사용자 ID
    Long userId = 1234L;

    // 서버 메소드 호출
    client.getGame(userId);

    // ManagedChannel을 닫기 전에 비동기 호출이 완료될 시간을 기다림
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }

  @Test
  void getGames() throws InterruptedException {
    // 서버 주소와 포트에 맞게 ManagedChannel을 설정
    ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8191)
                                                  .usePlaintext()
                                                  .build();

    GameClient client = new GameClient(channel);

    // 예시 사용자 ID
    Long userId = 1234L;
    client.getGames();

    // ManagedChannel을 닫기 전에 비동기 호출이 완료될 시간을 기다림
    channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
  }


  public static class GameClient {

    private final GameGrpc.GameStub asyncStub;

    public GameClient(ManagedChannel channel) {
      // 'asyncStub' is for non-blocking/asynchronous calls
      asyncStub = GameGrpc.newStub(channel);
    }

    // 요청한 게임 시작에 대한 클라이언트 메소드
    public void requestGameStart(Long userId) {
      GameRequest request = GameRequest.newBuilder().setUserId(userId).build();
      asyncStub.requestGameStart(request, new StreamObserver<>() {
        @Override
        public void onNext(GameResponse value) {
          System.out.println("Game started: " + value.getGameId());
        }

        @Override
        public void onCompleted() {
          System.out.println("Server completed sending data");
        }

        @Override
        public void onError(Throwable t) {
          t.printStackTrace();
        }
      });
    }

    // 게임 정보 가져오기에 대한 클라이언트 메소드
    public void getGame(Long userId) {
      GameRequest request = GameRequest.newBuilder().setUserId(userId).build();
      asyncStub.getGame(request, new StreamObserver<>() {
        @Override
        public void onNext(GameResponse value) {
          System.out.println("Game info: " + value.getMessage());
        }

        @Override
        public void onCompleted() {
          System.out.println("Server completed sending data");
        }

        @Override
        public void onError(Throwable t) {
          t.printStackTrace();
        }
      });
    }

    // 여러 게임 스트림 가져오기에 대한 클라이언트 메소드
    public void getGames() throws InterruptedException {
      CountDownLatch finishLatch = new CountDownLatch(1);
      StreamObserver<GameRequest> requestObserver = asyncStub.getGames(new StreamObserver<>() {
        @Override
        public void onNext(GameResponse value) {
          System.out.println("Game stream userId: " + value.getUserId());
          System.out.println("Game stream message: " + value.getMessage());
        }

        @Override
        public void onCompleted() {
          System.out.println("Server completed sending data");
          finishLatch.countDown();
        }

        @Override
        public void onError(Throwable t) {
          t.printStackTrace();
          finishLatch.countDown();
        }
      });

      // 여러 게임 요청을 스트리밍
      for (int i = 0; i < 10; i++) {
        requestObserver.onNext(GameRequest.newBuilder()
                                          .setUserId(i)
                                          .build());
      }

      // 스트리밍 완료 신호
      requestObserver.onCompleted();

      // 모든 응답이 도착할 때까지 대기
      finishLatch.await(5, TimeUnit.MINUTES);
    }
  }
}