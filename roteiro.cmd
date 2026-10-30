@echo off
REM Roteiro de conferencia pelo terminal do Windows (usa o curl.exe que ja vem no Windows 10/11).
REM Rodar com a aplicacao recem-iniciada (banco vazio). Mostra o status HTTP de cada passo.
set B=http://localhost:8080/salas
set J=-H "Content-Type: application/json"

echo Passo 1 (esperado 201):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B% %J% -d "{\"codigo\":\"LAB-01\",\"nome\":\"Lab Informatica\",\"capacidade\":30}"
echo Passo 2 (esperado 409):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B% %J% -d "{\"codigo\":\"LAB-01\",\"nome\":\"Outra Sala\",\"capacidade\":20}"
echo Passo 3 (esperado 400):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B% %J% -d "{\"codigo\":\"LAB-02\",\"capacidade\":20}"
echo Passo 4 (esperado 200):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B%/1/reservar
echo Passo 5 (esperado 409):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B%/1/reservar
echo Passo 6 (esperado 409):
curl -s -o NUL -w "%%{http_code}\n" -X DELETE %B%/1
echo Passo 7 (esperado 200):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B%/1/liberar
echo Passo 8 (esperado 409):
curl -s -o NUL -w "%%{http_code}\n" -X POST %B%/1/liberar
echo Passo 9 (esperado 204):
curl -s -o NUL -w "%%{http_code}\n" -X DELETE %B%/1
echo Passo 10 (esperado 404):
curl -s -o NUL -w "%%{http_code}\n" %B%/1
