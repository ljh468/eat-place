// package com.eatplace.user;
//
// import org.springframework.data.jpa.repository.EntityGraph;
// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
//
// import java.util.List;
//
// @Repository
// public interface TeamRepository extends JpaRepository<Team, Long> {
//
//   @Query("select t from Team t join fetch t.members")
//   List<Team> findAllJoinFetch();
//
//   @Query("select t from Team t")
//   @EntityGraph(attributePaths = "members", type = EntityGraph.EntityGraphType.LOAD)
//   List<Team> findAllEntityGraph();
// }
