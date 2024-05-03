package com.estate.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estate.back.entity.BoardEntity;

// Estate 데이터베이스의 board 테이블 작업을 위한 리포지토리
@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>{
  
  List<BoardEntity> findByOrderByReceptionNumberDesc();
  // Contains / Containing / IsContaining => Like '%word%'
  // StartingWith => Like 'word%'
  // EndingWith => Like '%word'
  List<BoardEntity> findByTitleContainsOrderByReceptionNumberDesc(String title);
}
