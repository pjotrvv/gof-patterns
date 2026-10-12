#!/usr/bin/env sh
# Compiles every pattern and runs its Demo class.
# Usage: ./run.sh            (all patterns)
#        ./run.sh observer   (a single pattern)
set -e
cd "$(dirname "$0")"

if [ $# -gt 0 ]; then
  patterns="$*"
else
  patterns="observer templatemethod state strategy"
fi

rm -rf out
mkdir -p out
javac -Xlint:all -d out $(find src -name '*.java')

for pattern in $patterns; do
  echo
  java -cp out "$pattern.Demo"
done