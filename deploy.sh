#!/bin/sh
rm -rf ~/.ivy2/cache/framboos/framboos/
cd ../framboos
mvn install
cd -
sbt assembly && rsync -av target/scala-2.10/braambes-assembly-0.0.1-SNAPSHOT.jar pi@pi:

