package dev.nightzen.codesharing.presentation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import dev.nightzen.codesharing.business.entity.Code;
import dev.nightzen.codesharing.business.service.CodeService;
import dev.nightzen.codesharing.presentation.dto.AddCodeRequestDto;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class CodeSharingController {

    @Autowired
    CodeService codeService;

    @GetMapping("/api/code/{uuid}")
    @ResponseBody
    public Code getCode(@PathVariable String uuid) {
        return codeService.getCode(uuid);
    }

    @GetMapping(value = "/code/{uuid}")
    public String getHtmlCode(@PathVariable String uuid, Model model) {
        Code code = codeService.getCode(uuid);
        model.addAttribute("code", code);
        return "code";
    }

    @PostMapping("/api/code/new")
    @ResponseBody
    public Map<String, String> addCode(@RequestBody @Valid AddCodeRequestDto addCodeRequestDto) {
        String uuid = codeService.addCode(addCodeRequestDto);
        return Map.of("id", uuid);
    }

    @GetMapping(value = "/code/new")
    public String getHtmlCodeForm() {
        return "code-form";
    }

    @GetMapping("/api/code/latest")
    @ResponseBody
    public Iterable<Code> getLatestCode() {
        return codeService.getLatest();
    }

    @GetMapping(value = "/code/latest")
    public String getLatestHtmlCode(Model model) {
        Iterable<Code> codes = codeService.getLatest();
        model.addAttribute("codes", codes);
        return "codes";
    }
}
