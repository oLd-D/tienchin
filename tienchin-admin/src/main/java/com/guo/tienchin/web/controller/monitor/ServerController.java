package com.guo.tienchin.web.controller.monitor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.guo.tienchin.common.core.domain.AjaxResult;
import com.guo.tienchin.framework.web.domain.Server;

/**
 * 服务器监控
 *
 * @author tienchin
 */
@RestController
@RequestMapping("/monitor/server")
public class ServerController {
    @PreAuthorize("hasPermission('monitor:server:list')")
    @GetMapping()
    public AjaxResult getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return AjaxResult.success(server);
    }
}
