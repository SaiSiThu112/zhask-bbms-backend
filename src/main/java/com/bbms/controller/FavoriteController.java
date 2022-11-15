package com.bbms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbms.dto.WorkspaceDto;
import com.bbms.model.Workspace;
import com.bbms.service.WorkspaceService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class FavoriteController {
	@Autowired
	private WorkspaceService workspaceService;

	@GetMapping(value = "/favorite/workspace/{userId}", produces = "application/json")
	public ResponseEntity<List<WorkspaceDto>> selectFavoriteWorkspacec(@PathVariable Long userId) {
		List<WorkspaceDto> workspaces = workspaceService.getFavWorkspace(userId);
		return ResponseEntity.ok(workspaces);
	}
	@PutMapping(value = "/favorite/workspace/{workspaceId}", produces = "application/json")
	public ResponseEntity<Boolean> updateWorkspacec(@PathVariable Long workspaceId , @RequestBody Workspace workspace) {

		WorkspaceDto workspaceDto = new WorkspaceDto();
		workspaceDto.setId(workspaceId);
		workspaceDto.setDescription(workspace.getDescription());
		workspaceDto.setName(workspace.getName());
		workspaceDto.setMarked(true);
	    workspaceService.setFavWorkspace(workspaceDto);
		return ResponseEntity.ok(true);
	}
}
