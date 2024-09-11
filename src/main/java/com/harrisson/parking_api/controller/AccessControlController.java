package com.harrisson.parking_api.controller;

import com.harrisson.parking_api.model.AccessControl;
import com.harrisson.parking_api.service.AccessControlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/access-controls")
public class AccessControlController {

    @Autowired
    private AccessControlService accessControlService;

    @PostMapping
    public ResponseEntity<AccessControl> registerEntry(@RequestParam Long vehicleId, @RequestParam Long establishmentId) {
        AccessControl accessControl = accessControlService.registerEntry(vehicleId, establishmentId);
        return ResponseEntity.ok(accessControl);
    }


    @PostMapping("/exit")
    public ResponseEntity<AccessControl> registerExit(@RequestParam Long vehicleId, @RequestParam Long establishmentId) {
        AccessControl accessControl = accessControlService.registerExit(vehicleId, establishmentId);
        return ResponseEntity.ok(accessControl);
    }
}