package com.bbms.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bbms.dto.WorkspaceDto;
import com.bbms.model.MessageResponse;
import com.bbms.model.Workspace;
import com.bbms.service.WorkspaceService;

@RestController
@RequestMapping("/api/v1/")
public class WorkspaceController {

	@Autowired
	private WorkspaceService workspaceService;

	@PostMapping(value = "/workspace", produces = "application/json")
	public ResponseEntity<?> createWorkspacec(@RequestBody Workspace workspace) {

		WorkspaceDto workspacedto = new WorkspaceDto();
		workspacedto.setName(workspace.getName());
		workspacedto.setDescription(workspace.getDescription());
		workspaceService.insert(workspacedto);
		return ResponseEntity.ok(new MessageResponse("Insert Successfully!"));
	}

	@GetMapping(value = "/workspace", produces = "application/json")
	public ResponseEntity<List<WorkspaceDto>> selectAllWorkspacec() {
		List<WorkspaceDto> workspaces = workspaceService.getAllWorkspace();
		return ResponseEntity.ok(workspaces);
	}

	@PutMapping(value = "/workspace/{workspaceId}", produces = "application/json")
	public ResponseEntity<Boolean> updateWorkspacec(@PathVariable Long workspaceId , @RequestBody Workspace workspace) {

		WorkspaceDto workspaceDto = new WorkspaceDto();
		workspaceDto.setId(workspaceId);
		workspaceDto.setDescription(workspace.getDescription());
		workspaceDto.setName(workspace.getName());
	    workspaceService.updateWorkspace(workspaceDto);
		return ResponseEntity.ok(true);
	}

	@DeleteMapping(value = "/workspace/{workspaceId}", produces = "application/json")
	public ResponseEntity<Boolean> deleteWorkspacec(@PathVariable Long workspaceId) {
		workspaceService.deleteWorkspace(workspaceId);
		return ResponseEntity.ok(true);
	}

}
