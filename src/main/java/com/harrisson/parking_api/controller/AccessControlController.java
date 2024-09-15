package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.enums.Type;
import com.harrisson.parking_api.model.AccessControl;
import com.harrisson.parking_api.service.AccessControlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Access Control", description = "Access Control API")
@RestController
@RequestMapping("/access-controls")
@SecurityRequirement(name = "bearer-key")
public class AccessControlController {

    @Autowired
    private AccessControlService accessControlService;


    @Operation(summary = "Register entry", description = "Register entry")
    @PostMapping
    public ResponseEntity<AccessControl> registerEntry(@RequestParam String plate, @RequestParam Type type, @RequestParam Long establishmentId) {
        AccessControl accessControl = accessControlService.registerEntry(plate, type, establishmentId);
        return ResponseEntity.ok(accessControl);
    }


    @Operation(summary = "Register exit", description = "Register exit")
    @PostMapping("/exit")
    public ResponseEntity<AccessControl> registerExit(@RequestParam String plate) {
        AccessControl accessControl = accessControlService.registerExit(plate);
        return ResponseEntity.ok(accessControl);
    }
}