package de.jd.server.demodata;

import de.jd.server.demodata.service.DemodataService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemodataController {
    private DemodataService demodataService;

    @RequestMapping(value = "/generate")
    @ResponseBody
    public void recipe() {
        demodataService.createRecipes();
    }

    public void setDemodataService(DemodataService demodataService) {
        this.demodataService = demodataService;
    }
}
