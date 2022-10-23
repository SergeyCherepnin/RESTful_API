package com.cherepnin.restful_api.controller;

import com.cherepnin.restful_api.model.FilePath;
import com.cherepnin.restful_api.service.RestfulApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/")
public class MedianValueController {
    private final RestfulApiService restfulApiService;

    public MedianValueController(RestfulApiService restfulApiService) {
        this.restfulApiService = restfulApiService;
    }

    @Tag(name = "Median value", description = "Median value definition")
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = {@ExampleObject(
                            name = "File_Path",
                            value ="{\"file_path\": \"C:/test_data/10m.txt\"}")})),
            responses = {@ApiResponse(responseCode = "200",
                    description = "Success response",
                    content = @Content(
                            examples = {@ExampleObject(
                                    name = "median value",
                                    value = "{\"median_value\": \"25216.0\"}")}))})
    @PostMapping(value = "/get_median_value",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getMedianValue(@RequestBody FilePath filePath) {
        if (filePath == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HashMap<String, String> map = new HashMap<>();
        map.put("median_value", restfulApiService.getMedianValue(filePath).toString());
        return ResponseEntity.ok(map);
    }
}