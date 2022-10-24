package com.cherepnin.restful_api.controllers;

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
public class DescendingSequenceController {
    private final RestfulApiService restfulApiService;

    public DescendingSequenceController(RestfulApiService restfulApiService) {
        this.restfulApiService = restfulApiService;
    }

    @Tag(name = "Descending sequence", description = "Longest descending sequence definition")
    @Operation(requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                    examples = {@ExampleObject(
                            name = "File_Path",
                            value ="{\"file_path\": \"C:/test_data/10m.txt\"}")})),
            responses = {@ApiResponse(responseCode = "200",
                    description = "Success response",
                    content = @Content(
                            examples = {@ExampleObject(
                                    name = "descending sequence",
                                    value = "{\n" +
                                            "    \"descending_sequence\": [\n" +
                                            "        [\n" +
                                            "            47689379,\n" +
                                            "            42381213,\n" +
                                            "            30043880,\n" +
                                            "            12102356,\n" +
                                            "            -4774057,\n" +
                                            "            -5157723,\n" +
                                            "            -11217378,\n" +
                                            "            -23005285,\n" +
                                            "            -23016933,\n" +
                                            "            -39209115,\n" +
                                            "            -49148762\n" +
                                            "        ]\n" +
                                            "    ]\n" +
                                            "}")}))})
    @PostMapping(value = "/get_longest_desc_seq",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?> getLongestDescendingSequence(@RequestBody FilePath filePath) {
        if (filePath == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        HashMap<String, Object> map = new HashMap<>();
        map.put("descending_sequence", restfulApiService.longestDescendingSequence(filePath));
        return ResponseEntity.ok(map);
    }
}
