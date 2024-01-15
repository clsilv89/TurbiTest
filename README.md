Antes de compilar o projeto, crie no arquivo `gradle.properties` as variáveis responsáveis por inicializar as chaves publicas e privadas da API da Marvel.
Sem essas variáveis, o app não será capaz de acessar a API corretamente.

`systemProp.PUBLIC_KEY=${YOUR_PUBLIC_API_KEY}`
`systemProp.PRIVATE_KEY=${YOUR_PRIVATE_API_KEY}`

Antes de rodar o projeto no Emulador ou dispositivo, rode o comando `./gradlew assembleDebug` para gerar as variáveis no BuildConfig.
