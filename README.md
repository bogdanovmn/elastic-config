# Usage

In your default config resources/main.properties:
```
secret.value=12345
```

In your outside config file (for example on production server or your developer machine)
```
secret.value="super secret!"
```

Run your App with param ref to outside config:
```
$ java -jar YourMegaApp.jar -Dcustom.option.name=/path/to/secret.properties
```

In your App:
```
ElasticConfig conf = new ElasticConfig("custom.option.name).prepare();
String secretValue = conf.get("secret.value"); // "super secret!"
```
