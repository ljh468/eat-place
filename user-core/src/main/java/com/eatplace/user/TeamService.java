// package com.eatplace.user;
//
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// import java.util.List;
//
// @Service
// public class TeamService {
//
//   private final TeamRepository teamRepository;
//
//   @Autowired
//   public TeamService(TeamRepository teamRepository) {
//     this.teamRepository = teamRepository;
//   }
//
//   @Transactional
//   public List<Team> findAll() {
//     return teamRepository.findAll();
//   }
//
//   public List<Team> findAllJoinFetch() {
//     return teamRepository.findAllJoinFetch();
//   }
//
//   public List<Team> findAllEntityGraph() {
//     return teamRepository.findAllEntityGraph();
//   }
// }
