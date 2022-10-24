package com.cherepnin.restful_api.service.impl;

import com.cherepnin.restful_api.model.FilePath;
import com.cherepnin.restful_api.service.RestfulApiService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class RestfulApiServiceImpl implements RestfulApiService {

    // максимальное число в списке
    @Override
    public Integer getMaxValue(FilePath filePath) {
        return readFile(filePath.getFile_path())
                .stream()
                .mapToInt(e -> e)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }
    // минимальное число в списке
    @Override
    public Integer getMinValue(FilePath filePath) {
        return readFile(filePath.getFile_path())
                .stream()
                .mapToInt(e -> e)
                .min()
                .orElseThrow(NoSuchElementException::new);
    }

    // медиана
    @Override
    public Double getMedianValue(FilePath filePath) {
        Double median;

        List<Integer> list = readFile(filePath.getFile_path())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        if (list.size() % 2 == 0) {
            median = (double) (list.get(list.size() / 2) + list.get((list.size() / 2) - 1)) / 2;
        }
        else median = (double) list.get(list.size() / 2);

        return median;
    }

    // среднее арифметическое
    @Override
    public Double getAverageValue(FilePath filePath) {
        return readFile(filePath.getFile_path())
                .stream()
                .mapToInt(e -> e)
                .average()
                .orElseThrow(NoSuchElementException::new);
    }

    // список наибольших возрастающих последовательностей
    @Override
    public List<Set<Integer>> longestAscendingSequence(FilePath filePath) {
        List<Integer> startList = readFile(filePath.getFile_path());    //исходный список чисел
        List<Set<Integer>> resultList = new ArrayList<>();     // список списков последовательностей
        Set<Integer> sequence = new LinkedHashSet<>();    // список каждой последовательности
        int maxLength = 0;

        allAscendingSequences(startList, resultList, sequence);

        return filterSequences(resultList, maxLength);
    }

    // список наибольших убывающих последовательностей
    @Override
    public List<Set<Integer>> longestDescendingSequence(FilePath filePath) {
        List<Integer> startList = readFile(filePath.getFile_path());    //исходный список чисел
        List<Set<Integer>> resultList = new ArrayList<>();     // список списков последовательностей
        Set<Integer> sequence = new LinkedHashSet<>();    // список каждой последовательности
        int maxLength = 0;

        allDescendingSequences(startList, resultList, sequence);

        return filterSequences(resultList, maxLength);
    }


    /** =============================== Вспомогательные методы ================================================== **/
    // Считываем данные из файла и преобразовываем в список чисел
    public List<Integer> readFile(String filePath) {
        List<Integer> integerList = new ArrayList<>();

        if (filePath != null) {
            try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    Integer number = Integer.parseInt(line);
                    integerList.add(number);
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return integerList;
    }

    // находим ВСЕ возрастающие последовательности чисел
    private void allAscendingSequences(List<Integer> startList, List<Set<Integer>> resultList, Set<Integer> sequence) {
        for (int i = 1; i< startList.size(); i++) {
            sequence.add(startList.get(i-1));

            if (startList.get(i) > startList.get(i-1)) {
                sequence.add(startList.get(i));
                if (i == startList.size()-1) {
                    resultList.add(new LinkedHashSet<>(sequence));
                    sequence.clear();
                }
            }
            else {
                resultList.add(new LinkedHashSet<>(sequence));
                sequence.clear();
            }
        }
    }

    // находим ВСЕ убывающие последовательности чисел
    private void allDescendingSequences(List<Integer> startList, List<Set<Integer>> resultList, Set<Integer> sequence) {
        for (int i = 1; i< startList.size(); i++) {
            sequence.add(startList.get(i-1));

            if (startList.get(i) < startList.get(i-1)) {
                sequence.add(startList.get(i));
                if (i == startList.size()-1) {
                    resultList.add(new LinkedHashSet<>(sequence));
                    sequence.clear();
                }
            }
            else {
                resultList.add(new LinkedHashSet<>(sequence));
                sequence.clear();
            }
        }
    }

    // оставляем самые длинные последовательности
    private List<Set<Integer>> filterSequences(List<Set<Integer>> resultList, int maxLength) {
        for (Set<Integer> set : resultList) {
            if (set.size() >= maxLength) {
                maxLength = set.size();
            }
        }

        int finalMaxLength = maxLength;

        return resultList.stream()
                .filter(e -> e.size() == finalMaxLength)
                .collect(Collectors.toList());
    }
 }
