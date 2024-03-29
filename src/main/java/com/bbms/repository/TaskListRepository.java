package com.bbms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bbms.dto.TaskListDto;

@Transactional
public interface TaskListRepository extends JpaRepository<TaskListDto, Long> {

	@Query(value = "SELECT tasklist.* , user_has_board.user_id FROM tasklist INNER JOIN user_has_board ON tasklist.board_id=user_has_board.board_id WHERE tasklist.board_id=?1 and user_has_board.user_id=?2 and delete_status=0", nativeQuery = true)
	public List<TaskListDto> getTaskListDtoList(Long boardId, Long userId);

	@Modifying
	@Query(value = "UPDATE tasklist SET title=? WHERE id=?", nativeQuery = true)
	public void updateTaskListTitle(String title, Long tasklistId);

	@Modifying
	@Query(value = "UPDATE tasklist SET delete_status=1 WHERE id=? ", nativeQuery = true)
	public void deleteTaskListById(@Param("id") Long taskListId);

	@Query(value = "SELECT * FROM tasklist WHERE id=? and delete_status=0 ", nativeQuery = true)
	public TaskListDto getTaskListById(Long taskListId);
}
