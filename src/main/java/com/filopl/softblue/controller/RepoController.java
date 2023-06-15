package com.filopl.softblue.controller;

import com.filopl.softblue.exception.ErrorResponse;
import com.filopl.softblue.exception.UnauthorizedException;
import com.filopl.softblue.model.RepositoryDTO;
import com.filopl.softblue.service.RepoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

/**
 * Created by T. Filo Zegarlicki on 10.06.2023
 **/

@RestController
@Api(tags = "Repozytoria")
@RequestMapping("/repositories")
public class RepoController {

    private final RepoService repositoryService;

    @Autowired
    public RepoController(RepoService repositoryService) {
        this.repositoryService = repositoryService;
    }

    @GetMapping("/{owner}/{repositoryName:.+}")
    @ApiOperation("Pobierz dane repozytorium")
    public ResponseEntity<?> getRepositoryData(@PathVariable String owner, @PathVariable String repositoryName) {
        try {
            ResponseEntity<RepositoryDTO> repositoryDTO = repositoryService.getRepositoryData(owner, repositoryName);
            return new ResponseEntity<>(repositoryDTO, HttpStatus.OK);
        } catch (NoSuchElementException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found", exception);
        } catch (UnauthorizedException exception) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Unauthorized", exception);
        } catch (Exception exception) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", exception);
        }
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErrorResponse> handleResponseStatusException(ResponseStatusException exception) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(exception.getStatus().value());
        errorResponse.setMessage(exception.getReason());
        errorResponse.setTimestamp(LocalDateTime.now());

        return new ResponseEntity<>(errorResponse, exception.getStatus());
    }
}
