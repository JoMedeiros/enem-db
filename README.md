# Enem DataBase

## Descrição

Uma aplicação para analisar os resultados do Enem 2018.

## VM Options

Para poder carregar totalmente o arquivo na memória é necessário habilitar a opção:
```$xslt
 -Xmx5G
```
Opções para Garbage Collection:
```
-XX:ConcGCThreads=4
```
ou
```
-XX:+UseG1GC
```
Opção do GC para remover Strings duplicadas:
```
-XX:+UseStringDeduplication
```
Opções Sugeridas:
```
-XX:+UseG1GC -XX:+UnlockCommercialFeatures -XX:+FlightRecorder -XX:StartFlightRecording=duration=200s,filename=semfiltros.jfr
```

### Checklist

- [ ] Mostrar todas as Respostas
    - [ ] Criar uma função para modularizar a contagem de gabarito na classe DataAnalyzer e Result
- [ ] Armazenar informações
- [ ] Criar uma classe de filtros
