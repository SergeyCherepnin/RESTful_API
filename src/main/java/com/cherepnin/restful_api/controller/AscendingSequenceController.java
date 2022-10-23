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
public class AscendingSequenceController {
    private final RestfulApiService restfulApiService;

    public AscendingSequenceController(RestfulApiService restfulApiService) {
        this.restfulApiService = restfulApiService;
    }

    @Tag(name = "Ascending sequence", description = "Longest ascending sequence definition")
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = {@ExampleObject(
                            name = "File_Path",
                            value ="{\"file_path\": \"C:/test_data/10m.txt\"}")})),
            responses = {@ApiResponse(responseCode = "200",
                    description = "Success response",
                    content = @Content(
                            examples = {@ExampleObject(
                                    name = "ascending sequence",
                                    value = "{\n" +
                                            "    \"ascending_sequence\": [\n" +
                                            "        [\n" +
                                            "            -48190694,\n" +
                                            "            -47725447,\n" +
                                            "            -43038241,\n" +
                                            "            -20190291,\n" +
                                            "            -17190728,\n" +
                                            "            -6172572,\n" +
                                            "            8475960,\n" +
                                            "            25205909,\n" +
                                            "            48332507,\n" +
                                            "            48676185\n" +
                                            "        ]\n" +
                                            "    ]\n" +
                                            "}")}))})
    @PostMapping(value = "/get_longest_asc_seq",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getLongestAscendingSequence(@RequestBody FilePath filePath) {
        if (filePath == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        HashMap<String, Object> map = new HashMap<>();
        map.put("ascending_sequence", restfulApiService.longestAscendingSequence(filePath));
        return ResponseEntity.ok(map);
    }
}
