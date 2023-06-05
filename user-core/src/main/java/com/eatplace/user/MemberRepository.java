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
// public interface MemberRepository extends JpaRepository<Member, Long> {
//
//   @Query("select m from Member m join fetch m.team")
//   List<Member> findAllJoinFetch();
//
//   @Query("select m from Member m")
//   @EntityGraph(attributePaths = "team", type = EntityGraph.EntityGraphType.LOAD)
//   List<Member> findAllEntityGraph();
// }
