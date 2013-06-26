package eu.nyerel.appmonitor.web.results;

import eu.nyerel.appmonitor.core.service.MonitoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rastislav Papp (rastislav.papp@ibacz.eu))
 */
@Controller
public class MonitoringResultsController {

    @Autowired
    private MonitoringService monitoringService;

    @RequestMapping("/")
    public String render(Model model) {
        model.addAttribute("methodCalls", monitoringService.getMonitoredMethodCalls());
        return "view";
    }

}
