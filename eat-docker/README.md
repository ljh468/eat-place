1. 몽고db 최신 이미지 pull 받기
```sh
docker pull mongo
```

2. 필요할 시 태깅
```sh
docker tag mongo:latest mongo:${MY_TAG}
```

3.  replica set 의 컨테이너가 작동할 docker network 생성
```sh
docker network create mongoCluster
```

4. replica set 인증에 사용될 key file 생성
```sh
openssl rand -base64 756 > ~/.ssh/mongodb.key
chmod 400 ~/.ssh/mongodb.key
```

5. docker-compose 파일 위치로 이동
```sh
cd eat-docker
```

6. 실행
```sh
docker-compose up
## 또는 백그라운드 실행
docker-compose up -d

## docker container 로 접속
docker exec -it mongo1 /bin/bash

## mongosh 접속
mongosh -u root -p root
```

7. Replica Set 초기화 (id: 0이 primary 가 됨)
```sh
rs.initiate()

# 복제 인스턴스가 있을때 여러개 등록
# rs.initiate({
# 	 _id: "rs0",
# 	 members: [
# 	   {_id: 0, host: "mongo1:27017"},
# 	   {_id: 1, host: "mongo2:27017"}
# 	 ]
# });

# replicaSet member 추가 하려면?
# rs.add()
```

8. Replica Set 상태 확인
```sh
rs.isMaster()
rs.status()
rs.config()
```

9. MongoDB UI 툴 접속
```sh
여러개의 복제 인스턴스가 있는 경우 MongoDB UI 툴을 통해서 접속시 Connection Type을 Direct Connection으로 해서 27017로 접속해야함

스프링부트 프로젝트에서 아래와 같이 설정하고 접속하였을 때 Replica Set이 적용된 것으로 연결되기 때문에

@Transactional 어노테이션을 통해서 트랜잭션 동작을 확인할 수 있음

ReplicaSet 이 적용 안된 몽고 디비에 연결 시 내부적으로 @Transactional 어노테이션을 사용하면 에러가 발생
```
