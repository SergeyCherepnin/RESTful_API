# RESTful_API (ТЗ компании РЕЛЭКС)
## Задание:
Реализовать RestFul_API Service с помощью Spring Boot.
Каждое новое число в файле с новой строки(разделено символом "\n").
В сервисе необходимо реализовать следующие операции (REST-запросы), возвращающие:
* максимальное число в файле;
* минимальное число в файле;
* медиана;
* среднее арифметическое значение;
* самая длинная последовательность идущих подряд чисел, которая увеличивается;
* самая длинная последовательность идущих подряд чисел, которая уменьшается.

Тип запрашиваемой операции может передаваться как параметр в json ("operation": "get_max_value"), так и определяться из URL запроса (http://../get_max_value).
Результат выдается так же в виде json

_______________________________________________________________________________________________________________________________________________

## Решение:
Путь к файлу передается в body запроса: 

{
  "file_path": "C:/test_data/10m.txt"
}

В headers можно изменить формат получаемого ответа(accept:application/json  или  accept:application/xml)

Примеры запросов и ответов:
- Максимальное число из файла():
      Request URL: 
          http://localhost:8080/api/v1/get_max_value
      
      Curl:
          curl -X 'POST' \
          'http://localhost:8080/api/v1/get_max_value' \
          -H 'accept: application/json' \
          -H 'Content-Type: application/json' \
          -d '{
               "file_path": "C:/Users/Admin/Downloads/10m.txt"
              }'
       
       Response body:
           {
             "max_value": "49999978"
           }
 _______________________________________________________________________________________________________        
- Минималное число из файла:
      Request URL: 
          http://localhost:8080/api/v1/get_min_value
      
      Curl:
          curl -X 'POST' \
          'http://localhost:8080/api/v1/get_min_value' \
          -H 'accept: application/json' \
          -H 'Content-Type: application/json' \
          -d '{
               "file_path": "C:/Users/Admin/Downloads/10m.txt"
              }'
       
       Response body:
          {
            "min_value": "-49999996"
          }
 ________________________________________________________________________________________________________
 - Медиана:
      Request URL: 
          http://localhost:8080/api/v1/get_median_value
      
      Curl:
            curl -X 'POST' \
            'http://localhost:8080/api/v1/get_median_value' \
            -H 'accept: application/json' \
            -H 'Content-Type: application/json' \
            -d '{
                 "file_path": "C:/test_data/10m.txt"
                }'
       
       Response body:
          {
            "median_value": "25216.0"
          }
_________________________________________________________________________________________________________
- Среднее арифметическое значение:
      Request URL: 
          http://localhost:8080/api/v1/get_average_value
      
      Curl:
            curl -X 'POST' \
            'http://localhost:8080/api/v1/get_average_value' \
            -H 'accept: application/json' \
            -H 'Content-Type: application/json' \
            -d '{
                 "file_path": "C:/test_data/10m.txt"
                }'
       
       Response body:
          {
            "average_value": "7364.42"
          }
_________________________________________________________________________________________________________      
- Самая длинная последовательность увеличивающихся чисел:
      Request URL: 
          http://localhost:8080/api/v1/get_longest_asc_seq
      
      Curl:
            curl -X 'POST' \
            'http://localhost:8080/api/v1/get_longest_asc_seq' \
            -H 'accept: application/json' \
            -H 'Content-Type: application/json' \
            -d '{
                 "file_path": "C:/test_data/10m.txt"
                }'
       
       Response body:
          {
            "ascending_sequence": [
              [
                -48190694,
                -47725447,
                -43038241,
                -20190291,
                -17190728,
                -6172572,
                8475960,
                25205909,
                48332507,
                48676185
              ]
            ]
          }
__________________________________________________________________________________________________________
- Самая длинная последовательность уменьшающихся чисел:
      Request URL: 
          http://localhost:8080/api/v1/get_longest_desc_seq
      
      Curl:
            curl -X 'POST' \
            'http://localhost:8080/api/v1/get_longest_desc_seq' \
            -H 'accept: application/json' \
            -H 'Content-Type: application/json' \
            -d '{
                 "file_path": "C:/test_data/10m.txt"
                }'
       
       Response body:
          {
            "descending_sequence": [
              [
                47689379,
                42381213,
                30043880,
                12102356,
                -4774057,
                -5157723,
                -11217378,
                -23005285,
                -23016933,
                -39209115,
                -49148762
              ]
            ]
          }
____________________________________________________________________________________________________________
Максимальное число: 49999978

Минимальное число: -49999996

Медиана:            25216.0

Среднее арифмет.:   7364.42

Самая длинная возрастающая последовательность: -48190694, -47725447, -43038241, -20190291, -17190728, -6172572, 8475960, 25205909, 48332507, 48676185

Самая длинная убывающая последовательность: 47689379, 42381213, 30043880, 12102356, -4774057, -5157723, -11217378, -23005285, -23016933, -39209115, -49148762

К RESTful_API подключен и настроен Swagger(http://localhost:8080/swagger-ui.html)
