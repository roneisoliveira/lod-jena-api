# Instalação

##### 1. Instalar o JDK 17 

Obter em: https://www.oracle.com/java/technologies/downloads/

##### 2. Instalar o Spring Tool Suite 4

Obter em: https://spring.io/tools

Configurar o Java JDK no STS4

```console
Window > Preferences > Java > Installed JREs > Add..
```

##### 3. Clonar o projeto

```console
> git clone https://github.com/roneisoliveira/lod-jena-api.git
```

##### 4. Configurar o projeto

Executar os comandos no console a partir da raiz do projeto:

```console
> .\gradlew cleanEclipse eclipse
```

##### 5. Importar o projeto no STS4

```console
File > Import... > Gradle > Existing Gradle Project
```