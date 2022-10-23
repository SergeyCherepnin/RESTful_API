package com.cherepnin.restful_api.service;

import com.cherepnin.restful_api.model.FilePath;

import java.util.List;
import java.util.Set;


public interface RestfulApiService {
    Integer getMaxValue(FilePath filePath);
    Integer getMinValue(FilePath filePath);
    Double getMedianValue(FilePath filePath);
    Double getAverageValue(FilePath filePath);
    List<Set<Integer>> longestAscendingSequence(FilePath filepath);
    List<Set<Integer>> longestDescendingSequence(FilePath filePath);
}
