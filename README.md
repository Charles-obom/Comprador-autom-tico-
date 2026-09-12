# Comprador Automático — MVP 1.0

Primeira versão funcional para Android.

## O que já funciona
- Tela inicial com busca
- Produtos de teste
- Comparação de 3 fornecedores
- Cálculo de custo total (produto + frete)
- Indicador de melhor custo-benefício
- Alternativa equivalente
- Botão de contato via WhatsApp (número de demonstração)

## Abrir no Android Studio
1. Extraia o ZIP.
2. Abra a pasta `CompradorAutomatico` no Android Studio.
3. Aguarde o Gradle sincronizar.
4. Conecte um celular Android com depuração USB ou use um emulador.
5. Clique em Run ▶.

## Gerar APK
No Android Studio:
Build > Build Bundle(s) / APK(s) > Build APK(s)

O APK de debug normalmente ficará em:
app/build/outputs/apk/debug/app-debug.apk

## Importante
Os fornecedores, preços, fretes e WhatsApp desta primeira versão são dados de demonstração.
A próxima versão deve substituir esses dados por banco de dados real e contatos reais.
