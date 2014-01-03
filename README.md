# Braambes #

Braambes is a push-enabled, reactive web application accessing and controlling low-level I/O on Raspberry Pi and (hopefully) similar boards like BeagleBoard. It has an interactive client based on [AngularJS](http://angularjs.org) and a scalable and resilient RESTful server written in [Scala](http://www.scala-lang.org), [Akka](http://akka.io) and [Spray](http://spray.io). It is based on (and forked from) [Gabbler](http://github.com/hseeberger/gabbler) and uses the more low-level [Framboos](http://github.com/jkransen/framboos) GPIO/UART library.

## Running Braambes ##

As its dependency Framboos is not yet released, you first need to download and install it from [Github](http://github.com/jkransen/framboos).

If, like me, you are developing from Scala IDE, you may want to run `sbt eclipse` to turn your repo into an Eclipse project you can import.

This is the script that I run to install and deploy the code to my Raspberry Pi:

```
rm -rf ~/.ivy2/cache/framboos/framboos/
cd ../framboos
mvn install
cd -
sbt assembly && rsync -av target/scala-2.10/braambes.jar pi@pi:
```

Open a browser and point it to [pi:8080/](http://pi:8080/). Use the same value for username and password, e.g. "John" and "John".

## License ##

This code is open source software licensed under the [Apache 2.0 License]("http://www.apache.org/licenses/LICENSE-2.0.html").
