package dev.nightzen.codesharing.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import dev.nightzen.codesharing.business.entity.Code;
import dev.nightzen.codesharing.persistance.CodeRepository;
import dev.nightzen.codesharing.presentation.dto.AddCodeRequestDto;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static java.time.ZoneOffset.UTC;

@Component
public class CodeService {
    @Autowired
    CodeRepository codeRepository;

    public Code getCode(String uuid) {
        Optional<Code> optionalCode = codeRepository.findByUuid(uuid);

        if (optionalCode.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Code code = optionalCode.get();

        if (code.isExpired()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        if (code.getViewsRestriction() && code.getViews() > 0) {
            code.setViews(code.getViews() - 1);
            return codeRepository.save(code);
        }

        return code;
    }

    public String addCode(AddCodeRequestDto addCodeRequestDto) {
        Code code = new Code();
        code.setCode(addCodeRequestDto.getCode());
        code.setDate(LocalDateTime.now());
        UUID uuid = UUID.randomUUID();
        code.setUuid(uuid.toString());
        Long time = addCodeRequestDto.getTime();
        code.setTimeRestriction(time > 0);
        code.setTime(time <= 0 ? time : LocalDateTime.now().toEpochSecond(UTC) + time);
        code.setViewsRestriction(addCodeRequestDto.getViews() > 0);
        code.setViews(addCodeRequestDto.getViews());
        return codeRepository.save(code).getUuid();
    }

    public Iterable<Code> getLatest() {
        return codeRepository.findTop10ByTimeRestrictionFalseAndViewsRestrictionFalseOrderByIdDesc();
    }
}
